package model;

public class CustomerUsageReport {
	int usagePercentage;
	int dataUsed;
	int dataUsedPercentage;
	int availableData;
	int availableDataPercentage;

	int customUsagePercentage;
	int customDataUsed;

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
