package dao;

import java.util.List;
import java.util.Scanner;

import single.LibraryStorage;
import vo.UserVO;

public class UserViewerImpl implements UserViewer {

	Scanner sc = new Scanner(System.in);
	List<UserVO> list = LibraryStorage.getInstance().getUserList();

	@Override
	public void printUsers() {
		// 유저 목록 조회
		System.out.printf("총 %d건\n", list.size());
		for (UserVO vo : list) {
			System.out.println(vo);
		}
	}

	@Override
	public void findUserById() {
		// 아이디로 검색하기
		String id;
		System.out.print("검색할 아이디 입력 > ");
		id = sc.next();
		int cnt = 0;
		System.out.println("===검색 결과===");
		for (UserVO vo : list) {
			if (vo.getId().indexOf(id) >= 0) {
				System.out.println(vo);
				cnt++;
			}
		}

		if (cnt > 0) {
			System.out.println("==== " + cnt + "건의 사용자 정보를 찾았습니다. ====");
		} else {
			System.out.println("사용자를 찾을 수 없습니다.");
		}

	}

}
