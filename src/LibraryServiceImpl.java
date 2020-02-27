import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.BookManage;
import dao.BookState;
import dao.BookTransaction;
import dao.UserManage;
import dao.UserViewer;
import single.LibraryStorage;
import single.Services;
import util.DateMaker;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class LibraryServiceImpl implements LibraryService {

	Scanner sc = new Scanner(System.in);
	BookManage bm = Services.getInstance().getBookManage();
	BookState bs = Services.getInstance().getBookState();
	BookTransaction bt = Services.getInstance().getBookTransaction();
	UserViewer uv = Services.getInstance().getUserViewer();
	UserManage um = Services.getInstance().getUserManage();
	List<UserVO> userList = LibraryStorage.getInstance().getUserList();
	DateMaker dm = new DateMaker();

	public void entrance() {
		// 로그인 상태에 따라 메뉴 다르게 구성하기
		boolean isGoing = true;
		while (isGoing) {
			UserVO loginMember = LibraryStorage.getInstance().getLoginMember();
			try {
				if (loginMember == null) {
					// 비로그인 상태
					isGoing = showDefaultMenu();
				} else if (loginMember.getId().equals(ADMIN_ID)) {
					// 관리자 상태
					isGoing = showAdminMenu();
				} else {
					// 일반 유저 상태
					isGoing = showUserMenu();
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력 가능합니다.");
				sc.nextLine();
			}
		}

	}

	@Override
	public boolean showDefaultMenu() {
		int ch;
		do {
			System.out.print("1.로그인 2.회원가입 3.종료 > ");
			ch = sc.nextInt();
		} while (ch < 1 || ch > 3);
		if (ch == 3) {
			return false;
		}
		switch (ch) {
		case 1:// 로그인
			um.login();
			break;
		case 2:// 회원가입
			um.join();
			break;
		}
		return true;
	}

	@Override
	public boolean showAdminMenu() {
		int ch;
		System.out.println("\n===관리자 메뉴===");
		System.out.print("1.도서관리 2.회원관리 3.로그아웃 > ");
		ch = sc.nextInt();
		switch (ch) {
		case 1:
			System.out.print("1.도서등록 2.도서수정 3.도서삭제 4.ISBN으로검색 5.도서명으로검색 6.보유한도서리스트 7.종료=> ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				bm.insertBook();
				break;
			case 2:
				bm.updateBook();
				break;
			case 3:
				bm.deleteBook();
				break;
			case 4:
				bm.findByISBN();
				break;
			case 5:
				bm.findByTitle();
				break;
			case 6:
				bm.listBook();
				break;
			}
			break;
		case 2:// 회원관리
			System.out.print("1.회원목록 2.아이디검색 3.이전메뉴 > ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:// 회원목록
				uv.printUsers();
				break;
			case 2:// 아이디 검색
				uv.findUserById();
				break;
			}
			break;
		case 3:// 로그아웃
			um.logout();
			break;
		case 4:// 이전 메뉴로
			return false;
		}
		return true;
	}

	@Override
	public boolean showUserMenu() {
		System.out.println("\n===원하시는 메뉴를 고르시오===");
		int ch;
		System.out.println("\n===사용자 메뉴===");
		System.out.print("1.도서대여 2.도서반납 3.도서검색 4.로그아웃 5.정보수정 6.탈퇴 7.이전메뉴 > ");
		ch = sc.nextInt();
		switch (ch) {
		case 1:// 도서대여
			bt.rentalBook();
			break;
		case 2:// 도서반납
			bt.returnBook();
			break;
		case 3:// 도서검색
			System.out.print("1.전체 검색 2.도서명 검색 3.ISBN 검색 4.이전 메뉴> ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:// 전체 목록
				bm.listBook();
				break;
			case 2:// 도서명 검색
				bm.findByTitle();
				break;
			case 3:// 도서코드 검색
				bm.findByISBN();
				break;
			default:
				System.out.println("잘못 선택하셨습니다.");
			}
			break;
		case 4:// 로그아웃
			um.logout();
			break;
		case 5:
			um.update();
			break; // 정보수정
		case 6:
			um.out();// 탈퇴
			break;
		case 7: // 이전 메뉴로
			return false;
		}
		return true;
	}

	public LibraryServiceImpl() {
		List<UserVO> list = LibraryStorage.getInstance().getUserList();
		// 더미데이터 완성시키기
		list.add(new UserVO("history1", "0000", "전한길", "hangil@gmail.com"));
		list.add(new UserVO("history2", "0000", "설민석", "minseok@gmail.com"));
		list.add(new UserVO("history3", "0000", "강민성", "minseong@gmail.com"));
		list.add(new UserVO("history4", "0000", "문동균", "dong-gyun@gmail.com"));
		list.add(new UserVO("history5", "0000", "신영식", "yeongsik@gmail.com"));
		list.add(new UserVO("history6", "0000", "고종훈", "jonghoon@gmail.com"));
		list.add(new UserVO("history7", "0000", "신명섭", "myungseob@gmail.com"));
		list.add(new UserVO("korean1", "1111", "이선재", "seonjae@gmail.com"));
		list.add(new UserVO("korean2", "1111", "김병태", "byeongtae@gmail.com"));
		list.add(new UserVO("korean3", "1111", "고혜원", "hyaewon@gmail.com"));
		list.add(new UserVO("korean4", "1111", "이태종", "taejong@gmail.com"));
		list.add(new UserVO("english1", "2222", "이동기", "dong-gi@gmail.com"));
		list.add(new UserVO("english2", "2222", "심우철", "woocheol@gmail.com"));
		list.add(new UserVO("english3", "2222", "조태정", "taejeong@gmail.com"));
		list.add(new UserVO("english4", "2222", "손진숙", "jinsuk@gmail.com"));

		list.add(new UserVO("english5", "2222", "김기훈", "gihun@gmail.com"));
		list.add(new UserVO("computer", "3333", "박미진", "mijin@gmail.com"));

		Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
		bookList.put("9788934900115", new BookVO("9788934900115", "지능의 함정", "데이비드 롭슨 저/이창신 역", "김영사",
				dm.toDate("20200113"), randomNumber(), "인문"));
		bookList.put("9791189995539", new BookVO("9791189995539", "우리가 인생이라 부르는 것들", "정재찬 저", "인플루엔셜",
				dm.toDate("20200225"), randomNumber(), "인문"));
		bookList.put("9791189584559", new BookVO("9791189584559", "죽은 철학자의 살아있는 인생수업", "시라토리 하루히코, 지지엔즈 저/김지윤 역",
				"포레스트북스", dm.toDate("20200306"), randomNumber(), "인문"));
		bookList.put("9791162242551", new BookVO("9791162242551", "개발 7년차, 매니저 1일차", "카미유 푸르니에/권원상, 한민주 역", "한빛미디어",
				dm.toDate("20200204"), randomNumber(), "IT 모바일"));
		bookList.put("9791160509762", new BookVO("9791160509762", "2020 시나공 정보처리기사 필기", "길벗알앤디 저", "길벗",
				dm.toDate("20191118"), randomNumber(), "컴퓨터 수험서"));
		bookList.put("9788952753946", new BookVO("9788952753946", "날씨가 좋으면 찾아가겠어요", "이도우 저", "시공사",
				dm.toDate("20200201"), 0, "소설/시/희곡"));

		List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
		rentalList.add(new BookManageVO("9791160509762", "history1", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791160509762", "history2", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791160509762", "history3", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791189995539", "history1", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791189995539", "history1", dm.toDate("20200207"), null));
	}

	public int randomNumber() {
		return (int) (Math.random() * 100);
	}

}
