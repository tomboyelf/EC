package jp.co.aforce.tool;

import java.util.ArrayList;
import java.util.List;

public class Message {
	//	フィールド
	private List<String> signupErrorMsg = new ArrayList<>();
	private List<String> loginErrorMsg = new ArrayList<>();

	//	コンストラクタ
	public Message() {
		//		登録時のエラーメッセージ一覧
		/*0*/ signupErrorMsg.add("このユーザーネームは既に登録されています");
		/*1*/ signupErrorMsg.add("このメールアドレスは既に登録されています");
		/*2*/ signupErrorMsg.add("ユーザーネームを再入力してください");
		/*3*/ signupErrorMsg.add("パスワードを再入力してください");
		/*4*/ signupErrorMsg.add("苗字は日本語のみです");
		/*5*/ signupErrorMsg.add("名前は日本語のみです");
		/*6*/ signupErrorMsg.add("正しいメールアドレスを入力してください");

		//		ログイン時のエラーメッセージ一覧
		/*0*/ loginErrorMsg.add("ユーザーネームが違います");
		/*1*/ loginErrorMsg.add("パスワードが違います");

	}

	public String getSignupErrorMsg(int i) {
		return signupErrorMsg.get(i);
	}

	public String getLoginErrorMsg(int i) {
		return loginErrorMsg.get(i);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Message msg = new Message();
		System.out.println(msg.getSignupErrorMsg(2));
		System.out.println(msg.getLoginErrorMsg(0));

	}

}
