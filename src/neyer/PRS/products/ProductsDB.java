package neyer.PRS.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import neyer.PRS.business.Products;
import neyer.PRS.business.Vendors;
import neyer.PRS.dbutils.DBException;
import neyer.PRS.dbutils.DBUtil;

public class ProductsDB implements ProductsDAO {
	static Connection connection;

	public ProductsDB() {
	}

	public void addProduct(Products product) throws DBException {
		String sql = "insert into Products (Name, PartNumber, Price, Unit, VendorID, PhotoPath) "
				+ "Values (?,?,?,?,?,?);";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getPartNumber());
			ps.setDouble(3, product.getPrice());
			ps.setString(4, product.getUnit());
			ps.setInt(5, product.getVendorID());
			ps.setString(6, product.getPhotoPath());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public ArrayList<Products> getAllProducts(int vendorID) throws DBException {
		String sql = "select * from Products "
				+ "WHERE VendorID = ?; ";
		ArrayList<Products> products = new ArrayList<>();
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, vendorID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				products.add(getProductFromRow(rs));
			}
			rs.close();
			return products;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public Products getProduct(String partNumber, int vID) throws DBException {
		String sql = "select * from Products where PartNumber = ? and VendorID = ?;";
		connection = DBUtil.getConnection();
		Products p = null;
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ArrayList<Products> products = getAllProducts(vID);
			for (Products u : products) {
				if (u.getPartNumber().equalsIgnoreCase(partNumber)) {
					ps.setString(1, partNumber);
					ps.setInt(2, vID);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						p = getProductFromRow(rs);
						rs.close();
					} else {
						rs.close();
					}
				}
			}
			return p;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
	
	public void updateProduct(Products product) throws DBException{
		String sql = "UPDATE Products SET "
				+ "Name = ? "
				+ "Price = ? "
				+ "Unit = ? "
				+ "PhotoPath = ? "
				+ "VendorID = ? "
				+ "WHERE "
				+ "PartNumber = ? ";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, product.getName());
			ps.setDouble(2, product.getPrice());
			ps.setString(3, product.getUnit());
			ps.setString(4, product.getPhotoPath());
			ps.setInt(5, product.getVendorID());
			ps.setString(6, product.getPartNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
	
	private Products getProductFromRow(ResultSet rs) throws DBException, SQLException {
		try {
			String name = rs.getString("Name");
			String partNumber = rs.getString("PartNumber");
			double price = rs.getDouble("Price");
			String unit = rs.getString("Unit");
			int vendorID = rs.getInt("VendorID");
			String photoPath = rs.getString("PhotoPath");
			int ID = rs.getInt("ID");

			Products p = new Products();
			p.setProductsID(ID);
			p.setName(name);
			p.setPartNumber(partNumber);
			p.setPrice(price);
			p.setUnit(unit);
			p.setVendorID(vendorID);
			p.setPhotoPath(photoPath);
			return p;

		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
}
