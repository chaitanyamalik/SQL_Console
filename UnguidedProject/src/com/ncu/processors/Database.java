package com.ncu.processors;
import java.io.*;
import java.util.*;
import com.ncu.exceptions.*;
import com.ncu.validators.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Database{
	//Logger logger = Logger.getLogger(Database.class);
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	DatabaseValidators dv = new DatabaseValidators();

	/*public static boolean emptyDatabaseName(String dbname){
		if (dbname == null || dbname.trim().isEmpty()) {
			return true;
		}
		else
			return false;
	}*/
	
	public void createDatabase(){
		//Logger logger = Logger.getLogger(Database.class);
		try{
			Scanner in = new Scanner(System.in);
			input = new FileInputStream(configMessages);
			prop.load(input);
			System.out.print("Enter database name:- ");
			String dbname = in.nextLine();
			if(!dv.EmptyDatabaseName(dbname)){
				File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
				if(!dv.DatabaseExists(dbname)){
					if(dtb.mkdir()){
						System.out.print("Database created successfully!");
					}
				}
				else{
					throw new DatabaseExistsException("");
				}
			}
			else
				throw new EmptyDatabaseNameException("");
		}
		catch(EmptyDatabaseNameException e){
			System.out.println(prop.getProperty("EmptyDatabaseNameException"));
		}
		catch(DatabaseExistsException e){
			System.out.println(prop.getProperty("DatabaseExistsException"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void deleteDatabase(){
		//Logger logger = Logger.getLogger(Database.class);
		Scanner in = new Scanner(System.in);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			System.out.print("Enter database name:- ");
			String dbname = in.nextLine();
			File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
			if(!dv.DatabaseNotFound(dbname)){
				dtb.delete();
				System.out.println("Database deleted successfully !");
			}
			else{
				dtb.mkdir();
				throw new DatabaseNotFoundException("");
			}
		}
		catch(DatabaseNotFoundException e){
			System.out.println(prop.getProperty("DatabaseNotFoundException"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}