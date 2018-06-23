package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bank implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4132742045791959827L;
	List<UserAccount> userAccounts = new ArrayList<UserAccount>();
	List<AdminAccount> adminAccounts = new ArrayList<AdminAccount>();
	List<BankerAccount> bankerAccounts = new ArrayList<BankerAccount>();
	String name;
	String address;
	String phone;
	
	public Bank(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}
	
	public List<UserAccount> getUserAcc(){
		return userAccounts;
	}
	
	public List<AdminAccount> getAdminAcc(){
		return adminAccounts;
	}

	@Override
	public String toString() {
		return "Bank [userAccounts=" + Arrays.toString(userAccounts.toArray()) + ", adminAccounts=" + Arrays.toString(adminAccounts.toArray()) + ", name=" + name
				+ ", address=" + address + ", phone=" + phone + "]";
	}
	
	
}
