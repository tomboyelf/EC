package jp.co.aforce.beans;

import java.sql.Timestamp;

public class Song implements java.io.Serializable {

	private int id;
	private int albumId;
	private String name;
	private String albumName;
	private String artist;
	private int price;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public Song(int id, int albumId, String name, int price, Timestamp createdAt, Timestamp updatedAt) {
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
	
	public String getAlbumName() {
		return albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

}
