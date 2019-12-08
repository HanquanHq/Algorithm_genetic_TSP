package cn.hanquan.ai.pojo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.hanquan.ai.util.Utils;

public class DnaPool {
	private static Logger logger = Logger.getLogger(Dna.class);
	/**
	 * Dna池
	 */
	public ArrayList<Dna> dnaList;

	/**
	 * 初始化空的Dna池
	 */
	public DnaPool() {
		this.dnaList = new ArrayList<Dna>();
	}
	
	/**
	 * 指定Dna个数的Dna池
	 * @param size 初始Dna个数
	 */
	public DnaPool(int size) {
		this.dnaList = new ArrayList<Dna>();
		for(int i=0;i<size;i++) {
			Dna dna = Utils.genRandomDna();
			dnaList.add(dna);
		}
	}
	
	
}
