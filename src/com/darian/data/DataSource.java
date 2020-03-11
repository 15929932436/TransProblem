package com.darian.data;

/**
 * ����Դ��
 *
 * @author Darian
 * @since 2020-03-11
 */
public class DataSource {
	/**
	 * AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
	 * 
	 *   A B C D E
	 * A 0 5   5 7
	 * B   0 4
	 * C     0 8 2
	 * D     8 0 6
	 * E   3     0
	 */
	public static final int[][] data = {
		{0, 5, -1, 5, 7},
		{-1, 0, 4, -1, -1},
		{-1, -1, 0, 8, 2},
		{-1, -1, 8, 0, 6},
		{-1, 3, -1, -1, 0}};
	
	/**
	 * ������ת��Ϊ�����±�
	 *
	 * @param input ������
	 * @return �����±�
	 */
	public static int convert2Num(char input) {
		return input - 'A';
	}
	
	/**
	 * �����±�ת��Ϊ������
	 *
	 * @param num �����±�
	 * @return ������
	 */
	public static char convert2Char(int num) {
		return (char) ((char)num + 'A');
	}
}
