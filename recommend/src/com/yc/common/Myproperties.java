package com.yc.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Myproperties extends Properties{
	public static Myproperties properties=null;
	private Myproperties() throws IOException{
		InputStream iss=Myproperties.class.getClassLoader().getResourceAsStream("db.properties");
		this.load(iss);
	}
	public static Myproperties getInstance() throws IOException{
		if(properties==null){
			properties=new Myproperties();
		}
		return properties;
	}
}
