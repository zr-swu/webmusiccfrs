package com.wmcfrs.util;

import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * sql 工具了类 主要是处理参数
 */
public class SqlUtil {

	public Criteria setSqlParams(Criteria criteria,Map<String,Object[]> params){
		if(params!=null && params.size()>0){
			Set<String> sets = params.keySet();
			for(String key:sets){
				Object[] obj = params.get(key);
				if(obj[1]!=null && !obj[1].toString().equals("")){
					if(obj[0].toString().equals("=")){
						Criterion criterion = Restrictions.eq(key,obj[1]);
						criteria.add(criterion);
					}else
						if(obj[0].toString().equals("like")){
							Criterion criterion = Restrictions.like(key,obj[1].toString(),MatchMode.ANYWHERE);
							criteria.add(criterion);
						}
				}
			}
		}
		return criteria;
	}
	
}
