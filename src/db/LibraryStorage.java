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
		// 생성자 제어 못하게 막기
	}

	// 현재 세션
	private Map<String, UserVO> session = new HashMap<>();

	public Map<String, UserVO> getSession() {
		return session;
	}

	public UserVO getUser(String id) {//유저 검색하여 가져오기 없으면 null 반환
		for (UserVO vo : userList) {
			if (id.equals(vo.getId())) {
				return vo;
			}
		}
		return null;
	}

	// 회원 목록
	private List<UserVO> userList = new ArrayList<>();
	// 도서 목록
	private Map<String, BookVO> bookList = new HashMap<>();
	// 대여-반납 목록
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

	// static 중첩 클래스
	private static class Holder {
		public static final LibraryStorage INSTANCE = new LibraryStorage();
	}

	public static LibraryStorage getInstance() {
		return Holder.INSTANCE;
	}
}
