package util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMaker {
	public Date toDate(String strDate) {
		Date date = null;
		try {
			strDate = strDate.replaceAll("(\\-|\\.|/)", "");
			if (strDate.length() != 8) {
				return null;
			}

			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			date = f.parse(strDate);
			return date;
		} catch (Exception e) {
			System.out.println("Date ��ȯ ����");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public String toString(Date date) {
		String s = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy�� MM�� dd��");
		s = f.format(date);
		return s;
	}
}
