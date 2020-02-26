package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class LibraryStorage {
	private LibraryStorage() {
		// ������ ���� ���ϰ� ����
	}

	// ���� ����
	private Map<String, UserVO> session = new HashMap<>();

	public Map<String, UserVO> getSession() {
		return session;
	}

	public UserVO getUser(String id) {//���� �˻��Ͽ� �������� ������ null ��ȯ
		for (UserVO vo : userList) {
			if (id.equals(vo.getId())) {
				return vo;
			}
		}
		return null;
	}

	// ȸ�� ���
	private List<UserVO> userList = new ArrayList<>();
	// ���� ���
	private Map<String, BookVO> bookList = new HashMap<>();
	// �뿩-�ݳ� ���
	private List<BookManageVO> rentalList = new ArrayList<>();

	public List<UserVO> getUserList() {
		return userList;
	}

	public Map<String, BookVO> getBookList() {
		return bookList;
	}

	public List<BookManageVO> getRentalList() {
		return rentalList;
	}

	// static ��ø Ŭ����
	private static class Holder {
		public static final LibraryStorage INSTANCE = new LibraryStorage();
	}

	public static LibraryStorage getInstance() {
		return Holder.INSTANCE;
	}
}
