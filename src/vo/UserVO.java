package vo;

import java.util.ArrayList;
import java.util.List;

public class UserVO {
	private String id;// 아이디
	private String pw;// 비밀번호
	private String name;// 회원명
	private String email; // 이메일

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", email=" + email + ", name=" + name + "]";
	}

	public String toStringWithPassword() {
		return "[id=" + id + ", pw=" + pw + ", email=" + email + ", name=" + name + "]";
	}

	/**
	 * 더미데이터 삽입을 위한 생성자
	 * 
	 * @param id
	 * @param pw
	 * @param email
	 * @param name
	 */
	public UserVO(String id, String pw, String name, String email) {
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.name = name;
	}

	/**
	 * 관리자 로그인 세션을 위한 생성자
	 * 
	 * @param id
	 * @param pw
	 * @param name
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
