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
 * 싱글톤으로 관리하는 이유 BookManage, BookTransaction, UserManage, UserViewer
 * 
 * 1. 위의 클래스를 객체 1개만 생성하여 관리하기 위함 - 실제로 프로그램을 실행하면서 단 1개의 객체만 필요하기 때문에 2. 추후
 * BookManage에서 구현된 기능을 BookTransaction에서 쓸 수도 있음. - 생성자를 통해서 인자를 넘기는 것 대신에
 * 싱글톤으로 구현하여 간편하게 불러오도록 하기로 함.
 */

public class Services {
	private Services() {
		// 생성자 건드리지 못하게 제한
	}

	// 서비스
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
