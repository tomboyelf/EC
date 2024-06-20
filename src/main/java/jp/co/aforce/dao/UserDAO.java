package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.User;
import jp.co.aforce.tool.Message;

public class UserDAO extends DAO {
	Message msg = new Message();

	public List<Integer> duplicationCheck(String username, String mailaddress) throws Exception {
		List<Integer> errorMessageList = new ArrayList<>();

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

	public List<Integer> duplicationCheckForUsername(String username) throws Exception {
		List<Integer> errorMessageList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from users where username=?")) {
			st.setString(1, username);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					if (username.equals(rs.getString("username"))) {
						errorMessageList.add(0);
					}
				}
			}
		}
		return errorMessageList;
	}

	public List<Integer> duplicationCheckForMailaddress(String mailaddress) throws Exception {
		List<Integer> errorMessageList = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(
						"select * from users where mailaddress=?")) {
			st.setString(1, mailaddress);

			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
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

	public int insertProfile(String username, String sex, Date birthdate, int id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"update USERS set username = ?, sex = ?, birthdate = ? where id = ?");
		st.setString(1, username);
		st.setString(2, sex);
		st.setDate(3, birthdate);
		st.setInt(4, id);
		int line = st.executeUpdate();
		st.close();
		con.close();
		return line;
	}

	public int insertPassword(String password, int id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"update USERS set password = ? where id = ?");
		st.setString(1, password);
		st.setInt(2, id);
		int line = st.executeUpdate();
		st.close();
		con.close();
		return line;
	}

	public int insertMailaddress(String mailaddress, int id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
		st = con.prepareStatement(
				"update USERS set mailaddress = ? where id = ?");
		st.setString(1, mailaddress);
		st.setInt(2, id);
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
				user.setFirstname(rs.getString("firstname"));
				user.setSex(rs.getString("sex"));
				user.setBirthdate(rs.getDate("birthdate"));
				user.setMailaddress(rs.getString("mailaddress"));

				//		⑤パスワードが違えばloginErrorMsgの1を返す
			} else {
				user.setLoginErrorMsg(msg.getLoginErrorMsg(1));
			}
		} else {
			//		②ユーザーネームが違うならloginErrorMsgの0を返す
			user.setLoginErrorMsg(msg.getLoginErrorMsg(0));
		}

		st.close();
		con.close();
		return user;
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
	}

	public int quit(int userId) throws Exception {

		//		try {
		Connection con = getConnection();
		PreparedStatement albumSt = null;
		PreparedStatement songSt  = null;
		PreparedStatement userSt  = null;
		try {
			//			トランザクション実装
			con.setAutoCommit(false);

			albumSt = con.prepareStatement("delete from album_statuses where user_id=?");
			albumSt.setInt(1, userId);
			int albumLine = albumSt.executeUpdate();
			System.out.println(albumLine);

			songSt = con.prepareStatement("delete from song_statuses where user_id=?");
			songSt.setInt(1, userId);
			int songLine = songSt.executeUpdate();
			System.out.println(songLine);

			userSt = con.prepareStatement("delete from users where id=?");
			userSt.setInt(1, userId);
			int userLine = userSt.executeUpdate();
			System.out.println(userLine);

			con.commit();

			return albumLine + songLine + userLine;
		} catch (Exception e) {
			int line = 0;
			return line;
		} finally {
			albumSt.close();
			songSt.close();
			userSt.close();
			con.setAutoCommit(true);
			con.close();
		}

	}

	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		try {
			System.out.println(dao.duplicationCheckForMailaddress("TEST1@.JP"));

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
