package jp.co.aforce.beans;

import java.sql.Timestamp;

public class Album implements java.io.Serializable, Comparable<Album> {

	private int id;
	private int categoryId;
	private String name;
	private String albumImgName;
	private String artist;
	private int traffic;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	//	別テーブルのカラム
	private String categoryName;
	private String categoryImgName;
	
	public Album(String albumImgName, String name, String artist, int categoryId, String categoryName) {
		this.albumImgName = albumImgName;
		this.name = name;
		this.artist = artist;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public Album() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getAlbumImgName() {
		return albumImgName;
	}
	
	public void setAlbumImgName(String albumImgName) {
		this.albumImgName = albumImgName;
	}
	
	public String getCategoryImgName() {
		return categoryImgName;
	}
	
	public void setCategoryImgName(String categoryImgName) {
		this.categoryImgName = categoryImgName;
	}
	
	

	@Override
	public int compareTo(Album o) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}