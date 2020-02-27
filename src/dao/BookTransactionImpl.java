package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import single.LibraryStorage;
import util.DateMaker;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class BookTransactionImpl implements BookTransaction {
	private List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
	private Scanner sc = new Scanner(System.in);

//로그인했는지 확인
	private UserVO getUser() {
		UserVO user = LibraryStorage.getInstance().getLoginMember();
		if (user == null) {
			System.out.println("로그인 상태가 아닙니다");
			return null;
		}
		return user;
	}

	@Override
	public void bookTransaction(BookManageVO vo) {
		UserVO user = getUser();
	}

//대여
	@Override
	public BookManageVO rentalBook() {
		String code;
		UserVO user = getUser();

		System.out.println("대여 ?\n");

		System.out.println("대여할 책코드 ?");
		code = sc.next();

		BookVO vo = readBook(code);
		if (vo == null) {
			System.out.println("대여가 완료되지않았습니다.\n");
			return null;
		}
		// LibraryStorage.getInstance().getBookList().remove(vo);

		System.out.println("대여가 완료되었습니다. ");

		BookManageVO rentalVO = new BookManageVO(code, user.getId(), new Date(), null);
		rentalList.add(rentalVO);
		return rentalVO;
	}

//반납
	@Override
	public BookManageVO returnBook() {
		UserVO user = getUser();
		String endDateStr;
		Date endDate;
		System.out.println("반납?");
		String code;
		System.out.println("반납할 책코드 ?");
		code = sc.next();

		System.out.println("반납일 입력(2020-02-27)");
		endDateStr = sc.next();

		DateMaker dm = new DateMaker();
		endDate = dm.toDate(endDateStr);

		if (endDate == null) {
			System.out.println("유효하지 않은 날짜 정보를 입력하셨습니다.");
			return null;
		}

		for (BookManageVO vo : rentalList) {
			String isbn = vo.getIsbn13();
			String id = vo.getId();
			if (isbn.equals(code) && id.equals(user.getId())) {
				vo.setEndDate(endDate);
				return vo;
			}
		}
		return null;
	}

//책코드비교
	private BookVO readBook(String code) {
		Map<String, BookVO> list = LibraryStorage.getInstance().getBookList();
		Set<String> isbns = list.keySet();
		Iterator<String> it = isbns.iterator();
		while (it.hasNext()) {
			String isbn = it.next();
			BookVO vo = list.get(isbn);
			if (vo != null && vo.getIsbn13().equals(code)) {
				return vo;
			}
		}
		return null;
	}
}
