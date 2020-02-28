package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
		BookManage bm = Services.getInstance().getBookManage();
		String code;
		UserVO user = getUser();

		System.out.println("�뿩�� å ����� ����ּ���\n");
		bm.listBook();

		System.out.print("�뿩�� å�ڵ� ? ");
		code = sc.next();

		BookVO vo = readBook(code);
		if (vo == null) {
			System.out.println("�ùٸ��� ���� �ڵ带 �Է��ϼ̽��ϴ�.\n");
			return null;
		}
		LibraryStorage ls = LibraryStorage.getInstance();
		BookManageVO log = ls.getRentalNotReturn(code, user.getId());
		// Case 1. �ݳ��� ������ �ٽ� �뿩�� �� ���� �ִ�.
		if (log != null && (log.getStartDate() != null && log.getEndDate() == null)) {
			System.out.println("�̹� �뿩 ���Դϴ�.");
			return null;
		}

		// ���� �ľ�
		int amount = vo.getAmount() - 1;
		if (amount < 0) {
			System.out.println("���� " + vo.getTitle() + "�� ��� å�� ���� ���Դϴ�. �ݳ��Ǵ� ��� ���� �����帮�ڽ��ϴ�.");
			return null;
		}
		System.out.println("�츮 �������� [" + vo.getTitle() + "] ������ ���� " + vo.getAmount() + "�� => " + (vo.getAmount() - 1)
				+ "���� �˴ϴ�.");
		vo.setAmount(amount);
		System.out.println("[" + vo.getTitle() + "] å �뿩�� �Ϸ�Ǿ����ϴ�. ");

		BookManageVO rentalVO = new BookManageVO(code, user.getId(), new Date(), null);
		rentalList.add(rentalVO);
		return rentalVO;
	}

//�ݳ�
	@Override
	public BookManageVO returnBook() {
		BookState bs = Services.getInstance().getBookState();

		String endDateStr;
		Date endDate;
		System.out.println("==========�ݳ�==========");

		UserVO vo = getUser();
		System.out.println(vo);
		bs.findId(vo.getId());

		System.out.print("�ݳ��� å�ڵ� ? ");
		String code = sc.next();
		BookVO book = readBook(code);
		if (book == null) {
			System.out.println("�ùٸ��� ���� �ڵ带 �Է��ϼ̽��ϴ�.\n");
			return null;
		}
		LibraryStorage ls = LibraryStorage.getInstance();
		BookManageVO log = ls.getRentalNotReturn(code, vo.getId());
		if (log == null || (log != null && log.getEndDate() != null)) {
			System.out.println("�뿩���� ���� å�̰ų� �̹� �ݳ��ϼ̽��ϴ�.");
			return null;
		}

		System.out.print("�ݳ��� �Է�(yyyy-mm-dd) > ");
		endDateStr = sc.next();
		endDate = dm.toDate(endDateStr);

		return returnBook(code, endDate);
	}

	@Override
	public BookManageVO returnBook(BookManageVO vo) {
		if (vo == null) {
			System.out.println("��ϵ��� ���� å �ڵ��Դϴ�.");
			return null;
		}
		if (vo.getEndDate() != null) {
			System.out.println("�̹� �ݳ��ϼ̽��ϴ�.");
			return vo;
		}
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
			if(!vo.getIsbn13().equals(code)) {
				//���� ���� ���� ���� ��Ȳ������ � å�� �ݳ��� ���ΰ��� �޷ȴ�.
				//���� �������� �ݳ��ϰ��� �ϴ� å�� �ƴ϶�� �̹� ��� vo�� �ǳʶڴ�.
				continue;
			}
			BookVO book = bookList.get(vo.getIsbn13());
			String bookTitle = book.getTitle();
			String isbn = vo.getIsbn13();
			String id = vo.getId();

			// �ݳ����ڰ� �뿩���ں��� ũ�� �ݳ����ְ�, ������ �ݳ� ���ϵ���.
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

			// �뿩����
			Date sday = vo.getStartDate();
			String borrowday = new SimpleDateFormat("yyyy-MM-dd").format(sday);

			// �ݳ�����
			Date eday = endDate;
			String endday = new SimpleDateFormat("yyyy-MM-dd").format(eday);

			try {
				eday = dateFormat.parse(endday);
				sday = dateFormat.parse(borrowday);

				System.out.println();
				int compare = eday.compareTo(sday);
				if (compare < 0) {
					System.out.println(dm.toString(sday) + "���� ū ��¥�� �Է��ϼ���.");
					return null;
				} else if (compare > 0) {
//					System.out.println(dm.toString(eday) + "���� �ݳ����ּ���.");
					if (isbn.equals(code) && id.equals(user.getId())) {
						// ���� ����
						book.setAmount(book.getAmount() + 1);// ���� ����
						vo.setEndDate(endDate);
						// �ȳ� ���� ���
						System.out.printf("%s�Բ��� �������� %s å�� %s�η� �ݳ� ó���Ǿ����ϴ�.", name, bookTitle, dm.toString(endDate));
						System.out.println();
						return vo;
					}
				} else {// compare���� 0�� ���
					// ���� ����
					book.setAmount(book.getAmount() + 1);// ���� ����
					vo.setEndDate(endDate);
					// �ȳ����� ���
					System.out.println("���� �ݳ��ϼ̽��ϴ�. (" + dm.toString(endDate) + ")");
					return vo;
				}

			} catch (ParseException e) {
				e.printStackTrace();
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
