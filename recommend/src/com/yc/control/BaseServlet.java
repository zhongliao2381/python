package com.yc.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 输出整数
	 * 
	 * @param response
	 * @param i
	 * @throws IOException
	 */
	public void printtoString(HttpServletResponse response, Integer i) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(i);
		out.flush();
		out.close();
	}

	/**
	 * 输出字符串
	 * 
	 * @param response
	 * @param i
	 * @throws IOException
	 */
	public void printtoString(HttpServletResponse response, String str) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
		out.close();
	}

	/**
	 * 输出对象
	 * 
	 * @param response
	 * @param i
	 * @throws IOException
	 */
	public void printtoString(HttpServletResponse response, Object obj) throws IOException {
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		System.out.println(gson.toJson(obj));
		out.print(gson.toJson(obj));
		out.flush();
		out.close();
	}

	/**
	 * 封装页面上表单元素的值设置对应的对象中 表单元素 name名称必须和javaBean对象中的字符段一样
	 * 
	 * @param request
	 * @param c
	 * @return
	 */
	public <T> T fromRquesttoObject(HttpServletRequest request, Class c) {
		// 获取表单上所有Name名
		Enumeration<String> names = request.getParameterNames();
		try {
			T t = (T) c.newInstance();// 创建一个对象
			Method[] methods = c.getMethods();
			// 循环表单上所有的name
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = request.getParameter(name);
				
				for (Method m : methods) {
					if (("set" + name).equalsIgnoreCase(m.getName())) {
						System.out.println(value+"---"+m.getName());
						m.invoke(t, value);//激活方法设置值   
					}
				}
			}
			return t;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}