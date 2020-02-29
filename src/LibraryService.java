
public interface LibraryService {

	public void entrance(); // 로그인 상태에 따라 다른 화면 보여주기

	public boolean showDefaultMenu();

	public boolean showAdminMenu();

	public boolean showUserMenu();

}
