package com.darian.data;

/**
 * 数据源类
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
	 * 城镇码转换为数组下标
	 *
	 * @param input 城镇码
	 * @return 数组下标
	 */
	public static int convert2Num(char input) {
		return input - 'A';
	}
	
	/**
	 * 数组下标转换为城镇码
	 *
	 * @param num 数组下标
	 * @return 城镇码
	 */
	public static char convert2Char(int num) {
		return (char) ((char)num + 'A');
	}
}
