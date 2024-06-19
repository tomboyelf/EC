package jp.co.aforce.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Message {
	//	フィールド
	private List<String> signupErrorMsg = new ArrayList<>();
	private List<String> loginErrorMsg = new ArrayList<>();
	private List<String> purchaseErrorMsg = new ArrayList<>();
	private List<String> completeMsg = new ArrayList<>();
	private List<String> adminCompleteMsg = new ArrayList<>();

	//	コンストラクタ
	public Message() {
		//		登録時のエラーメッセージ一覧
		/*0*/ signupErrorMsg.add("このユーザーネームは既に登録されています");
		/*1*/ signupErrorMsg.add("このメールアドレスは既に登録されています");
		/*2*/ signupErrorMsg.add("ユーザーネームを再入力してください");
		/*3*/ signupErrorMsg.add("大文字、小文字、数字の3つを組み合わせたパスワードにしてください");
		/*4*/ signupErrorMsg.add("苗字は日本語のみです");
		/*5*/ signupErrorMsg.add("名前は日本語のみです");
		/*6*/ signupErrorMsg.add("正しいメールアドレスを入力してください");
		/*7*/ signupErrorMsg.add("登録に失敗しました");
		/*8*/ signupErrorMsg.add("登録されているメールアドレスと異なります");
		/*9*/ signupErrorMsg.add("退会手続きに失敗しました");

		//		ログイン時のエラーメッセージ一覧
		/*0*/ loginErrorMsg.add("ユーザーネームが違います");
		/*1*/ loginErrorMsg.add("パスワードが違います");
		
		//		購入時のエラーメッセージ一覧
		/*0*/ purchaseErrorMsg.add("カートに追加済みです");
		/*1*/ purchaseErrorMsg.add("購入済みです");
		/*2*/ purchaseErrorMsg.add("商品を選んでください");
		/*3*/ purchaseErrorMsg.add("購入に失敗しました");
		/*4*/ purchaseErrorMsg.add("カート追加に失敗しました");
		/*5*/ purchaseErrorMsg.add("カートが空です");
		/*6*/ purchaseErrorMsg.add("購入済みの商品がありません");
		
		//		成功時のメッセージ一覧
		/*0*/ completeMsg.add("会員登録が完了しました");
		/*1*/ completeMsg.add("お気に入りリストに追加されました");
		/*2*/ completeMsg.add("カートに追加されました");
		/*3*/ completeMsg.add("購入が完了しました");
		/*4*/ completeMsg.add("登録情報が変更されました");
		/*5*/ completeMsg.add("カートから削除されました");
		/*6*/ completeMsg.add("退会手続きが完了しました");
		
//		管理者側成功メッセージ一覧
		/*0*/ adminCompleteMsg.add("カテゴリー情報が変更されました");
		/*1*/ adminCompleteMsg.add("アルバム情報が変更されました");
		/*2*/ adminCompleteMsg.add("曲情報が変更されました");
		/*3*/ adminCompleteMsg.add("追加に成功しました");

	}

	public String getSignupErrorMsg(int i) {
		return signupErrorMsg.get(i);
	}

	public String getLoginErrorMsg(int i) {
		return loginErrorMsg.get(i);
	}
	
	public String getPurchaseErrorMsg(int i) {
		return purchaseErrorMsg.get(i);
	}
	
	public String getCompleteMsg(int i) {
		return completeMsg.get(i);
	}
	
	public String getAdminCompleteMsg(int i) {
		return adminCompleteMsg.get(i);
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String filePath = "/home/data/image.jpg";
		String fileName = new File(filePath).getName();
		System.out.println(fileName);

	}

}
