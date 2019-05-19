package com.yc.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DbHelper{
	private PreparedStatement pstmt = null;
	private ResultSet rst = null;
	private static ConnPool connPool=new ConnPool();
	private Connection conn;
	private static DbHelper db=null;
	private DbHelper(){
		
	}
	public static DbHelper getdb(){
		if(db==null){
			db=new DbHelper();
		}
		return db;
	}
	public Connection getConn(){
		try {
			conn= connPool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rst) {
		if (null != rst) {
			try {
				rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != pstmt) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public <T> List<T> slesctTmultiObject(String sql, List<Object> params,Class c)
			throws Exception{
		List<T> list = new ArrayList<T>();
		conn = this.getConn();
		pstmt = conn.prepareStatement(sql);
		this.setParams(pstmt, params);
		rst = pstmt.executeQuery();
		List<String> columnNames = this.getList(rst);
		Method [] methods=c.getMethods();
		String methodName=null;
		Blob blob;
		Object obj = null;
		String typeName = null;
		byte[] bt = null;
		InputStream is = null;
		T t;
		while (rst.next()) {
			t =(T) c.newInstance();
			for (String cn : columnNames) {
				obj = rst.getObject(cn);
				if(obj==null){
					continue;
				}
				for(Method m:methods){
					methodName=m.getName();
					if(("set"+cn).equalsIgnoreCase(methodName)){
						typeName=obj.getClass().getName();
						System.out.println(typeName+"=="+obj);
						if("java.lang.Integer".equals(typeName)){
							m.invoke(t, rst.getInt(cn));
						}
						if("java.math.BigDecimal".equals(typeName)){
							m.invoke(t, rst.getInt(cn));
						}
						if("java.lang.String".equals(typeName)){
							m.invoke(t, rst.getString(cn));
						}
						if("java.lang.Double".equals(typeName)){
							m.invoke(t, rst.getDouble(cn));
						}
						if("[B".equals(typeName)){
							blob = (com.mysql.jdbc.Blob) obj;
							bt = new byte[(int) blob.length()];
							is = blob.getBinaryStream();
							is.read(bt);
							m.invoke(t, bt);
						}
						if("java.sql.Timestamp".equals(typeName)){
							m.invoke(t, rst.getTimestamp(cn).toString());
						}
					}
					
				}
				
			
			}
			list.add(t);
		}
		this.closeAll(conn, pstmt, rst);
		return list;
	}

	public <T> T selectOne(String sql, List<Object> params,Class c) throws Exception {
		conn = this.getConn();
		pstmt = conn.prepareStatement(sql);
		this.setParams(pstmt, params);
		rst = pstmt.executeQuery();
		List<String> columnNames = this.getList(rst);
		Method [] methods=c.getMethods();
		String methodName=null;
		Blob blob;
		Object obj = null;
		String typeName = null;
		byte[] bt = null;
		InputStream is = null;
		T t = null;
		if (rst.next()) {
			t =(T) c.newInstance();
			for (String cn : columnNames) {
				obj = rst.getObject(cn);
				if(obj==null){
					continue;
				}
				for(Method m:methods){
					methodName=m.getName();
					if(("set"+cn).equalsIgnoreCase(methodName)){
						typeName=obj.getClass().getName();
						System.out.println(typeName+"**"+methodName);
						if("java.lang.Integer".equals(typeName)){
							m.invoke(t, rst.getInt(cn));
						}
						if("java.math.BigDecimal".equals(typeName)){
							m.invoke(t, rst.getObject(cn).toString());
						}
						if("java.lang.String".equals(typeName)){
							m.invoke(t, rst.getString(cn));
						}
						if("[B".equals(typeName)){
							blob = (com.mysql.jdbc.Blob) obj;
							bt = new byte[(int) blob.length()];
							is = blob.getBinaryStream();
							is.read(bt);
							m.invoke(t, bt);
						}
						if("oracle.sql.CLOB".equals(typeName)){
							String str=ClobToString(rst.getClob(cn));
							m.invoke(t, str);
						}
					}
					
				}
				
			
			}
		}
		this.closeAll(conn, pstmt, rst);
		return t;
	}

	public void setParams(PreparedStatement pstmt, List<Object> params) throws SQLException, FileNotFoundException {
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				if (params.get(i) instanceof File) {
					File file = (File) params.get(i);
					InputStream in = new FileInputStream(file);
					pstmt.setBinaryStream(i + 1, in, (int) file.length());
				} else {
					pstmt.setObject(i + 1, params.get(i));
				}
			}
		}
	}

	public List<String> getList(ResultSet rst) throws SQLException {
		List<String> list = new ArrayList<String>();
		int length = rst.getMetaData().getColumnCount();
		for (int i = 1; i <= length; i++) {
			String str = rst.getMetaData().getColumnName(i);
			list.add(str);
		}
		return list;
	}

	public int upDate(String sql, List<Object> params) throws SQLException, IOException {
		conn = this.getConn();
		pstmt = conn.prepareStatement(sql);
		this.setParams(pstmt, params);
		int i = pstmt.executeUpdate();
		this.closeAll(conn, pstmt, rst);
		return i;
	}
	public int upDates(List<String> sqls, List<List<Object>> params) throws SQLException {
		try{
		conn = this.getConn();
		conn.setAutoCommit(false);
		for(int i=0;i<sqls.size();i++){
			pstmt = conn.prepareStatement(sqls.get(i));
			if(params!=null)
			this.setParams(pstmt, params.get(i));
			pstmt.executeUpdate();
		}
		conn.commit();
		}catch(Exception e){
			try {
				e.printStackTrace();
				conn.rollback();
				return 0;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		conn.setAutoCommit(true);
		this.closeAll(conn, pstmt, rst);
		return 1;
	}

	public double getCount(String sql, List<Object> params) throws SQLException, IOException {
		conn = this.getConn();
		double i = 0;
		pstmt = conn.prepareStatement(sql);
		this.setParams(pstmt, params);
		rst = pstmt.executeQuery();
		if (rst.next()) {
			i = rst.getDouble(1);
		}
		this.closeAll(conn, pstmt, rst);
		return i;
	}
	private String ClobToString(Clob c) throws SQLException, IOException{
		String reString="";
		Reader is=c.getCharacterStream();
		BufferedReader br=new BufferedReader(is);
		String s=br.readLine();
		StringBuffer sb=new StringBuffer();
		while(s!=null){
			sb.append(s);
			s=br.readLine();
		}
		reString=sb.toString();
		return reString;
	}
}
