package my.edu.utar;

public class User
{
	private String name;
	private String member_type;
	private boolean excl_reward;
	private Booking[] booking;
	private WaitingList waiting;

	public User(String theName, String theMemberType)
	{
		name = theName;
		member_type = theMemberType;

		if(member_type.equals("vip"))
		{
			booking = new Booking[3];
			excl_reward = true;
		}
		else if(member_type.equals("member"))
		{
			booking = new Booking[2];
			excl_reward = true;
		}
		else if(member_type.equals("normal"))
		{
			booking = new Booking[1];
			excl_reward = false;
		}
	}
	
	public String getName()
	{
		return name;
	}

	public String getMemberType()
	{
		return member_type;
	}
	
	public void setReward(boolean set)
	{
		excl_reward = set;
	}
	public boolean getReward()
	{
		return excl_reward;
	}
	
	public void set_booking(Booking theBooking)
	{
		boolean theNull = false;	
		int i = 0;	

		while(i < booking.length && !theNull)
		{
			if(booking[i] == null)
			{
				booking[i] = theBooking;
				theNull = true;
			}
			else
			{
				i++;
			}
		}
	}

	public void cancel_booking()
	{
		booking = null;
 	}
	
	public Booking[] get_booking()
	{
		return booking;
	}

}
