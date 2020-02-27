package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import single.LibraryStorage;
import single.Services;
import util.DateMaker;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class BookTransactionImpl implements BookTransaction {
	private List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
	private Scanner sc = new Scanner(System.in);
	private DateMaker dm = new DateMaker();

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

		System.out.println("대여할 책 목록을 골라주세요\n");

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
		String endDateStr;
		Date endDate;
		System.out.println("==========반납==========");

		UserVO vo = getUser();
		BookState bs = Services.getInstance().getBookState();
		System.out.println(vo);
		bs.findId(vo.getId());
		
		System.out.println("반납할 책코드 ?");
		String code = sc.next();
		
		System.out.println("반납일 입력(2020-02-27)");
		endDateStr = sc.next();
		endDate = dm.toDate(endDateStr);

		return returnBook(code, endDate);
	}

	@Override
	public BookManageVO returnBook(BookManageVO vo) {
		return returnBook(vo.getIsbn13(), new Date());
	}

	@Override
	public BookManageVO returnBook(String code, Date endDate) {
		UserVO user = getUser();
		String name = user.getName();

		if (endDate == null) {
			System.out.println("유효하지 않은 날짜 정보를 입력하셨습니다.");
			return null;
		}

		Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
		for (BookManageVO vo : rentalList) {
			BookVO book = bookList.get(vo.getIsbn13());
			String bookTitle = book.getTitle();
			String isbn = vo.getIsbn13();
			String id = vo.getId();
			if (isbn.equals(code) && id.equals(user.getId())) {
				// 삭제 연산
				book.setAmount(book.getAmount() + 1);// 수량 원복
				vo.setEndDate(endDate);
				// 안내 문구 출력
				System.out.printf("%s님께서 빌려가신 %s 책이 %s부로 반납 처리되었습니다.", name, bookTitle, dm.toString(endDate));
				System.out.println();
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
