package Jdbc.LMS;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Database.DBConnection;

public class Librarian {
	private static final String Sql = null;
	DBConnection connect = new DBConnection();
	Scanner scan = new Scanner(System.in);
	//EmailException emailVerifier = new EmailException(null);

	public void adminoption() {
	    boolean continueLoop = true; // Control loop continuation

	    while (continueLoop) {
	    	System.out.println("-------------------------------------------");
			System.out.println("Librarian");
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
			System.out.println("Librarian registration");
			System.out.println("-------------------------------------------");
			System.out.print("enter the user Id (ex:L001):");
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
		System.out.println("Librarian login");
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
	            System.out.println("2. Library Card");
	            System.out.println("3. generate Reports");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scan.nextInt();

	            switch (choice) {
	                case 1:
	                    manageBooks();
	                    break;
	                case 2:
                    libraryCard();
	                    break;
	                case 3:
	                    generateReports();
	                    break;
	                case 4:
	                    continueLoop = false;
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	    }

	    public void manageBooks() {
	        boolean continueLoop = true;

	        while (continueLoop) {
	            System.out.println("-------------------------------------------");
	            System.out.println("Manage Books");
	            System.out.println("-------------------------------------------");

	            System.out.println("1. Add Book");
	            System.out.println("2. View Books");
	            System.out.println("3. Update Book");
	            System.out.println("4. Delete Book");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scan.nextInt();

	            switch (choice) {
	                case 1:
	                    addBook();
	                    break;
	                case 2:
	                    viewBook();
	                    break;
	                case 3:
	                    updateBook();
	                    break;
	                case 4:
	                    deleteBook();
	                    break;
	                case 5:
	                    continueLoop = false;
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	    }

	    public void addBook() {
	        try {
	            System.out.print("Enter book ID: ");
	            String book_id = scan.next();
	            System.out.print("Enter Book Name: ");
	            String bName = scan.next();
	            System.out.print("Enter Author Name: ");
	            String aName = scan.next();
	            scan.nextLine(); // Consume extra newline character

	            connect.InitiateDB();
	            String sql = "INSERT INTO books (book_id, bName,aName) VALUES (?, ?, ?)";
	            Object[] params = { book_id, bName,aName };
	            int rowsInserted = connect.InsertDB(sql, params);
	            if (rowsInserted > 0) {
	                System.out.println("A new book was added!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void viewBook() {
	        try {
	            connect.InitiateDB();
	            String sql = "SELECT BOOK_ID, BNAME, ANAME FROM BOOKS";
	            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();
	            
	            System.out.println("BOOK_ID\t\tBNAME\t\tANAME");
	            while(resultSet.next()) {
	                String book_Id = resultSet.getString("BOOK_ID");
	                String bName = resultSet.getString("BNAME");
	                String aName = resultSet.getString("ANAME");

	                System.out.println(book_Id + "\t\t" + bName + "\t\t" + aName);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void updateBook() {
	        try {
	            System.out.print("Enter the book_id of the book you want to update: ");
	            String title = scan.next();
	            System.out.print("Enter the new author: ");
	            String author = scan.next();
	            System.out.print("Enter the new book name: ");
	            String bName = scan.next();

	            connect.InitiateDB();
	            String sql = "UPDATE books SET aname = ? ,bName=? WHERE book_id = ?";
	            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	            statement.setString(1, author);
	            statement.setString(2, bName);
	            statement.setString(3, title);
	            

	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0)
	                System.out.println("Book updated successfully!");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteBook() {
	        try {
	            System.out.print("Enter the Book_ID of the book you want to delete: ");
	            String book_id = scan.next();

	            connect.InitiateDB();
	            String sql = "DELETE FROM books WHERE BOOK_ID = ?";
	            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	            statement.setString(1, book_id);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Book deleted successfully!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void libraryCard() {
	        boolean continueLoop = true;

	        while (continueLoop) {
	            System.out.println("-------------------------------------------");
	            System.out.println("Manage Library Card");
	            System.out.println("-------------------------------------------");

	            System.out.println("1. Add card");
	            System.out.println("2. View card");
	            System.out.println("3. Update card");
	            System.out.println("4. Delete card");
	            System.out.println("5. Update book Limit");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scan.nextInt();

	            switch (choice) {
	                case 1:
	                    addCard();
	                    break;
	                case 2:
	                    viewCard();
	                    break;
	                case 3:
	                    updateCard();
	                    break;
	                case 4:
	                    deleteCard();
	                    break;
	                case 5:
	                	updateLimit();
	                	break;
	                  
	                case 6: 
	                	  continueLoop = false;
		                    System.out.println("Exiting...");
		                    break;
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	    }

	    
	    public void addCard() {
	        try {
	            System.out.print("Enter card ID: ");
	            String card_id = scan.next();
	            System.out.println("Enter the Patrons Name: ");
	            String patron_Name = scan.next();
	            System.out.println("Enter the patrons contactNo.: ");
	            String c_No = scan.next();
	            System.out.println("Enter the Patrons Limits of book: ");
	            String book_Limit = scan.next();
	            System.out.print("Enter Patrons Locations: ");
	            String location = scan.next();

	            connect.InitiateDB();
	            String sql = "INSERT INTO libraryCard (CARD_ID, PATRON_NAME, C_NO, BOOK_LIMIT, LOCATION) VALUES (?, ?, ?, ?, ?)";
	            Object[] params = {card_id, patron_Name, c_No, book_Limit, location};
	            int rowsInserted = connect.InsertDB(sql, params);
	            if (rowsInserted > 0) {
	                System.out.println("A new Card was added!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void viewCard() {
	        try {
	            connect.InitiateDB();
	            String sql = "SELECT CARD_ID, PATRON_NAME, C_NO, BOOK_LIMIT, LOCATION, REMAINING_LIMIT FROM libraryCard";
	            PreparedStatement statement = connect.getConnection().prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();
	            
	            System.out.println("CARD_ID\t\tPATRON_NAME\t\tC_NO\t\tBOOK_LIMIT\t\tLOCATION\t\tREMAINING_LIMIT");
	            while(resultSet.next()) {
	                String cardId = resultSet.getString("CARD_ID");
	                String patron_Name = resultSet.getString("PATRON_NAME");
	                String cNo = resultSet.getString("C_NO");
	                String bookLimit = resultSet.getString("BOOK_LIMIT");
	                String location = resultSet.getString("LOCATION");
	                String remainingLimit = resultSet.getString("REMAINING_LIMIT");

	                System.out.println(cardId + "\t\t" + patron_Name + "\t\t" + cNo + "\t\t" + bookLimit + "\t\t" + location + "\t\t" + remainingLimit);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void updateCard() {
	        try {
	            System.out.print("Enter the card_id of the patron you want to update: ");
	            String card_id = scan.next();
	            scan.nextLine(); // Consume extra newline character

	            connect.InitiateDB();

	            // Check if the card exists
	            String checkSql = "SELECT * FROM LIBRARYCARD WHERE card_id = ?";
	            PreparedStatement checkStatement = connect.getConnection().prepareStatement(checkSql);
	            checkStatement.setString(1, card_id);
	            ResultSet resultSet = checkStatement.executeQuery();

	            if (!resultSet.next()) {
	                System.out.println("Card with ID " + card_id + " does not exist.");
	                return;
	            }

	            // Card exists, proceed with the update
	            System.out.print("Enter the new Patron Name: ");
	            String patron_Name = scan.nextLine();
	            System.out.print("Enter the new contact Number: ");
	            String c_No = scan.nextLine();
	            System.out.print("Enter the new Book Limits: ");
	            int book_Limit = scan.nextInt();
	            System.out.print("Enter the new Patrons Location: ");
	            String location = scan.nextLine();

	            String updateSql = "UPDATE LIBRARYCARD SET patron_Name=?, c_No=?, book_Limit=?, location=? WHERE card_id=?";
	            try (PreparedStatement statement = connect.getConnection().prepareStatement(updateSql)) {
	                statement.setString(1, patron_Name);
	                statement.setString(2, c_No);
	                statement.setInt(3, book_Limit);
	                statement.setString(4, location);
	                statement.setString(5, card_id);

	                int rowsUpdated = statement.executeUpdate();
	                if (rowsUpdated > 0) {
	                    System.out.println("Card updated successfully!");
	                } else {
	                    System.out.println("Failed to update card.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	    public void updateLimit () {
	    	try {
	    		System.out.println("Enter card id to update availability limit : ");
	    		String card_id = scan.next();
	    		
	    		
	    		 connect.InitiateDB();

		            // Check if the card exists
		            String checkSql = "SELECT * FROM LIBRARYCARD WHERE card_id = ?";
		            PreparedStatement checkStatement = connect.getConnection().prepareStatement(checkSql);
		            checkStatement.setString(1, card_id);
		            ResultSet resultSet = checkStatement.executeQuery();

		            if (!resultSet.next()) {
		                System.out.println("Card with ID " + card_id + " does not exist.");
		                return;
		            }

		           
		            System.out.println("Enter the remaining limit : ");
		            String rLimit = scan.next();
		            System.out.println("Enter the borrowerd limit : ");
		            String bLimit = scan.next();

		            String updateSql = "UPDATE LIBRARYCARD SET remaining_Limit = ?,bookBorrowed=? WHERE card_id=?";
		            try (PreparedStatement statement = connect.getConnection().prepareStatement(updateSql)) {
		                statement.setString(1, rLimit);
		                statement.setString(2, bLimit);
		                statement.setString(3, card_id);

		                int rowsUpdated = statement.executeUpdate();
		                if (rowsUpdated > 0) {
		                    System.out.println("Remaining Limit updated successfully!");
		                } else {
		                    System.out.println("Failed to update remaining limit.");
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
	    	
	    public void deleteCard() {
	        try {
	            System.out.print("Enter the card_id of the card you want to delete: ");
	            String card_id = scan.next();

	            connect.InitiateDB();
	            String sql = "DELETE FROM LIBRARYCARD WHERE CARD_ID = ?";
	            try (PreparedStatement statement = connect.getConnection().prepareStatement(sql)) {
	                statement.setString(1, card_id);

	                int rowsDeleted = statement.executeUpdate();
	                if (rowsDeleted > 0) {
	                    System.out.println("Card deleted successfully!");
	                } else {
	                    System.out.println("No card found with ID: " + card_id);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void generateReports() {
            System.out.println("------------------------------------------------------");
            int input;
            do {
                System.out.println("Report Management");
                System.out.println("1. Book Details");
                System.out.println("2. Card Details");
                System.out.println("3. Back");
                System.out.print("Enter your operation: ");
                input = scan.nextInt();
                switch (input) {
                    case 1:
                    	viewBook();
                        break;
                    case 2:
                    	viewCard();
                        break;
                    case 4:
                        return; // Return from the method, exiting the loop
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        break;
                }
            } while (true);
        }
}


	    	
	    	
	    







