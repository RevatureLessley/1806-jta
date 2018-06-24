package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;
import java.util.HashMap;

public class VendingMachineList implements Serializable {

	private HashMap<String, VendingMachine> vendingMachineList;
	
	public VendingMachineList() {
		this.vendingMachineList = new HashMap<String, VendingMachine>();
	}
	
	public void addVendingMachine(VendingMachine machine) {
		if (machine == null) {
			return;
		} else {
			this.vendingMachineList.put(machine.getVendingMachineId(), machine);
		}
	}
	
	public VendingMachine getVendingMachine(String key) {
		if (this.vendingMachineList.containsKey(key)) {
			return this.vendingMachineList.get(key);
		} else {
			return null;
		}
	}
	
	public int size() {
		return this.vendingMachineList.size();
	}
	
}
