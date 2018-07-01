package discompanydatcompany.vendingmachine.VendingMachineDAO;

import java.util.List;

import discompanydatcompany.vendingmachine.beans.VendingMachine;

public interface VendingMachineDAO {

	public void InsertVendingMachine(VendingMachine vendingMachine);
	public VendingMachine selectVendingMachineById(VendingMachine vendingMachine);
	public List<VendingMachine> selectAllVendingMachines();
	public String deleteVendingMachine(String vendingMachineUUID);
	public String deleteVendingMachine(VendingMachine vendingMachine);
	public boolean insertVendingMachine(VendingMachine vendingMachine);
	public Integer insertVendingMachineViaStoredProcedure();
}
