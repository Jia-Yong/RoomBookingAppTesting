package my.edu.utar;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class UserTest {
	
	//EP approach
	@Test	
	@Parameters(method = "getUserTestData")
	public void set_bookingTest(String name, String memberType, String roomType, Booking[] expectedBookingArray)
	{
		User user = new User(name, memberType);
		Room room = new Room();
		
		Booking book1 = new Booking(roomType, user, room);
		Booking book2 = new Booking(roomType, user, room);
		Booking book3 = new Booking(roomType, user, room);
		
		user.set_booking(book1);
		user.set_booking(book2);
		user.set_booking(book3);
		
		Booking[] tempBooking = new Booking[3];
		tempBooking = user.get_booking();
		
		assertArrayEquals(expectedBookingArray, tempBooking);
	}
	
	//EP approach
	@Test
	@Parameters(method = "getUserTestData")
	public void cancel_bookingTest(String name, String memberType, String roomType, Booking[] expectedBookingArray)
	{
		User user = new User(name, memberType);
		Room room = new Room();
		Booking book1 = new Booking(roomType, user, room);
		Booking book2 = new Booking(roomType, user, room);
		Booking book3 = new Booking(roomType, user, room);
		
		user.set_booking(book1);
		user.set_booking(book2);
		user.set_booking(book3);
		
		user.cancel_booking();
		
		assertNull(user.get_booking());
	}
	
	private Object[] getUserTestData() {
		
		User user = new User("Lai", "vip");
		Room room = new Room();
		
		Booking[] tempVipBook = new Booking[3];
		tempVipBook[0] = new Booking("vip", user, room);
		tempVipBook[1] = new Booking("vip", user, room);
		tempVipBook[2] = new Booking("vip", user, room);
		
		Booking[] tempDeluxeBook = new Booking[3];
		tempDeluxeBook[0] = new Booking("deluxe", user, room);
		tempDeluxeBook[1] = new Booking("deluxe", user, room);
		tempDeluxeBook[2] = new Booking("deluxe", user, room);
		
		Booking[] tempStandardBook = new Booking[3];
		tempStandardBook[0] = new Booking("standard", user, room);
		tempStandardBook[1] = new Booking("standard", user, room);
		tempStandardBook[2] = new Booking("standard", user, room);
		
		return new Object[] {
				new Object[] {"Lai","vip","vip", tempVipBook},
				new Object[] {"Lai","vip","deluxe", tempDeluxeBook},
				new Object[] {"Lai","vip","standard", tempStandardBook}
		};
	}
	
}
