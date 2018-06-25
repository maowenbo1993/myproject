package com.mao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	
	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String dateFormat = df.format(new Date());
		return dateFormat;
	}	
}
