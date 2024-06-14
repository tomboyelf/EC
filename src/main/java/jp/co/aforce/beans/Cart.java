package jp.co.aforce.beans;

public class Cart implements java.io.Serializable {

	private int albumId;
	private int songId;
	private int price;
	private String albumName;
	private String songName;
	private String artist;

	public int getAlbumId() {
        return albumId;
    }

    public int getSongId() {
        return songId;
    }
    
    public int getPrice() {
    	return price;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtist() {
        return artist;
    }

    // セッタ（setter）メソッド
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public void setPrice(int price) {
    	this.price = price;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}