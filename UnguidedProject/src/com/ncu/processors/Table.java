package com.ncu.processors;
import java.io.*;
import java.util.*;
import java.lang.*;
import com.ncu.exceptions.*;
import com.ncu.validators.*;

public class Table{
	static Scanner in = new Scanner(System.in);
	String configMessages = System.getProperty("user.dir")+ File.separator + "configs/constants/exceptions.properties";
	Properties prop = new Properties();
	InputStream input = null;
	TableValidators tv = new TableValidators();

	public void createTable(){
		System.out.print("Enter database name:- ");
		String dbname = in.nextLine();
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			FileWriter fw = null;
			BufferedWriter bw = null;
			if(dtb.exists()){
				try{
					System.out.print("Enter table name:- ");
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
								System.out.print("Enter Column "+(i+1)+":- ");
								header[i] = in.next();
								sb.append(header[i]).append(",");
							}
							columns = sb.deleteCharAt(sb.length() - 1).toString();
							fw = new FileWriter(file);
							bw = new BufferedWriter(fw);
							bw.write(columns);
							System.out.println("Table Created Successfully !!");
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
					System.out.println(prop.getProperty("TableExistsException"));
				}
				catch(EmptyTableNameException e){
					System.out.println(prop.getProperty("EmptyTableNameException"));
				}
			}
			else{
				throw new DatabaseNotFoundException("");
			}
		}
		catch(DatabaseNotFoundException e){
			System.out.println(prop.getProperty("DatabaseNotFoundException"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	




	public void deleteTable(){
		System.out.print("Enter database name:- ");
		String dbname = in.nextLine();
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
		try{
			input = new FileInputStream(configMessages);
			prop.load(input);
			if(dtb.exists()){
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
		catch(DatabaseNotFoundException e){
			System.out.println(prop.getProperty("DatabaseNotFoundException"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}