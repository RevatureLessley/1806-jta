package trms.dao;

public interface FinanceDAO {
	public Integer getUserAvailableReimbursementsAmount(String uuid);
	public Integer getUserPendingReimbursementsAmount(String uuid);
	public Integer getUserAwardedReimbursementAmount(String uuid);
	public Integer getUserExceededReimbursementAmount(String uuid);
	public boolean updateAvailableReimbursementsAmount(String uuid, Integer amount);
	public boolean updatePendingReimbursementsAmount(String uuid, Integer amount);
	public boolean updateAwardedReimbursementsAmount(String uuid, Integer amount);
	public boolean updateExceededReimbursementAmount(String uuid, Integer amount);
}
