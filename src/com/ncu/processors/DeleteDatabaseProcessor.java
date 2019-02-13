package com.ncu.processors;
import java.io.*;
import java.util.*;
import java.io.file;
import org.apache.log4j.*;

public class DeleteDatabaseProcessor{

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String folder = in.nextLine();
        if(folder != /*blah*/)
        	system.out.print("no database exists");
        else
        {

        //delete folder recursively
        recursiveDelete(new File(folder));
        }
    }    
    
    public static void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists())
            return;
        
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
        System.out.println("Deleted file/folder: "+file.getAbsolutePath());
    }

}