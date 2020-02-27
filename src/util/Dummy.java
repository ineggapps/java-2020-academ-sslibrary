package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import vo.BookManageVO;
import vo.BookVO;
import vo.UserVO;

public class Dummy {
	DateMaker dm = new DateMaker();

	public Dummy(List<UserVO> userList, Map<String, BookVO> bookList, List<BookManageVO> rentalList) {
		randomUser(userList);
		randomBook(bookList);
		randomRental(rentalList);
	}

	private void randomUser(List<UserVO> list) {
		// ���̵����� �ϼ���Ű��
		list.add(new UserVO("history1", "0000", "���ѱ�", "hangil@gmail.com"));
		list.add(new UserVO("history2", "0000", "���μ�", "minseok@gmail.com"));
		list.add(new UserVO("history3", "0000", "���μ�", "minseong@gmail.com"));
		list.add(new UserVO("history4", "0000", "������", "dong-gyun@gmail.com"));
		list.add(new UserVO("history5", "0000", "�ſ���", "yeongsik@gmail.com"));
		list.add(new UserVO("history6", "0000", "������", "jonghoon@gmail.com"));
		list.add(new UserVO("history7", "0000", "�Ÿ�", "myungseob@gmail.com"));
		list.add(new UserVO("korean1", "1111", "�̼���", "seonjae@gmail.com"));
		list.add(new UserVO("korean2", "1111", "�躴��", "byeongtae@gmail.com"));
		list.add(new UserVO("korean3", "1111", "������", "hyaewon@gmail.com"));
		list.add(new UserVO("korean4", "1111", "������", "taejong@gmail.com"));
		list.add(new UserVO("english1", "2222", "�̵���", "dong-gi@gmail.com"));
		list.add(new UserVO("english2", "2222", "�ɿ�ö", "woocheol@gmail.com"));
		list.add(new UserVO("english3", "2222", "������", "taejeong@gmail.com"));
		list.add(new UserVO("english4", "2222", "������", "jinsuk@gmail.com"));
		list.add(new UserVO("english5", "2222", "�����", "gihun@gmail.com"));
		list.add(new UserVO("computer", "3333", "�ڹ���", "mijin@gmail.com"));
	}

	private void randomBook(Map<String, BookVO> bookList) {
		bookList.put("9788952753946",
				new BookVO("9788952753946", "������ ������ ã�ư��ھ��", "�̵��� ��", "�ð���", dm.toDate("20200201"), 0, "�Ҽ�/��/���"));
		bookList.put("9788934900115", new BookVO("9788934900115", "������ ����", "���̺�� �ӽ� ��/��â�� ��", "�迵��",
				dm.toDate("20200113"), randomNumber(), "�ι�"));
		bookList.put("9791189995539", new BookVO("9791189995539", "�츮�� �λ��̶� �θ��� �͵�", "������ ��", "���÷翣��",
				dm.toDate("20200225"), randomNumber(), "�ι�"));
		bookList.put("9791189584559", new BookVO("9791189584559", "���� ö������ ����ִ� �λ�����", "�ö��丮 �Ϸ�����, �������� ��/������ ��",
				"������Ʈ�Ͻ�", dm.toDate("20200306"), randomNumber(), "�ι�"));
		bookList.put("9791162242551", new BookVO("9791162242551", "���� 7����, �Ŵ��� 1����", "ī���� Ǫ���Ͽ�/�ǿ���, �ѹ��� ��", "�Ѻ��̵��",
				dm.toDate("20200204"), randomNumber(), "IT �����"));
		bookList.put("9791160509762", new BookVO("9791160509762", "2020 �ó��� ����ó����� �ʱ�", "����˾ص� ��", "���",
				dm.toDate("20191118"), randomNumber(), "��ǻ�� ���輭"));
		bookList.put("9791190630092", new BookVO("9791190630092", "����, �̷��� �ȵǸ� �����ϼ���", "������ ��", "������Ͽ콺",
				dm.toDate("20200220"), randomNumber(), "�ڱ���"));
		bookList.put("9791189584542", new BookVO("9791189584542", "���� ���� ���̷� Ű��� ��", "��ī�� ���� ��/�ָ��� ��", "������Ʈ�Ͻ�",
				dm.toDate("20200214"), randomNumber(), "���� �츲"));
		bookList.put("9791155812587", new BookVO("9791155812587", "�Ѻ��� ���� ��", "ī�þ� ����Ʈ Ŭ���� ��/������ ��", "����(willbook)",
				dm.toDate("20200210"), randomNumber(), "����"));
		bookList.put("9788954670630", new BookVO("9788954670630", "���� �־��ٴ� ��", "�ǿ��� ��", "���е���", dm.toDate("20200214"),
				randomNumber(), "�Ҽ�/��/���"));
		bookList.put("9791160803235",
				new BookVO("9791160803235", "�λ��� �����", "������ ��", "�޸ӴϽ�Ʈ", dm.toDate("20200217"), randomNumber(), "����"));

	}

	private void randomRental(List<BookManageVO> rentalList) {
		rentalList.add(new BookManageVO("9791160509762", "history1", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791160509762", "history2", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791160509762", "history3", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791189995539", "history1", dm.toDate("20200207"), null));
		rentalList.add(new BookManageVO("9791189995539", "history1", dm.toDate("20200207"), null));
	}

	public int randomNumber() {
		return (int) (Math.random() * 100);
	}
}
