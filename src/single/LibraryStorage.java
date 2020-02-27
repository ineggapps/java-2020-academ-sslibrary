package single;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class LibraryStorage {
	private LibraryStorage() {
		// ������ ���� ���ϰ� ����
	}

	// ���� ����
	private UserVO loginMember = null;

	public UserVO getLoginMember() {
		return loginMember;
	}

	public void setLoginMember(UserVO loginMember) {// ����
		this.loginMember = loginMember;
	}

	public UserVO getUser(String id) {// ���� �˻��Ͽ� �������� ������ null ��ȯ
		for (UserVO vo : userList) {
			if (id.equals(vo.getId())) {
				return vo;
			}
		}
		return null;
	}

	public BookManageVO getBorrowUser(String id) {// ���� �˻��Ͽ� �������� ������ null ��ȯ
		for (BookManageVO bmv : rentalList) {
			if (id.equals(bmv.getId())) {
				return bmv;
			}
		}
		return null;
	}

	// ȸ�� ���
	private List<UserVO> userList = new ArrayList<>();
	// ���� ���
	private Map<String, BookVO> bookList = new TreeMap<>();
	// �뿩-�ݳ� ���
	private List<BookManageVO> rentalList = new ArrayList<>();

	public List<UserVO> getUserList() {
		return userList;
	}

	// ���� ��� �̸��� ����
	public void sortByUserName() {
		Comparator<UserVO> comparator = new Comparator<UserVO>() {
			@Override
			public int compare(UserVO o1, UserVO o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(userList, comparator);
	}

	public Map<String, BookVO> getBookList() {
		return bookList;
	}

	public List<BookManageVO> getRentalList() {
		return rentalList;
	}

	public BookVO getBook(BookManageVO vo) {
		return bookList.get(vo.getIsbn13());
	}

	// static ��ø Ŭ����
	private static class Holder {
		public static final LibraryStorage INSTANCE = new LibraryStorage();
	}

	public static LibraryStorage getInstance() {
		return Holder.INSTANCE;
	}
}
