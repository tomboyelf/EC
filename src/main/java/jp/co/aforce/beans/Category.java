package jp.co.aforce.beans;

import java.sql.Timestamp;

public class Category implements java.io.Serializable {

	private int id;
	private String name;
	private String imgName;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// 管理者側カテゴリー情報変更用
	public Category(int id, String imgName, String name) {
		this.id = id;
		this.imgName = imgName;
		this.name = name;
	}
	
	public Category() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
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
