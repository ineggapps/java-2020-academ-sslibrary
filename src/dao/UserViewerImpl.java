package dao;

import java.util.List;

import db.LibraryStorage;
import vo.UserVO;

public class UserViewerImpl implements UserViewer {

	List<UserVO> list = LibraryStorage.getInstance().getUserList();

	@Override
	public void printUsers() {
		// ���� ��� ��ȸ
		System.out.printf("�� %d��\n", list.size());
		for (UserVO vo : list) {
			System.out.println(vo);
		}
	}

	@Override
	public void findUserById() {
		// ���̵�� �˻��ϱ�
	}

}
