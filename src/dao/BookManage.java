package dao;

import java.util.List;

import vo.BookVO;

public interface BookManage {

	public void insertBook();
	public void updateBook();
	public void deleteBook();
	public void findByISBN();
	public void findByTitle();
	public void listBook();
	
	
	
	/**
	 * ���� �߰�, ����, ����, �ڵ� �˻�, ������ �˻� (����)
	 */
}
