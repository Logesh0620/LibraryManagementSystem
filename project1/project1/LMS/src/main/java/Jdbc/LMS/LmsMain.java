package Jdbc.LMS;
import java.util.Scanner;

public class LmsMain {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("-------------------------------------------");
			System.out.println("Welcome to the Library Management System");
			System.out.println("-------------------------------------------");
			System.out.println("1. Librarian");
			System.out.println("2. Patrons");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();

			Librarian library = new Librarian();
			Patrons patron = new Patrons();
			
			switch (choice) {
			case 1:
				library.adminoption();
				break;
			case 2:
				patron.patronsOption();
				break;
			case 3:
				System.out.println("Exiting the system...");
				scanner.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}