package com.ncu.main;
import java.util.*;
import com.ncu.processors.*;
public class SQLConsole{
	public static void main(String[] args) {
		char ch;
		Scanner in = new Scanner(System.in);
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println(">>            SQL CONSOLE            <<");
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("\nWelcome to SQL Console !!\nThis is a JAVA Program that mimics the Data Definition functions of SQL.");
		do{
			System.out.println("\nHere's a list of functions you can perform:- ");
			System.out.println("1. Create Database");
			System.out.println("2. Delete Database");
			System.out.println("3. Create Table");
			System.out.println("4. Delete Table");
			System.out.print("Enter your choice:- ");
			int menu = in.nextInt();
			Database db = new Database();
			Table tab = new Table(); 
			switch(menu){
				case 1 : db.createDatabase();
				break;
				case 2 : db.deleteDatabase();
				break;
				case 3 : tab.createTable();
				break;
				/*case 4 : tab.deleteTable();
				break;*/ 
			}
			System.out.print("Do you want to perform some more functions ? Press N to exit or press any key to continue :- ");
			ch = in.next().charAt(0);
		}while(ch != 'N');
	}
}