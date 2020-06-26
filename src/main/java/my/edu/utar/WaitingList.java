package my.edu.utar;

import java.util.ArrayList;

public class WaitingList
{
  private ArrayList<User> vip;
  private ArrayList<User> member;
  private ArrayList<User> normal;

  public WaitingList()
  {
    vip = new ArrayList<User>();
    member = new ArrayList<User>();
    normal = new ArrayList<User>();
  }

  public void addWaiting(User user)
  {
    if(user.getMemberType().equals("vip"))
    {
      vip.add(user);
    }
    else if(user.getMemberType().equals("member"))
    {
      member.add(user);
    }
    else if(user.getMemberType().equals("normal"))
    {
      normal.add(user);
    }
    else
    	throw new IllegalArgumentException();
  }
  
  public boolean getWaiting(User user)
  {
		boolean inList = false;
		
		if (user.getMemberType().equals("vip")) {
			int i=0;
			while(i<vip.size() && !inList) {
				if(user.equals(vip.get(i))) {
					inList = true;
				}
				i++;
			}
		}
		
		if (user.getMemberType().equals("member")) {
			int i=0;
			while(i<member.size() && !inList) {
				if(user.equals(member.get(i))) {
					inList = true;
				}
				i++;
			}
		}
		
		if (user.getMemberType().equals("normal")) {
			int i=0;
			while(i<normal.size() && !inList) {
				if(user.equals(normal.get(i))) {
					inList = true;
				}
				i++;
			}
		}
		
		return inList;
  }
  
  
  public boolean removeWaiting(User user)
  {
    int i = 0;
    boolean found = false;

    if(user.getMemberType().equals("vip")) //if user is VIP
    {
      while(i < vip.size() && !found)
      {
        if(vip.get(i).getName().equals(user.getName()))
        {
          found = true;
        }
      }

      if(found)
      {
        User tempUser = vip.get(i);
        vip.remove(tempUser);
      }
      else
    	  throw new IllegalArgumentException();

    }
    else if(user.getMemberType().equals("member")) //If user is a member
    {
      while(i < member.size() && !found)
      {
        if(member.get(i).getName().equals(user.getName()))
        {
          found = true;
        }
      }

      if(found)
      {
        User tempUser = member.get(i);
        member.remove(tempUser);
      }
      else
    	  throw new IllegalArgumentException();
    }
    else if(user.getMemberType().equals("normal")) // If user is a non member
    {
      while(i < normal.size() && !found)
      {
        if(normal.get(i).getName().equals(user.getName()))
        {
          found = true;
        }
        
      }

      if(found)
      {
        User tempUser = normal.get(i);
        normal.remove(tempUser);
      }
      else
    	  throw new IllegalArgumentException();
    }
    
    return found;
  }
}
