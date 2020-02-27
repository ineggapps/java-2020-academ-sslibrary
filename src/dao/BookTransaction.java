package dao;

import java.util.Date;
import java.util.List;

import vo.BookManageVO;

public interface BookTransaction {
	/** å �˻�(�������� ������ �˻�), �뿩, �ݳ� */
	//�������� ������ �˻��� ������ ������ �˻� �޼��带 �̿��Ͽ��� ������
	//������ ������ �޼��带 �̿��� ��� �������谡 �߻���.
	public void bookTransaction(BookManageVO vo);
	public BookManageVO rentalBook();
	//�����ε�
	public BookManageVO returnBook();
	public BookManageVO returnBook(BookManageVO vo);
	public BookManageVO returnBook(String code, Date endDate);
}
