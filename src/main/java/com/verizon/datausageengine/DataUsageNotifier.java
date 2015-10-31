package com.verizon.datausageengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import model.CustomerUsageReport;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class DataUsageNotifier {

	public static void main(String[] args) {
		Cluster cluster = null;
		Session session = null;
		try {

			ArrayList distictCustid = new ArrayList();
			HashMap custIdCump = new HashMap();
			// Connect to the cluster and keyspace "DATA_USAGE"
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			session = cluster.connect("DATA_USAGE");
			/*
			 * ResultSet results = session.execute(
			 * "select system.sum(usage)as usage, customerid from test.cust where starttime >'2015-10-01 00:00:00'  and customerid='0004' ALLOW FILTERING"
			 * ); for (Row row : results) { System.out.format("" +
			 * row.getLong("usage") + row.getString("customerid")); // Clean up
			 * the connection by closing it }
			 */

			distictCustid = getalltheUniqueCustomer(session);
			custIdCump = getActualUsage(session, distictCustid);
			// insertCusmTable(session, custIdCump);
			// alertCumption(22, 0, session);
		} finally {
			session.close();
			cluster.close();

		}
	}

	private static void updateCumption(Session sess, String custIdCump, int i) {
		int newal = i + 1;
		sess.execute("update user_profile set no_of_Notification=" + newal
				+ " where customerId='" + custIdCump + "'");
	}

	private static void alertCumption(int range, int noofAlert, Session sess) {
		String custIdcump = null;
		String query = "select customerId, no_of_notification from DATA_USAGE.USER_PROFILE where perconsup = "
				+ range
				+ " and no_of_notification = "
				+ noofAlert
				+ " ALLOW FILTERING";
		System.out.println("final " + query);
		ResultSet rs1 = sess.execute(query);
		for (Row row : rs1) {
			custIdcump = row.getString("customerId");
			System.out.println("Alert this customer:::" + custIdcump + "::;");
			updateCumption(sess, custIdcump, noofAlert);
		}

	}

	private static void insertCusmTable(Session sess, HashMap custIdCump) {
		Set<String> keys = custIdCump.keySet();
		for (String key : keys) {
			System.out.println(key);
			System.out
					.println("INSERT INTO DATA_USAGE.USER_PROFILE (CustomerID , perconsup,no_of_notification)VALUES ("
							+ "'"
							+ key
							+ "'"
							+ ","
							+ custIdCump.get(key)
							+ ","
							+ 0 + ")");
			sess.execute("INSERT INTO DATA_USAGE.USER_PROFILE (CustomerID , perconsup,no_of_notification)VALUES ("
					+ "'"
					+ key
					+ "'"
					+ ","
					+ custIdCump.get(key)
					+ ","
					+ 0
					+ ")");
			System.out.println("The insert is complete");
		}

	}

	private static HashMap getActualUsage(Session sess, ArrayList mylist) {
		HashMap tmp = new HashMap();
		int tmp1 = 0;
		for (int i = 0; i < mylist.size(); i++) {
			String custid = (String) mylist.get(i);
			ResultSet rs = sess
					.execute("Select System.sum(usage) as usage,customerId from DATA_USAGE.CONSUMPTION where customerId="
							+ "'"
							+ custid.toString()
							+ "'"
							+ " and starttime < dateof(now())");
			for (Row row : rs) {
				Long usage = row.getLong("usage");
				float finalUsageGB = getUsageInGB(usage);// in GB
				int usagePercentage = Math.round((finalUsageGB / 150) * 100);
				System.out.println("usage percentage: " + usagePercentage);
				if (usagePercentage >= 95) {
					tmp1 = 95;
				} else if (usagePercentage >= 90) {
					tmp1 = 90;
				} else if (usagePercentage >= 85) {
					tmp1 = 85;
				} else if (usagePercentage >= 80) {
					tmp1 = 80;
				} else if (usagePercentage >= 75) {
					tmp1 = 75;
				} else if (usagePercentage >= 70) {
					tmp1 = 70;
				} else {
					tmp1 = usagePercentage;
				}

				tmp.put("dataConsumed", tmp1);
			}
		}
		return tmp;
	}

	private static float getUsageInGB(Long usage) {
		int size = 1024;
		int value = (int) (usage / (size * size * size));
		System.out.println("Usage:::" + usage + "+:GB is" + value);
		return value;
	}

	private static CustomerUsageReport getTotalUsage(Session session,
			String customerId) {
		final int maxUsageLimit = 150;// in GB

		CustomerUsageReport totalUsage = new CustomerUsageReport();
		ResultSet rs = session
				.execute("Select System.sum(usage) as usage from DATA_USAGE.CONSUMPTION where customerId="
						+ "'" + customerId.toString() + "';");
		for (Row row : rs) {
			Long usage = row.getLong("usage");
			float finalUsageGB = getUsageInGB(usage);// in GB
			int usagePercentage = (int) ((finalUsageGB / maxUsageLimit) * 100);
			System.out.println("usage percentage: " + usagePercentage);

			float availableData = maxUsageLimit - finalUsageGB;
			System.out.println("availableData " + availableData);
			System.out.println(maxUsageLimit);
			System.out.println(availableData / maxUsageLimit);
			float availableDataPercentage = (availableData / maxUsageLimit) * 100;
			System.out.println("availableDataPercentage "
					+ availableDataPercentage);
			totalUsage.setUsagePercentage(usagePercentage);
			totalUsage.setDataUsed((int) finalUsageGB);
			totalUsage.setDataUsedPercentage(usagePercentage);
			totalUsage.setAvailableData((int) availableData);
			totalUsage
					.setAvailableDataPercentage((int) availableDataPercentage);

			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}

		return totalUsage;
	}

	private static ArrayList getalltheUniqueCustomer(Session sess) {
		ArrayList tmp = new ArrayList();

		ResultSet rs = sess
				.execute("Select distinct customerid From DATA_USAGE.CONSUMPTION");
		for (Row row : rs) {
			System.out.println("The distict custId is ::::"
					+ row.getString("customerid") + "::::");
			tmp.add(row.getString("customerid"));
		}
		return tmp;

	}

	private static CustomerUsageReport CumptionbyTime(Session sess,
			String customerId, String starttime, String endtime,
			CustomerUsageReport customerUsageReport) {
		System.out.println("custom usage " + starttime + " " + endtime);
		long usage = 0;
		String query = "select System.sum(usage) as usage from DATA_USAGE.CONSUMPTION where customerId='"
				+ customerId.toString()
				+ "' and startTime>='"
				+ starttime
				+ "' and startTime<='" + endtime + "' ALLOW FILTERING";

		System.out.println(query);

		ResultSet rs = sess.execute(query);
		for (Row row : rs) {
			usage = row.getLong("usage");
			System.out.println("us " + usage);
		}
		float usageGb = getUsageInGB(usage);
		float usagePercentage = (usageGb / 150) * 100;
		customerUsageReport.setCustomDataUsed((int) usageGb);
		customerUsageReport.setCustomUsagePercentage((int) usagePercentage);
		return customerUsageReport;

	}

	public CustomerUsageReport getCutomerUsage(String customerId,
			String cassandraFrom, String cassandraTo) throws Exception {
		Cluster cluster = null;
		Session session = null;
		CustomerUsageReport customerUsageReport = null;
		CustomerUsageReport customerUsageReport2 = null;
		try {
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			session = cluster.connect("DATA_USAGE");
			customerUsageReport = getTotalUsage(session, customerId);
			if (null != customerUsageReport) {
				customerUsageReport2 = CumptionbyTime(session, customerId,
						cassandraFrom, cassandraTo, customerUsageReport);
			}

		} finally {
			session.close();
			cluster.close();
		}
		return customerUsageReport2;
	}
}
