package my.edu.utar;

public class Printer {
	
	public Printer() {
		
	}
	
	public void printInfo(User user,String roomType) {
		System.out.println("Name: " + user.getName() 
		+ "\nMember Type: " + user.getMemberType()
		+ "\nRoom Type: " + roomType			
		);
	}
}
