package com.darian.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.darian.data.DataSource;
import com.darian.entry.ServiceException;
import com.darian.entry.Town;

/**
 * 核心实现类
 *
 * @author Darian
 * @since 2020-03-11
 */
public class FrameWork {
	private static volatile FrameWork instance;

	private final String routeSplit = "-";

	private final String NO_SUCH_ROUTE_MSG = "NO SUCH ROUTE";
	
	private final String PARAM_ERROR_MSG = "PARAMETER ERROR";

	private FrameWork() {
		/* Do Nothing */
	}

	/**
	 * 单例获取实例
	 *
	 * @return 实例
	 */
	public static FrameWork getInstance() {
		if (instance == null) {
			synchronized (FrameWork.class) {
				if (instance == null) {
					instance = new FrameWork();
				}
			}
		}
		return instance;
	}

	/**
	 * 根据路径获取距离
	 *
	 * @param router 路径
	 * @return 距离
	 * @throws ServiceException 路径不存在返回该异常
	 */
	public int getDistance(String router) throws ServiceException {
		String[] townRouters = router.split(routeSplit);

		List<Integer> stopList = new LinkedList<Integer>();
		for (String town : townRouters) {
			if (town.length() != 1) {
				throw new ServiceException(PARAM_ERROR_MSG);
			}

			char stop = town.toCharArray()[0];
			if (stop < 'A' || stop > 'Z') {
				throw new ServiceException(PARAM_ERROR_MSG);
			}
			stopList.add(DataSource.convert2Num(stop));
		}

		int currentStopCode = stopList.get(0);
		int ret = 0;
		for (int i = 1; i < stopList.size(); i++) {
			int currentDistance = DataSource.data[currentStopCode][stopList
					.get(i)];

			if (currentDistance <= 0) {
				throw new ServiceException(NO_SUCH_ROUTE_MSG);
			}
			ret += currentDistance;
			currentStopCode = stopList.get(i);
		}

		return ret;
	}

	/**
	 * 获取起点到终点所走步数小于或等于最大步数的路径和
	 *
	 * @param startTown 起始城镇
	 * @param endTown 终点城镇
	 * @param steps 步数
	 * @param max 是否为最大步数
	 * @return 满足条件的路径和
	 * @throws ServiceException 路径不存在返回该异常
	 */
	public int getPathNum(Town startTown, Town endTown, int steps, boolean max)
			throws ServiceException {
		if (startTown == null || endTown == null || steps <= 0) {
			throw new ServiceException(PARAM_ERROR_MSG);
		}
		// 使用BFS来解
		Queue<Town> queue = new LinkedList<Town>();
		queue.add(startTown);

		int level = 0;
		int ret = 0;
		while (!queue.isEmpty()) {
			level++;
			if (level > steps) {
				break;
			}

			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				Town currentTown = queue.poll();
				List<Town> nextTownList = currentTown.findNextTownList();
				for (Town nextTown : nextTownList) {
					// 找到一条路径
					if (nextTown.getNameCode() == endTown.getNameCode()) {
						if (max && level <= steps) {
							ret++;
						} else if (steps == level) {
							ret++;
						}
					}
					queue.add(nextTown);
				}
			}
		}
		
		if (ret == 0) {
			throw new ServiceException(NO_SUCH_ROUTE_MSG);
		}
		return ret;
	}

	/**
	 * 获取两个城镇最短路径
	 *
	 * @param startTown 起始城镇
	 * @param endTown 终点城镇
	 * @return 最短路径
	 * @throws ServiceException 路径不存在返回该异常
	 */
	public int getMinDistance(Town startTown, Town endTown)
			throws ServiceException {
		if (startTown == null || endTown == null) {
			throw new ServiceException(PARAM_ERROR_MSG);
		}
		
		// 同样使用BFS求解
		Queue<Town> queue = new LinkedList<Town>();
		queue.add(startTown);

		int level = 0;
		int ret = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			level++;
			if (level > DataSource.data.length) {
				break;
			}

			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				Town currentTown = queue.poll();
				List<Town> nextTownList = currentTown.findNextTownList();
				for (Town nextTown : nextTownList) {
					if (nextTown.getNameCode() == endTown.getNameCode()) {
						ret = ret > nextTown.getDistance() ? nextTown
								.getDistance() : ret;
					}
					queue.add(nextTown);
				}
			}
		}

		if (ret == Integer.MAX_VALUE) {
			throw new ServiceException(NO_SUCH_ROUTE_MSG);
		}
		return ret;
	}

	/**
	 * 获取两个城镇距离小于某个值的所有路径数
	 *
	 * @param startTown 起始城镇
	 * @param endTown 终点城镇
	 * @param maxDistance 最大距离
	 * @return 路径数
	 * @throws ServiceException 路径不存在返回该异常
	 */
	public int getLessDistancePathNum(Town startTown, Town endTown,
			int maxDistance) throws ServiceException {
		if (startTown == null || endTown == null || maxDistance <= 0) {
			throw new ServiceException(PARAM_ERROR_MSG);
		}
		
		Queue<Town> queue = new LinkedList<Town>();
		queue.add(startTown);

		int ret = 0;
		
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				Town currentTown = queue.poll();
				List<Town> nextTownList = currentTown.findNextTownList();
				for (Town nextTown : nextTownList) {
					// 距离大于最大路径，不进行判断，过滤
					if (nextTown.getDistance() >= maxDistance) {
						continue;
					}
					
					if (nextTown.getNameCode() == endTown.getNameCode()) {
						ret++;
						// System.out.println(nextTown.getRouter() + " " + nextTown.getDistance());
					}
					queue.add(nextTown);
				}
			}
		}
		
		if (ret == 0) {
			throw new ServiceException(NO_SUCH_ROUTE_MSG);
		}
		return ret;
	}

}
