package dao;

import java.util.List;

import vo.BookManageVO;

public interface BookTransaction {
	/** å �˻�(�������� ������ �˻�), �뿩, �ݳ� */
	//�������� ������ �˻��� ������ ������ �˻� �޼��带 �̿��Ͽ��� ������
	//������ ������ �޼��带 �̿��� ��� �������谡 �߻���.
	public void bookTransaction(BookManageVO vo);
	public BookManageVO rentalBook();
	public BookManageVO returnBook();

}
