package dao;

import java.util.Date;

import vo.BookManageVO;

public interface BookTransaction {
	/** å �˻�(�������� ������ �˻�), �뿩, �ݳ� */
	//�뿩 ��: BookManager���� ���� ��� ���
	//�ݳ� ��: BookState���� �뿩 ���� ���� ��ϸ� ���
	public BookManageVO rentalBook();
	//�����ε�
	public BookManageVO returnBook();
	public BookManageVO returnBook(BookManageVO vo);
	public BookManageVO returnBook(String code, Date endDate);
}
