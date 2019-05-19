package com.yc.util;

import java.util.Calendar;
import java.util.Random;

public class IDRandom {
	//TODO
	public static String getID(String op){
		String id=null;
		Calendar cd=Calendar.getInstance();
		Random rd=new Random();
		String  stime="D"+cd.getTimeInMillis()+(rd.nextInt(89)+10);
		stime=stime.substring(4, stime.length());
		if (op.equals("d")){
			id="D"+stime;
		}else if (op.equals("c")){
			id="C"+stime;
		}else if (op.equals("t")){
			id="T"+stime;
		}
		return id;
	}
}
