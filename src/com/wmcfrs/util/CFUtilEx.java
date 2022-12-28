package com.wmcfrs.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import com.wmcfrs.model.Score;
import com.wmcfrs.model.User;

/**
 * 协同过滤算法工具类
 * 基于项目（音乐）的协同过滤推荐算法实现原理：
 * 1、根据用户评分信息构建用户-音乐评分矩阵
 * 2、根据用户-音乐评分矩阵计算音乐之间的相似度
 * 3、预测目标用户未评分音乐的评分并进行推荐
 */
public class CFUtilEx {
	
	public static final int cfCount = 8;//推荐数量
	
	/**
	 * 根据所有音乐评分记录构建用户-音乐评分矩阵
	 * @param cUser 当前目标用户
	 * @param scoreList 音乐评分集合
	 * @return
	 */
	public DataModel getDadaModel(User cUser,List<Score> scoreList){
		Boolean temp = false;//当前用户的评分记录,用来判断当前用户是否有评分，如果没有评分停止推荐
		//如果有有效的评分记录
		if(scoreList!=null && scoreList.size()>0){
			//定义map保存用户id和用户的评分聚合
			Map<Integer,List<Preference>> map = new HashMap<Integer,List<Preference>>();
			//遍历所有评分
			for(Score score:scoreList){
				int userid = score.getUser().getId();//用户id
				//判断是否是当前用户的评分
				if(userid==cUser.getId()){
					temp = true;
				}
				int itemid = score.getMusic().getId();//音乐id
				float preference = score.getPoint();//评分
				//评分集合
				List<Preference> preferenceList = null;
				//如果用户-评分集合 map集合中已存在用户
				if(map.containsKey(userid)){
					preferenceList = map.get(userid);
				}else{
					//不存在将用户放入map中
					preferenceList = new ArrayList<Preference>();
				}
				//评分集合添加数据
				preferenceList.add(new GenericPreference(userid, itemid, preference));
				//将评分集合放入map集合中
				map.put(userid, preferenceList);
			}
			//判断当前用户是否存在评分
			if(!temp){
				System.out.println("当前登录用户没有评分数据！");
				return null;
			}
			//定义用户-项目评分集合
			FastByIDMap<PreferenceArray> preferences = new FastByIDMap<PreferenceArray>();
			//得到用户集合
			Set<Integer> set = map.keySet();
			//遍历用户集合
			for(Integer i:set){
				List<Preference> preferenceList = map.get(i);
				preferences.put(i,new GenericUserPreferenceArray(preferenceList));
			}
			//定义用户-项目评分矩阵
			DataModel model = new GenericDataModel(preferences);
			return model;
		}else{
			System.out.println("数据库中没有评分数据！");
			return null;
		}
	}
	
	/**
	 * 基于项目的协同过滤推荐
	 * @param cUser 当前用户
	 * @param model 项目-用户评分矩阵
	 * @return
	 */
	public Map<Integer,Float> baseItem(User cUser,DataModel model){
		System.out.println("基于项目的协同过滤推荐算法开始");
		//推荐结果
		Map<Integer,Float> cfMap = new HashMap<Integer, Float>();
		//判断是否有数据
		if(model==null){
			System.out.println("没有评分数据！");
			System.out.println("基于项目的协同过滤推荐算法结束");
			return cfMap;
		}
		try {
			//定义项目相似度，余弦算法
			ItemSimilarity similarity = new UncenteredCosineSimilarity(model);
			System.out.println("项目相似度：");
			//获取矩阵中的所有项目
			LongPrimitiveIterator iterator = model.getItemIDs();
			//获取第一个项目
			long idTemp = iterator.next();
			//遍历其他项目
			while(iterator.hasNext()){
				long id = iterator.next();
				//计算相似度
				double sim = similarity.itemSimilarity(idTemp, id);
				System.out.println("项目:"+idTemp+"  与项目："+id+"  相似度="+sim);
			}
			//定义推荐引擎
			Recommender recommender = new GenericItemBasedRecommender(model, similarity);// 构造推荐引擎
			//推荐
			List<RecommendedItem> recommendations = recommender.recommend(cUser.getId(), cfCount);
			//输出结果
			System.out.printf("目标用户uid:%s", cUser.getId());
			System.out.println("推荐结果：");
            for (RecommendedItem ritem : recommendations) {
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
                System.out.println("");
                cfMap.put((int)ritem.getItemID(), ritem.getValue());
            }
		} catch (TasteException e) {
			e.printStackTrace();
		}
		System.out.println("基于项目的协同过滤推荐算法结束");
		return cfMap;
	}
	
}