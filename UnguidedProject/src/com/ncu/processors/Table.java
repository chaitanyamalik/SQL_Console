package com.ncu.processors;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.ncu.exceptions.*;
import com.ncu.validators.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Table{
	static Scanner in = new Scanner(System.in);
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	TableValidators tv = new TableValidators();
	DatabaseValidators dv = new DatabaseValidators();

	public void createTable(){
		Logger logger = Logger.getLogger(Table.class);
		String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		logger.info("Enter database name:- ");
		String dbname = in.nextLine();
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			FileWriter fw = null;
			BufferedWriter bw = null;
			if(!dv.EmptyDatabaseName(dbname)){
				if(!dv.DatabaseNotFound(dbname)){
					try{
						logger.info("Enter table name:- ");
						String tbname = in.nextLine();
						File file = new File(System.getProperty("user.dir")+ File.separator+"databases"+File.separator+dbname+File.separator+tbname+".csv");
						if(!tv.EmptyTableName(tbname)){
							if(!tv.TableExists(dbname,tbname)){
								System.out.print("Please enter the number of columns:- ");
								int col = in.nextInt();
								String[] header = new String[col];
								String columns = null;
								StringBuilder sb = new StringBuilder();
								for(int i=0;i<col;i++){
									logger.info("Enter Column "+(i+1)+":- ");
									header[i] = in.next();
									sb.append(header[i]).append(",");
								}
								columns = sb.deleteCharAt(sb.length() - 1).toString();
								fw = new FileWriter(file);
								bw = new BufferedWriter(fw);
								bw.write(columns);
								logger.info("Table Created Successfully !!\n");
								bw.close();
								fw.close(); 
							}
							else{
								throw new TableExistsException("");
							}
						}
						else
							throw new EmptyTableNameException("");
					}	
					catch(TableExistsException e){
						logger.error(prop.getProperty("TableExistsException")+"\n");
					}
					catch(EmptyTableNameException e){
						logger.error(prop.getProperty("EmptyTableNameException")+"\n");
					}
				}
				else{
					throw new DatabaseNotFoundException("");
				}
			}
			else
				throw new EmptyDatabaseNameException("");
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

	




	public void deleteTable(){
		Logger logger = Logger.getLogger(Table.class);
		String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "configs/logger/logger.properties";
		PropertyConfigurator.configure(log4jConfigFile);
		System.out.print("Enter database name:- ");
		String dbname = in.nextLine();
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			if(!dv.EmptyDatabaseName(dbname)){
				if(!dv.DatabaseNotFound(dbname)){
					try{
						System.out.print("Enter table name:- ");
						String tbname = in.nextLine();
						File file = new File(System.getProperty("user.dir")+ File.separator+"databases"+File.separator+dbname+File.separator+tbname+".csv");
						if(!tv.EmptyTableName(tbname)){
							if(!tv.TableNotFound(dbname,tbname)){
								file.delete();
								System.out.println("Table Deleted Successfully !!");
							}
							else{
								throw new TableNotFoundException("");
							}
						}
						else
							throw new EmptyTableNameException("");
					}
					catch(TableNotFoundException e){
						System.out.println(prop.getProperty("TableNotFoundException"));
					}
					catch(EmptyTableNameException e){
						System.out.println(prop.getProperty("EmptyTableNameException"));
					}
				}
				else{
					throw new DatabaseNotFoundException("");
				}
			}
			else
				throw new EmptyDatabaseNameException("");
			
		}
		catch(EmptyDatabaseNameException e){
			logger.error(prop.getProperty("EmptyDatabaseNameException")+"\n");
		}
		catch(DatabaseNotFoundException e){
			System.out.println(prop.getProperty("DatabaseNotFoundException"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}