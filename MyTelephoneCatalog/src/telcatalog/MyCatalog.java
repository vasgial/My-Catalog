/**
 * 
 */
package telcatalog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyCatalog {
	static String [] telCatalogNames = new String[50];
	static String [] telCatalogTels = new String[50];
	
	public MyCatalog() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		//String fileName= new String();
		readFile("bin/telcatalog/telcatalog.txt");
		System.out.println(size());
		printEntries();
		
		boolean running=true;
		while(running) {
			Scanner sc = new Scanner(System.in);
			System.out.println("What do you want to do?");
			System.out.println("1.Search name? ");
			System.out.println("2.Search telephone? ");
			System.out.println("3.Create new entry? ");
			System.out.println("4.Update entry from name? ");
			System.out.println("5.Update entry from telephone?");
			System.out.println("6.Delete entry from name?");
			System.out.println("7.Delete entry from telephone?");
			System.out.println("Enter a number from 1 to 7: ");
			String enter = sc.nextLine();
			
			
			if(enter.equals("1")) {
				System.out.println("give me a name: ");
				String name = sc.nextLine();
				searchArrayName(name);
				
			}
			else if(enter.equals("2")) {
				System.out.println("give me a telephone: ");
				String tele = sc.nextLine();
				searchArrayTel(tele);
			}
			else if(enter.equals("3")) {
				System.out.println("give me a new name");
				String name = sc.nextLine();
				System.out.println("give me a new telephone");
				String tele = sc.nextLine();
				CreateEntry(name, tele);
			}
			else if(enter.equals("4")) {
				System.out.println("give me the old name");
				String name1 = sc.nextLine();
				System.out.println("give me the new name");
				String name2 = sc.nextLine();
				updateEntryName(name1, name2);
			}
			else if(enter.equals("5")) {
				System.out.println("give me the old telephone");
				String tele1 = sc.nextLine();
				System.out.println("give me the new telephone");
				String tele2 = sc.nextLine();
				updateEntryTels(tele1, tele2);
			}
			else if(enter.equals("6")) {
				System.out.println("give me the name, who you want to delete");
				String nam = sc.nextLine();
				deleteEntryNames(nam);
			}
			else if(enter.equals("7")) {
				System.out.println("give me the telephone, who you want to delete");
				String tel = sc.nextLine();
				deleteEntryTels(tel);
			}
			System.out.println("Do you want to continue (Y/N):");
			String answer=sc.next();
			if(answer.equals("N")) {
				running=false;
			}
			}
		}

	
	
	public static void readFile(String fileName) {
		
		 try {

	            File f = new File("bin/telcatalog/telcatalog.txt");

	            BufferedReader b = new BufferedReader(new FileReader(f));

	            String readLine = "";
	            int index = 0;

	            while ((readLine = b.readLine()) != null) {
	                //System.out.println(readLine);
	                //readLine.indexOf(",");
	                telCatalogNames[index] = readLine.substring(0, readLine.indexOf(","));
	                telCatalogTels[index] = readLine.substring(readLine.indexOf(",")+1, readLine.length()).trim();
	                index++;
	                if(index==telCatalogNames.length-1) {
	                	resize();
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	static void printEntries() {
		int i = 0;
		
		for(i = 0; i<telCatalogNames.length; i++) {
			
			if(telCatalogNames[i] != null) {
				System.out.println(telCatalogNames[i] + " - " + telCatalogTels[i] );
			}
			else {break;}
		}
	}
		
	public static boolean searchArrayName( String name) {
		//System.out.println("give me a name ");
		boolean find = false;
		for(int i = 0 ; i<telCatalogNames.length ; i++) {
			
			if (telCatalogNames[i] != null) {
				
				if (telCatalogNames[i].equals(name)) {
					System.out.println("contains.");
					find = true;
					break;
				} 		
			}
			else {break;}
		}
		if(find == false) {
			System.out.println("does not contain.");
		}
		return find;
	}
	
	public static boolean searchArrayTel( String tels) {
		//System.out.println("give me a tels: ");
		boolean find = false;
		for(int i = 0 ; i<telCatalogTels.length ; i++) {
			
			if (telCatalogTels[i] != null) {
				
				if (telCatalogTels[i].equals(tels)) {
					System.out.println("contains.");
					find = true;
					break;
				} 		
			}
			else {break;}
		}
		if(find == false) {
			System.out.println("does not contain.");
		}
		return find;
	}
	
	public static int capacity() {
		int capacity = telCatalogNames.length;
		return capacity;
	}
	
	public static int size() {
		int count = 0;
		for(int i = 0 ; i<telCatalogNames.length ; i++) {
			if(telCatalogNames[i] == null) {
				count +=1;
			}
		}
		return telCatalogNames.length - count;
	}
	
	public static void CreateEntry(String name, String tel) {
		boolean find = false;
		if(size()==telCatalogNames.length-1) {
         	resize();
		}
		for(int i = 0 ; i<telCatalogNames.length ; i++) {
			if(telCatalogTels[i] == null) {
				telCatalogTels[i] = tel;
				telCatalogNames[i] = name;
				find = true;
				writeFile();
				break ;
			}
		}
	}
	
	public static void resize() {
		String [] temp = telCatalogNames;
		int cap = capacity();
		telCatalogNames = new String[cap+10];
		for(int i = 0 ; i<telCatalogNames.length ; i++) {
			telCatalogNames[i] = temp[i];
		}
		temp = telCatalogTels;
		telCatalogTels = new String[cap+10];
		for(int i = 0 ; i<telCatalogTels.length ; i++) {
			telCatalogTels[i] = temp[i];
		}
	}
	
	public static boolean updateEntryName(String name1, String name2) {
		boolean find = false;
		//Scanner sc = new Scanner(System.in);
		//System.out.println("give me the name, who must to be replaced: ");
		//String  = sc.nextLine();
		//System.out.println("give me the new name: ");
	
		for(int i = 0 ; i<telCatalogNames.length ; i++) {
			if(telCatalogNames[i].equals(name1)) {
				telCatalogNames[i] = name2;
				find = true;
				writeFile();
				break;
			}		
		}
		if(!find) {
			System.out.println("The old name doesn't exist.");
			}
		return find;
	}
	
	public static boolean updateEntryTels(String tel1, String tel2) {
		boolean find = false;
		//Scanner sc = new Scanner(System.in);
		//System.out.println("give me the name, who must to be replaced: ");
		//String  = sc.nextLine();
		//System.out.println("give me the new name: ");
		for(int i = 0 ; i<telCatalogTels.length ; i++) {
			if(telCatalogTels[i].equals(tel1)) {
				telCatalogTels[i] = tel2;
				find = true;
				writeFile();
				break;
			}
		}
		if(!find) {
			System.out.println("The old telephone doesn't exist.");
			}
		return find;
	}
	
	public static boolean deleteEntryNames(String name) {
		boolean find = false;
		int oldSize = size();
		for(int i = 0 ; i<size() ; i++) {
			if(telCatalogNames[i].equals(name)) {
				telCatalogNames[i] = telCatalogNames[telCatalogNames.length-1] ;
				telCatalogTels[i] = telCatalogTels[telCatalogNames.length-1];
				telCatalogNames[telCatalogNames.length-1] = null;
				telCatalogTels[oldSize] = null;
				find = true;
				writeFile();
				break;
			}
		}
		if(!find){
			System.out.println("This name doesn't exist.");
		}
		return find;
	}
	
	public static boolean deleteEntryTels(String tel) {
		boolean find = false;
		int oldSize = size();
		for(int i = 0 ; i<size() ; i++) {
			if(telCatalogTels[i].equals(tel)) {
				telCatalogNames[i] = telCatalogNames[telCatalogTels.length-1] ;
				telCatalogTels[i] = telCatalogTels[telCatalogTels.length-1];
				telCatalogNames[telCatalogTels.length-1] = null;
				telCatalogTels[oldSize] = null;
				find = true;
				writeFile();
				break;
			}
		}
		if(!find){
			System.out.println("This telephone doesn't exist.");
		}
		return find;
		
	}
	
	public static void writeFile()  {
		PrintWriter writer;
		try {
			writer = new PrintWriter("bin/telcatalog/telcatalog.txt", "UTF-8");
			int i = 0;
			
			for(i = 0; i<telCatalogNames.length; i++) {
				
				if(telCatalogNames[i] != null) {
					writer.println(telCatalogNames[i] + "," + telCatalogTels[i] );
				}
				else {break;}
			}

			writer.close();
		} catch(UnsupportedEncodingException ex) {         
			System.out.println("error");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error1");
		}
		
	}
	

	
}


