package dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import single.LibraryStorage;
import util.DateMaker;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class BookTransactionImpl implements BookTransaction {
	private List<BookManageVO> rentalList = LibraryStorage.getInstance().getRentalList();
	private Scanner sc = new Scanner(System.in);

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

		System.out.println("�뿩 ?\n");

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
		UserVO user = getUser();
		String endDateStr;
		Date endDate;
		System.out.println("�ݳ�?");
		String code;
		System.out.println("�ݳ��� å�ڵ� ?");
		code = sc.next();

		System.out.println("�ݳ��� �Է�(2020-02-27)");
		endDateStr = sc.next();

		DateMaker dm = new DateMaker();
		endDate = dm.toDate(endDateStr);

		if (endDate == null) {
			System.out.println("��ȿ���� ���� ��¥ ������ �Է��ϼ̽��ϴ�.");
			return null;
		}

		for (BookManageVO vo : rentalList) {
			String isbn = vo.getIsbn13();
			String id = vo.getId();
			if (isbn.equals(code) && id.equals(user.getId())) {
				vo.setEndDate(endDate);
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
