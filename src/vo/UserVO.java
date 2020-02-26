package vo;
import java.util.ArrayList;
import java.util.List;

public class UserVO {
	private String id;// 아이디
	private String pw;// 비밀번호
	private String name;// 회원명
	private List<BookManageVO> list;// 대여 목록

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookManageVO> getList() {
		if(list==null) {
			list = new ArrayList<BookManageVO>();
		}
		return list;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", list=" + list + "]";
	}

	public String toStringWithPassword() {
		return "[id=" + id + ", pw=" + pw + ", name=" + name + ", list=" + list + "]";
	}

	/**
	 * @param id
	 * @param pw
	 * @param name
	 * @param list
	 */
	public UserVO(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	/**
	 * 
	 */
	public UserVO() {
	}
	
	

}
