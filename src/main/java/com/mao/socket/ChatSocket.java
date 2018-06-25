package com.mao.socket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.mao.domain.Message;

@ServerEndpoint("/chatSocket")
public class ChatSocket {
	private static Set<ChatSocket> sockets = new HashSet<ChatSocket>();
	private static List<String> users = new ArrayList<String>();
	private Session session;
	private String nickname;
	private Gson gson = new Gson();
	
	@OnOpen
	public void open(Session session) {
		this.session = session;
		sockets.add(this);
		
		String queryString = session.getQueryString();
		String code = queryString.split("=")[1];
		this.nickname = "";
		try {
			this.nickname = URLDecoder.decode(code, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		users.add(this.nickname);
		Message message = new Message();
		message.setUsers(users);
		message.setAlert(this.nickname + " 加入了房间");
		
		broadcast(gson.toJson(message));
	}
	
	@OnMessage
	public void sendMessage(Session session, String msg) {
		Message message = new Message();
		message.setFrom(this.nickname);
		message.setMessage(msg);
		
		broadcast(gson.toJson(message));
	}
	
	@OnClose
	public void close() {
		sockets.remove(this);
		users.remove(this.nickname);
		
		Message message = new Message();
		message.setUsers(users);
		message.setAlert(this.nickname + " 退出了房间");
		
		broadcast(gson.toJson(message));
	}
	
	@OnError
    public void onError(Throwable t) throws Throwable {
        System.out.println("OnError");
    }
		
	public void broadcast(String msg) {
		for (ChatSocket socket : sockets) {
			try {
				synchronized (socket) {
					socket.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				sockets.remove(socket);
				users.remove(socket.nickname);
				try {
					socket.session.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Message message = new Message();
				message.setUsers(users);
				message.setAlert(socket.nickname + " 退出了房间");
				broadcast(gson.toJson(message));
			}
		}
	}
	
}
