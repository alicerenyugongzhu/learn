package com.example.learn;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 
 * @author Administrator
 * Our DAO class
 *
 */
public class TestDatabaseSource {
	//Database part
	private SQLiteDatabase database;
	DatabaseContext dbContext;
	private databaseAdp dbAdp;
	private String[] allColumns = {databaseAdp.COLUMN_ID, databaseAdp.COLUMN_NAME};
	
	public TestDatabaseSource(Context context){
		dbContext = new DatabaseContext(context);
		dbAdp = new databaseAdp(dbContext);
	}
	
	public void Open() throws SQLException{
		database = dbAdp.getWritableDatabase();
	}
	
	public void Close(){
		dbAdp.close();
	}
	
	public Comment CreateName(String name){
		ContentValues value = new ContentValues();
		value.put(databaseAdp.COLUMN_NAME, name);
		long insertId = database.insert(databaseAdp.TABLE_TEST, null, value);
		Cursor cursor = database.query(databaseAdp.TABLE_TEST, allColumns, 
				databaseAdp.COLUMN_ID + "=" + insertId, null, null, null, null);
		cursor.moveToFirst();
		Log.d("alice", "Done for Create Name");
		return cursorToComment(cursor);
	}
	
	public void DeleteName(Comment comment){
		long id = comment.getId();
		System.out.println("Comment delete with ID : " + id);
		database.delete(databaseAdp.TABLE_TEST, databaseAdp.COLUMN_ID + "=" + id, null);
	}
	
	public List<Comment> getAllName(){
		List<Comment> comments = new ArrayList<Comment>();
		Cursor cursor = database.query(databaseAdp.TABLE_TEST, allColumns, null,
				null, null, null, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		cursor.close();
		return comments;
	}

	private Comment cursorToComment(Cursor cursor) {
		
		Comment comment = new Comment();
		comment.setId(cursor.getLong(0));
		comment.setName(cursor.getString(1));
		return comment;
	}

}