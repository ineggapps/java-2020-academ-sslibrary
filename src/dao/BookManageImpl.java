package dao;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import single.LibraryStorage;
import util.DateMaker;
import vo.BookVO;

public class BookManageImpl implements BookManage {
	private Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
	Scanner sc = new Scanner(System.in);
	DateMaker dm = new DateMaker();

	@Override
	public void insertBook() {
		System.out.println("\n도서 등록...");

		try {

			BookVO vo = new BookVO();
			String isbn;
			System.out.print("등록할 도서의 ISBN(13자리)을 입력해주세요... > ");
			isbn = sc.next();
			vo.setISBN13(isbn);

			System.out.print("도서명 ? ");
			vo.setTitle(sc.nextLine());
			System.out.print("저자 ? ");
			vo.setAuthor(sc.nextLine());
			System.out.print("출판사 ? ");
			vo.setPublisher(sc.nextLine());
			System.out.print("출시일 ? ");
			Date releaseDate = dm.toDate(sc.next());
			if (releaseDate == null) {
				throw new Exception("날짜가 올바르지 않습니다. 2020-02-27 형식으로 입력해 주세요.");
			}
			vo.setReleaseDate(releaseDate);
			System.out.print("분야 ? ");
			vo.setField(sc.nextLine());
			System.out.print("등록할 도서 수량? ");
			vo.setAmount(sc.nextInt());

			bookList.put(isbn, vo);
			System.out.println("등록완료....\n");
		} catch (InputMismatchException e) {
			System.out.println("등록 실패: 숫자만 입력해 주시기 바랍니다.");
			sc.nextLine();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("등록 실패: " + e.getMessage());
			sc.nextLine();
		}
	}

	@Override
	public void updateBook() {
		System.out.println("\n도서 수정...");

		String isbn;
		System.out.println("수정할 도서의 ISBN(13자리)을 입력해주세요...");
		isbn = sc.next();

		BookVO vo = readBook(isbn);
		if (vo == null) {
			System.out.println("등록된 도서가 아닙니다.\n");
			return;
		}
		try {
			System.out.print("도서명 ? ");
			vo.setTitle(sc.nextLine());
			System.out.print("저자 ? ");
			vo.setAuthor(sc.nextLine());
			System.out.print("출판사 ? ");
			vo.setPublisher(sc.nextLine());
			System.out.print("출시일 ? ");
			vo.setReleaseDate(dm.toDate(sc.next()));
			System.out.print("분야 ? ");
			vo.setField(sc.nextLine());

			bookList.put(isbn, vo);
			System.out.println("도서 수정완료....\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBook() {
		System.out.println("\n도서 삭제...");
		String isbn;
		System.out.print("삭제할 도서의 ISBN(13자리)을 입력해주세요...");
		isbn = sc.next();
		BookVO vo = bookList.get(isbn);

		if (vo == null) {
			System.out.println("등록된 도서가 아닙니다.\n");
			return;
		}
		System.out.println(vo.getTitle() + "도서를 삭제하시겠습니까? 1.네 2.아니오");
		int ch;
		ch = sc.nextInt();
		if (ch == 1) {
			vo = bookList.remove(isbn);
			System.out.println(vo.getTitle() + "도서 삭제 완료...\n");
		} else if (ch != 1) {
			System.out.println(vo.getTitle() + "도서를 삭제하지 않습니다.");
		}

	}

	@Override
	public void findByISBN() {
		System.out.println("\nISBN(13자리)로 도서 검색...");

		String isbn;
		System.out.print("검색할 ISBN(13자리)? ");
		isbn = sc.next();

		BookVO vo = bookList.get(isbn);
		if (vo == null) {
			System.out.println("등록되지 않은 도서입니다...\n");
			return;
		}

		System.out.println("ISBN,제목,저자,출판사,출판일,수량,분야");
		System.out.print(vo.getIsbn13() + " | ");
		System.out.print(vo.getTitle() + " | ");
		System.out.print(vo.getAuthor() + " | ");
		System.out.print(vo.getPublisher() + " | ");
		System.out.print(vo.getReleaseDate() + " | ");
		System.out.print(vo.getAmount() + " | ");
		System.out.print(vo.getField() + "\n");
		System.out.println();
	}

	@Override
	public void findByTitle() {

		System.out.println("\n도서명으로 도서 검색...");
		String title;
		System.out.print("검색할 도서명? ");
		title = sc.next();

		Iterator<String> it = bookList.keySet().iterator();
		System.out.println("ISBN,제목,저자,출판사,출판일,수량,분야");
		while (it.hasNext()) {
			String isbn = it.next();
			BookVO vo = bookList.get(isbn);
			if (vo.getTitle().indexOf(title) >= 0) {
				System.out.print(vo.getIsbn13() + " | ");
				System.out.print(vo.getTitle() + " | ");
				System.out.print(vo.getAuthor() + " | ");
				System.out.print(vo.getPublisher() + " | ");
				System.out.print(dm.toString(vo.getReleaseDate()) + " | ");
				System.out.print(vo.getAmount() + " | ");
				System.out.print(vo.getField() + "\n");
			}
		}
		System.out.println();
	}

	private BookVO readBook(String ISBN13) {
		BookVO vo = null;

		Iterator<String> it = bookList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			vo = bookList.get(key);

		}
		return vo;
	}

	@Override
	public void listBook() {
		System.out.println("\n등록된 도서 목록...");
		System.out.println("등록된 도서 : " + bookList.size() + " 종");
		BookVO vo = null;
		Iterator<String> it = bookList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			vo = bookList.get(key);
			System.out.println(vo);
//			System.out.println(key + ":" + vo);
		}
		System.out.println();
	}
}
