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

public class TableValidators{

 	

	String tableName;
	String databaseName;
	/*DatabaseValidators object;
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	int fileLength;
	String configValidation = System.getProperty("user.dir")+ File.separator + "configs/constants/constants.properties";

	*/
	public boolean EmptyTableName(String tableName){	
		if (tableName == null || tableName.trim().isEmpty()) 
			return true;
		else
			return false;
	}

	/* Generate "DatabaseAlreadyExists" Exception if user given file already exists into directory */
	public boolean TableExists(String databaseName, String tableName){
		File file = new File(System.getProperty("user.dir")+ File.separator+"databases"+databaseName+File.separator+tableName+".csv");
		if(file.exists())
			return true;
		else
			return false;	
		
	}
	/*Generate "DatabaseNotFound" Exception if user given file is not Available */
	public boolean TableNotFound(String databasename, String tableName){
		File file = new File(System.getProperty("user.dir")+ File.separator+"databases"+databaseName+File.separator+tableName+".csv");
		if(file.exists())
			return true;
		else
			return false;
	}
}