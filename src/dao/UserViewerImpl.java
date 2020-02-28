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
		// ���� ��� ��ȸ
		System.out.printf("�� %d��\n", list.size());
		for (UserVO vo : list) {
			System.out.println(vo);
		}
	}

	@Override
	public void findUserById() {
		// ���̵�� �˻��ϱ�
		String id;
		System.out.print("�˻��� ���̵� �Է� > ");
		id = sc.next();
		int cnt = 0;
		System.out.println("===�˻� ���===");
		for (UserVO vo : list) {
			if (vo.getId().indexOf(id) >= 0) {
				System.out.println(vo);
				cnt++;
			}
		}

		if (cnt > 0) {
			System.out.println("==== " + cnt + "���� ����� ������ ã�ҽ��ϴ�. ====");
		} else {
			System.out.println("����ڸ� ã�� �� �����ϴ�.");
		}

	}

}
