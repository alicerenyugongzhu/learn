package com.example.learn;

/**
 * 
 * @author Administrator
 * Comments class for Database
 * This class contains the data we want to save into database
 * And the data we want to show in the user interface
 *
 */
public class Comment{
	private long id;
	private String name;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;  
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}