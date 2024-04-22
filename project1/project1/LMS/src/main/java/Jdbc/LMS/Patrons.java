package Jdbc.LMS;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Database.DBConnection;

public class Patrons {
	DBConnection connect = new DBConnection();
	Scanner scan = new Scanner(System.in);
	//EmailException emailVerifier = new EmailException(null);

	public void patronsOption() {
	    boolean continueLoop = true; // Control loop continuation

	    while (continueLoop) {
	    	System.out.println("-------------------------------------------");
			System.out.println("Patrons");
			System.out.println("-------------------------------------------");
	        System.out.println("1. Registration");
	        System.out.println("2. Login");
	        System.out.println("3. Exit");
	        System.out.print("Enter your choice: ");
	        int choice1 = scan.nextInt();

	        switch (choice1) {
	            case 1:
	                register();
	                break;
	            case 2:
	                login();
	                break;
	            case 3:
	                continueLoop = false; // Exit the loop
	                System.out.println("Exiting...");
	                break;
	            default:
	                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
	                break;
	        }
	    }
	}


	// register method starts here
	public void register() {

		try {
			System.out.println("-------------------------------------------");
			System.out.println("Patrons registration");
			System.out.println("-------------------------------------------");
			System.out.print("enter the user Id (ex:P001):");
			String userid = scan.next();

			System.out.print("enter the user name:");
			String username = scan.next();

			System.out.print("enter the Email:");
			String Email = scan.next();

			//emailVerifier.setEmail(Email);
			//System.out.println("Email is valid.");

			System.out.print("enter the contact no:");
			String contact = scan.next();

			System.out.print("enter the Password:");
			String Password = scan.next();
            DBConnection db=new DBConnection();
            db.InitiateDB();
		//	connect.InitiateDB();
			String sql = "INSERT INTO UserTable (user_id,user_name,user_email,user_phone,user_psw) VALUES (?, ?, ?, ?, ?)";
			Object[] params = { userid, username, Email, contact, Password };
			int rowsInserted = db.InsertDB(sql, params);
			if (rowsInserted > 0) {
				System.out.println("A new user was inserted!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public void login() {
		System.out.println("-------------------------------------------");
		System.out.println("Patrons Login");
		System.out.println("-------------------------------------------");
		System.out.print("enter the user id:");
		String userid = scan.next();
		System.out.print("enter the Password:");
		String Password = scan.next();
		if (canUserLogin(userid, Password)) {
			System.out.println("Login successful!");
			start();
		} else {
			System.out.println("Login failed. Invalid username or password.");
		}
	}

	// Method to check if the user can log in with the provided username and
	// password
	public boolean canUserLogin(String userid, String password) {
		boolean isValidUser = false;
		try {
		connect.InitiateDB(); // Ensure the database connection is initiated

		
			// Prepare SQL query to select the user
			String sql = "SELECT * FROM UserTable WHERE user_id = ? AND user_psw = ?";
			PreparedStatement statement = connect.getConnection().prepareStatement(sql);

			// Set the parameters for the prepared statement
			statement.setString(1, userid);
			statement.setString(2, password);

			// Execute the query
			ResultSet resultSet = statement.executeQuery();

			// If the result set is not empty, then the user exists
			isValidUser = resultSet.next();

			// Close the resultSet and statement
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isValidUser;
	}
	public void start() {
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("-------------------------------------------");
            System.out.println("Library Management System");
            System.out.println("-------------------------------------------");

            System.out.println("1. Manage Books");
            System.out.println("2. Borrower Book");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    manageBooks();
                    break;
                case 2:
                	borrowerBook();
                    break;
                case 3:
                    continueLoop = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
	public void manageBooks()
	{
		 boolean continueLoop = true;
		System.out.println("1. view Book");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                viewBook();
                break;          
            case 2:
                continueLoop = false;
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
	public void viewBook() {

		try {
//			System.out.print("Enter book code to view details: ");
//			String book_id = scan.next();
//			scan.nextLine(); // Consume extra newline character

			connect.InitiateDB();
			String sql = "SELECT * FROM Books";
			PreparedStatement statement = connect.getConnection().prepareStatement(sql);
		//	statement.setString(1, book_id);

			ResultSet resultSet = statement.executeQuery();
			
			System.out.println("--------------------------------------");
			System.out.println("\tAvailability of Books");
			System.out.println("--------------------------------------");

			System.out.println("--------------------------------------");
			System.out.println("BOOK_ID\t\tBNAME\t\tANAME");
			System.out.println("--------------------------------------");

			while(resultSet.next()) {
				String bookId = resultSet.getString("BOOK_ID");
				String bName = resultSet.getString("BNAME");
				String aName = resultSet.getString("ANAME");
				
				System.out.println(bookId+"\t\t"+bName+"\t\t"+aName);
				System.out.println("--------------------------------------");

			}
//			if (resultSet.next()) {
//				String bName = resultSet.getString("bName");
//				System.out.println("Book Code: " + book_id);
//				System.out.println("Book Name: " + bName);
//			} else {
//				System.out.println("Book not found with code: " + book_id);
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//***********************************************************
	
	public void borrowerBook() {
	    try {
	        System.out.println("Enter your card id : ");
	        String cardId = scan.next();

	        connect.InitiateDB();

	        if (canCardLogin(cardId)) {
	        	
//	        	updateBooksBorrowed(cardId);
//                System.out.println("Book borrowed successfully.");
	            if (checkBookLimit1(cardId)) {
	                // Increment the books borrowed count and update the database
	                updateBooksBorrowed(cardId);
	                System.out.println("Book borrowed successfully.");
	                updateBooksBorrowed(cardId);
	                remainingLimit(cardId);
	                } else {
	                System.out.println("Sorry, you have reached the limit for borrowing books.");
	            }
	        } else {
	            System.out.println("Invalid card ID. Please try again.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the SQL exception appropriately, such as logging the error or displaying an error message
	    }
	}

//	private boolean checkBookLimit(String cardId) {
//		
//		
//		return false;
//	}


	public boolean canCardLogin(String card_Id) throws SQLException {
	    boolean isValidUser = false;
	    // Prepare SQL query to select the user
	    String sql = "SELECT * FROM librarycard WHERE card_id = ?";

	    //	    String sql = "SELECT * FROM librarycard WHERE card_Id = ?";
	    PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	    // Set the parameters for the prepared statement
	    statement.setString(1, card_Id);
	    // Execute the query
	    ResultSet resultSet = statement.executeQuery();
	    // If the result set is not empty, then the user exists
	    isValidUser = resultSet.next();
	    // Close the resultSet and statement
	    resultSet.close();
	    statement.close();
	    return isValidUser;
	}
	
	public boolean checkBookLimit1(String card_Id) throws SQLException {
	    boolean canBorrow = false;
	    // Prepare SQL query to get the current book count for the user
	    String sql = "SELECT bookBorrowed, book_Limit FROM librarycard WHERE card_Id = ?";
	    PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	    statement.setString(1, card_Id);

	    ResultSet resultSet = statement.executeQuery();
	    if (resultSet.next()) {
	        int bookBorrowed = resultSet.getInt("bookBorrowed");
	        int book_Limit = resultSet.getInt("book_limit");
	        canBorrow = bookBorrowed < book_Limit;
	    }
	    resultSet.close();
	    statement.close();
	    return canBorrow;
	}


	public void updateBooksBorrowed(String cardId) throws SQLException {
	    // Prepare SQL update statement to increment books borrowed count
		int bookBorrowed = 0;
		
		String sql = "UPDATE librarycard SET bookBorrowed = bookBorrowed + 1 WHERE card_id = ?";

		//	    String sql = "UPDATE librarycard SET books_borrowed = books_borrowed + 1 WHERE card_Id = ?";
	    PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	    statement.setString(1, cardId);
	    // Execute the update
	    
	    statement.executeUpdate();
	    statement.close();
	    
	}


public void remainingLimit(String cardId) throws SQLException {
    // Prepare SQL update statement to increment books borrowed count
	String sql = "UPDATE librarycard SET remaining_Limit = book_limit-bookBorrowed WHERE card_id = ?";

	//	    String sql = "UPDATE librarycard SET books_borrowed = books_borrowed + 1 WHERE card_Id = ?";
    PreparedStatement statement = connect.getConnection().prepareStatement(sql);
    statement.setString(1, cardId);
    // Execute the update
    statement.executeUpdate();
    statement.close();
}
}


	//***********************************************************
