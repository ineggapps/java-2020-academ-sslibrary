import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.UserManageImpl;
import dao.UserViewer;
import single.LibraryStorage;
import single.Services;
import vo.UserVO;

public class LibraryServiceImpl implements LibraryService {

	Scanner sc = new Scanner(System.in);
	UserViewer uv = Services.getInstance().getUserViewer();
	List<UserVO> userList = LibraryStorage.getInstance().getUserList();
	UserManageImpl um = new UserManageImpl(); 
	
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
	public void login() {
		System.out.println("\n===로그인===");
		UserVO loginMember = LibraryStorage.getInstance().getLoginMember();
		String id;
		String pw;

		System.out.print("아이디 입력 ? ");
		id = sc.next();
		System.out.print("비밀번호 입력 ? ");
		pw = sc.next();

		System.out.println(id + ", " + pw);
		if (id.equals(ADMIN_ID) && pw.equals(ADMIN_PW)) {
			System.out.println("관리자로 로그인하셨습니다!");
			LibraryStorage.getInstance().setLoginMember(new UserVO(ADMIN_ID, ADMIN_PW, "관리자"));
			return;
		}

		// 일반 유저 로그인
		UserVO vo = LibraryStorage.getInstance().getUser(id);
		if (vo == null) {
			System.out.println("존재하지 않는 계정입니다.");
			return;
		} else if (!pw.equals(vo.getPw())) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}

		LibraryStorage.getInstance().setLoginMember(vo);
		System.out.println("로그인하셨습니다.");
	}

	@Override
	public void logout() {
		LibraryStorage.getInstance().setLoginMember(null);
		System.out.println("안전하게 로그아웃 되었습니다.");
	}

	@Override
	public void register() {
		System.out.println("\n===회원가입===");
		//join() 실행 
		System.out.println("회원가입 완료!");
	}

	@Override
	public boolean showAdminMenu() {
		int ch;
		System.out.println("\n===관리자 메뉴===");
		System.out.print("1.도서관리 2.회원관리 3.로그아웃 > ");
		ch = sc.nextInt();
		switch (ch) {
		case 1:// 도서관리
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
			logout();
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
		System.out.print("1.도서대여 2.도서검색 3.로그아웃 4.정보수정 5.탈퇴 6.이전메뉴");
		ch = sc.nextInt();
		switch (ch) {
		case 1:// 도서대여
			break;
		case 2:// 도서검색
			break;
		case 3:// 로그아웃
			logout();
			break;
		case 4:// 정보수정
			break;
		case 5:// 탈퇴
			break;
		case 6: // 이전 메뉴로
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
	}

}
