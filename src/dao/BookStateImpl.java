package dao;

import java.util.List;
import java.util.Scanner;

import single.LibraryStorage;
import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class BookStateImpl implements BookState {
	Scanner sc = new Scanner(System.in);
	private List<BookManageVO> list = LibraryStorage.getInstance().getRentalList();

	public void BookState(BookManageVO vo) {
		list.add(vo);
	}
	
	

	@Override
	public void allList() {
		// �뿩 �߰ų� �뿩 ���� ���� ��� ��ü	
		System.out.println("�뿩 ���� ���� ���");
		for (BookManageVO vo : list) {
				System.out.println(vo);
		}
	}



	@Override
	public void borrowList() {
		System.out.println("�뿩 ���� ���� ���");
		for (BookManageVO vo : list) {
			if (vo.getEndDate() == null) {
				System.out.println(vo);
			}
		}

	}

	@Override
	public void returnList() {
		System.out.println("���� �ݳ� ���");
		for (BookManageVO vo : list) {
			if (vo.getEndDate() != null) {
				System.out.println(vo);
			}
		}

	}

	@Override
	public void findId() {
		System.out.print("���̵� �Է��ϼ��� > ");
		String id = sc.next();
		findId(id);
	}

	@Override
	public void findId(String id) {
		try {
			boolean exist = false;
			UserVO user = LibraryStorage.getInstance().getUser(id);
			if (user == null) {
				System.out.println("���̵� �߸� �Է��ϼ̽��ϴ�.");
				return;
			}
			System.out.println(user.getName() + "���� �뿩 ���� ���� ����� ������ �����ϴ�.");
			System.out.println("==========================");
			for (BookManageVO vo : list) {
				BookVO book = LibraryStorage.getInstance().getBook(vo);
				if (vo.getId().equals(id)) {
					System.out.println(vo);
					System.out.println("\t��" + book);
					exist = true;
				}
			}
			if (exist == false) {
				System.out.println(user.getName() + "���� �뿩 ���� ������ �����ϴ�.");
			}
		} catch (Exception e) {
			System.out.println("������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���.\n" + e.getMessage());
		}
	}

}
