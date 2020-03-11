package com.darian.entry;

import java.util.LinkedList;
import java.util.List;

import com.darian.data.DataSource;

/**
 * Town类
 *
 * @author Darian
 * @since 2020-03-11
 */
public class Town {
	private char nameCode;
	
	private String router;
	
	private int distance;
	
	/**
	 * 构造方法
	 *
	 * @param nameCode 城镇代码
	 */
	public Town(char nameCode) {
		this.nameCode = nameCode;
		this.router = nameCode + "";
		this.distance = 0;
	}
	
	/**
	 * 构造方法
	 *
	 * @param nameCode 城镇代码
	 * @param router 起始城镇到此城镇的路径
	 * @param distance 起始城镇到此城镇的距离
	 */
	public Town(char nameCode, String router, int distance) {
		this.nameCode = nameCode;
		this.router = router;
		this.distance = distance;
	}
	
	/**
	 * 获取当前城镇下一个可到达的城镇列表
	 *
	 * @return 城镇列表
	 */
	public List<Town> findNextTownList() {
		List<Town> nextTownList = new LinkedList<Town>();
		
		int start = DataSource.convert2Num(nameCode);
		for (int next = 0; next < DataSource.data.length; next++) {
			if (DataSource.data[start][next] > 0) {
				nextTownList.add(new Town(DataSource.convert2Char(next),
						router + "-" + DataSource.convert2Char(next), 
						distance + DataSource.data[start][next]));
			}
		}
		
		return nextTownList;
	}

	/**
	 * 获取城镇代码
	 *
	 * @return 城镇代码
	 */
	public char getNameCode() {
		return nameCode;
	}

	/**
	 * 获取路由信息
	 *
	 * @return 路由
	 */
	public String getRouter() {
		return router;
	}

	/**
	 * 获取距离起始城镇的距离
	 *
	 * @return 距离
	 */
	public int getDistance() {
		return distance;
	}
}
