package cn.hanquan.ai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.hanquan.ai.Init;
import cn.hanquan.ai.pojo.CityBoard;
import cn.hanquan.ai.pojo.Dna;
import cn.hanquan.ai.pojo.DnaPool;
import cn.hanquan.ai.pojo.Node;

/**
 * 用于计算的工具类
 * 
 * @author luyang.gong
 *
 */
public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);

	/**
	 * 读取所有城市坐标
	 * 
	 * @return cityBoard
	 */
	public static CityBoard readCityBoard() {
		CityBoard cityBoard = new CityBoard();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < Init.CITYNUM; i++) {
			String str = "";
			try {
				str = br.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] arr = str.split(" ");
			int num = Integer.parseInt(arr[0]);

			Node node = new Node();
			node.num = num;
			node.posX = Double.parseDouble(arr[1]);
			node.posY = Double.parseDouble(arr[2]);

			System.out.println(node);
			cityBoard.allCityMap.put(num, node);
		}
		return cityBoard;
	}

	/**
	 * 随机生成Dna
	 * 
	 * @return
	 */
	public static Dna genRandomDna() {
		Dna dna = new Dna(Init.CITYNUM);
		ArrayUtils.shuffle(dna.arr);
		return dna;
	}

	/**
	 * 直接输入答案
	 * 
	 * @return
	 */
	public static Dna genSolutionDna() {
		Dna dna = new Dna(Init.CITYNUM);
		dna.arr = Init.ANSWERDNA;
		return dna;
	}

	/**
	 * 计算旅行总距离
	 * 
	 * @param dna
	 * @return
	 */
	public static double calTotalDistance(Dna dna) {
		double totalDistance = 0;
		for (int i = 0; i < Init.CITYNUM - 1; i++) {
			Node n1 = Init.CITYBOARD.allCityMap.get(dna.arr[i]);
			Node n2 = Init.CITYBOARD.allCityMap.get(dna.arr[i + 1]);
			double dis = Utils.calDistance(n1, n2);
			totalDistance += dis;
		}
		Node firstNode = Init.CITYBOARD.allCityMap.get(dna.arr[0]);
		Node lastNode = Init.CITYBOARD.allCityMap.get(dna.arr[Init.CITYNUM-1]);
//		logger.info("firstNode="+firstNode);
//		logger.info("lastNode="+lastNode);
		double goBackDis = Utils.calDistance(firstNode, lastNode);
		totalDistance += goBackDis;
		
		logger.debug(totalDistance);
		return totalDistance;
	}
	
	/**
	 * 根据旅行总距离，计算适应值。适应值越大，越利于繁衍。
	 * @return
	 */
	public static double calAdaptValue(double totalDistance) {
//		double adaptVal = 1 / (Init.MAXADAPT - totalDistance);
//		if (adaptVal > Init.MAXADAPT) {
//			Init.MAXADAPT = adaptVal;
//		}
		double adaptVal = Init.MAXADAPT - totalDistance;
		return adaptVal;
	}
	
	/**
	 * 计算并更新Dna池中所有Dna的适应值
	 */
	public static void updateAdaptValueForAll(DnaPool dnaPool) {
		ArrayList<Dna> dnaList = dnaPool.dnaList;
		for(int i=0;i<dnaList.size();i++) {
			Dna dna = dnaList.get(i);
			double totalDistance = calTotalDistance(dna);
			dna.adaptValue=calAdaptValue(totalDistance);
		}
	}
	
	private static double calProb(double curAdaptValue,double totalAdaptValue) {
		return curAdaptValue/totalAdaptValue;
	}
	
	/**
	 * 计算并更新Dna池中所有Dna的繁衍概率
	 * @param dnaPool
	 */
	public static void updateProbForAll(DnaPool dnaPool) {
		ArrayList<Dna> dnaList = dnaPool.dnaList;
		Double totalAdaptValue = 0.0;
		for (int i = 0; i < dnaList.size(); i++) {// 计算适应值之和
			Dna dna = dnaList.get(i);
			totalAdaptValue += dna.adaptValue;
		}
		for (int i = 0; i < dnaList.size(); i++) {// 计算个体适应值
			Dna dna = dnaList.get(i);
			dna.prob = calProb(dna.adaptValue, totalAdaptValue);
		}
	}

	/**
	 * 计算两城市之间的距离
	 * 
	 * @param n1 城市1
	 * @param n2 城市2
	 * @return
	 */
	public static double calDistance(Node n1, Node n2) {
		double square = (n1.posX - n2.posX) * (n1.posX - n2.posX) + (n1.posY - n2.posY) * (n1.posY - n2.posY);
		double distance = Math.pow(square, 0.5);
		logger.debug("(" + n1.posX + "," + n1.posY + ")" + "(" + n2.posX + "," + n2.posY + ")" + "distance=" + distance);
		return distance;
	}
	
	/**
	 * 轮盘赌算法
	 * @param dnaPool
	 * @return
	 */
	public static Dna getRandCycleDna(DnaPool dnaPool) {
		double rand = Math.random();
//		logger.info("rand = "+rand);
		ArrayList<Dna> dnaList = dnaPool.dnaList;

		double curBegin = 0;
		double curEnd = 0;

//		for (int i = 0; i < dnaList.size(); i++) {
//			Dna curDna = dnaList.get(i);
//			logger.info("curDna.prob = "+curDna.prob);
//		}
		for (int i = 0; i < dnaList.size(); i++) {
			Dna curDna = dnaList.get(i);
			curEnd += curDna.prob;

			logger.debug("[" + curBegin + ", " + curEnd + "]");
			if (rand >= curBegin && rand <= curEnd) {
				return curDna;
			} else {
				curBegin += curDna.prob;
			}
		}
		logger.error("轮盘赌异常");
		return null;
	}

}
