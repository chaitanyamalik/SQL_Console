package com.ncu.processors;
import java.io.*;
import java.util.*;
import com.ncu.exceptions.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Database{
   	/*String DBPath = System.getProperty("user.dir")+ File.separator+"databases/";
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();*/
	
	public void createDatabase(){
		Scanner in = new Scanner(System.in);
		Logger logger = Logger.getLogger(Database.class);
		String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		//Database db = new Database();
		try{
			logger.info("Enter database name:- ");
			String dbname = in.nextLine();
			File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
			if(!dtb.exists()){
				if(dtb.mkdir()){
				logger.info("Database created successfully!");
				}
			}
			else{
				throw new DatabaseExistsException("");
			}
		}
		catch(DatabaseExistsException e){
			e.printStackTrace();
		}
	}

	public void deleteDatabase(){
		Scanner in = new Scanner(System.in);
		Logger logger = Logger.getLogger(Database.class);
		String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		try{
			logger.info("Enter database name:- ");
			String dbname = in.nextLine();
			File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
			if(dtb.exists()){
				dtb.delete();
				logger.info("Database deleted successfully !");
			}
			else{
				dtb.mkdir();
				throw new DatabaseNotFoundException("");
			}
		}
		catch(DatabaseNotFoundException e){
			e.printStackTrace();
		}
	}
}