package com.ncu.processors;
import java.io.*;
import java.util.*;
import com.ncu.exceptions.*;

public class Database{
   	/*String DBPath = System.getProperty("user.dir")+ File.separator+"databases/";
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();*/
	
	public void createDatabase(){
		Scanner in = new Scanner(System.in);
		//Database db = new Database();
		try{
			System.out.print("Eneter database name:- ");
			String dbname = in.nextLine();
			File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
			if(!dtb.exists()){
				if(dtb.mkdir()){
				System.out.print("Database created successfully!");
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
		try{
			System.out.print("Enter database name:- ");
			String dbname = in.nextLine();
			File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
			if(dtb.exists()){
				dtb.delete();
				System.out.println("Database deleted successfully !");
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