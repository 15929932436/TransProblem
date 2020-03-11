package com.darian;

import com.darian.entry.ServiceException;
import com.darian.entry.Town;
import com.darian.impl.FrameWork;

/**
 * ³ÌÐòÈë¿Ú
 *
 * @author Darian
 * @since 2020-03-11
 */
public class Main {
	public static void main(String[] args) {
		FrameWork instance = FrameWork.getInstance();
		
		try {
			System.out.println(instance.getDistance("A-B-C"));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getDistance("A-D"));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getDistance("A-D-C"));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getDistance("A-E-B-C-D"));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getDistance("A-E-D"));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getPathNum(new Town('C'), new Town('C'), 3, true));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getPathNum(new Town('A'), new Town('C'), 4, false));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getMinDistance(new Town('A'), new Town('C')));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getMinDistance(new Town('B'), new Town('B')));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
		
		try {
			System.out.println(instance.getLessDistancePathNum(new Town('C'), new Town('C'), 30));
		} catch (ServiceException se) {
			System.out.println(se.getMessage());
		}
	}
}
