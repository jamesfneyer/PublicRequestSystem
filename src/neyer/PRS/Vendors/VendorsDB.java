package neyer.PRS.Vendors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import neyer.PRS.business.Vendors;
import neyer.PRS.dbutils.DBException;
import neyer.PRS.dbutils.DBUtil;

public class VendorsDB implements VendorsDAO {
	static Connection connection;

	public VendorsDB() {
	}

	public ArrayList<Vendors> getAllVendors() throws DBException {
		String sql = "select * from Vendors;";
		connection = DBUtil.getConnection();
		ArrayList<Vendors> vendors = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				vendors.add(getVendorFromRow(rs));
			}
			rs.close();
			return vendors;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public Vendors getVendor(String code) throws DBException {
		String sql = "select * from Vendors " + "WHERE Code = ?;";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Vendors v = getVendorFromRow(rs);
				rs.close();
				return v;
			} else {
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	private Vendors getVendorFromRow(ResultSet rs) throws DBException, SQLException {
		try {
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			String address = rs.getString("Address");
			String city = rs.getString("City");
			String state = rs.getString("State");
			String zipCode = rs.getString("ZipCode");
			String phone = rs.getString("Phone");
			String email = rs.getString("Email");
			Boolean preapproved = rs.getBoolean("Preapproved");
			int ID = rs.getInt("ID");
			Vendors v = new Vendors();
			v.setID(ID);
			v.setAddress(address);
			v.setCode(code);
			v.setName(name);
			v.setState(state);
			v.setZipCode(zipCode);
			v.setCity(city);
			v.setPhone(phone);
			v.setEmail(email);
			v.setPreapproved(preapproved);
			return v;

		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	public int getVendorID(String code) throws DBException {
		String sql = "select ID, Code " + "from Vendors " + "where Code = ?;";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ArrayList<Vendors> vendors = getAllVendors();
			int p = 0;
			for (Vendors a : vendors) {
				if (code.equalsIgnoreCase(a.getCode())) {
					ps.setString(1, code);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						p = rs.getInt("ID");
						rs.close();
						return p;

					} else {
						rs.close();
					}
				}
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	public void addVendor(Vendors vendor) throws DBException {
		String sql = "INSERT INTO Vendors (Code, Name, Address, City, State, ZipCode, Phone, Email, Preapproved) "
				+ "VALUES(?,?,?,?,?,?,?,?,?);";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, vendor.getCode());
			ps.setString(2, vendor.getName());
			ps.setString(6, vendor.getZipCode());
			ps.setString(3, vendor.getAddress());
			ps.setString(4, vendor.getCity());
			ps.setString(5, vendor.getState());
			ps.setString(7, vendor.getPhone());
			ps.setString(8, vendor.getEmail());
			ps.setBoolean(9, vendor.isPreapproved());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}
	
	public void updateVendor(Vendors vendor) throws DBException{
		String sql = "UPDATE Vendors SET " 
				+ "Name = ?, "
				+ "Address = ?, "
				+ "City = ?, "
				+ "State = ?, "
				+ "ZipCode = ?, "
				+ "Phone = ?, "
				+ "Email = ?, "
				+ "Preapproved = ? " 
				+ "Where Code = ?;";
		connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(9, vendor.getCode());
			ps.setString(1, vendor.getName());
			ps.setString(5, vendor.getZipCode());
			ps.setString(2, vendor.getAddress());
			ps.setString(3, vendor.getCity());
			ps.setString(4, vendor.getState());
			ps.setString(6, vendor.getPhone());
			ps.setString(7, vendor.getEmail());
			ps.setBoolean(8, vendor.isPreapproved());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

}
