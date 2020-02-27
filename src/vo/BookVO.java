package vo;

import java.util.Date;

import util.DateMaker;

public class BookVO {
	private String isbn13; // ISBN 13
	private String title;// å ����
	private String author;// �۾���
	private String publisher; // ���ǻ��
	private Date releaseDate;// ����� (20011010 �������� ����)
	private int amount; // ��� (�뿩 �� ���ڶ���� �Ǻ��ϴ� ����)
	private String field; // �о�
	private DateMaker dm = new DateMaker();

	public String getIsbn13() {
		return isbn13;
	}

	public void setISBN13(String isbn13) {
		this.isbn13 = isbn13;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
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
		return "[" + isbn13 + ", " + title + ", " + author + ", " + publisher + ", " + dm.toString(releaseDate) + ", "
				+ amount + ", " + field + "]";
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
	public BookVO(String isbn13, String title, String author, String publisher, Date releaseDate, int amount,
			String field) {
		this.isbn13 = isbn13;
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
