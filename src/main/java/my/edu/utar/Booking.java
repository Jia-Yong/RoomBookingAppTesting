package my.edu.utar;

public class Booking
{
	private String roomType;
	private User user;
	private Room room;
	private WaitingList wl;

	public Booking(String type, User aUser, Room theRoom)
	{
		roomType = type;
		user = aUser;
		room = theRoom;
		wl = new WaitingList();
	}

	public String setBooking()
	{
		String test = null;
		if(user.getMemberType().equals("vip")) //if member is vip
		{

			if(roomType.equals("vip"))
			{
				boolean vipFound = room.checkRoom(roomType);
				if(vipFound)
				{
					boolean set = true;  //Used to give a reward to members who book a VIP room
					user.setReward(set); //set the excl_reward in User class to true which is giving reward to the user(use assertTrue or assertFalse)

					room.setVip(-1); // use assertEquals
					test = "vip";
				}
				else
				{
					roomType = "deluxe";
					
				}
   
			}
        
			if(roomType.equals("deluxe"))
			{
				boolean deluxeFound = room.checkRoom(roomType);
				if(deluxeFound)
				{
					boolean set = true;  //Used to give a reward to members who book a VIP room
					user.setReward(set); //set the excl_reward in User class to true which is giving reward to the user

					room.setDeluxe(-1);
					test = "deluxe";
				}	
				else
				{
					roomType = "standard";
				}
         
			}
        
			if(roomType.equals("standard"))
			{
				boolean standardFound = room.checkRoom(roomType);
				if(standardFound)
				{
					room.setStandard(-1);
					test = "standard";
				}
				else
				{
					wl.addWaiting(user);
				} 
			}
			
			else if(!roomType.equals("vip")&&!roomType.equals("deluxe")&&!roomType.equals("standard"))
				throw new IllegalArgumentException();

			user.set_booking(new Booking(roomType, user, room));
		}
		else if(user.getMemberType().equals("member")) //if user is a member
		{
			boolean check = user.getReward();

			if(roomType.equals("vip"))
			{
				boolean vipFound = room.checkRoom(roomType);
				if(vipFound && check == true)
				{
					room.setVip(-1);
					test = "vip";
				}
				else
				{
					roomType = "deluxe";
				}	
			}

			if(roomType.equals("deluxe"))
			{
				boolean deluxeFound = room.checkRoom(roomType);
				if(deluxeFound)
				{
					boolean set = true; //Used to give a reward to members who book a deluxe room
					user.setReward(set); //set the excl_reward in User class to true which is giving reward to the user

					room.setDeluxe(-1);
					test = "deluxe";
				}
				else
				{
					roomType = "standard";
				}
			}

			if(roomType.equals("standard"))
			{
				boolean standardFound = room.checkRoom(roomType);
				if(standardFound)
				{
					room.setStandard(-1);
					test = "standard";
				}
				else
				{
					wl.addWaiting(user);
				}
			}
			
			else if(!roomType.equals("vip")&&!roomType.equals("deluxe")&&!roomType.equals("standard"))
				throw new IllegalArgumentException();

			user.set_booking(new Booking(roomType, user, room));
		}
		else if(user.getMemberType().equals("normal")) //if user is a non member
		{
			boolean standardFound = room.checkRoom(roomType);

			if(standardFound)
			{
				if(roomType.equals("standard"))
				{
					user.set_booking(new Booking(roomType, user, room));
					room.setStandard(-1);
					test = "standard";
					
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
			else
			{
				wl.addWaiting(user);
			}
		}
		
		return test;
	}

//=======================================================================================================================
	public void cancelBooking(WaitingList waiting, User cancelUserBooking)
	{
		if(waiting.getWaiting(cancelUserBooking))
			waiting.removeWaiting(cancelUserBooking);
		else
			cancelUserBooking.cancel_booking();

	}

	public User getUser()
	{
		return user;
	}
	
	public WaitingList getWaitingList()
	{
		return wl;
	}
}
