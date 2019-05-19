package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.DishesBean;
import com.yc.bean.OrderBean;
import com.yc.common.DbHelper;

public class OrderDao {
	private DbHelper db=DbHelper.getdb();
	
	//插入
	public int addOrder(OrderBean ob) throws SQLException, IOException{
		String sql="insert into `订单表` values(?,?,?,?,?,?,?)";
		List<Object> params=new ArrayList<>();
		params.add(ob.getDd_id());
		params.add(ob.getCp_id());
		params.add(ob.getName());
		params.add(ob.getTel());
		params.add(ob.getTable_number());
		params.add(ob.getTotalPrice());
		params.add(ob.getOrder_time());
		return db.upDate(sql, params);
	}
	public List<OrderBean> findDishes(OrderBean ob,String op) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select * from `订单表` ");
		List<Object> params=new ArrayList<Object>();
		if(op.equals("lable")){
			sb.append("where name=? ");
			params.add(ob.getName());
		}
		return db.slesctTmultiObject(sb.toString(), params, OrderBean.class);
	}
}
