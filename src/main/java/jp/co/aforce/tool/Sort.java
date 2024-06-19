package jp.co.aforce.tool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jp.co.aforce.beans.Album;

public class Sort {

	//	今日のデータ
	public static List<Album> getAlbumListDaily(List<Album> albumList) {
		//		今日の日付取得
		Date today = new Date();
		//		カレンダーオブジェクト
		Calendar calendar = Calendar.getInstance();
		//		カレンダー型へ今日の日付を変換
		calendar.setTime(today);
		//		今日の頭に変更
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//		今日を取得
		Date todayStart = calendar.getTime();

		//		明日の頭
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		//		明日を取得
		Date tomorrowStart = calendar.getTime();
		//		returnするリスト
		List<Album> albumListDaily = new ArrayList<>();
		for (Album album : albumList) {
			Date albumCreatedAtDate = album.getCreatedAt();
			if (albumCreatedAtDate != null && albumCreatedAtDate.before(tomorrowStart)
					&& albumCreatedAtDate.after(todayStart)) {
				albumListDaily.add(album);
			}
		}
		return albumListDaily;
	}

	//	今週のデータ
	public static List<Album> getAlbumListWeekly(List<Album> albumList) {
		//		今日の日付取得
		Date currentDate = new Date();
		//		カレンダーオブジェクト
		Calendar calendar = Calendar.getInstance();
		//		カレンダー型へ今日の日付を変換
		calendar.setTime(currentDate);
		//		一週間前に変更
		calendar.add(Calendar.DAY_OF_MONTH, -7);

		//		一週間前を取得
		Date aWeekAgoDate = calendar.getTime();
		//		returnするリスト
		List<Album> albumListWeekly = new ArrayList<>();
		for (Album album : albumList) {
			Date albumCreatedAtDate = album.getCreatedAt();
			System.out.println("日付:" + albumCreatedAtDate);
			if (albumCreatedAtDate != null && albumCreatedAtDate.compareTo(aWeekAgoDate) > 0) {
				System.out.println("今週" + album.getArtist());
				albumListWeekly.add(album);
			}
		}
		return albumListWeekly;
	}

	//	先週のデータ
	public static List<Album> getAlbumListLastWeekly(List<Album> albumList) {
		//		今日の日付取得
		Date currentDate = new Date();
		//		カレンダーオブジェクト
		Calendar calendar = Calendar.getInstance();
		Calendar calendarTwoWeeksAgo = Calendar.getInstance();
		//		カレンダー型へ今日の日付を変換
		calendar.setTime(currentDate);
		//		一週間前に変更
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		//		二週間前に変更
		calendarTwoWeeksAgo.add(Calendar.DAY_OF_MONTH, -14);
		//		一週間前を取得
		Date aWeekAgoDate = calendar.getTime();
		Date twoWeeksAgoDate = calendarTwoWeeksAgo.getTime();
		//		returnするリスト
		List<Album> albumListLastWeekly = new ArrayList<>();
		for (Album album : albumList) {
			Date albumCreatedAtDate = album.getCreatedAt();
			if (albumCreatedAtDate != null && albumCreatedAtDate.compareTo(aWeekAgoDate) < 0
					&& albumCreatedAtDate.compareTo(twoWeeksAgoDate) > 0) {
				System.out.println("先週" + album.getArtist());
				albumListLastWeekly.add(album);
			}
		}
		return albumListLastWeekly;
	}

	//	今月のデータ
	public static List<Album> getAlbumListMonthly(List<Album> albumList) {
		//		今日の日付取得
		Date currentDate = new Date();
		//		カレンダーオブジェクト
		Calendar calendar = Calendar.getInstance();
		//		カレンダー型へ今日の日付を変換
		calendar.setTime(currentDate);
		//		一ヵ月前に変更
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		//		一ヵ月前を取得
		Date aMonthAgoDate = calendar.getTime();
		//		returnするリスト
		List<Album> albumListMonthly = new ArrayList<>();
		for (Album album : albumList) {
			Date albumCreatedAtDate = album.getCreatedAt();
			if (albumCreatedAtDate != null && albumCreatedAtDate.compareTo(aMonthAgoDate) > 0) {
				albumListMonthly.add(album);
			}
		}
		return albumListMonthly;
	}

	public static void main(String[] args) {
	}

}
