package cn.hanquan.ai.pojo;

import org.apache.log4j.Logger;

public class Node {
	private static Logger logger = Logger.getLogger(Node.class);
	
	public int num;
	public double posX;
	public double posY;

	@Override
	public String toString() {
		return "Node [num=" + num + ", posX=" + posX + ", posY=" + posY + "]";
	}

}
