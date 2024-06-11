package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.User;
import jp.co.aforce.tool.Message;

public class UserDAO extends DAO {
	List<Integer> errorMessageList = new ArrayList<>();
	Message msg = new Message();

	public List<Integer> duplicationCheck(String username, String mailaddress) throws Exception {

		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from users where username=? or mailaddress=?")) {
			st.setString(1, username);
			st.setString(2, mailaddress);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					if (username.equals(rs.getString("username"))) {
						errorMessageList.add(0);
					}
					if (mailaddress.equals(rs.getString("mailaddress"))) {
						errorMessageList.add(1);
					}
				}
			}
		}

		return errorMessageList;
	}

	public int insert(User user) throws Exception {

		Connection con = getConnection();
		PreparedStatement st;

		st = con.prepareStatement(
				"INSERT INTO USERS (USERNAME, PASSWORD, LASTNAME, FIRSTNAME, SEX, BIRTHDATE, MAILADDRESS) VALUES (?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, user.getUsername());
		st.setString(2, user.getPassword());
		st.setString(3, user.getLastname());
		st.setString(4, user.getFirstname());
		st.setString(5, user.getSex());
		st.setDate(6, user.getBirthdate());
		st.setString(7, user.getMailaddress());
		int line = st.executeUpdate();
		st.close();
		con.close();
		return line;
	}

	public User login(String username, String password) throws Exception {
		Message msg = new Message();

		//		try {
		Connection con = getConnection();
		PreparedStatement st;

		//		①ユーザーネームで検索かける
		st = con.prepareStatement(
				"select * from users where username=?");
		st.setString(1, username);
		ResultSet rs = st.executeQuery();
		User user = new User();

		//		③ユーザーネームがあればパスワードを比較
		if (rs.next()) {
			if (password.equals(rs.getString("password"))) {
				//		④二つそろえば情報が取り出せるので、とりあえず全部取って返す
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setLastname(rs.getString("lastname"));
				user.setSex(rs.getString("sex"));
				user.setBirthdate(rs.getDate("birthdate"));
				user.setMailaddress(rs.getString("mailaddress"));

				//		⑤パスワードが違えばloginErrorMsgの1を返す
			} else {
				user.setLoginErrorMsg(msg.getLoginErrorMsg(1));
			}
		} else {
			//		②ユーザーネーム未登録ならloginErrorMsgの0を返す
			user.setLoginErrorMsg(msg.getLoginErrorMsg(0));
		}

		st.close();
		con.close();
		return user;
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
	}
	//	public List<User> showAll() throws Exception {
	//		List<User> allList = new ArrayList();
	//		Connection con = getConnection();
	//		PreparedStatement st;
	//
	//		st = con.prepareStatement(
	//				"select * from login");
	//
	//		ResultSet rs = st.executeQuery();
	//		while (rs.next()) {
	//			User customer = new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
	//			allList.add(customer);
	//		}
	//
	//		st.close();
	//		con.close();
	//
	//		return allList;
	//	}
	//
	//	public int deleteAcc(int id) throws Exception {
	//		Connection con = getConnection();
	//		PreparedStatement st;
	//
	//		st = con.prepareStatement(
	//				"delete from login where id=?");
	//		st.setInt(1, id);
	//		int line = st.executeUpdate();
	//
	//		st.close();
	//		con.close();
	//		return line;
	//	}
}
