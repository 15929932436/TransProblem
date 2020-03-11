package com.darian.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.darian.data.DataSource;
import com.darian.entry.ServiceException;
import com.darian.entry.Town;

/**
 * ����ʵ����
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
	 * ������ȡʵ��
	 *
	 * @return ʵ��
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
	 * ����·����ȡ����
	 *
	 * @param router ·��
	 * @return ����
	 * @throws ServiceException ·�������ڷ��ظ��쳣
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
	 * ��ȡ��㵽�յ����߲���С�ڻ�����������·����
	 *
	 * @param startTown ��ʼ����
	 * @param endTown �յ����
	 * @param steps ����
	 * @param max �Ƿ�Ϊ�����
	 * @return ����������·����
	 * @throws ServiceException ·�������ڷ��ظ��쳣
	 */
	public int getPathNum(Town startTown, Town endTown, int steps, boolean max)
			throws ServiceException {
		if (startTown == null || endTown == null || steps <= 0) {
			throw new ServiceException(PARAM_ERROR_MSG);
		}
		// ʹ��BFS����
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
					// �ҵ�һ��·��
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
	 * ��ȡ�����������·��
	 *
	 * @param startTown ��ʼ����
	 * @param endTown �յ����
	 * @return ���·��
	 * @throws ServiceException ·�������ڷ��ظ��쳣
	 */
	public int getMinDistance(Town startTown, Town endTown)
			throws ServiceException {
		if (startTown == null || endTown == null) {
			throw new ServiceException(PARAM_ERROR_MSG);
		}
		
		// ͬ��ʹ��BFS���
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
	 * ��ȡ�����������С��ĳ��ֵ������·����
	 *
	 * @param startTown ��ʼ����
	 * @param endTown �յ����
	 * @param maxDistance ������
	 * @return ·����
	 * @throws ServiceException ·�������ڷ��ظ��쳣
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
					// ����������·�����������жϣ�����
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
