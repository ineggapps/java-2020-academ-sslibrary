package dao;

public interface BookState {
	/**
	 * 대여, 반납 리스트 출력 (은지) BookStateImpl 구현
	 */
	public void allList();
	public void borrowList();
	public void returnList();
	public void findId(); 
	public void findId(String id);
}
