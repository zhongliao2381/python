package com.yc.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class ConnPool implements DataSource {

	private static LinkedList<Connection> listConnection=new LinkedList<Connection>();
	
	static {
		try {
			int poolSize=Integer.parseInt(Myproperties.getInstance().getProperty("maxActive"));
			for(int i=0;i<poolSize;i++){
				DataSource ds=BasicDataSourceFactory.createDataSource(Myproperties.getInstance());
				Connection conn =ds.getConnection();
				listConnection.add(conn);
				System.out.println("初始化数据库连接池,第"+i+"连接已创建");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		if(listConnection.size()>0){
			final Connection conn=listConnection.removeFirst();
			return (Connection)Proxy.newProxyInstance(ConnPool.class.getClassLoader(), new Class[]{Connection.class}, new InvocationHandler() {
				
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					if(!"close".equals(method.getName())){
						return method.invoke(conn, args);
					}else{
						listConnection.add(conn);
						System.out.println("连接回到连接池中,还剩"+listConnection.size());
						return null;
					}
				}
			});
		}
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
