
package com.ncu.validators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter; 
import java.io.File; 
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner; 
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import com.ncu.exceptions.*;

 class TableValidators{

	String tableName;
	BufferedReader br;
	TableValidators object;
	String permissionValue="y";
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	int fileLength;
	String configValidation = System.getProperty("user.dir")+ File.separator + "configs/constants/constants.properties";


	public boolean TableValidators(String tableName)	{ 

		TableValidators object = new TableValidators();

		try {
			input = new FileInputStream(configMessages);
			// load a properties file
			prop.load(input);

			object.EmptyTableName(tableName);
			
			object.TableExists(tableName);
		}   catch(EmptyTableNameException e){
			logger.error("\n \n"+e+prop.getProperty("emptyTableNameMessage")+"\n");
			return false;
		}	catch(TableExists e){
			TableValidators fileShowObj=new TableValidators();
			fileShowObj.showAllFiles(e);
			logger.error("\n \n"+e+prop.getProperty("tableExists")+"\n");
			return false;
		}	catch(Exception e){
			logger.error("\n"+e+"\n"+"\n");
			return false;
		}		
		return true;
	}

	/* Generate "EmptyTableNameException" Exception if user enters blank space as a file name  */
	private void EmptyTableName(String tableName) throws EmptyTableNameException {	
		if (tableName == null || tableName.trim().isEmpty()) {
			throw new EmptyTableNameException("");
		}
	}

	/* Generate "TableExists" Exception if user given file already exists into directory */
	private void TableExists(String tableName) throws TableExists {
		if(new File(tableName).exists()){
			throw new TableExists("");
		}
	}

	/* Method to display all files which available into directory  */
	public void showAllFiles(Exception expObj){ 

		Logger logger = Logger.getLogger(TableValidators.class);
		String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			logger.info("\n \n"+expObj+prop.getProperty("notAvailableMessage")+"\n");
			logger.info("Do You Want To See List Of Available tables Press - y/n  ");
		}catch(Exception e)	{
			logger.error(e);
		}
		Scanner sobject = new Scanner(System.in);
		String permission = sobject.nextLine();

		if(permissionValue.equalsIgnoreCase(permission)){
			File file = new File(tableName);
			logger.info("----------- All Available tables In Directory --------- ");

			File[] files = file.listFiles();
			for(File f: files){
				logger.info("-"+f.getName());
			}
			logger.info("------------------------------------------------------ ");
		}
	}
}