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
		System.out.println("\n���� ���...");

		try {

			BookVO vo = new BookVO();
			String isbn;
			System.out.print("����� ������ ISBN(13�ڸ�)�� �Է����ּ���... > ");
			isbn = sc.next();
			vo.setISBN13(isbn);

			System.out.print("������ ? ");
			vo.setTitle(sc.nextLine());
			System.out.print("���� ? ");
			vo.setAuthor(sc.nextLine());
			System.out.print("���ǻ� ? ");
			vo.setPublisher(sc.nextLine());
			System.out.print("����� ? ");
			Date releaseDate = dm.toDate(sc.next());
			if (releaseDate == null) {
				throw new Exception("��¥�� �ùٸ��� �ʽ��ϴ�. 2020-02-27 �������� �Է��� �ּ���.");
			}
			vo.setReleaseDate(releaseDate);
			System.out.print("�о� ? ");
			vo.setField(sc.nextLine());
			System.out.print("����� ���� ����? ");
			vo.setAmount(sc.nextInt());

			bookList.put(isbn, vo);
			System.out.println("��ϿϷ�....\n");
		} catch (InputMismatchException e) {
			System.out.println("��� ����: ���ڸ� �Է��� �ֽñ� �ٶ��ϴ�.");
			sc.nextLine();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("��� ����: " + e.getMessage());
			sc.nextLine();
		}
	}

	@Override
	public void updateBook() {
		System.out.println("\n���� ����...");

		String isbn;
		System.out.println("������ ������ ISBN(13�ڸ�)�� �Է����ּ���...");
		isbn = sc.next();

		BookVO vo = readBook(isbn);
		if (vo == null) {
			System.out.println("��ϵ� ������ �ƴմϴ�.\n");
			return;
		}
		try {
			System.out.print("������ ? ");
			vo.setTitle(sc.nextLine());
			System.out.print("���� ? ");
			vo.setAuthor(sc.nextLine());
			System.out.print("���ǻ� ? ");
			vo.setPublisher(sc.nextLine());
			System.out.print("����� ? ");
			vo.setReleaseDate(dm.toDate(sc.next()));
			System.out.print("�о� ? ");
			vo.setField(sc.nextLine());

			bookList.put(isbn, vo);
			System.out.println("���� �����Ϸ�....\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBook() {
		System.out.println("\n���� ����...");
		String isbn;
		System.out.print("������ ������ ISBN(13�ڸ�)�� �Է����ּ���...");
		isbn = sc.next();
		BookVO vo = bookList.get(isbn);

		if (vo == null) {
			System.out.println("��ϵ� ������ �ƴմϴ�.\n");
			return;
		}
		System.out.println(vo.getTitle() + "������ �����Ͻðڽ��ϱ�? 1.�� 2.�ƴϿ�");
		int ch;
		ch = sc.nextInt();
		if (ch == 1) {
			vo = bookList.remove(isbn);
			System.out.println(vo.getTitle() + "���� ���� �Ϸ�...\n");
		} else if (ch != 1) {
			System.out.println(vo.getTitle() + "������ �������� �ʽ��ϴ�.");
		}

	}

	@Override
	public void findByISBN() {
		System.out.println("\nISBN(13�ڸ�)�� ���� �˻�...");

		String isbn;
		System.out.print("�˻��� ISBN(13�ڸ�)? ");
		isbn = sc.next();

		BookVO vo = bookList.get(isbn);
		if (vo == null) {
			System.out.println("��ϵ��� ���� �����Դϴ�...\n");
			return;
		}

		System.out.println("ISBN,����,����,���ǻ�,������,����,�о�");
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

		System.out.println("\n���������� ���� �˻�...");
		String title;
		System.out.print("�˻��� ������? ");
		title = sc.next();

		Iterator<String> it = bookList.keySet().iterator();
		System.out.println("ISBN,����,����,���ǻ�,������,����,�о�");
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
		System.out.println("\n��ϵ� ���� ���...");
		System.out.println("��ϵ� ���� : " + bookList.size() + " ��");
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
