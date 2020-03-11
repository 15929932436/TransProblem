package test;

import junit.framework.Assert;

import org.junit.Test;

import com.darian.entry.ServiceException;
import com.darian.entry.Town;
import com.darian.impl.FrameWork;

@SuppressWarnings({ "deprecation" })
public class TrainTest {

	@Test
	public void testGetDistance1() throws ServiceException {
		int result = FrameWork.getInstance().getDistance("A-B-C");
		Assert.assertEquals(9, result);
	}
	
	@Test
	public void testGetDistance2() throws ServiceException {
		int result = FrameWork.getInstance().getDistance("A-D");
		Assert.assertEquals(5, result);
	}

	@Test
	public void testGetDistance3() throws ServiceException {
		int result = FrameWork.getInstance().getDistance("A-D-C");
		Assert.assertEquals(13, result);
	}
	
	@Test
	public void testGetDistance4() throws ServiceException {
		int result = FrameWork.getInstance().getDistance("A-E-B-C-D");
		Assert.assertEquals(22, result);
	}
	
	@Test(expected = ServiceException.class)
	public void testGetDistance5() throws ServiceException {
		FrameWork.getInstance().getDistance("A-E-D");
	}
	
	@Test
	public void testGetPathNum1() throws ServiceException {
		int result = FrameWork.getInstance().getPathNum(new Town('C'), new Town('C'), 3, true);
		Assert.assertEquals(2, result);
	}
	
	@Test
	public void testGetPathNum2() throws ServiceException {
		int result = FrameWork.getInstance().getPathNum(new Town('A'), new Town('C'), 4, false);
		Assert.assertEquals(3, result);
	}
	
	@Test
	public void testGetMinDistance1() throws ServiceException {
		int result = FrameWork.getInstance().getMinDistance(new Town('A'), new Town('C'));
		Assert.assertEquals(9, result);
	}
	
	@Test
	public void testGetMinDistance2() throws ServiceException {
		int result = FrameWork.getInstance().getMinDistance(new Town('B'), new Town('B'));
		Assert.assertEquals(9, result);
	}
	
	@Test
	public void testLessDistancePathNum1() throws ServiceException {
		int result = FrameWork.getInstance().getLessDistancePathNum(new Town('C'), new Town('C'), 30);
		Assert.assertEquals(7, result);
	}
}
