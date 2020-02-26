import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.UserViewer;
import dao.UserViewerImpl;
import single.LibraryStorage;
import single.Services;
import vo.UserVO;

public class LibraryServiceImpl implements LibraryService {

	Scanner sc = new Scanner(System.in);
	UserViewer uv = Services.getInstance().getUserViewer();
	List<UserVO> userList = LibraryStorage.getInstance().getUserList();

	public void entrance() {
		Map<String, UserVO> session = LibraryStorage.getInstance().getSession();
		// �α��� ���¿� ���� �޴� �ٸ��� �����ϱ�
		boolean isGoing = true;
		while (isGoing) {
			if (session == null || session.size() == 0) {
				// ��α��� ����
				isGoing = showDefaultMenu();
			} else if (session.containsKey(ADMIN_ID)) {
				// ������ ����
				isGoing = showAdminMenu();
			} else {
				// �Ϲ� ���� ����
				isGoing = showUserMenu();
			}
		}

	}

	@Override
	public boolean showDefaultMenu() {
		int ch;
		do {
			System.out.print("1.�α��� 2.ȸ������ 3.���� > ");
			ch = sc.nextInt();
		} while (ch < 1 || ch > 3);
		if (ch == 3) {
			return false;
		}
		switch (ch) {
		case 1:// �α���
			login();
			break;
		case 2:// ȸ������
			register();
			break;
		}
		return true;
	}

	@Override
	public void login() {
		System.out.println("\n===�α���===");
		Map<String, UserVO> session = LibraryStorage.getInstance().getSession();
		String id;
		String pw;

		System.out.print("���̵� �Է� ? ");
		id = sc.next();
		System.out.print("��й�ȣ �Է� ? ");
		pw = sc.next();

		System.out.println(id + ", " + pw);
		if (id.equals(ADMIN_ID) && pw.equals(ADMIN_PW)) {
			System.out.println("�����ڷ� �α����ϼ̽��ϴ�!");
			session.put(ADMIN_ID, new UserVO(ADMIN_ID, ADMIN_PW, "������"));
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

		session.put(id, vo);
		System.out.println("�α����ϼ̽��ϴ�.");
	}

	@Override
	public void logout() {
		LibraryStorage.getInstance().getSession().clear();
		System.out.println("�����ϰ� �α׾ƿ� �Ǿ����ϴ�.");
	}

	@Override
	public void register() {
		System.out.println("\n===ȸ������===");

		System.out.println("ȸ������ �Ϸ�!");
	}

	@Override
	public boolean showAdminMenu() {
		int ch;
		System.out.println("\n===������ �޴�===");
		System.out.print("1.�������� 2.ȸ������ 3.�α׾ƿ� > ");
		ch = sc.nextInt();
		switch (ch) {
		case 1:// ��������
			break;
		case 2:// ȸ������
			System.out.print("1.ȸ����� 2.���̵�˻� 3.�����޴� > ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:// ȸ�����
				uv.printUsers();
				break;
			case 2:// ���̵� �˻�
				uv.findUserById();
				break;
			}
			break;
		case 3:// �α׾ƿ�
			logout();
			break;
		case 4:// ���� �޴���
			return false;
		}
		return true;
	}

	@Override
	public boolean showUserMenu() {
		System.out.println("\n===���Ͻô� �޴��� ���ÿ�===");
		int ch;
		System.out.println("\n===����� �޴�===");
		System.out.print("1.�����뿩 2.�����˻� 3.�α׾ƿ� 4.�������� 5.Ż�� 6.�����޴�");
		ch = sc.nextInt();
		switch (ch) {
		case 1:// �����뿩
			break;
		case 2:// �����˻�
			break;
		case 3:// �α׾ƿ�
			logout();
			break;
		case 4:// ��������
			break;
		case 5:// Ż��
			break;
		case 6: // ���� �޴���
			return false;
		}
		return true;
	}

	public LibraryServiceImpl() {
		List<UserVO> list = LibraryStorage.getInstance().getUserList();
		// ���̵����� �ϼ���Ű��
		list.add(new UserVO("history1", "0000", "���ѱ�"));
		list.add(new UserVO("history2", "0000", "���μ�"));
		list.add(new UserVO("history3", "0000", "���μ�"));
		list.add(new UserVO("history4", "0000", "������"));
		list.add(new UserVO("history5", "0000", "�ſ���"));
		list.add(new UserVO("history6", "0000", "������"));
		list.add(new UserVO("history7", "0000", "�Ÿ�"));
		list.add(new UserVO("korean1", "1111", "�̼���"));
		list.add(new UserVO("korean2", "1111", "�躴��"));
		list.add(new UserVO("korean3", "1111", "������"));
		list.add(new UserVO("korean4", "1111", "������"));
		list.add(new UserVO("english1", "2222", "�̵���"));
		list.add(new UserVO("english2", "2222", "�ɿ�ö"));
		list.add(new UserVO("english3", "2222", "������"));
		list.add(new UserVO("english4", "2222", "������"));
		list.add(new UserVO("english5", "2222", "�����"));
		list.add(new UserVO("computer", "3333", "�ڹ���"));
	}

}
