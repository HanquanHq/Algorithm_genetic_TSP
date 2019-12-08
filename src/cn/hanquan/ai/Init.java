package cn.hanquan.ai;

import cn.hanquan.ai.pojo.CityBoard;

/**
 * 用于初始化的全局变量
 * @author Buuug
 *
 */
public class Init {
	/**
	 * 城市总数
	 */
	public static final int CITYNUM = 22;
	
	/**
	 * 种群规模
	 */
	public static final int LIVINGSIZE = 100;
	
	/**
	 * 迭代次数
	 */
	public static final int GENERATION = 2000;
	
	/**
	 * 交配概率(1)->(1)+(2)+(3)=1
	 */
	public static final double PC = 0.3;
	
	/**
	 * 变异概率(2)->(1)+(2)+(3)=1
	 */
	public static final double PM = 0.3;
	
	/**
	 * 直接保留父代比例(3)->(1)+(2)+(3)=1
	 */
	public static final double PS = 0.4;
	
	/**
	 * 交配位比例
	 */
	public static final double PCRatio = 0.4;
	
	/**
	 * 变异位比例
	 */
	public static final double PMRatio = 0.1;
	
	/**
	 * 直接输入答案
	 */
	public static final Integer[] ANSWERDNA = new Integer[] { 1, 14, 13, 12, 7, 6, 15, 5, 11, 9, 10, 19, 20, 21, 16, 3,
			2, 17, 22, 4, 18, 8, -1 };
	
	/**
	 * 城市坐标总图
	 */
	public static CityBoard CITYBOARD;
	
	public static double MAXADAPT = 200;
}
