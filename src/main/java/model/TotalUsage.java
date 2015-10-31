package model;

public class TotalUsage {
	int usagePercentage;
	int dataUsed;
	int dataUsagePercentage;
	int availableData;
	int availableDataPercentage;

	public int getUsagePercentage() {
		return usagePercentage;
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

	public int getDataUsagePercentage() {
		return dataUsagePercentage;
	}

	public void setDataUsagePercentage(int dataUsagePercentage) {
		this.dataUsagePercentage = dataUsagePercentage;
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
