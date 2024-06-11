package jp.co.aforce.beans;

public class Album implements java.io.Serializable {
	
		private int id;
		private int albumId;
		private String name;
		private int price;
		private datetime created_at;
		private datetime updated_at;
		
		public Album(int productId, String productName, String productArtist, int productPrice, String productRelease, int productCategory) {
			this.productId=productId;
			this.productName=productName;
			this.productArtist=productArtist;
			this.productPrice=productPrice;
			this.productRelease=productRelease;
			this.productCategory=productCategory;
//			this.productAlbum=productAlbum;
		}
		
		public Album(String productName, String productArtist, int productPrice, String productRelease, int productCategory) {
			this.productName=productName;
			this.productArtist=productArtist;
			this.productPrice=productPrice;
			this.productRelease=productRelease;
			this.productCategory=productCategory;
		}
		
		public Album() {}
		
		
		public int getProductId() {
			return productId;
		}
		
		public String getProductName() {
			return productName;
		}
		
		public String getProductArtist() {
			return productArtist;
		}
		
		public int getProductPrice() {
			return productPrice;
		}
		
		public String getProductRelease() {
			return productRelease;
		}
		
		public int getProductCategory() {
			return productCategory;
		}
		
		public void setProductId(int productId) {
			this.productId=productId;
		}
		
		public void setProductName(String productName) {
			this.productName=productName;
		}
		
		public void setProductArtist(String productArtist) {
			this.productArtist=productArtist;
		}
		
		public void setProductPrice(int productPrice) {
			this.productPrice=productPrice;
		}
		
		public void setProductRelease(String productRelease) {
			this.productRelease=productRelease;
		}
		
		public void setProductCategory(int productCategory) {
			this.productCategory=productCategory;
		}
		
}
