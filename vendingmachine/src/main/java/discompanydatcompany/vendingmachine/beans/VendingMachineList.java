package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;
import java.util.HashMap;

public class VendingMachineList implements Serializable {

	private HashMap<String, VendingMachine> vendingMachineList;
	
	/**
	 * VendingMachineList default constructor instantiates an empty HashMap for vendingMachineList
	 */
	public VendingMachineList() {
		this.vendingMachineList = new HashMap<String, VendingMachine>();
	}
	
	/**
	 * Adds a VendingMachine object to the list
	 * 
	 * @param machine the VendingMachine to include.
	 */
	public void addVendingMachine(VendingMachine machine) {
		if (machine == null) {
			return;
		} else {
			this.vendingMachineList.put(machine.getVendingMachineId(), machine);
		}
	}
	
	/**
	 * Returns the VendingMachine associated with the key-value passed to the function. Returns null otherwise.
	 * 
	 * @param key the VendingMachine's VendingMachineID value
	 * @return returns a VendingMachine object. Null otherwise.
	 */
	public VendingMachine getVendingMachine(String key) {
		if (this.vendingMachineList.containsKey(key)) {
			return this.vendingMachineList.get(key);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the size of the VendingMachineList
	 * 
	 * @return returns the size of the VendingMachineList
	 */
	public int size() {
		return this.vendingMachineList.size();
	}
	
}
