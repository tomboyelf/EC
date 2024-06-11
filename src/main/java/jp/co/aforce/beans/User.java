package jp.co.aforce.beans;

import java.sql.Date;

public class User implements java.io.Serializable {

		private int id;
		private String username;
		private String password;
		private String lastname;
		private String firstname;
		private String sex;
		private Date birthdate;
		private String mailaddress;
//		エラーメッセージ格納用フィールド
		private String loginErrorMsg;
		
		
		
		public User(String username, String password, String lastname, String firstname, String sex, Date birthdate, String mailaddress) {
			this.username=username;
			this.password=password;
			this.lastname=lastname;
			this.firstname=firstname;
			this.sex=sex;
			this.birthdate=birthdate;
			this.mailaddress=mailaddress;
		}
		
		public User() {}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id=id;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username=username;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password=password;
		}
		
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname=lastname;
		}
		
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname=firstname;
		}
		
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex=sex;
		}
		
		public Date getBirthdate() {
			return birthdate;
		}
		public void setBirthdate(Date birthdate) {
			this.birthdate=birthdate;
		}
		
		public String getMailaddress() {
			return mailaddress;
		}
		public void setMailaddress(String mailaddress) {
			this.mailaddress=mailaddress;
		}
		
		public String getLoginErrorMsg() {
			return loginErrorMsg;
		}
		public void setLoginErrorMsg(String loginErrorMsg) {
			this.loginErrorMsg=loginErrorMsg;;
		}
		
		public void reset() {
	        this.id = 0;
	        this.username = null; 
	        this.password = null;
	        this.lastname = null;
	        this.firstname = null;
	        this.sex = null; 
	        this.birthdate = null; 
	        this.mailaddress = null;
	        this.loginErrorMsg = null;
	    }
}
