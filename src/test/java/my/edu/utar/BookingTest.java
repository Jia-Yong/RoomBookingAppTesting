package my.edu.utar;
import junitparams.JUnitParamsRunner;
import static org.mockito.Mockito.*;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
public class BookingTest {
	
	private Room rMock;
	
	@Before
	public void init() {	
		 rMock = mock(Room.class);
	}
	
	//------------------------------------------------------------------------------------------------------------------
	//Test setBooking method in Booking class with all valid data if all rooms available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest1Data")
	public void setBookingTest1(String name,String memberType,String roomType, String expected, boolean checkRoom) {
		User user = new User(name,memberType);
		Booking book = new Booking(roomType,user,rMock);
		when(rMock.checkRoom(roomType)).thenReturn(checkRoom); 
		String result = book.setBooking();
		assertEquals(expected,result);
				
	}
	
	//Test cancelBooking method in Booking class with all valid data if all rooms available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest1Data")
	public void cancelBookingTest1(String name,String memberType,String roomType, String expected, boolean checkRoom)
	{
			User user = new User(name,memberType);
			Booking book = new Booking(roomType,user,rMock);
			WaitingList wl = new WaitingList();
			when(rMock.checkRoom(roomType)).thenReturn(checkRoom);
			String result = book.setBooking();
			book.cancelBooking(wl, user);
			
			assertArrayEquals(null, user.get_booking());
	}
		
	
	private Object[] getBookingTest1Data() {
		return new Object[] {
		new Object[] {"Lai", "vip","vip","vip",true},
		new Object[] {"Leon","vip","deluxe","deluxe",true},
		new Object[] {"Lim","vip","standard","standard",true},
		new Object[] {"Loh","member","vip","vip",true},
		new Object[] {"Lee","member","deluxe","deluxe",true},
		new Object[] {"Tan","member","standard","standard",true},
		new Object[] {"Chan","normal","standard","standard",true}
		};
	}

	
	//------------------------------------------------------------------------------------------------------------------
	
	//Test setBooking method in Booking Class with all invalid data if all rooms available
	//EP approach
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getBookingTest2Data")
	public void setBookingTest2(String name,String memberType,String roomType, String expected, boolean checkRoom) {
		User user = new User(name,memberType);
		Booking book = new Booking(roomType,user,rMock);
		when(rMock.checkRoom(roomType)).thenReturn(checkRoom); //assume all rooms are available
		String result = book.setBooking();
		assertEquals(expected,result);
				
	}
	private Object[] getBookingTest2Data() {
		return new Object[] {
		new Object[] {"Lai","vip","empty",null,true}, 
		new Object[] {"Leon","member","empty",null,true},
		new Object[] {"Lim","normal","vip",null,true}, 
		new Object[] {"Loh","normal","deluxe",null,true},
		new Object[] {"Lee","normal","empty",null,true}
		};
	}
	
	//------------------------------------------------------------------------------------------------------------------

	//Test setBooking method in Booking Class with (vip/member)user who want to book vip rooms if all vip rooms are NOT available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest3Data")
	public void setBookingTest3(String name, String memberType, String vipRoom, String deluxeRoom, String expected, boolean checkVip, boolean checkDeluxe) {
		User user = new User(name,memberType);
		Booking book = new Booking(vipRoom,user,rMock);
		when(rMock.checkRoom(vipRoom)).thenReturn(checkVip); 
		when(rMock.checkRoom(deluxeRoom)).thenReturn(checkDeluxe); 	
		String result = book.setBooking();
		assertEquals(expected, result);
	}
	
	//Test cancelBooking method in Booking Class with (vip/member)user who want to book vip rooms if all vip rooms are NOT available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest3Data")
	public void cancelBookingTest3(String name, String memberType, String vipRoom, String deluxeRoom, String expected, boolean checkVip, boolean checkDeluxe)
	{
			User user = new User(name,memberType);
			Booking book = new Booking(vipRoom,user,rMock);
			when(rMock.checkRoom(vipRoom)).thenReturn(checkVip); 
			when(rMock.checkRoom(deluxeRoom)).thenReturn(checkDeluxe); 	
			String result = book.setBooking();
			
			WaitingList wl = new WaitingList();
			book.cancelBooking(wl, user);
			
			assertArrayEquals(null, user.get_booking());
	}
	
