package trms.dao;

import java.util.List;

import trms.beans.Role;
import trms.beans.User;

public interface RoleDAO {
	public List<Role> getUserRoleList();
	public boolean isUserASupervisor(User user);
	public boolean isUserABenefitsCoordinator(User user);
	public boolean isUserADepartmentHead(User user);
	public boolean isUserAnAdmin(User user);
	public boolean addUserAsSupervisor(User user);
	public boolean addUserAsBenefitsCoordinator(User user);
	public boolean addUserAsDepartmentHead(User user);
	public boolean addUserAsAdmin(User user);
	public boolean removeUserAsSupervisor(User user);
	public boolean removeUserAsBenefitsCoordinator(User user);
	public boolean removeUserAsDepartmentHead(User user);
}
