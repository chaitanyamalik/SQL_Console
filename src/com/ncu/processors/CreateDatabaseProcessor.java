package com.ncu.processors;
import java.io.*;
import java.util.*;
import org.apache.log4j.*;

public class CreateDatabaseProcessor{
	String DBPath = System.getProperty("user.dir")+ File.separator+"databases/";
	public void createDB(String dbname){
		try{
			File db = new File("user.dir"+File.separator+"databases/"+dbname).mkdir();	
		}
		catch(DatabaseExistsException e){
			e.printStackTrace();
		}
	}	
}