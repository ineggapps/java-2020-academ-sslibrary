package dao;

import java.util.Date;

import vo.BookManageVO;

public interface BookTransaction {
	/** 책 검색(관리자의 도서명 검색), 대여, 반납 */
	//대여 시: BookManager에서 도서 목록 출력
	//반납 시: BookState에서 대여 중인 도서 목록만 출력
	public BookManageVO rentalBook();
	//오버로딩
	public BookManageVO returnBook();
	public BookManageVO returnBook(BookManageVO vo);
	public BookManageVO returnBook(String code, Date endDate);
}
