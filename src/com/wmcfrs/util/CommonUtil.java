package com.wmcfrs.util;

import java.util.Random;

/**
 * 常用的工具类
 */
public class CommonUtil {

	/**
	 * 随机产生一个六位随机数
	 * @return
	 */
	public static int getRandom(){
		return (int)((Math.random()*9+1)*100000);
	}
	
	/**
	 * 程序运行暂停一会
	 */
	public static void getRandomSleep(){
		//休息一会
		Random random = new Random();
		int sleep = random.nextInt(15)*1000+10000;
		System.out.println("**休息一会开始（"+(sleep/1000)+"秒）**");
		try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println("**休息一会结束（"+(sleep/1000)+"秒）**");
	}
	
}
