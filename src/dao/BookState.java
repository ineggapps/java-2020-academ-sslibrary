package dao;

public interface BookState {
	/**
	 * �뿩, �ݳ� ����Ʈ ��� (����) BookStateImpl ����
	 */
	public void allList();
	public void borrowList();
	public void returnList();
	public void findId(); 
	public void findId(String id);
}
