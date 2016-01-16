package com.example.learn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databaseAdp extends SQLiteOpenHelper{

	public static final String TABLE_TEST = "test";  //database name without .db
	public static final String COLUMN_ID = "_id";  //column _id
	public static final String COLUMN_NAME = "name";  //column name
	
	public static final String DATABASE_NAME = "test.db";
	public static final int DATABASE_VERSION = 1;
	
	//SQL command
	public static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_TEST +
			"(" + COLUMN_ID + " integer primary key autoincrement, " + 
			COLUMN_NAME + " text not null);";
	
	public databaseAdp(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public databaseAdp(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		arg0.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		Log.w(databaseAdp.class.getName(), "Update database from version "+ arg1 + 
				" to version " + arg2 + " database will be destroy and setup again");
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
		onCreate(arg0);
	}
	
}