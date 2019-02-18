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
	Logger logger = Logger.getLogger(Database.class);
	String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
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
			Logger logger = Logger.getLogger(Database.class);
			String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
			PropertyConfigurator.configure(log4jConfigFile);
			Scanner in = new Scanner(System.in);
			input = new FileInputStream(configMessages);
			prop.load(input);
			logger.info("Enter database name:- ");
			String dbname = in.nextLine();
			if(!dv.EmptyDatabaseName(dbname)){
				File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
				if(!dv.DatabaseExists(dbname)){
					if(dtb.mkdir()){
						logger.info("Database created successfully!\n");
					}
				}
				else{
					throw new DatabaseExistsException("");
				}
			}
			else{
				throw new EmptyDatabaseNameException("");
			}

		}
		catch(EmptyDatabaseNameException e){
			logger.error(prop.getProperty("EmptyDatabaseNameException")+"\n");
		}
		catch(DatabaseExistsException e){
			logger.error(prop.getProperty("DatabaseExistsException")+"\n");
		}
		catch(IOException e){
			logger.error(e+"\n");
		}
	}

	public void deleteDatabase(){
		//Logger logger = Logger.getLogger(Database.class);
		Scanner in = new Scanner(System.in);
		try{
			Logger logger = Logger.getLogger(Database.class);
			String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
			PropertyConfigurator.configure(log4jConfigFile);
			input = new FileInputStream(configMessages);
			prop.load(input);
			logger.info("Enter database name:- ");
			String dbname = in.nextLine();
			if(!dv.EmptyDatabaseName(dbname)){
				File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
				if(dv.DatabaseExists(dbname)){
					dtb.delete();
					logger.info("Database deleted successfully !\n");
				}
				else{
					dtb.mkdir();
					throw new DatabaseNotFoundException("");
				}
			}
			else{
				throw new EmptyDatabaseNameException("");
			}	 
		}
		catch(DatabaseNotFoundException e){
			logger.error(prop.getProperty("DatabaseNotFoundException")+"\n");
		}
		catch(EmptyDatabaseNameException e){
			logger.error(prop.getProperty("EmptyDatabaseNameException")+"\n");
		}
		catch(IOException e){
			logger.error(e+"\n");
		}
	}
}