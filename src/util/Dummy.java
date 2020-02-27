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
		// 더미데이터 완성시키기
		list.add(new UserVO("history1", "0000", "전한길", "hangil@gmail.com"));
		list.add(new UserVO("history2", "0000", "설민석", "minseok@gmail.com"));
		list.add(new UserVO("history3", "0000", "강민성", "minseong@gmail.com"));
		list.add(new UserVO("history4", "0000", "문동균", "dong-gyun@gmail.com"));
		list.add(new UserVO("history5", "0000", "신영식", "yeongsik@gmail.com"));
		list.add(new UserVO("history6", "0000", "고종훈", "jonghoon@gmail.com"));
		list.add(new UserVO("history7", "0000", "신명섭", "myungseob@gmail.com"));
		list.add(new UserVO("korean1", "1111", "이선재", "seonjae@gmail.com"));
		list.add(new UserVO("korean2", "1111", "김병태", "byeongtae@gmail.com"));
		list.add(new UserVO("korean3", "1111", "고혜원", "hyaewon@gmail.com"));
		list.add(new UserVO("korean4", "1111", "이태종", "taejong@gmail.com"));
		list.add(new UserVO("english1", "2222", "이동기", "dong-gi@gmail.com"));
		list.add(new UserVO("english2", "2222", "심우철", "woocheol@gmail.com"));
		list.add(new UserVO("english3", "2222", "조태정", "taejeong@gmail.com"));
		list.add(new UserVO("english4", "2222", "손진숙", "jinsuk@gmail.com"));
		list.add(new UserVO("english5", "2222", "김기훈", "gihun@gmail.com"));
		list.add(new UserVO("computer", "3333", "박미진", "mijin@gmail.com"));
	}

	private void randomBook(Map<String, BookVO> bookList) {
		bookList.put("9788952753946",
				new BookVO("9788952753946", "날씨가 좋으면 찾아가겠어요", "이도우 저", "시공사", dm.toDate("20200201"), 0, "소설/시/희곡"));
		bookList.put("9788934900115", new BookVO("9788934900115", "지능의 함정", "데이비드 롭슨 저/이창신 역", "김영사",
				dm.toDate("20200113"), randomNumber(), "인문"));
		bookList.put("9791189995539", new BookVO("9791189995539", "우리가 인생이라 부르는 것들", "정재찬 저", "인플루엔셜",
				dm.toDate("20200225"), randomNumber(), "인문"));
		bookList.put("9791189584559", new BookVO("9791189584559", "죽은 철학자의 살아있는 인생수업", "시라토리 하루히코, 지지엔즈 저/김지윤 역",
				"포레스트북스", dm.toDate("20200306"), randomNumber(), "인문"));
		bookList.put("9791162242551", new BookVO("9791162242551", "개발 7년차, 매니저 1일차", "카미유 푸르니에/권원상, 한민주 역", "한빛미디어",
				dm.toDate("20200204"), randomNumber(), "IT 모바일"));
		bookList.put("9791160509762", new BookVO("9791160509762", "2020 시나공 정보처리기사 필기", "길벗알앤디 저", "길벗",
				dm.toDate("20191118"), randomNumber(), "컴퓨터 수험서"));
		bookList.put("9791190630092", new BookVO("9791190630092", "공부, 이래도 안되면 포기하세요", "이지훈 저", "위즈덤하우스",
				dm.toDate("20200220"), randomNumber(), "자기계발"));
		bookList.put("9791189584542", new BookVO("9791189584542", "돈에 강한 아이로 키우는 법", "사카이 레오 저/최말숙 역", "포레스트북스",
				dm.toDate("20200214"), randomNumber(), "가정 살림"));
		bookList.put("9791155812587", new BookVO("9791155812587", "총보다 강한 실", "카시아 세인트 클레어 저/안진이 역", "윌북(willbook)",
				dm.toDate("20200210"), randomNumber(), "역사"));
		bookList.put("9788954670630", new BookVO("9788954670630", "아직 멀었다는 말", "권여선 저", "문학동네", dm.toDate("20200214"),
				randomNumber(), "소설/시/희곡"));
		bookList.put("9791160803235",
				new BookVO("9791160803235", "인삼의 세계사", "설혜심 저", "휴머니스트", dm.toDate("20200217"), randomNumber(), "역사"));

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
