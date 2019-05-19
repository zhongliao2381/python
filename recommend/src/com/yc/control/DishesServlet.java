package com.yc.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.DishesBean;
import com.yc.dao.DishesDao;
import com.yc.dao.LableDao;

/**
 * Servlet implementation class DishesServlet
 */
@WebServlet("/dishes.action")
public class DishesServlet extends BaseServlet {

	/**
	 * 
	 */
	private DishesDao dDao=new DishesDao();
	private LableDao LDao=new LableDao();
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		try {
			Method methon=this.getClass().getDeclaredMethod(op, HttpServletRequest.class,HttpServletResponse.class);
			try {
				methon.invoke(this, request,response);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void findLabel(HttpServletRequest request, HttpServletResponse response){
		try {
			printtoString(response,LDao.findDishes() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void findDishes(HttpServletRequest request, HttpServletResponse response){
		String lable=request.getParameter("type");
		DishesBean dish=new DishesBean();
		dish.setLable(lable);
		try {
			List<DishesBean> list=dDao.findDishes(dish, "lable");
			printtoString(response, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
