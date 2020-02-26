package vo;

import java.util.Date;

public class BookVO {
	private String ISBN13; // ISBN 13
	private String title;// 책 제목
	private String author;// 글쓴이
	private String publisher; // 출판사명
	private Date releaseDate;// 출시일 (20011010 형식으로 저장)
	private String amount; // 재고 (대여 시 모자라는지 판별하는 변수)
	private String field; // 분야

	public String getISBN13() {
		return ISBN13;
	}

	public void setISBN13(String iSBN13) {
		ISBN13 = iSBN13;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	@Override
	public String toString() {
		return "[title=" + title + ", author=" + author + ", publisher=" + publisher + ", ISBN13=" + ISBN13
				+ ", releaseDate=" + releaseDate + ", amount=" + amount + ", field=" + field + "]";
	}

	/**
	 * @param iSBN13
	 * @param title
	 * @param author
	 * @param publisher
	 * @param releaseDate
	 * @param amount
	 * @param field
	 */
	public BookVO(String iSBN13, String title, String author, String publisher, Date releaseDate, String amount,
			String field) {
		ISBN13 = iSBN13;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
		this.amount = amount;
		this.field = field;
	}

	/**
	 * 
	 */
	public BookVO() {
	}

}
