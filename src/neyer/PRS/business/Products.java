package neyer.PRS.business;

public class Products {
	private String name;
	private String partNumber;
	private double price;
	private String unit;
	private String VendorCode;
	private String photoPath;
	private int productsID;
	private int vendorID;

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public int getProductsID() {
		return productsID;
	}

	public void setProductsID(int productsID) {
		this.productsID = productsID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVendorCode() {
		return VendorCode;
	}

	public void setVendorCode(String vendorCode) {
		VendorCode = vendorCode;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

}
