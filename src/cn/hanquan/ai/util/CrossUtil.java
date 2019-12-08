package cn.hanquan.ai.util;

import java.util.ArrayList;

import cn.hanquan.ai.Init;
import cn.hanquan.ai.pojo.Dna;

/**
 * 交配操作
 * 
 * @author luyang.gong
 *
 */
public class CrossUtil {
	/**
	 * 进行交配
	 * 
	 * @param d1
	 * @param d2
	 * @return 含有两个Dna的ArrayList
	 */
	public static ArrayList<Dna> doCross(Dna d1, Dna d2) {
		Dna d1_cpy = new Dna(Init.CITYNUM);
		Dna d2_cpy = new Dna(Init.CITYNUM);
		for (int i = 0; i < Init.CITYNUM; i++) {// 手动实现deepcopy
			d1_cpy.arr[i] = d1.arr[i];
			d2_cpy.arr[i] = d2.arr[i];
		}
		int crossCount = (int) (Init.CITYNUM * Init.PCRatio);// 交配位数
		for (int i = 0; i < crossCount; i++) {// 直接交换
			double rand = Math.random();
			int index = (int) (Init.CITYNUM * rand);

			int temp = d1_cpy.arr[index]; // 交换
			d1_cpy.arr[index] = d2_cpy.arr[index];
			d2_cpy.arr[index] = temp;
		}

		// 处理冲突
		while (true) {
			if(getConfIndex(d1_cpy.arr) == -1) {
				break;
			}
			int confIndex = getConfIndex(d1_cpy.arr);
			for (int i = 0; i < Init.CITYNUM; i++) {
				int curNum = d2_cpy.arr[i];
				if (notHasNum(d1_cpy.arr, curNum)) {
					d1_cpy.arr[confIndex] = curNum;
					break;
				}
			}
		}

		// 处理冲突
		while (true) {
			if(getConfIndex(d2_cpy.arr) == -1) {
				break;
			}
			int confIndex = getConfIndex(d2_cpy.arr);
			for (int i = 0; i < Init.CITYNUM; i++) {
				int curNum = d1_cpy.arr[i];
				if (notHasNum(d2_cpy.arr, curNum)) {
					d2_cpy.arr[confIndex] = curNum;
					break;
				}
			}
		}

		ArrayList<Dna> twoDna = new ArrayList<Dna>();
		twoDna.add(d1_cpy);
		twoDna.add(d2_cpy);
		return twoDna;
	}

	/**
	 * 获取数组重复元素的索引，如果没有重复元素，返回-1
	 * 
	 * @param arr
	 * @return 重复元素的索引
	 */
	private static int getConfIndex(Integer[] arr) {
		Integer[] hasNum = new Integer[Init.CITYNUM + 1];
		for (int i = 0; i < Init.CITYNUM + 1; i++) {
			hasNum[i] = 0;
		}
		for (int i = 0; i < arr.length; i++) {
			if (0 == hasNum[arr[i]]) {// 当前没重复
				hasNum[arr[i]]++;
			} else {// 当前有重复
				return i;
			}
		}
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
//		System.out.println("没重复");
		return -1;// 整体没重复
	}

	/**
	 * 数组中是否含有某个数
	 * 
	 * @param arr
	 * @param num
	 * @return
	 */
	private static boolean notHasNum(Integer[] arr, int num) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == num) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 交换数组中指定两个位置数值
	 * @param arr
	 * @param n1
	 * @param n2
	 */
	private static void swap(Integer[] arr, int n1, int n2) {
		int temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}

	public static void main(String[] args) {// 测试用
		Dna d1 = new Dna(Init.CITYNUM);
		swap(d1.arr, 2, 5);
		swap(d1.arr, 3, 6);
		swap(d1.arr, 8, 7);
		swap(d1.arr, 15, 11);
		swap(d1.arr, 3, 14);
		Dna d2 = new Dna(Init.CITYNUM);
		swap(d1.arr, 1, 2);
		swap(d1.arr, 5, 8);
		swap(d1.arr, 3, 11);
		swap(d1.arr, 4, 15);
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(doCross(d1, d2));
	}
}
