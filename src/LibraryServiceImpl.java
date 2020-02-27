import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import dao.UserManageImpl;
import dao.UserViewer;
import single.LibraryStorage;
import single.Services;
import util.DateMaker;
import vo.BookVO;
import vo.UserVO;

public class LibraryServiceImpl implements LibraryService {

	Scanner sc = new Scanner(System.in);
	UserViewer uv = Services.getInstance().getUserViewer();
	List<UserVO> userList = LibraryStorage.getInstance().getUserList();
	UserManageImpl um = new UserManageImpl();

	public void entrance() {
		// �α��� ���¿� ���� �޴� �ٸ��� �����ϱ�
		boolean isGoing = true;
		while (isGoing) {
			UserVO loginMember = LibraryStorage.getInstance().getLoginMember();
			try {
				if (loginMember == null) {
					// ��α��� ����
					isGoing = showDefaultMenu();
				} else if (loginMember.getId().equals(ADMIN_ID)) {
					// ������ ����
					isGoing = showAdminMenu();
				} else {
					// �Ϲ� ���� ����
					isGoing = showUserMenu();
				}
			} catch (InputMismatchException e) {
				System.out.println("���ڸ� �Է� �����մϴ�.");
				sc.nextLine();
			}
		}

	}

	@Override
	public boolean showDefaultMenu() {
		int ch;
		do {
			System.out.println("�ˢˢ� �ֿ� �������� ���Ű� ȯ���մϴ�.�ˢˢ�");
			System.out.print("1.�α��� 2.ȸ������ 3.���� > ");
			ch = sc.nextInt();
		} while (ch < 1 || ch > 3);
		if (ch == 3) {
			return false;
		}
		switch (ch) {
		case 1:// �α���
			um.login();
			break;
		case 2:// ȸ������
			um.join();
			break;
		}
		return true;
	}

	@Override
	public void login() {
		System.out.println("\n===�α���===");
		UserVO loginMember = LibraryStorage.getInstance().getLoginMember();
		String id;
		String pw;

		System.out.print("���̵� > ");
		id = sc.next();
		System.out.print("��й�ȣ > ");
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
	public void register() {
		System.out.println("\n===ȸ������===");
		// join() ����
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
				// �ӽ� �ڵ�
			Map<String, BookVO> books = LibraryStorage.getInstance().getBookList();
			Set<String> isbn13Set = books.keySet();
			System.out.println("=== ������ �������� ���� ��� === �� " + isbn13Set.size() + "��");
			Iterator<String> it = isbn13Set.iterator();
			while (it.hasNext()) {
				String isbn = it.next();
				System.out.println(books.get(isbn));
			}
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
		System.out.println("\n>>> ���Ͻô� �޴��� ���ÿ� <<<");
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
		case 4:um.update();break; //��������
		case 5:um.out();// Ż��
			break;
		case 6: // ���� �޴���
			return false;
		}
		return true;
	}

	public LibraryServiceImpl() {
		List<UserVO> list = LibraryStorage.getInstance().getUserList();
		// ���̵����� �ϼ���Ű��
		list.add(new UserVO("history1", "0000", "���ѱ�", "hangil@gmail.com"));
		list.add(new UserVO("history2", "0000", "���μ�", "minseok@gmail.com"));
		list.add(new UserVO("history3", "0000", "���μ�", "minseong@gmail.com"));
		list.add(new UserVO("history4", "0000", "������", "dong-gyun@gmail.com"));
		list.add(new UserVO("history5", "0000", "�ſ���", "yeongsik@gmail.com"));
		list.add(new UserVO("history6", "0000", "������", "jonghoon@gmail.com"));
		list.add(new UserVO("history7", "0000", "�Ÿ�", "myungseob@gmail.com"));
		list.add(new UserVO("korean1", "1111", "�̼���", "seonjae@gmail.com"));
		list.add(new UserVO("korean2", "1111", "�躴��", "byeongtae@gmail.com"));
		list.add(new UserVO("korean3", "1111", "������", "hyaewon@gmail.com"));
		list.add(new UserVO("korean4", "1111", "������", "taejong@gmail.com"));
		list.add(new UserVO("english1", "2222", "�̵���", "dong-gi@gmail.com"));
		list.add(new UserVO("english2", "2222", "�ɿ�ö", "woocheol@gmail.com"));
		list.add(new UserVO("english3", "2222", "������", "taejeong@gmail.com"));
		list.add(new UserVO("english4", "2222", "������", "jinsuk@gmail.com"));

		list.add(new UserVO("english5", "2222", "�����", "gihun@gmail.com"));
		list.add(new UserVO("computer", "3333", "�ڹ���", "mijin@gmail.com"));

		DateMaker dm = new DateMaker();
		Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
		bookList.put("9788934900115", new BookVO("9788934900115", "������ ����", "���̺�� �ӽ� ��/��â�� ��", "�迵��",
				dm.toDate("20200113"), randomNumber(), "�ι�"));
		bookList.put("9791189995539", new BookVO("9791189995539", "�츮�� �λ��̶� �θ��� �͵�", "������ ��", "���÷翣��",
				dm.toDate("20200225"), randomNumber(), "�ι�"));
		bookList.put("9791189584559", new BookVO("9791189584559", "���� ö������ ����ִ� �λ�����", "�ö��丮 �Ϸ�����, �������� ��/������ ��",
				"������Ʈ�Ͻ�", dm.toDate("20200306"), randomNumber(), "�ι�"));
		bookList.put("9791162242551", new BookVO("9791162242551", "���� 7����, �Ŵ��� 1����", "ī���� Ǫ���Ͽ�/�ǿ���, �ѹ��� ��", "�Ѻ��̵��",
				dm.toDate("20200204"), randomNumber(), "IT �����"));
		bookList.put("9791160509762", new BookVO("9791160509762", "2020 �ó��� ����ó����� �ʱ�", "����˾ص� ��", "���",
				dm.toDate("20191118"), randomNumber(), "��ǻ�� ���輭"));
		bookList.put("9788952753946", new BookVO("9788952753946", "������ ������ ã�ư��ھ��", "�̵��� ��", "�ð���",
				dm.toDate("20200201"), randomNumber(), "�Ҽ�/��/���"));
		bookList.put("9788936434267", new BookVO("9788936434267", "������ ������ ã�ư��ھ��", "�տ��� ��", "â��",
				dm.toDate("20170331"), randomNumber(), "�Ҽ�/��/���"));

	}

	public int randomNumber() {
		return (int) (Math.random() * 100);
	}

}
