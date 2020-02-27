package dao;

import java.util.List;
import java.util.Scanner;

import single.LibraryStorage;
import vo.BookManageVO;
import vo.UserVO;

public class BookStateImpl implements BookState {
	Scanner sc = new Scanner(System.in);
	private List<BookManageVO> list = LibraryStorage.getInstance().getRentalList();
	public void BookState(BookManageVO vo) {
		list.add(vo);
	}

	@Override
	public void borrowList() {
		System.out.println("대여 도서 목록");
		for (BookManageVO vo : list) {
			if (vo.getEndDate() == null) {
				System.out.println(vo);
			}
		}

	}

	@Override
	public void returnList() {
		System.out.println("도서 반납 목록");
		for (BookManageVO vo : list) {
			if (vo.getEndDate() != null) {
				System.out.println(vo);
			}
		}

	}

	@Override
	public void findId() {
		boolean exist = false;
		String id;
		try {
			System.out.print("아이디를 입력하세요 > ");
			id = sc.nextLine();
			UserVO user = LibraryStorage.getInstance().getUser(id);
			if(user==null) {
				System.out.println("아이디를 잘못 입력하셨습니다.");
				return;
			}
			System.out.println(user.getName()+"님의 대여 중인 도서 목록은 다음과 같습니다.");
			System.out.println("==========================");
			for (BookManageVO vo : list) {
				if (vo.getId().equals(id)) {
					System.out.println(vo);
					exist = true;
				}
			}
			if(exist==false){
				System.out.println(user.getName() + "님은 대여 중인 도서가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("너 설마 오류?");
		} 

	}

}
