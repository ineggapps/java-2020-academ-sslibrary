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
		System.out.println("ȸ������");
		// id(Ű), pw, name, email
		// id(Ű) �ߺ�üũ

		UserVO vo = new UserVO();
		String id;
		System.out.print("���̵� �Է� >");
		id = sc.next();
		vo.setId(id);

		if (LibraryStorage.getInstance().getUser(id) != null) {
			System.out.println("��ϵ� ���̵� �Դϴ� �Ф�. \n");
			return;
		}

		try {

			System.out.print("�̸� �Է� >");
			vo.setName(sc.next());

			/*
			 * System.out.print("���̵� �Է� >"); vo.setId(sc.next());
			 */

			System.out.print("��й�ȣ �Է� >");
			vo.setPw(sc.next());

			System.out.print("�̸��� �Է� >");
			vo.setEmail(sc.next());

			LibraryStorage.getInstance().getUserList().add(vo);
			System.out.println("��> ȸ�������� �Ϸ� �Ǿ����ϴ�. <��");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void login() {
		System.out.println("\n===�α���===");
		UserVO loginMember = LibraryStorage.getInstance().getLoginMember();
		String id;
		String pw;

		System.out.print("���̵� �Է� ? ");
		id = sc.next();
		System.out.print("��й�ȣ �Է� ? ");
		pw = sc.next();

		System.out.println(id + ", " + pw);
		if (id.equals(ADMIN_ID) && pw.equals(ADMIN_PW)) {
			System.out.println("�����ڷ� �α����ϼ̽��ϴ�!");
			LibraryStorage.getInstance().setLoginMember(new UserVO(ADMIN_ID, ADMIN_PW, "������"));
			return;
		}

		// �Ϲ� ���� �α���
		UserVO vo = LibraryStorage.getInstance().getUser(id);
		if (vo == null) {
			System.out.println("�������� �ʴ� �����Դϴ�.");
			return;
		} else if (!pw.equals(vo.getPw())) {
			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			return;
		}

		LibraryStorage.getInstance().setLoginMember(vo);
		System.out.println("�α����ϼ̽��ϴ�.");
	}

	@Override
	public void logout() {
		LibraryStorage.getInstance().setLoginMember(null);
		System.out.println("�����ϰ� �α׾ƿ� �Ǿ����ϴ�.");
	}

	@Override
	public void update() {
		System.out.println("ȸ������ ���� ����...\n");

		String id;
		String pw;

		System.out.println("=========���� Ȯ�� ========");
		System.out.print("���̵� �Է�>");
		id = sc.next();

		System.out.print("��й�ȣ �Է�>");
		pw = sc.next();

		UserVO vo = LibraryStorage.getInstance().getUser(id);

		if (vo == null || !vo.getPw().equals(pw) || !vo.getId().equals(id)) {
			System.out.println("���̵�� ��й�ȣ�� ��ġ ���� �ʾƿ� �Ф�...\n");
			return;
		}

		System.out.print("������ ��й�ȣ >");
		vo.setPw(sc.next());

		System.out.print("������ �̸� >");
		vo.setName(sc.next());

		System.out.print("������ �̸��� >");
		vo.setEmail(sc.next());

		System.out.println("������" + vo.getName() + "���� ���� : " + vo.toStringWithPassword());
		System.out.println(vo.getName() + "���� ���� ������ �Ϸ� �Ǿ����ϴ�.! \n");
	}

	@Override
	public void out() {
		String id;
		String pw;

		System.out.print("���̵� �Է� >");
		id = sc.next();

		System.out.print("��� ��ȣ �Է� >");
		pw = sc.next();

		UserVO vo = LibraryStorage.getInstance().getUser(id);
		if (vo == null || !vo.getPw().equals(pw) || !vo.getId().equals(id)) {
			System.out.println("���̵�� ��й�ȣ�� ��ġ ���� �ʾƿ� �Ф�...\n");
			return;
		}

		LibraryStorage.getInstance().getUserList().remove(vo);
		System.out.println(vo.getName() + " ȸ���� Ż�� �Ǽ̽��ϴ�...\n");
		logout();
	}

}
