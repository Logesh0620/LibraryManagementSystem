//package Jdbc.LMS;
//import java.util.Scanner;
//import Database.DBConnection;
//class Library {
//	static int id;
//	static String name,date,book,returnDate;
//	DBConnection db=new DBConnection();
//	Scanner sc=new Scanner(System.in);
////	public void library()
////	{
////		
////	}
//	void add()
//	{
//		 db.InitiateDB();
//		System.out.println("Enter a Book_no,book Name,AuthorName and Prize");
//		int bookNo=sc.nextInt();
//		String name=sc.nextLine();
//		String author=sc.nextLine();
//		float price=sc.nextFloat();
//		  String sql = "INSERT INTO Bookdetails (BOOK_ID,BOOK_Name,AUTHOR_NAME, PRICE) VALUES (?, ?, ?, ?)"; 
//				  Object[] params = { bookNo, name, author, price }; 
//				   
//				          int rowsInserted = db.InsertDB(sql, params); 
//				          if (rowsInserted > 0)  
//				              System.out.println("User details was inserted successfully!"); 
//				          else 
//				           System.out.println("User details was not inserted..."); 
//				           
//		
//				   } 
//		
//		
//	void issue()
//	{
//		System.out.println("Book Name:");
//		 name=sc.nextLine();
//		System.out.println("Book_id:");
//		id =sc.nextInt();
//		sc.nextLine();
//		System.out.println("Issue date:");
//		 date=sc.nextLine();
//		System.out.println("Total number of book Issued");
//		 book=sc.nextLine();
//		 sc.nextLine();
//		System.out.println("Return Book date");
//		 returnDate=sc.nextLine();
//	}
//	int getid()
//	{
//		return id;
//	}
//	void returnBook()
//			{
//		System.out.println("Enter Patrons_name & book_id");
//		String pname=sc.nextLine();
//		int bookid=sc.nextInt();
//		if(id==bookid)
//		{
//			System.out.println("Total details");
//			System.out.println("Book Name:"+Library.name);
//			System.out.println("Book id:"+Library.id);
//			System.out.println("issue date:"+Library.date);
//			System.out.println("Total number of book Issued:"+Library.book);
//			System.out.println("Book Return date:"+Library.returnDate);
//		}
//		else
//		{
//			System.out.println("worng id,pls enter correct id");
//			
//		}
//			}
//	void details()
//	{
//		System.out.println("Book Name:"+Library.name);
//		System.out.println("Book id:"+Library.id);
//		System.out.println("issue date"+Library.date);
//		System.out.println("Total number of book Issued:"+Library.book);
//		System.out.println("Book Return date:"+Library.returnDate);
//	}
//	void exit()
//	{
//		System.exit(0);
//	}
//
//}
