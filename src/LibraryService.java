
public interface LibraryService {
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";

	public void entrance(); // �α��� ���¿� ���� �ٸ� ȭ�� �����ֱ�

	public void login();

	public void logout();

	public void register();

	public boolean showDefaultMenu();

	public boolean showAdminMenu();

	public boolean showUserMenu();

}
