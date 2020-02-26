package single;

import dao.BookManage;
import dao.BookState;
import dao.BookTransaction;
import dao.UserManage;
import dao.UserViewer;
import dao.UserViewerImpl;

/**
 * �̱������� �����ϴ� ����
 * BookManage, BookTransaction, UserManage, UserViewer 
 * 
 * 1. ���� Ŭ������ ��ü 1���� �����Ͽ� �����ϱ� ����
 * - ������ ���α׷��� �����ϸ鼭 �� 1���� ��ü�� �ʿ��ϱ� ������
 * 2. ���� BookManage���� ������ ����� BookTransaction���� �� ���� ����.
 * - �����ڸ� ���ؼ� ���ڸ� �ѱ�� �� ��ſ� �̱������� �����Ͽ� �����ϰ� �ҷ������� �ϱ�� ��.
 * */

public class Services {
	private Services() {
		// ������ �ǵ帮�� ���ϰ� ����
	}
	
	//����
	BookManage bookManage;
	BookState bookState;

	UserViewer userViewer = new UserViewerImpl();
	UserManage userManage;
	
	BookTransaction bookTransaction;
	
	//Getter
	public UserViewer getUserViewer() {
		return userViewer;
	}

	private static class Holder {
		public static final Services INSTANCE = new Services();
	}

	public static final Services getInstance() {
		return Holder.INSTANCE;
	}
}
