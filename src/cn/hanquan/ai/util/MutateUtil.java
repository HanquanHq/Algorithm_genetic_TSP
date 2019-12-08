package cn.hanquan.ai.util;

import java.util.ArrayList;

import cn.hanquan.ai.Init;
import cn.hanquan.ai.pojo.Dna;

/**
 * 变异操作
 * @author luyang.gong
 *
 */
public class MutateUtil {
	/**
	 * 选择几个位置进行交换
	 * @param dna
	 * @return
	 */
	public static Dna doMutate(Dna dna) {
		Dna dna_cpy = new Dna(Init.CITYNUM);
		for (int i = 0; i < Init.CITYNUM; i++) {// 手动实现deepcopy
			dna_cpy.arr[i] = dna.arr[i];
		}

		int mutateCount = (int) (Init.CITYNUM * Init.PMRatio);// 变异位数
		for (int i = 0; i < mutateCount; i++) {
			int index1 = (int) (Init.CITYNUM * Math.random());
			int index2 = (int) (Init.CITYNUM * Math.random());
			swap(dna_cpy.arr, index1, index2);
		}
		return dna_cpy;
	}
	
	private static void swap(Integer[] arr, int n1, int n2) {
		int temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}
	
	public static void main(String[] args) {
		Dna d = new Dna(Init.CITYNUM);
		System.out.println(d);
		System.out.println(doMutate(d));
		
	}
}
