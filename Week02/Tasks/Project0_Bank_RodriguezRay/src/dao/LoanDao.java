package dao;

import java.util.List;

import beans.Loan;

public interface LoanDao {
	public void insertLoan(Loan loan);
	public Loan selectLoanById(Integer loanId, Integer custId);
	public List<Loan> selectAllLoansByAccId(Integer accId);
	public String selectAllLoans();
	public Integer deleteLoanById(Integer id);
	public Boolean updateLoan(Loan loan, Integer accId);
	public Boolean insertLoanViaSp(Loan loan, Integer id);
}
