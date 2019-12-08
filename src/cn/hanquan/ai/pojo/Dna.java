package cn.hanquan.ai.pojo;

import java.util.Arrays;

import org.apache.log4j.Logger;

import cn.hanquan.ai.Init;
import cn.hanquan.ai.util.Utils;

/**
 * 染色体,对应一种旅行顺序,命名为Order应该更准确
 * 
 * @author luyang.gong
 *
 */
public class Dna {
	private static Logger logger = Logger.getLogger(Dna.class);
	/**
	 * 采用整数编码 e.g. 5 9 2 4 6 1 10 7 3 8
	 */
	public Integer arr[];
	
	/**
	 * 适应值,越大越利于后代生存
	 */
	public Double adaptValue;
	
	/**
	 * 产生后代的概率
	 */
	public Double prob;

	@Override
	public String toString() {
		return "Dna [arr=" + Arrays.toString(arr) + "]";
	}

	/**
	 * 生成具有num个数的染色体,并按顺序赋初值
	 * 
	 * @param num
	 */
	public Dna(int num) {
		Integer arr[] = new Integer[num];
		for (int i = 0; i < num; i++) {
			arr[i] = i + 1;
		}
		this.arr = arr;
	}
	
}
