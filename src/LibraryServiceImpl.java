import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.BookManage;
import dao.BookState;
import dao.BookTransaction;
import dao.UserManage;
import dao.UserViewer;
import single.LibraryStorage;
import single.Services;
import util.DateMaker;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class LibraryServiceImpl implements LibraryService {

	Scanner sc = new Scanner(System.in);
	BookManage bm = Services.getInstance().getBookManage();
	BookState bs = Services.getInstance().getBookState();
	BookTransaction bt = Services.getInstance().getBookTransaction();
	UserViewer uv = Services.getInstance().getUserViewer();
	UserManage um = Services.getInstance().getUserManage();
	List<UserVO> userList = LibraryStorage.getInstance().getUserList();
	DateMaker dm = new DateMaker();

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
	public boolean showAdminMenu() {
		int ch;
		System.out.println("\n===������ �޴�===");
		System.out.print("1.�������� 2.ȸ������ 3.�α׾ƿ� > ");
		ch = sc.nextInt();
		switch (ch) {
		case 1:
			System.out.print("1.������� 2.�������� 3.�������� 4.ISBN���ΰ˻� 5.���������ΰ˻� 6.�����ѵ�������Ʈ 7.����=> ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				bm.insertBook();
				break;
			case 2:
				bm.updateBook();
				break;
			case 3:
				bm.deleteBook();
				break;
			case 4:
				bm.findByISBN();
				break;
			case 5:
				bm.findByTitle();
				break;
			case 6:
				bm.listBook();
				break;
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
			um.logout();
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
		System.out.print("1.�����뿩 2.�����ݳ� 3.�����˻� 4.�α׾ƿ� 5.�������� 6.Ż�� 7.�����޴� > ");
		ch = sc.nextInt();
		switch (ch) {
		case 1:// �����뿩
			bt.rentalBook();
			break;
		case 2:// �����ݳ�
			bt.returnBook();
			break;
		case 3:// �����˻�
			System.out.print("1.��ü �˻� 2.������ �˻� 3.ISBN �˻� 4.���� �޴�> ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:// ��ü ���
				bm.listBook();
				break;
			case 2:// ������ �˻�
				bm.findByTitle();
				break;
			case 3:// �����ڵ� �˻�
				bm.findByISBN();
				break;
			default:
				System.out.println("�߸� �����ϼ̽��ϴ�.");
			}
			break;
		case 4:// �α׾ƿ�
			um.logout();
			break;
		case 5:
			um.update();
			break; // ��������
		case 6:
			um.out();// Ż��
			break;
		case 7: // ���� �޴���
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
				dm.toDate("20200201"), 0, "�Ҽ�/��/���"));

		List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
		rentalList.add(new BookManageVO("9791160509762", "history1", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791160509762", "history2", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791160509762", "history3", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791189995539", "history1", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791189995539", "history1", dm.toDate("20200207"), null));
	}

	public int randomNumber() {
		return (int) (Math.random() * 100);
	}

}
