package trms.dao;

public interface FinanceDAO {
	public Integer getUserAvailableReimbursementsAmount(String uuid);
	public Integer getUserPendingReimbursementsAmount(String uuid);
	public Integer getUserAwarededReimbursementAmount(String uuid);
	public boolean insertAvailableReimbursementsAmount(Integer amount);
	public boolean insertPendingReimbursementsAmount(Integer amount);
	public boolean insertAwardedReimbursementsAmount(Integer amount);
	public boolean updateAvailableReimbursementsAmount(Integer amount);
	public boolean updatePendingReimbursementsAmount(Integer amount);
	public boolean updateAwardedReimbursementsAmount(Integer amount);
}
