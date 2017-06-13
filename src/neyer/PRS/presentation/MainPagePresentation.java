package neyer.PRS.presentation;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import neyer.PRS.Vendors.VendorsDAO;
import neyer.PRS.business.*;
import neyer.PRS.dbutils.DBException;
import neyer.PRS.javautil.DAOFactory;
import neyer.PRS.javautil.Validator;
import neyer.PRS.lineItems.LineItemDAO;
import neyer.PRS.products.ProductsDAO;
import neyer.PRS.requests.RequestsDAO;
import neyer.PRS.users.UsersDAO;

public class MainPagePresentation {
	static VendorsDAO vendorsDB = null;
	static LineItemDAO lineItemsDB = null;
	static RequestsDAO requestsDB = null;
	static Scanner sc = new Scanner(System.in);
	static ProductsDAO productsDB = null;
	static UsersDAO usersDB = null;
	static NumberFormat currency = NumberFormat.getCurrencyInstance();	

	public static void main(String[] args) {
		
		vendorsDB = DAOFactory.getVendorsDAO();
		lineItemsDB = DAOFactory.getLineItemDAO();
		requestsDB = DAOFactory.getRequestsDAO();
		productsDB = DAOFactory.getProductsDAO();
		usersDB = DAOFactory.getUsersDAO();
//		viewUsers();

		Users u = isValidUsername();
		System.out.println(usersDB.getUsersFullname(u.getID()));
		System.out.println(u.getRole());
		if(u != null){
		if(u.getRoleID() == 1){
			employeeFunctions(u);
		}
		else if(u.getRoleID() == 2){
			managerFunctions(u);
		}
		else if(u.getRoleID() == 3){
			adminFunctions(u);
		}
		}
		

	}
	
	//Main page for manager, sends them to their possible locations
	private static void managerFunctions(Users u) {
		viewManagerMenu();
		String choice = Validator.getString(sc, "Enter function: ");
		if(choice.equalsIgnoreCase("view"))
			viewRequests(u, true);
		else if(choice.equalsIgnoreCase("manage"))
			manageRequest(u);
		else if(choice.equals("help"))
			viewManagerMenu();
		else if(choice.equalsIgnoreCase("exit"))
			System.out.println("goodbye");
		else{
			System.out.println("Invalid function.");
			managerFunctions(u);
		}
		
	}

	//main page for admins, sends them to the next stage
	private static void adminFunctions(Users u) {
		viewAdminMenu();
		String choice = Validator.getString(sc, "Enter function: ");
		if(choice.equalsIgnoreCase("vendors"))
			vendorOptions(u);
		else if(choice.equalsIgnoreCase("products"))
			productOptions(u);
		else if(choice.equalsIgnoreCase("users"))
			usersOptions(u);
		else if(choice.equals("help"))
			viewAdminMenu();
		else if(choice.equalsIgnoreCase("exit"))
			System.out.println("goodbye");
		else{
			System.out.println("Invalid function.");
			adminFunctions(u);
		}
	}

	//vendor page for admins, allows them to affect the Vendors table in the DB
	private static void vendorOptions(Users u) {
		vendorFunctions();
		String choice = Validator.getString(sc, "Enter function: ");
		if(choice.equalsIgnoreCase("view")){
			viewVendors();
			vendorOptions(u);
		}
		else if(choice.equalsIgnoreCase("add"))
			addVendor(u);
		else if(choice.equalsIgnoreCase("update"))
			updateVendor(u);
		else if(choice.equals("help"))
			vendorFunctions();
		else if(choice.equalsIgnoreCase("back"))
			adminFunctions(u);
		else{
			System.out.println("Invalid function.");
			vendorOptions(u);
		}
	}

