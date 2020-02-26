package vo;

import java.util.Date;

public class BookManageVO {
	private BookVO book;
	private Date startDate;
	private Date endDate;

	public BookVO getBook() {
		return book;
	}

	public void setBook(BookVO book) {
		this.book = book;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param book
	 * @param startDate
	 * @param endDate
	 */
	public BookManageVO(BookVO book, Date startDate, Date endDate) {
		this.book = book;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * 
	 */
	public BookManageVO() {
	}

}
