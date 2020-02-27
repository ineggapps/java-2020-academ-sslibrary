package single;

import dao.BookManage;
import dao.BookManageImpl;
import dao.BookState;
import dao.BookStateImpl;
import dao.BookTransaction;
import dao.BookTransactionImpl;
import dao.UserManage;
import dao.UserManageImpl;
import dao.UserViewer;
import dao.UserViewerImpl;

/**
 * �̱������� �����ϴ� ���� BookManage, BookTransaction, UserManage, UserViewer
 * 
 * 1. ���� Ŭ������ ��ü 1���� �����Ͽ� �����ϱ� ���� - ������ ���α׷��� �����ϸ鼭 �� 1���� ��ü�� �ʿ��ϱ� ������ 2. ����
 * BookManage���� ������ ����� BookTransaction���� �� ���� ����. - �����ڸ� ���ؼ� ���ڸ� �ѱ�� �� ��ſ�
 * �̱������� �����Ͽ� �����ϰ� �ҷ������� �ϱ�� ��.
 */

public class Services {
	private Services() {
		// ������ �ǵ帮�� ���ϰ� ����
	}

	// ����
	private BookState bookState = new BookStateImpl();
	private BookManage bookManage = new BookManageImpl();

	private UserViewer userViewer = new UserViewerImpl();
	private UserManage userManage = new UserManageImpl();

	private BookTransaction bookTransaction = new BookTransactionImpl();

	// Getter
	public UserViewer getUserViewer() {
		return userViewer;
	}

	public UserManage getUserManage() {
		return userManage;
	}

	
	public BookState getBookState() {
		return bookState;
	}

	public BookManage getBookManage() {
		return bookManage;
	}

	public BookTransaction getBookTransaction() {
		return bookTransaction;
	}

	private static class Holder {
		public static final Services INSTANCE = new Services();
	}

	public static final Services getInstance() {
		return Holder.INSTANCE;
	}
}
