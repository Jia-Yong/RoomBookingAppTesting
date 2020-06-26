package my.edu.utar;
import static org.junit.Assert.assertEquals;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

@RunWith(JUnitParamsRunner.class)
public class WaitingListTest {
	
	WaitingList wl = new WaitingList();
	static ArrayList<String[]> textfile;
	static Scanner data;
	static {
		textfile = new ArrayList<String[]>();
		String fileName = "WaitingListTest.txt";
		data = null;
	
		try {
			data = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!" + fileName);
			System.exit(0);
		}
		while (data.hasNextLine()) {
			String line = data.nextLine();
			String[] tokens = line.split(" ");
			textfile.add(tokens);
		}
	}
	
	//EP approach
	@Test
	@Parameters(method = "getAddValid")
	public void addWaitingTestValid(String name, String memberType, boolean expected){
		 User user = new User(name,memberType);
		 wl.addWaiting(user); 
		 boolean result = wl.getWaiting(user);
		 assertEquals(expected,result);
	}
	
	private Object[] getAddValid(){
		return new Object[] {	
			new Object[] {"Leon","vip",true},		
			new Object[] {"Lai","member",true},
			new Object[] {"Lim","normal",true}
		};
	}
	
	//EP approach
	@Test(expected=IllegalArgumentException.class)
	@Parameters(method = "getAddInvalid")
	public void addWaitingTestInvalid(String name, String memberType, boolean expected) {	
		 User user = new User(name,memberType);
		 wl.addWaiting(user); 
		 boolean result = wl.getWaiting(user);
		 assertEquals(expected,result);
		
	}
	private Object[] getAddInvalid() {
		
		return new Object[] {
				new Object[] {"Lee","empty",true},
				new Object[] {"Loh","empty",false}
		};
	}
	
	//EP approach
	@Test
	@Parameters(method = "getRemoveValid")
	public void removeWaitingTestValid(String name, String memberType, boolean expected) {
		User user = new User(name,memberType);
		wl.addWaiting(user);
		boolean result = wl.removeWaiting(user);
		assertEquals(expected, result);
	}
	private Object[] getRemoveValid() {
		
		return new Object[] {
				new Object[] {"Leon","vip",true},
				new Object[] {"Lai","member",true},
				new Object[] {"Lim","normal",true}
		};
	}
	
	//EP approach
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getRemoveInvalid")
	public void removeWaitingTestInvalid(String name, String memberType, boolean expected) {
		User user = new User(name,memberType);
		wl.addWaiting(user);
		boolean result = wl.removeWaiting(user);
		assertEquals(expected, result);
	}
	private Object[] getRemoveInvalid() {
		
		return new Object[] {
				new Object[] {"Lee","empty",true},
				new Object[] {"Tan","empty",false}
		};
	}
	
	//EP approach
	@Test
	@Parameters(method = "getAddFromFileValid")
	public void addWaitingTestFileValid(String name,String memberType,boolean expected) { 
		 User user = new User(name,memberType);
		 wl.addWaiting(user); 
		 boolean result = wl.getWaiting(user);
		 assertEquals(expected,result);
	}
	private Object[] getAddFromFileValid() {
		String[] name = new String[textfile.get(0).length];
		String[] memberType = new String[textfile.get(1).length];
		boolean[] expected = new boolean[textfile.get(2).length];
		
		for(int i = 0; i < name.length; i++) {
			name[i] = textfile.get(0)[i];
			memberType[i] = textfile.get(1)[i];
			expected[i] = Boolean.parseBoolean(textfile.get(2)[i]);			
		}
		
		return new Object[] {
			new Object[] {name[0],memberType[0],expected[0]},
			new Object[] {name[1],memberType[1],expected[1]},
			new Object[] {name[2],memberType[2],expected[2]}
			};		
	}
	
	//EP approach
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getAddFromFileInvalid")
	public void addWaitingTestFileInvalid(String name,String memberType,boolean expected) { 
		 User user = new User(name,memberType);
		 wl.addWaiting(user); 
		 boolean result = wl.getWaiting(user);
		 assertEquals(expected,result);
	}
	private Object[] getAddFromFileInvalid() {
		String[] name = new String[textfile.get(3).length];
		String[] memberType = new String[textfile.get(4).length];
		boolean[] expected = new boolean[textfile.get(5).length];
		
		for(int i = 0; i < name.length; i++) {
			name[i] = textfile.get(3)[i];
			memberType[i] = textfile.get(4)[i];
			expected[i] = Boolean.parseBoolean(textfile.get(5)[i]);			
		}
		
		return new Object[] {
			new Object[] {name[0],memberType[0],expected[0]},
			new Object[] {name[1],memberType[1],expected[1]}
			};		
	}
	
	//EP approach
	@Test
	@Parameters(method = "getRemoveFromFileValid") 
	public void removeWaitingTestFileValid(String name, String memberType, boolean expected) {
		User user = new User(name,memberType);
		wl.addWaiting(user);
		boolean result = wl.removeWaiting(user);
		assertEquals(expected, result);
	}
	private Object[] getRemoveFromFileValid() {
		String[] name = new String[textfile.get(6).length];
		String[] memberType = new String[textfile.get(7).length];
		boolean[] expected = new boolean[textfile.get(8).length];
		
		for(int i = 0; i < name.length; i++) {
			name[i] = textfile.get(6)[i];
			memberType[i] = textfile.get(7)[i];
			expected[i] = Boolean.parseBoolean(textfile.get(8)[i]);
		}
		
		return new Object[] {
			new Object[] {name[0],memberType[0],expected[0]},
			new Object[] {name[1],memberType[1],expected[1]},
			new Object[] {name[2],memberType[2],expected[2]},
		};
	}
	
	//EP approach
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getRemoveFromFileInvalid") 
	public void removeWaitingTestFileInvalid(String name, String memberType, boolean expected) {
		User user = new User(name,memberType);
		wl.addWaiting(user);
		boolean result = wl.removeWaiting(user);
		assertEquals(expected, result);
	}
	private Object[] getRemoveFromFileInvalid() {
		String[] name = new String[textfile.get(9).length];
		String[] memberType = new String[textfile.get(10).length];
		boolean[] expected = new boolean[textfile.get(11).length];
		
		for(int i = 0; i < name.length; i++) {
			name[i] = textfile.get(9)[i];
			memberType[i] = textfile.get(10)[i];
			expected[i] = Boolean.parseBoolean(textfile.get(11)[i]);
		}
		
		return new Object[] {
			new Object[] {name[0],memberType[0],expected[0]},
			new Object[] {name[1],memberType[1],expected[1]},
		};
	}
	
	
	
}
