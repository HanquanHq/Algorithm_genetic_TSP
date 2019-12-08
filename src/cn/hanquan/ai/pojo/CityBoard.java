package cn.hanquan.ai.pojo;

import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * 城市坐标
 */
public class CityBoard {
	private static Logger logger = Logger.getLogger(CityBoard.class);
	/**
	 * 需要走过的城市坐标: 0,不需要经过 1,需要经过
	 */
	public int[][] arr;

	public HashMap<Integer, Node> allCityMap;

	public CityBoard() {
		this.allCityMap = new HashMap();
	}

	@Override
	public String toString() {
		return "CityBoard [allCityMap=" + allCityMap + "]";
	}
	
}
