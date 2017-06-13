package neyer.PRS.lineItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import neyer.PRS.business.LineItems;
import neyer.PRS.dbutils.*;

public class LineItemDB implements LineItemDAO {
	Connection connection;

	public LineItemDB() {

	}

	public void addLineItem(LineItems lineItem) throws DBException {
		String sql = "INSERT INTO LineItems "
				+ " (RequestID, ProductID, Quantity) " 
				+ "Values(?,?,?);";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, lineItem.getRequestID());
			ps.setInt(2, lineItem.getProductID());
			ps.setInt(3, lineItem.getQuantity());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		}

	}

	public ArrayList<LineItems> getUsersLineItems(int requestID) throws DBException {
		String sql = "select l.ID, RequestID, ProductID, Quantity, p.Name, p.Price, v.Preapproved "
				+ "from LineItems l, Products p, Vendors v "
				+ "where p.ID = l.ProductID "
				+ "and v.ID = p.VendorID "
				+ "and l.RequestID = ?; ";
		connection = DBUtil.getConnection();
		ArrayList<LineItems> lineItems = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, requestID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				lineItems.add(getLineItemFromRow(rs));
			} else {
			rs.close();
			}
			return lineItems;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
	
	public ArrayList<LineItems> getAllLineItems() throws DBException{
		String sql = "select l.ID, RequestID, ProductID, Quantity, p.Name, p.Price, v.Preapproved "
				+ "from LineItems l, Products p, Vendors v "
				+ "where p.ID = l.ProductID "
				+ "and v.ID = p.VendorID; ";
		ArrayList<LineItems> lineItems = new ArrayList<>();
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lineItems.add(getLineItemFromRow(rs));
			}
			rs.close();
			return lineItems;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	private LineItems getLineItemFromRow(ResultSet rs) throws DBException, SQLException {
		try{
			int id = rs.getInt("ID");
			int requestID = rs.getInt("RequestID");
			int productID = rs.getInt("ProductID");
			int quantity = rs.getInt("Quantity");
			String partNumber = rs.getString("p.Name");
			double price = rs.getDouble("p.Price");
			boolean preapproved = rs.getBoolean("v.Preapproved");
			double total = quantity*price;
			LineItems u = new LineItems();
			u.setProductID(productID);
			u.setRequestID(requestID);
			u.setId(id);
			u.setQuantity(quantity);
			u.setPartNumber(partNumber);
			u.setTotal(total);
			u.setPreapproved(preapproved);
			return u;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
}
