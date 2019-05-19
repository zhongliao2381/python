package com.yc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.bean.DishesBean;
import com.yc.bean.LableBean;
import com.yc.common.DbHelper;

public class LableDao {
	private DbHelper db=DbHelper.getdb();
	
	public Map<String,List<LableBean>> findDishes() throws Exception{
		List<LableBean> temp=new ArrayList<LableBean>();
		String sql="select temp from label group by temp";
		temp=db.slesctTmultiObject(sql, null, LableBean.class);
		Map<String,List<LableBean>> map=new HashMap<String,List<LableBean>>();
		String sql1="select label from label where temp=?";
		List<Object> params=new ArrayList<Object>();
		for(LableBean lable:temp){
			List<LableBean> temp2=new ArrayList<LableBean>();
			params.add(lable.getTemp());
			temp2=db.slesctTmultiObject(sql1, params, LableBean.class);
			map.put(lable.getTemp(), temp2);
			params.clear();
		}
		return map;
	}
}
