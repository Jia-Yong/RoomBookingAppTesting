package my.edu.utar;

public class Room
{
  private int vip;
  private int deluxe;
  private int standard;

  public Room()
  {
    vip = 20;
    deluxe = 20;
    standard = 20;
  }

  public void setVip(int num)
  {
    vip = vip + num;
  }

  public void setDeluxe(int num)
  {
    deluxe = deluxe + num;
  }

  public void setStandard(int num)
  {
    standard = standard + num;
  }

  public boolean checkRoom(String roomType)
  {
    boolean availability = false;

    if(roomType.equals("vip"))
    {
      if(vip > 0)
      {
        availability = true;
      }
    }
    else if(roomType.equals("deluxe"))
    {
      if(deluxe > 0)
      {
        availability = true;
      }
    }
    else if(roomType.equals("standard"))
    {
      if(standard > 0)
      {
        availability = true;
      }
    }

    return availability;
  }
}
