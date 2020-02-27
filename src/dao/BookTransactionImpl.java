package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import single.LibraryStorage;
import single.Services;
import util.DateMaker;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class BookTransactionImpl implements BookTransaction {
	private List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
	private Scanner sc = new Scanner(System.in);
	private DateMaker dm = new DateMaker();

//�α����ߴ��� Ȯ��
	private UserVO getUser() {
		UserVO user = LibraryStorage.getInstance().getLoginMember();
		if (user == null) {
			System.out.println("�α��� ���°� �ƴմϴ�");
			return null;
		}
		return user;
	}

	@Override
	public void bookTransaction(BookManageVO vo) {
		UserVO user = getUser();
	}

//�뿩
	@Override
	public BookManageVO rentalBook() {
		String code;
		UserVO user = getUser();

		System.out.println("�뿩�� å ����� ����ּ���\n");

		System.out.println("�뿩�� å�ڵ� ?");
		code = sc.next();

		BookVO vo = readBook(code);
		if (vo == null) {
			System.out.println("�뿩�� �Ϸ�����ʾҽ��ϴ�.\n");
			return null;
		}
		// LibraryStorage.getInstance().getBookList().remove(vo);

		System.out.println("�뿩�� �Ϸ�Ǿ����ϴ�. ");

		BookManageVO rentalVO = new BookManageVO(code, user.getId(), new Date(), null);
		rentalList.add(rentalVO);
		return rentalVO;
	}

//�ݳ�
	@Override
	public BookManageVO returnBook() {
		String endDateStr;
		Date endDate;
		System.out.println("==========�ݳ�==========");

		UserVO vo = getUser();
		BookState bs = Services.getInstance().getBookState();
		System.out.println(vo);
		bs.findId(vo.getId());
		
		System.out.println("�ݳ��� å�ڵ� ?");
		String code = sc.next();
		
		System.out.println("�ݳ��� �Է�(2020-02-27)");
		endDateStr = sc.next();
		endDate = dm.toDate(endDateStr);

		return returnBook(code, endDate);
	}

	@Override
	public BookManageVO returnBook(BookManageVO vo) {
		return returnBook(vo.getIsbn13(), new Date());
	}

	@Override
	public BookManageVO returnBook(String code, Date endDate) {
		UserVO user = getUser();
		String name = user.getName();

		if (endDate == null) {
			System.out.println("��ȿ���� ���� ��¥ ������ �Է��ϼ̽��ϴ�.");
			return null;
		}

		Map<String, BookVO> bookList = LibraryStorage.getInstance().getBookList();
		for (BookManageVO vo : rentalList) {
			BookVO book = bookList.get(vo.getIsbn13());
			String bookTitle = book.getTitle();
			String isbn = vo.getIsbn13();
			String id = vo.getId();
			if (isbn.equals(code) && id.equals(user.getId())) {
				// ���� ����
				book.setAmount(book.getAmount() + 1);// ���� ����
				vo.setEndDate(endDate);
				// �ȳ� ���� ���
				System.out.printf("%s�Բ��� �������� %s å�� %s�η� �ݳ� ó���Ǿ����ϴ�.", name, bookTitle, dm.toString(endDate));
				System.out.println();
				return vo;
			}
		}
		return null;
	}

//å�ڵ��
	private BookVO readBook(String code) {
		Map<String, BookVO> list = LibraryStorage.getInstance().getBookList();
		Set<String> isbns = list.keySet();
		Iterator<String> it = isbns.iterator();
		while (it.hasNext()) {
			String isbn = it.next();
			BookVO vo = list.get(isbn);
			if (vo != null && vo.getIsbn13().equals(code)) {
				return vo;
			}
		}
		return null;
	}
}
