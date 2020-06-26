package my.edu.utar;
import junitparams.JUnitParamsRunner;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class IntegrationTest {
	
	private Room room = new Room();
	private WaitingList wl = new WaitingList();
	private Printer print = new Printer();
	 
	//Test with valid roomCount
	//BVA approach
	@Test
	@Parameters(method = "getIntegrationTest1Data")
	public void integrationTest1(String name,String memberType,String roomType,int roomCount,String expected) {
		User user = new User(name,memberType);
		
	      if(memberType.equals("vip"))
	      {
	        if(roomCount > 3 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("member"))
	      {
	        if(roomCount > 2 && roomCount <= 0)
	        {	          
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("normal"))
	      {
	        if(roomCount > 1 && roomCount <= 0)
	        {     
	          throw new IllegalArgumentException();
	        }
	      }
		
	      Booking book = new Booking(roomType, user, room);

	      String result = book.setBooking();

	      if(!result.equals("vip")&&!result.equals("deluxe")&&!result.equals("standard"))
	      {
	    	//code
	      }
	      else
	      {
	        assertEquals(result,expected);
	      }
	      
	      System.out.println("--------------------integrationTest1--------------------");
	      print.printInfo(user,roomType);

	  }

	
	private Object[] getIntegrationTest1Data() {
		return new Object[] {
				new Object[] {"Leon","vip","vip",3,"vip"},
				new Object[] {"Lai","vip","vip",1,"vip"},
				new Object[] {"Leon","vip","deluxe",3,"deluxe"},
				new Object[] {"Lai","vip","deluxe",1,"deluxe"},
				new Object[] {"Leon","vip","standard",3,"standard"},
				new Object[] {"Lai","vip","standard",1,"standard"},
				new Object[] {"Tan","member","vip",2,"vip"},
				new Object[] {"Yap","member","vip",1,"vip"},
				new Object[] {"Tan","member","deluxe",2,"deluxe"},
				new Object[] {"Yap","member","deluxe",1,"deluxe"},
				new Object[] {"Tan","member","standard",2,"standard"},
				new Object[] {"Yap","member","standard",1,"standard"},
				new Object[] {"Lim","normal","standard",1,"standard"},
		};
	}
	
	//Test with invalid roomCount
	//BVA approach
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getIntegrationTest2Data")
	public void integrationTest2(String name,String memberType,String roomType,int roomCount,String expected) {
		User user = new User(name,memberType);	
		
	      if(memberType.equals("vip"))
	      {
	        if(roomCount > 3 || roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("member"))
	      {
	        if(roomCount > 2 || roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("normal"))
	      {
	        if(roomCount > 1 || roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }					   

	      Booking book = new Booking(roomType, user, room);
	      String result = book.setBooking();

	      if(!result.equals("vip")&&!result.equals("deluxe")&&!result.equals("standard"))
	      {
	        //code
	      }
	      else
	      {
	        assertEquals(result,expected);
	      }

	  }

	
	private Object[] getIntegrationTest2Data() {
		return new Object[] {
				new Object[] {"Leon","vip","vip",4,"vip"},
				new Object[] {"Lai","vip","vip",0,"vip"},
				new Object[] {"Leon","vip","deluxe",4,"deluxe"},
				new Object[] {"Lai","vip","deluxe",0,"deluxe"},
				new Object[] {"Leon","vip","standard",4,"standard"},
				new Object[] {"Lai","vip","standard",0,"standard"},
				new Object[] {"Tan","member","vip",3,"vip"},
				new Object[] {"Yap","member","vip",0,"vip"},
				new Object[] {"Tan","member","deluxe",3,"deluxe"},
				new Object[] {"Yap","member","deluxe",0,"deluxe"},
				new Object[] {"Tan","member","standard",3,"standard"},
				new Object[] {"Yap","member","standard",0,"standard"},
				new Object[] {"Lim","normal","standard",2,"standard"},
				new Object[] {"Lim","normal","standard",0,"standard"},
		};
	}
	
	//Test with valid roomCount but all rooms are NOT available
	//BVA approach
	@Test
	@Parameters(method = "getIntegrationTest3Data")
	public void integrationTest3V1(String name,String memberType,String roomType,int roomCount,boolean checkRoom) {
		User user = new User(name,memberType);
		Room rMock = mock(Room.class);
	
	      if(memberType.equals("vip"))
	      {
	        if(roomCount > 3 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("member"))
	      {
	        if(roomCount > 2 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("normal"))
	      {
	        if(roomCount > 1 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }	   
	      when(rMock.checkRoom(roomType)).thenReturn(checkRoom);
	      Booking book = new Booking(roomType, user, rMock);
	      String result = book.setBooking();
	      wl = book.getWaitingList();
	     
	      if(!result.equals("vip")&&!result.equals("deluxe")&&!result.equals("standard"))
	      {
	    	 if(!checkRoom) {										  //if all rooms are not available
	  			assertTrue(wl.getWaiting(user));
	  		}else {
	  			assertFalse(wl.getWaiting(user));
	  		}
	      } 
	      else
	      {
	        //code
	      }
	      
	      System.out.println("-------------------integrationTest3V1---------------------");
	      print.printInfo(user,roomType);
	  }
	
	@Test
	@Parameters(method = "getIntegrationTest3Data")
	public void integrationTest3V2(String name,String memberType,String roomType,int roomCount,boolean checkRoom) {
		User user = new User(name,memberType);
		Room rMock = mock(Room.class);
	
	      if(memberType.equals("vip"))
	      {
	        if(roomCount > 3 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("member"))
	      {
	        if(roomCount > 2 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }
	      else if(memberType.equals("normal"))
	      {
	        if(roomCount > 1 && roomCount <= 0)
	        {
	          throw new IllegalArgumentException();
	        }
	      }	   
	      
	      Booking book = new Booking(roomType, user, rMock);
	      when(rMock.checkRoom("vip")).thenReturn(false);
	      when(rMock.checkRoom("deluxe")).thenReturn(false);
	      when(rMock.checkRoom("standard")).thenReturn(false);
    	  String result = book.setBooking();
    	  WaitingList wlTest = book.getWaitingList();
    	  assertTrue(wlTest.getWaiting(user));
    	  
    	  System.out.println("-------------------integrationTest3V2---------------------");
	      print.printInfo(user,roomType);

	  }
	
	private Object[] getIntegrationTest3Data() {
		return new Object[] {
				new Object[] {"Leon","vip","vip",3,true},
				new Object[] {"Lai","vip","vip",1,true},
				new Object[] {"Leon","vip","deluxe",3,true},
				new Object[] {"Lai","vip","deluxe",1,true},
				new Object[] {"Leon","vip","standard",3,true},
				new Object[] {"Lai","vip","standard",1,true},
				new Object[] {"Tan","member","vip",2,true},
				new Object[] {"Yap","member","vip",1,true},
				new Object[] {"Tan","member","deluxe",2,true},
				new Object[] {"Yap","member","deluxe",1,true},
				new Object[] {"Tan","member","standard",2,true},
				new Object[] {"Yap","member","standard",1,true},
				new Object[] {"Lim","normal","standard",1,true}
		};
	}
	

}