	private Object[] getBookingTest3Data() {
		return new Object[] {
				new Object[] {"Lai", "vip", "vip", "deluxe", "deluxe", false, true},
				new Object[] {"Leon","member","vip", "deluxe", "deluxe", false, true}
		};
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Test setBooking method in Booking Class with (vip/member)user who want to book vip or deluxe rooms but both vip and deluxe rooms are NOT available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest4Data")
	public void setBookingTest4(String name, String memberType, String vipRoom, String deluxeRoom, String standardRoom, String expected, boolean checkVip, boolean checkDeluxe, boolean checkStandard) {
		User user = new User(name,memberType);
		Booking book = new Booking(vipRoom,user,rMock);
		when(rMock.checkRoom(vipRoom)).thenReturn(checkVip); 		
		when(rMock.checkRoom(deluxeRoom)).thenReturn(checkDeluxe);  
		when(rMock.checkRoom(standardRoom)).thenReturn(checkStandard); 
		String result = book.setBooking();
		assertEquals(expected, result);
	}
	
	//Test cancelBooking method in Booking class with (vip/member)user who want to book vip or deluxe rooms but both vip and deluxe rooms are NOT available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest4Data")
	public void cancelBookingTest4(String name, String memberType, String vipRoom, String deluxeRoom, String standardRoom, String expected, boolean checkVip, boolean checkDeluxe, boolean checkStandard)
	{
			User user = new User(name,memberType);
			Booking book = new Booking(vipRoom,user,rMock);
			when(rMock.checkRoom(vipRoom)).thenReturn(checkVip); 		
			when(rMock.checkRoom(deluxeRoom)).thenReturn(checkDeluxe);  
			when(rMock.checkRoom(standardRoom)).thenReturn(checkStandard); 
			String result = book.setBooking();
			
			WaitingList wl = new WaitingList();
			book.cancelBooking(wl, user);
			
			assertArrayEquals(null, user.get_booking());
	}
	
	private Object[] getBookingTest4Data() {
		return new Object[] {
				new Object[] {"Lai", "vip", "vip", "deluxe", "standard","standard", false, false, true},
				new Object[] {"Leon","member","vip", "deluxe", "standard","standard", false, false, true}
		};
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Test setBooking method in Booking Class with (vip/member)user who want to book any types of rooms but all rooms are NOT available
	//EP approach
	@Test
	@Parameters(method = "getBookingTest5Data")
	public void setBookingTest5(String name, String memberType, String vipRoom, String deluxeRoom, String standardRoom, boolean checkVip, boolean checkDeluxe, boolean checkStandard) {
		User user = new User(name,memberType);
		Booking book = new Booking(vipRoom,user,rMock);
		when(rMock.checkRoom(vipRoom)).thenReturn(checkVip); 		
		when(rMock.checkRoom(deluxeRoom)).thenReturn(checkDeluxe); 
		when(rMock.checkRoom(standardRoom)).thenReturn(checkStandard); 
	    String result = book.setBooking();
	    WaitingList wl = book.getWaitingList();

	    assertTrue(wl.getWaiting(user));
	
	}
	
	//Test cancelBooking method in Booking Class with (vip/member)user who want to book any types of rooms but all rooms are NOT available
	//EP approach	
	@Test
	@Parameters(method = "getBookingTest5Data")
	public void cancelBookingTest5(String name, String memberType, String vipRoom, String deluxeRoom, String standardRoom, boolean checkVip, boolean checkDeluxe, boolean checkStandard)
	{
			User user = new User(name,memberType);
			Booking book = new Booking(vipRoom,user,rMock);
			when(rMock.checkRoom(vipRoom)).thenReturn(checkVip); 		 
			when(rMock.checkRoom(deluxeRoom)).thenReturn(checkDeluxe); 
			when(rMock.checkRoom(standardRoom)).thenReturn(checkStandard); 
		    String result = book.setBooking();
		  
		    WaitingList wl = book.getWaitingList();
			book.cancelBooking(wl, user);
			
			assertFalse(wl.getWaiting(user));
	}
		
	
	private Object[] getBookingTest5Data() {
		return new Object[] {
				new Object[]{"Lai", "vip", "vip", "deluxe", "standard", false, false, false},
				new Object[]{"Leon","member","vip","deluxe", "standard", false, false, false}
		};
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Test setBooking method in Booking Class with (normal)user who want to book standard rooms but all standard rooms are NOT available
	//EP approach
	@Test
	@Parameters({"Lai, normal, standard, false"})
	public void setBookingTest6(String name, String memberType, String standardRoom, boolean checkStandard) {
		User user = new User(name,memberType);
		Booking book = new Booking(standardRoom,user,rMock);
		when(rMock.checkRoom(standardRoom)).thenReturn(checkStandard); 
	    book.setBooking();
	    WaitingList wl = book.getWaitingList();
	    
	    assertTrue(wl.getWaiting(user));
		
	}
	
	//Test cancelBooking method in Booking Class with (normal)user who want to book standard rooms but all standard rooms are not available
	//EP approach
	@Test
	@Parameters({"Lai, normal, standard, false"})
	public void cancelBookingTest6(String name, String memberType, String standardRoom, boolean checkStandard)
	{
		User user = new User(name,memberType);
		Booking book = new Booking(standardRoom,user,rMock);
		when(rMock.checkRoom(standardRoom)).thenReturn(checkStandard); 
	    book.setBooking();
	    
	    WaitingList wl = book.getWaitingList();
		book.cancelBooking(wl, user);
		
		assertFalse(wl.getWaiting(user));
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
		
}




