package dao;

public interface UserManage {
	/** ȸ������, �α���, �α׾ƿ�, ����� ��������, Ż�� (��ȭ) UserManageImpl */
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
	
	public void join();
	public void login();
	public void logout();
	public void update();
	public void out();
	
}
