package dao;

import java.util.List;
import java.util.Scanner;

import single.LibraryStorage;
import vo.BookManageVO;
import vo.UserVO;

public class BookStateImpl implements BookState {
	private List<BookManageVO> list = LibraryStorage.getInstance().getRentalList();
	public void BookState(BookManageVO vo) {
		list.add(vo);
	}

	@Override
	public void borrowList() {
		System.out.println("�뿩 ���� ���");
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
		Scanner sc = new Scanner(System.in);
		boolean exist = false;
		String id;
		try {
			System.out.println("���̵� �Է��ϼ���");
			id = sc.nextLine();
			System.out.println(id);
			for (BookManageVO vo : list) {
				System.out.println(vo);
				if (vo.getId().equals(id)) {
					exist = true;
				}
			}
			if(exist==false){
				UserVO user = LibraryStorage.getInstance().getUser(id);
				System.out.println(user.getName() + "���� �뿩 ���� ������ �����ϴ�.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�� ���� ����?");
		} finally {
			sc.close();
		}

	}

}
