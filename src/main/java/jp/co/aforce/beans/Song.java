package jp.co.aforce.beans;

import org.joda.time.DateTime;

public class Song implements java.io.Serializable {

	private int id;
	private int albumId;
	private String name;
	private int price;
	private DateTime createdAt;
	private DateTime updatedAt;

	public Song(int id, int albumId, String name, int price, DateTime createdAt, DateTime updatedAt) {
		this.id = id;
		this.albumId = albumId;
		this.name = name;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Song() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	// UpdatedAtのゲッターとセッター
	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
