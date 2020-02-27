package dao;

public interface UserManage {
	/** 회원가입, 로그인, 로그아웃, 사용자 정보수정, 탈퇴 (선화) UserManageImpl */
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
	
	public void join();
	public void login();
	public void logout();
	public void update();
	public void out();
	
}
