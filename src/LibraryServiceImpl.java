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
import util.Dummy;
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
		//생성자 호출을 위한 객체참조변수 선언
		List<UserVO> userList = LibraryStorage.getInstance().getUserList();
		Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
		List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
		//더미데이터 생성하기
		new Dummy(userList, bookList, rentalList);
	}

	

}
