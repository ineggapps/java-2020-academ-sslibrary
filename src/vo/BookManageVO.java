package vo;

import java.util.Date;

public class BookManageVO {
	private String isbn13;//대여한 책 코드 정보
	private String id;//대여한 사용자 ID정보
	private Date startDate;//대여일
	private Date endDate;//반납일
	
	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	 * @param isbn13
	 * @param id
	 * @param startDate
	 * @param endDate
	 */
	public BookManageVO(String isbn13, String id, Date startDate, Date endDate) {
		this.isbn13 = isbn13;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * 
	 */
	public BookManageVO() {
	}

	@Override
	public String toString() {
		return "[isbn13=" + isbn13 + ", id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	

}
