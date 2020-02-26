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

		System.out.println("===�˻� ���===");
		for (UserVO vo : list) {
			if (id.equals(vo.getId())) {
				System.out.println(vo);
				return;
			}
		}

		System.out.println("����ڸ� ã�� �� �����ϴ�.");

	}

}
