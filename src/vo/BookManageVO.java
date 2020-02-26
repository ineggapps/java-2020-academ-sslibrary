package vo;

import java.util.Date;

public class BookManageVO {
	private BookVO book;
	private UserVO user;
	private Date startDate;
	private Date endDate;
	
	public BookVO getBook() {
		return book;
	}

	public void setBook(BookVO book) {
		this.book = book;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
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
	public BookManageVO(BookVO book, UserVO user, Date startDate, Date endDate) {
		this.book = book;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * 
	 */
	public BookManageVO() {
	}

}
