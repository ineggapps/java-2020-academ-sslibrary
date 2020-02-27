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
import util.Dummy;
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
			System.out.print("1.���������ȸ 2. ������� 3.�������� 4.�������� 5.�����˻� 6. �뿩�����ȸ 7. �����޴� > ");
			ch = sc.nextInt();
			switch (ch) {
			case 1:// ���������ȸ
				bm.listBook();
			case 2:// �������
				bm.insertBook();
				break;
			case 3:// ��������
				bm.updateBook();
				break;
			case 4:// ��������
				bm.deleteBook();
				break;
			case 5:// �����˻�
				System.out.print("1.ISBN�˻� 2.å���� �˻� > ");
				ch = sc.nextInt();
				switch (ch) {
				case 1:// ISBN�˻�
					bm.findByISBN();
					break;
				case 2:// å ���� �˻�
					bm.findByTitle();
					break;
				default:
					System.out.println("ERR: �߸� �Է��ϼ̽��ϴ�.");
					break;
				}
				break;
			case 6:// �뿩��� ��ȸ
				System.out.print("1.�̹ݳ��� ��ȸ 2.�ݳ��� ������ȸ 3.��ü��ȸ > ");
				ch = sc.nextInt();
				switch (ch) {
				case 1:
					bs.borrowList();
					break;
				case 2:
					bs.returnList();
					break;
				case 3:
					bs.allList();
					break;
				default:
					System.out.println("ERR: �߸� �Է��ϼ̽��ϴ�.");
					break;
				}
				break;
			case 7:
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
		// ������ ȣ���� ���� ��ü�������� ����
		List<UserVO> userList = LibraryStorage.getInstance().getUserList();
		Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
		List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
		// ���̵����� �����ϱ�
		new Dummy(userList, bookList, rentalList);
	}

}
