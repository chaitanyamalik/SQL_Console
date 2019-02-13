package com.ncu.processors;
import java.io.File;
import java .util.*;
import java.lang.*;
public class DeleteFile{
	public static void main(String[] args) {
		try{
			File file=new File();
			if(file.delete()){
				System.out.println(file.getName()+"is Deleted.....!!!!!");
			}else{
				System.out.println("Deletion is failed!!!");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}