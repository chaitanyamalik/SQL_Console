package com.ncu.processors;
import java.io.*;
import java.util.*;
import com.ncu.exceptions.*;

public class Table{
	static Scanner in = new Scanner(System.in);

	public void createTable(){
		//Table Name code yet to be written
		//CSV writer error to be corrected
		Scanner input = new Scanner(System.in);
		System.out.print("Enter database name:- ");
		String dbname = in.nextLine();
		File dtb = new File(System.getProperty("user.dir")+ File.separator+"databases/"+dbname);
		try{
			FileWriter fw = null;
			BufferedWriter bw = null;
			if(dtb.exists()){
				try{
					File file = new File(System.getProperty("user.dir")+ File.separator+"databases"+File.separator+dbname+File.separator+"example.csv");
					if(!file.exists()){
						fw = new FileWriter(file);
						bw = new BufferedWriter(fw);
						//CSVWriter csv = new CSVWriter(outputfile);
						System.out.print("Please enter the number of columns:- ");
						int col = in.nextInt();
						String[] header = new String[col];
						for(int i=0;i<col;i++){
							header[i] = input.next();
							bw.write(header[i]);
						} 
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