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

public class DatabaseValidators{

 	

	String databaseName;
	/*DatabaseValidators object;
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	int fileLength;
	String configValidation = System.getProperty("user.dir")+ File.separator + "configs/constants/constants.properties";

	*/
	public boolean EmptyDatabaseName(String databaseName){	
		if (databaseName == null || databaseName.trim().isEmpty()) 
			return true;
		else
			return false;
	}

	/* Generate "DatabaseAlreadyExists" Exception if user given file already exists into directory */
	public boolean DatabaseExists(String databaseName){
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+databaseName);
		if(dtb.exists())
			return true;
		else
			return false;	
		
	}
	/*Generate "DatabaseNotFound" Exception if user given file is not Available */
	public boolean DatabaseNotFound(String databasename){
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+databaseName);
		if(dtb.exists())
			return true;
		else
			return false;
	}
}	