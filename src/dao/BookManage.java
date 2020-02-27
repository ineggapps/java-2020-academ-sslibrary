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
	 * 도서 추가, 수정, 삭제, 코드 검색, 도서명 검색 (다혜)
	 */
}
