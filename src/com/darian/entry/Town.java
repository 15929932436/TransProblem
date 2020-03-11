package com.darian.entry;

import java.util.LinkedList;
import java.util.List;

import com.darian.data.DataSource;

/**
 * Town��
 *
 * @author Darian
 * @since 2020-03-11
 */
public class Town {
	private char nameCode;
	
	private String router;
	
	private int distance;
	
	/**
	 * ���췽��
	 *
	 * @param nameCode �������
	 */
	public Town(char nameCode) {
		this.nameCode = nameCode;
		this.router = nameCode + "";
		this.distance = 0;
	}
	
	/**
	 * ���췽��
	 *
	 * @param nameCode �������
	 * @param router ��ʼ���򵽴˳����·��
	 * @param distance ��ʼ���򵽴˳���ľ���
	 */
	public Town(char nameCode, String router, int distance) {
		this.nameCode = nameCode;
		this.router = router;
		this.distance = distance;
	}
	
	/**
	 * ��ȡ��ǰ������һ���ɵ���ĳ����б�
	 *
	 * @return �����б�
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
	 * ��ȡ�������
	 *
	 * @return �������
	 */
	public char getNameCode() {
		return nameCode;
	}

	/**
	 * ��ȡ·����Ϣ
	 *
	 * @return ·��
	 */
	public String getRouter() {
		return router;
	}

	/**
	 * ��ȡ������ʼ����ľ���
	 *
	 * @return ����
	 */
	public int getDistance() {
		return distance;
	}
}
