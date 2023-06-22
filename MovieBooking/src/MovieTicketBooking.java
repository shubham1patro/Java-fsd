import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MovieTicketBooking {
    
    private static int counter=0;
    private static final int ROWS = 5;
    private static final int COLUMNS = 10;
    private static final String[][] SEATING_ARRANGEMENT = new String[ROWS][COLUMNS];
    
    // HashMap to store the booking details
    private static HashMap<String, ArrayList<String>> bookingDetails = new HashMap<>();
    
    // Login credentials for the front desk
    private static final String USERNAME = "a";
    private static String PASSWORD = "p";
    
    // Scanner object for input
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeSeatingArrangement();
//        loginToTheatre();
        displayMenu();
        bookTicket();
        displaySeatingArrangement();
        displayBookingDetails();
    }
    
	// Method to initialize the seating arrangement for the theatre
    private static void initializeSeatingArrangement() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                SEATING_ARRANGEMENT[i][j] = "o";                
            }
        }
    }
    
    // Method to login to the theatre using username and password
//    private static void loginToTheatre() {
//        System.out.println("Please login to the theatre");
//        System.out.print("Username: ");
//        String username = scanner.nextLine();
//        System.out.print("Password: ");
//        String password = scanner.nextLine();
//        
//        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
//            System.out.println("Login successful!");
//            System.out.print("---------------------------------------------------->");
//            displayMenu();
//        } else {
//            System.out.println("Invalid username or password!");
//            loginToTheatre();
//        }
//    }
    
    // Method to display the booking menu
    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Book a ticket");
        System.out.println("2. View seating arrangement");
        System.out.println("3. View booking details");
        System.out.println("4. Update Password");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: \n");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        switch (choice) {
            case 1:
                bookTicket();
                break;
            case 2:
                displaySeatingArrangement();
                displayMenu();
                break;
            case 3:
                displayBookingDetails();
                break;
            case 4:
                updatePassword();
                break;    
            case 5:
                System.out.println("Thank you for using our service!");                
                System.exit(0);
            default:
                System.out.println("Invalid choice! Please try again.");
                displayMenu();
        }
    }
    
 // Method to book a ticket for a date and showtime by entering the seat selection
    private static void bookTicket() {
    	System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Movie Name: ");
        String moviename = scanner.nextLine();
        System.out.print("Enter the date in (DD-MM-YY): ");
        String date = scanner.nextLine();
        System.out.print("Enter the show time in (HH:MM): ");
        String showTime = scanner.nextLine();
        
        
        // Get the seat selection from the user
        
        try {
        	// Get the number of tickets from the user        	
            System.out.print("Please enter the number of tickets: ");
            int numberOfTickets = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            String seatNumber[]= new String[numberOfTickets];
            
            
            for(int i=0;i<numberOfTickets;i++) {
     
            	System.out.print("\nPlease enter the row number: ");
                int rowNumber = scanner.nextInt();
                System.out.print("Please enter the column number: ");
                int columnNumber = scanner.nextInt();
            
                if ( rowNumber > ROWS  || columnNumber > COLUMNS) {
                    System.out.println("\nInvalid seat selection! Please try again.");
                    --i;
                    System.out.println("Select other or do you wish to finish booking? Press 1 to terminate booking. Press 2 to continue booking.");
                    int choice=scanner.nextInt();
                    if(choice==1) {
                       	break;
                        
                    }else {
                    	continue;
                    }
                }
            
            // Check if the seat is available
            if (SEATING_ARRANGEMENT[rowNumber - 1][columnNumber - 1].equals("o")) {
                // Seat is available, book the ticket
                SEATING_ARRANGEMENT[rowNumber - 1][columnNumber - 1] = "🤵";
                
                String seatNum = rowNumber + "-" + columnNumber;
                seatNumber[counter++]=seatNum;
                                
                }
             else {
                // Seat is not available, display error message
                System.out.println("\nSorry, the seat  not available!");
                --i;
                System.out.println("Select other or do you wish to finish booking? Press 1 to terminate booking. Press 2 to continue booking.");
                int choice=scanner.nextInt();
                if(choice==1) {
                   	break;
                    
                }else {
                	continue;
                }
                
            }
            
            }
//            Calculate the amount and display it to the user
            int price = 1000;
            int amount = price * counter;
            System.out.println("\nTotal amount: Rs" + amount);
            System.out.println("\nTicket booked successfully!");
            System.out.print("---------------------------------------------------->");

            // Store the booking details
            
            String seatNumS=Arrays.toString(seatNumber);
            String bookingDetailsString = "Movie:" + moviename + ", Seat number(s): " + seatNumS + "\nNumber of tickets: " + counter + ", Amount: Rs" + amount ;
            String bookingKey = date + " " + showTime + " " + name;
                ArrayList<String> bookings = new ArrayList<>();
                bookings.add(bookingDetailsString);
                bookingDetails.put(bookingKey, bookings);         
            counter=0;    
            displayMenu();
            
        }catch (InputMismatchException e) {
            System.out.println("\nInvalid input! Please try again.");
            scanner.nextLine(); 
            displayMenu();
        }
    }

private static void displaySeatingArrangement() {
	// TODO Auto-generated method stub 
	for (int i = 0; i < ROWS; i++) {
		for (int j = 0; j < COLUMNS; j++) {
			System.out.print(SEATING_ARRANGEMENT[i][j] + " ");
        }
        System.out.println();        
        System.out.print("---------------------->\n");


    }
    
}
//Method to check or enquire the booking status 
private static void displayBookingDetails() {
	System.out.print("Enter Name: ");
    String name = scanner.nextLine();
	 System.out.print("\nPlease enter the date: ");
	    String date = scanner.nextLine();
	    System.out.print("Please enter the show time: ");
	    String showTime = scanner.nextLine();
	    

	    String bookingKey = date + " " + showTime + " " + name;
	   
	    ArrayList<String> bookingList = bookingDetails.getOrDefault(bookingKey, new ArrayList<String>());

	    System.out.println("\nBooking status for " + name + "," + date + "," + showTime + ":"  );
	    if (bookingList.isEmpty()) {
	        System.out.println("No tickets booked!");
	    }else{
	        for (String seat : bookingList) {
	            System.out.println(seat);
	        }
	    }
	   
 displayMenu();
}
//Method to update the password
private static void updatePassword() {
 System.out.print("\nPlease enter the current password: ");
 String currentPassword = scanner.nextLine();

 if (currentPassword.equals(PASSWORD)) {
     System.out.print("Please enter the new password: ");
     String newPassword = scanner.nextLine();

     System.out.print("Please confirm the new password: ");
     String confirmPassword = scanner.nextLine();

     if (newPassword.equals(confirmPassword)) {
         PASSWORD = newPassword;
         System.out.println("Password updated successfully!");
     } else {
         System.out.println("New password and confirmation password do not match!");
     }
 } else {
     System.out.println("Invalid current password! Password not updated.");
 }
 loginToTheatre();
}}



		
	
	

