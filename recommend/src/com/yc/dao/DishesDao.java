package com.yc.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yc.bean.DishesBean;
import com.yc.common.DbHelper;

public class DishesDao {
	private DbHelper db=DbHelper.getdb();
	
	//插入
	public int addBishes(DishesBean ds) throws SQLException, IOException{
		String sql="insert into `菜品表` values(?,?,?,?,?)";
		List<Object> params=new ArrayList<>();
		params.add(ds.getCp_id());
		params.add(ds.getName());
		params.add(ds.getPrice());
		params.add(ds.getLable());
		params.add(ds.getPic());
		return db.upDate(sql, params);
	}
	public List<DishesBean> findDishes(DishesBean ds,String op) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select * from `菜品表` ");
		List<Object> params=new ArrayList<Object>();
		if(op.equals("lable")){
			sb.append("where lable=? ");
			params.add(ds.getLable());
		}
		return db.slesctTmultiObject(sb.toString(), params, DishesBean.class);
	}
}
