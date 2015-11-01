package model;

public class CustomerUsageReport {
	int usagePercentage;
	int dataUsed;
	int dataUsedPercentage;
	int availableData;
	int availableDataPercentage;

	int customUsagePercentage;
	int customDataUsed;

	int todaysUsage;
	int todaysPercentage;
	int last7DaysUsage;
	int last7DaysUsagePercentage;
	int last30daysUsage;
	int last30daysUsagePercentage;

	public int getTodaysPercentage() {
		return todaysPercentage;
	}

	public void setTodaysPercentage(int todaysPercentage) {
		this.todaysPercentage = todaysPercentage;
	}

	public int getLast7DaysUsagePercentage() {
		return last7DaysUsagePercentage;
	}

	public void setLast7DaysUsagePercentage(int last7DaysUsagePercentage) {
		this.last7DaysUsagePercentage = last7DaysUsagePercentage;
	}

	public int getLast30daysUsagePercentage() {
		return last30daysUsagePercentage;
	}

	public void setLast30daysUsagePercentage(int last30daysUsagePercentage) {
		this.last30daysUsagePercentage = last30daysUsagePercentage;
	}

	public int getTodaysUsage() {
		return todaysUsage;
	}

	public void setTodaysUsage(int todaysUsage) {
		this.todaysUsage = todaysUsage;
	}

	public int getLast7DaysUsage() {
		return last7DaysUsage;
	}

	public void setLast7DaysUsage(int last7DaysUsage) {
		this.last7DaysUsage = last7DaysUsage;
	}

	public int getLast30daysUsage() {
		return last30daysUsage;
	}

	public void setLast30daysUsage(int last30daysUsage) {
		this.last30daysUsage = last30daysUsage;
	}

	String fromDate;
	String toDate;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getUsagePercentage() {
		return usagePercentage;
	}

	public int getCustomUsagePercentage() {
		return customUsagePercentage;
	}

	public void setCustomUsagePercentage(int customUsagePercentage) {
		this.customUsagePercentage = customUsagePercentage;
	}

	public int getCustomDataUsed() {
		return customDataUsed;
	}

	public void setCustomDataUsed(int customDataUsed) {
		this.customDataUsed = customDataUsed;
	}

	public void setUsagePercentage(int usagePercentage) {
		this.usagePercentage = usagePercentage;
	}

	public int getDataUsed() {
		return dataUsed;
	}

	public void setDataUsed(int dataUsed) {
		this.dataUsed = dataUsed;
	}

	public int getDataUsedPercentage() {
		return dataUsedPercentage;
	}

	public void setDataUsedPercentage(int dataUsedPercentage) {
		this.dataUsedPercentage = dataUsedPercentage;
	}

	public int getAvailableData() {
		return availableData;
	}

	public void setAvailableData(int availableData) {
		this.availableData = availableData;
	}

	public int getAvailableDataPercentage() {
		return availableDataPercentage;
	}

	public void setAvailableDataPercentage(int availableDataPercentage) {
		this.availableDataPercentage = availableDataPercentage;
	}
}
