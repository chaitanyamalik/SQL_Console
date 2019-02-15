package com.ncu.processors;
import java.io.*;
import java.util.*;
import com.ncu.exceptions.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Table{
	static Scanner in = new Scanner(System.in);


	public void createTable(){
		Logger logger = Logger.getLogger(Table.class);
	String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
	PropertyConfigurator.configure(log4jConfigFile);
		//Table Name code yet to be written
		//CSV writer error to be corrected
		logger.info("Enter database name:- ");
		String dbname = in.nextLine();
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
		try{
			if(dtb.exists()){
				try{
					File file = new File(System.getProperty("user.dir")+ File.separator+"databases"+File.separator+dbname+File.separator+"example.csv");
					if(!file.exists()){
						FileWriter fw = new FileWriter(file);
						BufferedWriter bw = new BufferedWriter(fw);
						//CSVWriter csv = new CSVWriter(outputfile);
						logger.info("Please enter the number of columns:- ");
						int col = in.nextInt();
						String[] header = new String[col];
						for(int i=0;i<col;i++){
							header[i] = new String();
							bw.write(header[i]);
							// System.out.print("loop");
						} 
		        		fw.close(); 
		        		bw.close();	
					}
					else{
						throw new TableExistsException("");
					}
				}
				catch(TableExistsException e){
					e.printStackTrace();
				}
			}
			else{
				throw new DatabaseNotFoundException("");
			}
		}
		catch(DatabaseNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}