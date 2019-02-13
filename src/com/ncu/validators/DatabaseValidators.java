package com.ncu.validators;

import java.io.*;
import java.util.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ncu.exceptions.*;

public class DatabaseValidators{

	String databaseName;
	String databasePath = System.getProperty("user.dir")+ File.separator;
	BufferedReader br;
	NameValidator object;
	String permissionValue="y";
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	int fileLength;
	String configValidation = System.getProperty("user.dir")+ File.separator + "configs/constants/constants.properties";
	String RegexValue;

    
	/* method to check all validations of csv file name */

	public boolean DatabaseValidators(String databaseName, String flag)	{ 

		DatabaseValidators object = new DatabaseValidators();

		try {
			input = new FileInputStream(configMessages);
			// load a properties file
			prop.load(input);

			object.emptyDatabaseName(databaseName);
			
			object.fileExists(databaseName);
		}   catch(EmptyDatabaseNameException e){
			logger.error("\n \n"+e+prop.getProperty("emptyDatabaseNameMessage")+"\n");
			return false;
		}	catch(DatabaseExistsException e){
			DatabaseValidators fileShowObj=new DatabaseValidators();
			fileShowObj.showAllFiles(e);
			logger.error("\n \n"+e+prop.getProperty("DatabaseExists")+"\n");

			return false;
		}	catch(Exception e){
			logger.error("\n"+e+"\n"+"\n");
			return false;
		}		
		return true;
	}

	/* Generate "EmptyNameException" Exception if user enters blank space as a file name  */
	private void emptyDatabaseName(String databaseName) throws EmptyDatabaseNameException {	
		if (databaseName == null || databaseName.trim().isEmpty()) {
			throw new EmptyDatabaseNameException("");
		}
	}

	/* Generate "FileAlreadyExists" Exception if user given file already exists into directory */
	private void databaseExists(String databaseName) throws DatabaseExists {
		if(new File(databasePath+databaseName).exists()){
			throw new DatabaseExists("");
		}
	}

	/* Method to display all files which available into directory  */
	public void showAllFiles(Exception expObj){ 

		Logger logger = Logger.getLogger(DatabaseValidators.class);
		String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			logger.info("\n \n"+expObj+prop.getProperty("notAvailableMessage")+"\n");
			logger.info("Do You Want To See List Of Available databases Press - y/n  ");
		}catch(Exception e)	{
			logger.error(e);
		}
		Scanner sobject = new Scanner(System.in);
		String permission = sobject.nextLine();

		if(permissionValue.equalsIgnoreCase(permission)){
			File file = new File(databasePath);
			logger.info("----------- All Available databases In Directory --------- ");

			File[] files = file.listFiles();
			for(File f: files){
				logger.info("-"+f.getName());
			}
			logger.info("------------------------------------------------------ ");
		}
	}
}