package vo;

import java.util.ArrayList;
import java.util.List;

public class UserVO {
	private String id;// ���̵�
	private String pw;// ��й�ȣ
	private String name;// ȸ����
	private String email; // �̸���

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
	 * ���̵����� ������ ���� ������
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
	 * ������ �α��� ������ ���� ������
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
