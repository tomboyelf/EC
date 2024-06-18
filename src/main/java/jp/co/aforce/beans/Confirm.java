package jp.co.aforce.beans;

public class Confirm implements java.io.Serializable{

	private String imgName;

	private int categoryId;
	private String categoryName;
	
	private int albumId;
	private String albumName;
	private String artist;
	
	// 管理者側カテゴリー情報変更用
	public Confirm(int categoryId, String imgName, String categoryName) {
		this.categoryId = categoryId;
		this.imgName = imgName;
		this.categoryName = categoryName;
	}
	
	// 管理者側アルバム情報変更用
    public Confirm(int albumId, int categoryId, String imgName,  String albumName, String artist, String categoryName) {
        this.albumId = albumId;
        this.imgName = imgName;
        this.albumName = albumName;
        this.artist = artist;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }
    
    public Confirm() {}

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
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

    public String getCategoryName() {
    	return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
    	this.categoryName = categoryName;
    }
    
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}