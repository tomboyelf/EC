package jp.co.aforce.tool;

import java.util.ArrayList;
import java.util.List;

public class Valid {
	public List<Integer> validCheck(String username, String password, String lastname, String firstname, String mail) {
		List<Integer> errorMessageList = new ArrayList<>();
		//		ユーザーネームチェック
		if (!username.matches("^[a-zA-Z0-9]{5,}$")) {
			//		チェック対象と一致するメッセージ番号をリストへ追加
			//		例：ユーザーネームチェックは2番なので、2を追加
			errorMessageList.add(2);
		}
		//		パスワードチェック
		if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{5,}$")) {
			errorMessageList.add(3);
		}
		//		苗字チェック
		if (!lastname.matches("^[ぁ-んァ-ヶｱ-ﾝﾞﾟ一-龠]{1,}$")) {
			errorMessageList.add(4);
		}
		//		名前チェック
		if (!firstname.matches("^[ぁ-んァ-ヶｱ-ﾝﾞﾟ一-龠]{1,}$")) {
			errorMessageList.add(5);
		}
		//		メールチェック
		if (!mail.matches("^.*@.*$")) {
			errorMessageList.add(6);
		}

		return errorMessageList;
	}

	public static void main(String[] args) {
		//		Valid valid = new Valid();
		//		
		//        String username = "user";
		//        String password = "Pass1";
		//        String lastname = "山田";
		//        String firstname = "太郎";
		//        String mail = "example@example.com";
		//        
		//        List<String> errorMessageList=new ArrayList<>();
		//        
		//        errorMessageList=valid.validCheck(username, password, lastname, firstname, mail);
		//        System.out.println(errorMessageList);

	}
}
