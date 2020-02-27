package dao;

import java.util.Date;
import java.util.List;

import vo.BookManageVO;

public interface BookTransaction {
	/** 책 검색(관리자의 도서명 검색), 대여, 반납 */
	//관리자의 도서명 검색은 다혜의 도서명 검색 메서드를 이용하여도 무방함
	//다혜의 도서명 메서드를 이용할 경우 의존관계가 발생함.
	public void bookTransaction(BookManageVO vo);
	public BookManageVO rentalBook();
	//오버로딩
	public BookManageVO returnBook();
	public BookManageVO returnBook(BookManageVO vo);
	public BookManageVO returnBook(String code, Date endDate);
}
