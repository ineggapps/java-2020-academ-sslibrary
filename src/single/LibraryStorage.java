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
		// 생성자 제어 못하게 막기
	}

	// 현재 세션
	private UserVO loginMember = null;

	public UserVO getLoginMember() {
		return loginMember;
	}

	public void setLoginMember(UserVO loginMember) {// 수정
		this.loginMember = loginMember;
	}

	public UserVO getUser(String id) {// 유저 검색하여 가져오기 없으면 null 반환
		for (UserVO vo : userList) {
			if (id.equals(vo.getId())) {
				return vo;
			}
		}
		return null;
	}

	public BookManageVO getBorrowUser(String id) {// 유저 검색하여 가져오기 없으면 null 반환
		for (BookManageVO bmv : rentalList) {
			if (id.equals(bmv.getId())) {
				return bmv;
			}
		}
		return null;
	}

	// 회원 목록
	private List<UserVO> userList = new ArrayList<>();
	// 도서 목록
	private Map<String, BookVO> bookList = new TreeMap<>();
	// 대여-반납 목록
	private List<BookManageVO> rentalList = new ArrayList<>();

	public List<UserVO> getUserList() {
		return userList;
	}

	// 유저 목록 이름순 정렬
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

	// static 중첩 클래스
	private static class Holder {
		public static final LibraryStorage INSTANCE = new LibraryStorage();
	}

	public static LibraryStorage getInstance() {
		return Holder.INSTANCE;
	}
}
