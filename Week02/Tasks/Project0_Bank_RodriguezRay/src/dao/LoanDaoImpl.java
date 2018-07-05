package dao;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Loan;
import util.Connections;

public class LoanDaoImpl implements LoanDao{

	@Override
	public void insertLoan(Loan loan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Loan selectLoanById(Integer loanId, Integer custId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlLoan = "SELECT * FROM loan WHERE loan_id=? AND customer_id=?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlLoan);
			ps.setInt(1, loanId);
			ps.setInt(2, custId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new Loan(rs.getInt(1), rs.getFloat(2), rs.getFloat(3), rs.getDouble(4),
						rs.getInt(5), rs.getDouble(6), rs.getBoolean(7));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public List<Loan> selectAllLoansByAccId(Integer accId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlLoan = "SELECT * FROM loan WHERE acc_id=?"; 
		List<Loan> loans = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlLoan);
			ps.setInt(1, accId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				loans.add(new Loan(rs.getInt(1), rs.getFloat(2), rs.getFloat(3), rs.getDouble(4),
						rs.getInt(5), rs.getDouble(6), rs.getBoolean(7)));
			}
			return loans;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	@Override
	public String selectAllLoans() {
		String result = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT loan.loan_id, loan.interest_rate, loan.apr, loan.ori_fee, loan.loan_term, "
				+ "loan.loan_amount, loan.loan_approved, loan.acc_id, account.first_name, account.last_name "
				+ "FROM loan JOIN account ON loan.acc_id = account.acc_id"; 
		List<Loan> loans = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				System.out.format("%-12s|%-15s|%-14s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|%-12s|", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10));
			}
			return result;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public Integer deleteLoanById(Integer id) {
		return null;
	}

	@Override
	public Boolean updateLoan(Loan loan, Integer accId) {
		PreparedStatement ps = null;
		String sql = "UPDATE loan SET loan_approved=?"
				+ "WHERE loan_id=? AND acc_id=?";

		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, loan.isApproved());
			ps.setInt(2, loan.getId());
			ps.setInt(3, accId);
			
			if(ps.executeUpdate()>0) {
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
		}
		return false;
	}

	@Override
	public Boolean insertLoanViaSp(Loan loan, Integer id) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insertLoan(?,?,?,?, ?, ?, ?)}");

			stmt.setFloat(1, loan.getInterestRate());
			stmt.setFloat(2, loan.getApr());
			stmt.setDouble(3, loan.getOriFee());
			stmt.setInt(4, loan.getLoanTerm());
			stmt.setDouble(5, loan.getAmount());
			stmt.setBoolean(6, loan.isApproved());
			stmt.setInt(7, id);
			
			stmt.execute();	//returns amount of rows effected;
		
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return false;
	}

}
