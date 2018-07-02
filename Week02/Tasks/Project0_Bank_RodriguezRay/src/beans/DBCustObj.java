package beans;

public class DBCustObj {
	private Integer custId;
	private double balance;
	private Integer accId;
	private Integer bankId;
	
	public DBCustObj(Integer custId, double balance, Integer accId, Integer bankId) {
		super();
		this.custId = custId;
		this.balance = balance;
		this.accId = accId;
		this.bankId = bankId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	
	
}
