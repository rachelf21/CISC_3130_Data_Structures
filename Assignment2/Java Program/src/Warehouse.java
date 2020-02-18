import java.util.Arrays;

public class Warehouse {

	String warehouseCity;
	int[] items = { 0, 0, 0 };

	public Warehouse() {
	}

	public Warehouse(String loc) {
		warehouseCity = loc;
	}

	int getItem(int x) {
		return items[x];
	}

	void setItem(int x, int amount) {
		items[x] = amount;
	}

	public String getwarehouseCity() {
		return warehouseCity;
	}

	public void setwarehouseCity(String warehouseCity) {
		this.warehouseCity = warehouseCity;
	}

	@Override
	public String toString() {
		return warehouseCity + ": Items=" + Arrays.toString(items);
	}


}
