package cn.hanquan.ai;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.hanquan.ai.pojo.CityBoard;
import cn.hanquan.ai.pojo.Dna;
import cn.hanquan.ai.pojo.DnaPool;
import cn.hanquan.ai.pojo.Node;
import cn.hanquan.ai.util.CrossUtil;
import cn.hanquan.ai.util.MutateUtil;
import cn.hanquan.ai.util.Utils;

/**
 * 遗传算法求解旅行商问题
 * 
（1）给定群体规模 N，交配概率 pc 和变异概率 pm，t＝0；
（2）随机生成 N 个染色体作为初始群体；
（3）对于群体中的每一个染色体 xi 分别计算其适应值 F(xi)；
（4）如果算法满足停止准则，则转（10）；
（5）对群体中的每一个染色体 xi 计算概率；
（6）依据计算得到的概率值，从群体中随机地选取 N 个染色体，得到种群；
（7）依据交配概率 pc 从种群中选择染色体进行交配，其子代进入新的群体，种群中未进行交配的染色体，直接复制到新群体中；
（8）依据变异概率 pm 从新群体中选择染色体进行变异，用变异后的染色体代替新群体中的原染色体；
（9）用新群体代替旧群体，t=t+1转（3）；
（10）进化过程中适应值最大的染色体，经解码后作为最优解输出；
（11）结束. 

 * 
 * @author luyang.gong
 *
 */
public class Main {
	private static Logger logger = Logger.getLogger(Node.class);

	public static void main(String[] args) {
		int saveCount = (int) (Init.LIVINGSIZE * Init.PS);
		int crossCount = (int) (Init.LIVINGSIZE * Init.PC);
		int mutateCount = (int) (Init.LIVINGSIZE - saveCount - crossCount);
		
		System.out.println("Please input NODE_COORD_SECTION:");
		Init.CITYBOARD = Utils.readCityBoard();

		DnaPool dnaPool = new DnaPool(Init.LIVINGSIZE);

//		Dna dna = Utils.genRandomDna();
//		Dna dna = Utils.genSolutionDna(); //已知最优解:74.10873595815309
//		Dna dnaAns = Utils.genSolutionDna(); //22已知最优解:75.66514947135613
//		double answer = Utils.calTotalDistance(dnaAns);
//		logger.info("answer=" + answer);
//return;
//		
		double minDis = Double.POSITIVE_INFINITY;
		Dna bestDna = dnaPool.dnaList.get(0);
		
		for (int i = 0; i < Init.GENERATION; i++) { // 共进行GENERATION次迭代
			logger.info("第 " + i + "次迭代");
			DnaPool newPool = new DnaPool();
			Utils.updateAdaptValueForAll(dnaPool);
			Utils.updateProbForAll(dnaPool);
			
			//计算当前Dna池中数据
			for (int j = 0; j < Init.CITYNUM; j++) {// 当前旅行路径
				Dna curDna = dnaPool.dnaList.get(j);
				logger.info("curDna=" + curDna);
				double dis = Utils.calTotalDistance(curDna);
				
				if (dis < minDis) {
					minDis = dis;
					bestDna = curDna;
					logger.info("发生替换，替换后minDis=" + minDis);
					logger.info("发生替换，替换后bestDna=" + bestDna);
				}
			}
			
			// 直接进入下一代
			for (int j = 0; j < saveCount; j++) {
				Dna randDna = Utils.getRandCycleDna(dnaPool);
				newPool.dnaList.add(randDna);
			}
			// 交配产生的后代
			for (int j = 0; j < crossCount; j++) {
				Dna dna1 = Utils.getRandCycleDna(dnaPool);
				Dna dna2 = Utils.getRandCycleDna(dnaPool);
				ArrayList<Dna> twoDna = CrossUtil.doCross(dna1, dna2);
				newPool.dnaList.add(twoDna.get(0));
				newPool.dnaList.add(twoDna.get(1));
			}
			// 变异产生的后代
			for (int j = 0; j < mutateCount; j++) {
				Dna dna = Utils.getRandCycleDna(dnaPool);
				Dna newDna = MutateUtil.doMutate(dna);
				newPool.dnaList.add(newDna);
			}
			
			dnaPool = newPool;
			
			logger.info("当前迭代minDis=" + minDis);
			logger.info("当前迭代bestDna=" + bestDna);
		}

		logger.info("最终minDis=" + minDis);
		logger.info("最终bestDna=" + bestDna);
	}
}
