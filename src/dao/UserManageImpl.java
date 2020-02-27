package dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import single.LibraryStorage;
import vo.UserVO;


public class UserManageImpl implements UserManage{
	private Scanner sc = new Scanner(System.in);
	private Map<String, UserVO> map = new HashMap<>();
	
	@Override
	public void join() {
		System.out.println("ȸ������");
		// id(Ű), pw, name, email
		// id(Ű) �ߺ�üũ
		
		String id;
		System.out.print("���̵� �Է� >");
		id = sc.next();
		
		if(map.containsKey(id)) {
			System.out.println("��ϵ� ���̵� �Դϴ� �Ф�. \n");
			return;
		}
		
		try {
			UserVO vo = new UserVO();
			
			System.out.print("�̸� �Է� >");
			vo.setName(sc.next());
			
			/*	
			System.out.print("���̵� �Է� >");
			vo.setId(sc.next());
			*/
			
			System.out.print("��й�ȣ �Է� >");
			vo.setPw(sc.next());
			
			System.out.print("�̸��� �Է� >");
			vo.setEmail(sc.next());
			
			map.put(id, vo);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		
	}

}