	//adds a vendor to the DB, requires a unique code
	private static void addVendor(Users u) {
		Vendors vendor = new Vendors();
		try{
			boolean isValid = true;
			ArrayList<Vendors> vendors = vendorsDB.getAllVendors();
			String code = "";
			do {
				code = Validator.getString(sc, "Enter code: ",10);
				for (Vendors v : vendors) {
					if (code.equalsIgnoreCase(v.getCode())) {
						System.out.println("Error! Code must be unique.");
						isValid = false;
					}
				}
			} while (isValid == false);
			String name = Validator.getLine(sc, "Enter Name: ");
			String address = Validator.getLine(sc, "Enter Address: ");
			String city = Validator.getString(sc, "Enter City: ");
			String state = Validator.getString(sc, "Enter state: ", 2);
			String zipCode = Validator.getString(sc, "Enter zipcode: ", 5);
			String phone = Validator.getString(sc, "Enter phone: ",12);
			String email = Validator.getString(sc, "Enter email: ");
			String preapprovedString = Validator.getString(sc, "Preapproved? (y/n): ", "y", "n");
			boolean preapproved = true;
			if(preapprovedString.equalsIgnoreCase("y"))
				preapproved = true;
			else if(preapprovedString.equalsIgnoreCase("n"))
				preapproved = false;
			vendor.setName(name);
			vendor.setCode(code);
			vendor.setAddress(address);
			vendor.setCity(city);
			vendor.setEmail(email);
			vendor.setState(state);
			vendor.setZipCode(zipCode);
			vendor.setPhone(phone);
			vendor.setPreapproved(preapproved);
			vendorsDB.addVendor(vendor);
			
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	//allows an admin to update the vendors information. Everything but the unique identifier code can be changed
	private static void updateVendor(Users u) {
		Vendors vendor = new Vendors();
		try {
			String code = "";
			vendor = selectVendor();
			String name = Validator.getLine(sc, "Enter Name: ");
			String address = Validator.getLine(sc, "Enter Address: ");
			String city = Validator.getString(sc, "Enter City: ");
			String state = Validator.getString(sc, "Enter state: ");
			String zipCode = Validator.getString(sc, "Enter zipcode: ");
			String phone = Validator.getString(sc, "Enter phone: ");
			String email = Validator.getString(sc, "Enter email: ");
			String preapprovedString = Validator.getString(sc, "Preapproved? (y/n): ", "y", "n");
			boolean preapproved = true;
			if (preapprovedString.equalsIgnoreCase("y"))
				preapproved = true;
			else if (preapprovedString.equalsIgnoreCase("n"))
				preapproved = false;
			vendor.setName(name);
			vendor.setCode(code);
			vendor.setAddress(address);
			vendor.setCity(city);
			vendor.setEmail(email);
			vendor.setState(state);
			vendor.setZipCode(zipCode);
			vendor.setPhone(phone);
			vendor.setPreapproved(preapproved);
			vendorsDB.updateVendor(vendor);
			adminFunctions(u);

		} catch (DBException e) {
			e.printStackTrace();
		}

	}

	//product page for admins, allows them to affect the Products table in the DB
	private static void productOptions(Users u) {
		productFunctions();
		String choice = Validator.getString(sc, "Enter function: ");
		if (choice.equalsIgnoreCase("view")) {
			Vendors v = selectVendor();
			vendorProducts(v);
		} else if (choice.equalsIgnoreCase("add")) {
			Vendors v = selectVendor();
			addProduct(v,u);
		} else if (choice.equalsIgnoreCase("update")) {
			Vendors v = selectVendor();
			Products p = selectProduct(v);
			updateProduct(p, v,u);
		} else if (choice.equals("help"))
			productFunctions();
		else if (choice.equalsIgnoreCase("back"))
			adminFunctions(u);
		else {
			System.out.println("Invalid function.");
			productOptions(u);
		}
	}

	//allows an admin to update a products information, the product to be updated is passed into it
	private static void updateProduct(Products p, Vendors v, Users u) {
		Products product = p;
		try{
			String name = Validator.getString(sc, "Enter name: ", 10);
			double price = Validator.getDouble(sc, "Enter price: ", 0, 1000000000);
			String unit = Validator.getString(sc, "Enter unit: ", 10);
			String photoPath = Validator.getString(sc, "Enter photopath: ");
			int vendorID = v.getID();
			product.setName(name);
			product.setPrice(price);
			product.setUnit(unit);
			product.setPhotoPath(photoPath);
			product.setVendorID(vendorID);
			productsDB.updateProduct(product);
			adminFunctions(u);
			
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

	//Allows an admin to add a product to the DB, makes sure that the partnumber is unique
	private static void addProduct(Vendors v, Users u) {
		Products product = new Products();
		try{
			boolean isValid = true;
			ArrayList<Products> products = productsDB.getAllProducts(v.getID());
			String partNumber = "";
			do {
				partNumber = Validator.getString(sc, "Enter partnumber: ");
				for (Products p : products) {
					if (partNumber.equalsIgnoreCase(p.getPartNumber())) {
						System.out.println("Error! Partnumber must be unique.");
						isValid = false;
					}
				}
			} while (isValid == false);
			String name = Validator.getStringWithinLength(sc, "Enter name: ", 10);
			double price = Validator.getDouble(sc, "Enter price: ", 0, 1000000000);
			String unit = Validator.getStringWithinLength(sc, "Enter unit: ", 10);
			String photoPath = Validator.getStringWithinLength(sc, "Enter photopath: ",255);
			int vendorID = v.getID();
			product.setName(name);
			product.setPrice(price);
			product.setPartNumber(partNumber);
			product.setUnit(unit);
			product.setPhotoPath(photoPath);
			product.setVendorID(vendorID);
			productsDB.addProduct(product);
			adminFunctions(u);
			
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	//user page for admins, allows them to affect the Users table in the DB
	private static void usersOptions(Users u) {
		usersFunctions();
		String choice = Validator.getString(sc, "Enter function: ");
		if(choice.equalsIgnoreCase("view")){
			viewUsers();
			usersOptions(u);
		}
		else if(choice.equalsIgnoreCase("add"))
			addUser(u);
		else if(choice.equalsIgnoreCase("update"))
			updateUser(u);
		else if(choice.equals("help"))
			usersFunctions();
		else if(choice.equalsIgnoreCase("back"))
			adminFunctions(u);
		else{
			System.out.println("Invalid function.");
			usersOptions(u);
		}
		
	}
	
	//Allows an admin to update the users' information, after obtaining a valid usernames
	private static void updateUser(Users u) {
		Users user = new Users();
		try {
			boolean isValid = true;
			String username = "";
			ArrayList<Users> users = usersDB.getAllUsers();
			while (isValid == false) {
				username = Validator.getStringWithinLength(sc, "Enter username: ", 20);
				for (Users v : users) {
					if (username.equalsIgnoreCase(v.getUsername())) {
						isValid = true;
					}
				}
				if (isValid == false)
					System.out.println("Error! No user by that username.");
			}
			String password = Validator.getStringWithinLength(sc, "Enter password: ", 15);
			String firstName = Validator.getStringWithinLength(sc, "Enter first name: ", 20);
			String lastName = Validator.getStringWithinLength(sc, "Enter unit: ", 20);
			String phone = Validator.getString(sc, "Enter phone: ", 12);
			String email = Validator.getStringWithinLength(sc, "Enter email: ", 75);
			String role = Validator.getString(sc, "Enter role: ", "manager", "employee");
			int roleID = 0;
			if(role.equals("manager"))
				roleID = 2;
			if(role.equals("employee"))
				roleID = 1;
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(phone);
			user.setEmail(email);
			user.setRoleID(roleID);
			usersDB.updateUser(user);
			usersOptions(u);

		} catch (DBException e) {
			e.printStackTrace();
		}

	}

	//Allows an admin to add a user to the DB, requires a unique username
	private static void addUser(Users use) {
		Users user = new Users();
		try{
			boolean isValid = true;
			ArrayList<Users> users = usersDB.getAllUsers();
			String username = "";
			do {
				username = Validator.getStringWithinLength(sc, "Enter username: ", 20);
				for (Users u : users) {
					if (username.equalsIgnoreCase(u.getUsername())) {
						System.out.println("Error! Username must be unique.");
						isValid = false;
					}
				}
			} while (isValid == false);
			String password = Validator.getStringWithinLength(sc, "Enter password: ", 15);
			String firstName = Validator.getStringWithinLength(sc, "Enter first name: ", 20);
			String lastName = Validator.getStringWithinLength(sc, "Enter unit: ", 20);
			String phone = Validator.getString(sc, "Enter phone: ", 12);
			String email = Validator.getStringWithinLength(sc, "Enter email: ", 75);
			String role = Validator.getString(sc, "Enter role: ", "manager", "employee");
			int roleID = 0;
			if(role.equals("manager"))
				roleID = 2;
			if(role.equals("employee"))
				roleID = 1;
			user.setUsername(username);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(phone);
			user.setEmail(email);
			user.setRoleID(roleID);
			usersDB.addUser(user);
			usersOptions(use);
			
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

	//Display menu for an admin's user functions
	private static void usersFunctions(){
		System.out.println("Admin users functions: ");
		System.out.println("view\t\t-View users");
		System.out.println("add\t\t-Add users");
		System.out.println("update\t\t-Update users");
		System.out.println("help\t\t-Display this menu");
		System.out.println("back\t\t-Go back to the previous menu\n");
	}

	//Shows all the Vendors in the Vendors DB
	private static void viewVendors(){
		try {
			ArrayList<Vendors> vendors = vendorsDB.getAllVendors();
			for(Vendors v:vendors)
			{
				System.out.println("ID: "+v.getID());
				System.out.println("Code: "+v.getCode());
				System.out.println("Name: "+v.getName());
				System.out.println("Address: "+v.getAddress());
				System.out.println("City: "+v.getCity());
				System.out.println("State: "+v.getState());
				System.out.println("Zipcode: "+v.getZipCode());
				System.out.println("Phone: "+v.getPhone());
				System.out.println("Email: "+v.getEmail());
				System.out.println("Preapproved vendor: "+v.isPreapproved()+"\n");
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Shows all the Users in the Users DB
	private static void viewUsers(){
		try {
			ArrayList<Users> users = usersDB.getAllUsers();
			for(Users u:users){
				System.out.println("ID: "+u.getID());
				System.out.println("Username: "+u.getUsername());
				System.out.println("Password: "+u.getPassword());
				System.out.println("First Name: "+u.getFirstName());
				System.out.println("Last Name: "+u.getLastName());
				System.out.println("Phone: "+u.getPhone());
				System.out.println("Email: "+u.getEmail());
				System.out.println("Role: "+u.getRole());
				System.out.println("RoleID: "+u.getRoleID());
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Vendors selectVendor(){
		Vendors v = new Vendors();
		try {
			viewVendors();
			boolean isValid = false;
			while(isValid == false){
			String choice = Validator.getLine(sc, "Enter code: ");
			v = vendorsDB.getVendor(choice);
			if(isValid == false)
				System.out.println("Error! Invalid code.");
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return v;
		
	}
		
	private static void manageRequest(Users u) {
		viewRequests(u,false);
		try {
			ArrayList<Requests> itemRequests = requestsDB.getAllRequests();
			int choice = Validator.getInt(sc, "Enter request to affect: ", 0, itemRequests.size()+1);
			for(Requests r:itemRequests){
				if(r.getID() == choice){
					String status = Validator.getString(sc, "Enter status: ", "approved", "rejected");
					r.setStatus(status);
					requestsDB.updateRequest(r);
				}
			}
		} catch (DBException e) {
			e.printStackTrace();
		}
		managerFunctions(u);
	}

	private static void employeeFunctions(Users u) {
		viewEmployeeMenu();
		String choice = Validator.getString(sc, "Enter function: ");
		if (choice.equalsIgnoreCase("view"))
			viewRequests(u, true);
		else if (choice.equalsIgnoreCase("create"))
			createRequest(u);
		else if (choice.equalsIgnoreCase("help"))
			viewEmployeeMenu();
		else if (choice.equalsIgnoreCase("exit"))
			System.out.println("goodbye");
		else {
			System.out.println("Invalid function.");
			employeeFunctions(u);
		}

	}

	private static void createRequest(Users u) {
		
		//get Request info
		double total = 0;
		Requests r = new Requests();
		r.setDescription(Validator.getString(sc, "Enter description: "));
		r.setJustification(Validator.getString(sc, "Enter justification: "));
		r.setUsersID(u.getID());
		r.setDocAttached(false);
		Calendar submittedDateCal = Calendar.getInstance(); 
		submittedDateCal.getTime();
		java.util.Date submittedDate = submittedDateCal.getTime();
		java.sql.Date sqlDS = new java.sql.Date(submittedDate.getTime());
		java.util.Date dateNeeded = Validator.getDate(sc);
		java.sql.Date sqlDN = new java.sql.Date(dateNeeded.getTime());
		r.setSubmittedDate(sqlDS);
		r.setDateNeeded(sqlDN);
		r.setDeliveryMode(Validator.getString(sc, "Enter delivery mode: ", "pickup", "delivery"));
		
		//get a full detail for their request and only populate it when they don't need anything else.
		ArrayList<LineItems> lineItemsArray = new ArrayList<LineItems>();
		String choice = "";
		Vendors v = selectVendor();
		do{
		//Get line Item info
		vendorProducts(v);
		Products p = selectProduct(v);
		int q = Validator.getInt(sc, "Enter quantity: ", 0, 1000);
		LineItems lineItem = new LineItems();
		lineItem.setPartNumber(p.getPartNumber());
		lineItem.setProductID(p.getProductsID());
		lineItem.setQuantity(q);
		lineItem.setRequestID(u.getID());
		lineItem.setTotal(q*p.getPrice());
		lineItemsArray.add(lineItem);
		choice = Validator.getChoice(sc, "Another product? ");
		total += q*p.getPrice();
		}while(choice.equalsIgnoreCase("y")||choice.equalsIgnoreCase("yes"));
		try {
			r.setTotal(total);
			if(total<50||v.isPreapproved())
				r.setStatus("approved");
			else
				r.setStatus("submitted");
			requestsDB.addRequest(r);

			for(LineItems l:lineItemsArray){
				total += l.getTotal();
				lineItemsDB.addLineItem(l);
			}
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeFunctions(u);
	}

	private static Products selectProduct(Vendors v) {
		Products p = null;
		try{
			boolean isValid = false;
			while (isValid == false) {
				String pCode = Validator.getString(sc, "Enter part number: ");
				p = productsDB.getProduct(pCode, v.getID());
				if (p == null) {
					System.out.println("Error! Product not found");
				} else{
					isValid = true;
				}
			}
		}catch (DBException e){
			System.out.println(e + "\n");
		}
		return p;
	}

	private static void vendorProducts(Vendors v){
		ArrayList<Products> product = null;
		try{
			product = productsDB.getAllProducts(v.getID());
			if(product == null){
				System.out.println("Error! Vendor Products not found.");
			} else {
				for (Products p : product) {
					System.out.println("Name: " + p.getName());
					System.out.println("Part Number: " + p.getPartNumber());
					System.out.println("Price: " + currency.format(p.getPrice()));
					System.out.println("Unit: " + p.getUnit());
					System.out.println("Photopath: " + p.getPhotoPath() + "\n");
				}
			}
		} catch (DBException e){
			System.out.println(e + "\n");
		}
	}

	private static void vendorFunctions(){
			System.out.println("Admin vendor functions: ");
			System.out.println("view\t\t-View vendors");
			System.out.println("add\t\t-Add vendors");
			System.out.println("update\t\t-Update vendor");
			System.out.println("help\t\t-Display this menu");
			System.out.println("back\t\t-Go back to the previous menu\n");
	}
	
	private static void productFunctions(){
		System.out.println("Admin product menu: ");
		System.out.println("view\t\t-View products");
		System.out.println("add\t\t-Add products");
		System.out.println("update\t\t-Update product");
		System.out.println("help\t\t-Display this menu");
		System.out.println("back\t\t-Go back to the previous menu\n");
	}
	
	private static void viewRequests(Users u, boolean isValid) {
		ArrayList<Requests> itemRequests = new ArrayList<>();
		ArrayList<LineItems> lineItemRequests = new ArrayList<>();
		try {
			if (u.getRole().equals("Manager")) {
				itemRequests = requestsDB.getAllRequests();
				lineItemRequests = lineItemsDB.getAllLineItems();
			} else if (u.getRole().equals("Employee")) {
				itemRequests = requestsDB.getUserRequests(u.getID());
				for(Requests r: itemRequests){
					ArrayList<LineItems> w = lineItemsDB.getUsersLineItems(r.getID());
					for(LineItems p:w){
						lineItemRequests.add(p);
					}
				}
			}
			for(Requests r: itemRequests){
				System.out.println(usersDB.getUsersFullname(r.getUsersID())+" requests: ");
				for(LineItems p: lineItemRequests){
					System.out.println(p.getQuantity()+"\t"+p.getPartNumber()+"\t"+"Costing: "+currency.format(p.getTotal()));
				}
				System.out.println("Request number: "+r.getID());
				System.out.println("Description: "+r.getDescription());
				System.out.println("Justification: "+r.getJustification());
				System.out.println("Date Needed: "+r.getDateNeeded());
				System.out.println("Date Submitted: "+r.getSubmittedDate());
				System.out.println("Delivery Mode: "+r.getDeliveryMode());
				System.out.println("Status: "+r.getStatus());
				System.out.println("Total: "+currency.format(r.getTotal())+"\n");
			}
			if (u.getRole().equals("Manager")&& isValid == true) {
				managerFunctions(u);
			} else if (u.getRole().equals("Employee")) {
				employeeFunctions(u);
			}
		} catch (DBException e) {
			System.out.println(e + "\n");
		}
	}

	private static void viewEmployeeMenu(){
		System.out.println("Functions for employees");
		System.out.println("view\t\t-View requests and their statuses");
		System.out.println("create\t\t-Create request");
		System.out.println("help\t\t-Display this menu");
		System.out.append("exit\t\t-Exit this menu\n");
	}
	
	private static void viewManagerMenu(){
		System.out.println("Functions for managers");
		System.out.println("view\t\t-View requests and their statuses");
		System.out.println("manage\t\t-Manage requests");
		System.out.println("help\t\t-Display this menu");
		System.out.append("exit\t\t-Exit this menu\n");
	}
	
	private static void viewAdminMenu(){
		System.out.println("Functions for administrator");
		System.out.println("vendors\t\t-Access information of vendors");
		System.out.println("products\t-Access information of products");
		System.out.println("users\t\t-Access information of users");
		System.out.println("help\t\t-Display this menu");
		System.out.append("exit\t\t-Exit this menu\n");;
	}
	
	private static Users isValidUsername() {
		Users user = null;
		try {
			String username = Validator.getStringWithinLength(sc, "Enter username: ", 20);
			String password = "";
			user = usersDB.getUser(username);
			boolean isValid = false;
			while(isValid == false){
			if(user !=null){
				password = Validator.getStringWithinLength(sc, "Enter password: ", 15);

				if (!user.getPassword().equals(password)){
					System.out.println("Error! Invalid passwordd");
				}
				else
					isValid = true;
			}
			else{
				System.out.println("Error! Invalid username");
				isValidUsername();
			}
			}
		} catch (DBException e) {
			System.out.println(e + "\n");
		}
		return user;
	}
	
/*	private static Users isValidUsername() {
		ArrayList<Users> allUsers = null;
		Users user = null;
		try {
			allUsers = usersDB.getAllUsers();
			if (allUsers == null)
				System.out.println("ERROR! Unable to get users!");
			else {
				boolean isValid = false;
				do {
					String username = Validator.getStringWithinLength(sc, "Enter username: ", 20);
					for (Users u : allUsers) {
						if (u.getUsername().equals(username)) {
							isValidPassword(u);
							isValid = true;
							user = u;
						}
					}
					if(isValid == false)
						System.out.println("Error! Invalid username.");
				} while (isValid == false);
			}
		} catch (DBException e) {
			System.out.println(e + "\n");
		}
		return user;
	}
	
	private static void isValidPassword(Users user){
		boolean isValid = false;
		String password = "";
		while(isValid == false){
			password = Validator.getStringWithinLength(sc, "Enter password: ", 15);
			if(user.getPassword().equals(password))
				isValid = true;
			else
				System.out.println("Error! Wrong password");
		}
	}*/
}
