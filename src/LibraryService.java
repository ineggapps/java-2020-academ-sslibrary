
public interface LibraryService {
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";

	public void entrance(); // 로그인 상태에 따라 다른 화면 보여주기

	public void login();

	public void logout();

	public void register();

	public boolean showDefaultMenu();

	public boolean showAdminMenu();

	public boolean showUserMenu();

}
