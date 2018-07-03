package discompanydatcompany.vendingmachine.dao;

import java.util.List;

import discompanydatcompany.vendingmachine.beans.VendingMachine;

public interface VendingMachineDAO {
	public boolean addVendingMachine(VendingMachine vendingMachine);
	public VendingMachine selectVendingMachineById(VendingMachine vendingMachine);
	public List<VendingMachine> selectAllVendingMachines();
	public boolean deleteVendingMachine(String vendingMachineUUID);
	public boolean deleteVendingMachine(VendingMachine vendingMachine);
	public boolean updateVendingMachine(VendingMachine vendingMachine);
	public boolean preparedUpdate(VendingMachine vendingMachine);
}
