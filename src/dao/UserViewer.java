package dao;

public interface UserViewer {
	/**
	 * 회원 관리 - 회원 목록, 아이디 검색 (호중) UserViewerImpl 구현
	 */
	public void printUsers();
	public void findUserById();
}
