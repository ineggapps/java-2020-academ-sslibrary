package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import single.LibraryStorage;
import vo.UserVO;

public class UserManageImpl implements UserManage {
	private Scanner sc = new Scanner(System.in);

	@Override
	public void join() {
		System.out.println("회원가입");
		// id(키), pw, name, email
		// id(키) 중복체크

		UserVO vo = new UserVO();
		String id;
		System.out.print("아이디 입력 >");
		id = sc.next();
		vo.setId(id);

		if (LibraryStorage.getInstance().getUser(id) != null) {
			System.out.println("등록된 아이디 입니다 ㅠㅠ. \n");
			return;
		}

		try {

			System.out.print("이름 입력 >");
			vo.setName(sc.next());

			/*
			 * System.out.print("아이디 입력 >"); vo.setId(sc.next());
			 */

			System.out.print("비밀번호 입력 >");
			vo.setPw(sc.next());

			System.out.print("이메일 입력 >");
			vo.setEmail(sc.next());

			LibraryStorage.getInstance().getUserList().add(vo);
			System.out.println("축> 회원가입이 완료 되었습니다. <하");

		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public void update() {
		System.out.println("회원님의 정보 수정...\n");

		String id;
		String pw;

		System.out.println("=========본인 확인 ========");
		System.out.print("아이디 입력>");
		id = sc.next();

		System.out.print("비밀번호 입력>");
		pw = sc.next();

		UserVO vo = LibraryStorage.getInstance().getUser(id);

		if (vo == null || !vo.getPw().equals(pw) || !vo.getId().equals(id)) {
			System.out.println("아이디와 비밀번호가 일치 하지 않아요 ㅠㅠ...\n");
			return;
		}

		System.out.print("수정할 비밀번호 >");
		vo.setPw(sc.next());

		System.out.print("수정할 이름 >");
		vo.setName(sc.next());

		System.out.print("수정할 이메일 >");
		vo.setEmail(sc.next());

		System.out.println("수정된" + vo.getName() + "님의 정보 : " + vo.toStringWithPassword());
		System.out.println(vo.getName() + "님의 정보 수정이 완료 되었습니다.! \n");
	}

	@Override
	public void out() {
		String id;
		String pw;

		System.out.print("아이디 입력 >");
		id = sc.next();

		System.out.print("비밀 번호 입력 >");
		pw = sc.next();

		UserVO vo = LibraryStorage.getInstance().getUser(id);
		if (vo == null || !vo.getPw().equals(pw) || !vo.getId().equals(id)) {
			System.out.println("아이디와 비밀번호가 일치 하지 않아요 ㅠㅠ...\n");
			return;
		}

		LibraryStorage.getInstance().getUserList().remove(vo);
		System.out.println(vo.getName() + " 회원님 탈퇴가 되셨습니다...\n");
		logout();
	}

}
