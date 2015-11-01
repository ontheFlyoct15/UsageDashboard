/**
 * 
 */
package com.verizon.datausageengine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.verizon.datausageengine.DataUsageNotifier;

/**
 * @author Administrator
 *
 */
public class DataUsageNotifierTest {

	public static DataUsageNotifier dataUsageNotifier = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataUsageNotifier = new DataUsageNotifier();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.verizon.datausageengine.DataUsageNotifier#getUsageInGB(Long))}
	 * .
	 */

	@Test
	public void testMBToGBConversion() throws Exception {
		System.out.println("test MB to GB conversion");
		long usage = 1024 * 1024 * 1024;// KB
		int usageInGB = (int) dataUsageNotifier.getUsageInGB(usage);
		int expected = 1;
		Assert.assertEquals(expected, usageInGB);
	}
}
