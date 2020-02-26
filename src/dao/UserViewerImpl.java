package dao;

import java.util.List;

import db.LibraryStorage;
import vo.UserVO;

public class UserViewerImpl implements UserViewer {

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
	}

}
