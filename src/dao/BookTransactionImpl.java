package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
		BookManage bm = Services.getInstance().getBookManage();
		String code;
		UserVO user = getUser();

		System.out.println("대여할 책 목록을 골라주세요\n");
		bm.listBook();

		System.out.print("대여할 책코드 ? ");
		code = sc.next();

		BookVO vo = readBook(code);
		if (vo == null) {
			System.out.println("올바르지 않은 코드를 입력하셨습니다.\n");
			return null;
		}
		LibraryStorage ls = LibraryStorage.getInstance();
		BookManageVO log = ls.getRentalNotReturn(code, user.getId());
		// Case 1. 반납이 끝나고 다시 대여를 할 수도 있다.
		if (log != null && (log.getStartDate() != null && log.getEndDate() == null)) {
			System.out.println("이미 대여 중입니다.");
			return null;
		}

		// 수량 파악
		int amount = vo.getAmount() - 1;
		if (amount < 0) {
			System.out.println("현재 " + vo.getTitle() + "의 모든 책이 대출 중입니다. 반납되는 대로 추후 연락드리겠습니다.");
			return null;
		}
		System.out.println("우리 도서관의 [" + vo.getTitle() + "] 서적의 재고는 " + vo.getAmount() + "권 => " + (vo.getAmount() - 1)
				+ "권이 됩니다.");
		vo.setAmount(amount);
		System.out.println("[" + vo.getTitle() + "] 책 대여가 완료되었습니다. ");

		BookManageVO rentalVO = new BookManageVO(code, user.getId(), new Date(), null);
		rentalList.add(rentalVO);
		return rentalVO;
	}

//반납
	@Override
	public BookManageVO returnBook() {
		BookState bs = Services.getInstance().getBookState();

		String endDateStr;
		Date endDate;
		System.out.println("==========반납==========");

		UserVO vo = getUser();
		System.out.println(vo);
		bs.findId(vo.getId());

		System.out.print("반납할 책코드 ? ");
		String code = sc.next();
		BookVO book = readBook(code);
		if (book == null) {
			System.out.println("올바르지 않은 코드를 입력하셨습니다.\n");
			return null;
		}
		LibraryStorage ls = LibraryStorage.getInstance();
		BookManageVO log = ls.getRentalNotReturn(code, vo.getId());
		if (log == null || (log != null && log.getEndDate() != null)) {
			System.out.println("대여하지 않은 책이거나 이미 반납하셨습니다.");
			return null;
		}

		System.out.print("반납일 입력(yyyy-mm-dd) > ");
		endDateStr = sc.next();
		endDate = dm.toDate(endDateStr);

		return returnBook(code, endDate);
	}

	@Override
	public BookManageVO returnBook(BookManageVO vo) {
		if (vo == null) {
			System.out.println("등록되지 않은 책 코드입니다.");
			return null;
		}
		if (vo.getEndDate() != null) {
			System.out.println("이미 반납하셨습니다.");
			return vo;
		}
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
			if(!vo.getIsbn13().equals(code)) {
				//복수 개의 대출 중인 상황에서는 어떤 책을 반납할 것인가에 달렸다.
				//대출 중이지만 반납하고자 하는 책이 아니라면 이번 요소 vo는 건너뛴다.
				continue;
			}
			BookVO book = bookList.get(vo.getIsbn13());
			String bookTitle = book.getTitle();
			String isbn = vo.getIsbn13();
			String id = vo.getId();

			// 반납일자가 대여일자보다 크면 반납해주고, 적으면 반납 못하도록.
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

			// 대여일자
			Date sday = vo.getStartDate();
			String borrowday = new SimpleDateFormat("yyyy-MM-dd").format(sday);

			// 반납일자
			Date eday = endDate;
			String endday = new SimpleDateFormat("yyyy-MM-dd").format(eday);

			try {
				eday = dateFormat.parse(endday);
				sday = dateFormat.parse(borrowday);

				System.out.println();
				int compare = eday.compareTo(sday);
				if (compare < 0) {
					System.out.println(dm.toString(sday) + "보다 큰 날짜를 입력하세요.");
					return null;
				} else if (compare > 0) {
//					System.out.println(dm.toString(eday) + "까지 반납해주세요.");
					if (isbn.equals(code) && id.equals(user.getId())) {
						// 삭제 연산
						book.setAmount(book.getAmount() + 1);// 수량 원복
						vo.setEndDate(endDate);
						// 안내 문구 출력
						System.out.printf("%s님께서 빌려가신 %s 책이 %s부로 반납 처리되었습니다.", name, bookTitle, dm.toString(endDate));
						System.out.println();
						return vo;
					}
				} else {// compare값이 0인 경우
					// 삭제 연산
					book.setAmount(book.getAmount() + 1);// 수량 원복
					vo.setEndDate(endDate);
					// 안내문구 출력
					System.out.println("당일 반납하셨습니다. (" + dm.toString(endDate) + ")");
					return vo;
				}

			} catch (ParseException e) {
				e.printStackTrace();
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
