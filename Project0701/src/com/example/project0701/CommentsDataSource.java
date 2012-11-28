package com.example.project0701;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

// note SQLite database import statements
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CommentsDataSource {

	// private member to hold the database
	private SQLiteDatabase database;

	// private member to hold the helper class
	private MySQLiteHelper dbHelper;
	
	/*
	 **********************************************************
	 * TODO: Add constant COLUMN_APPROVED to list of columns  *
	 **********************************************************
	 */

	// private array list of the two columns (id number and comment text)
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_COMMENT };

	// constructor for our class that accepts context
	public CommentsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	// gets brand new database object with all permissions set correctly
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	// close the helper class
	public void close() {
		dbHelper.close();
	}

	/*
	 ******************************************************
	 * TODO: Add boolean value approved to parameter list *
	 ******************************************************
	 */

	public Comment createComment(String comment) {

		// holder for values
		ContentValues values = new ContentValues();

		// enter key/value pair
		// MySQLiteHelper.COLUMN_COMMENT = "comment" (the column name)
		// comment is the passed value
		values.put(MySQLiteHelper.COLUMN_COMMENT, comment);

		/*
		 **************************************************************
		 * TODO: Add MySQLiteHelper.COLUMN_APPROVED to values list.   *
		 * Since it is stored as an integer in the db, but held as an *
		 * boolean, you must convert from boolean to integer:         *
		 * 1 = true                                                   *
		 * 0 = false                                                  *
		 **************************************************************
		 */

		Log.v("log","test1");
		
		
		// insert a new record and get it's id value
		// MySQLiteHelper (extended from SQLiteHelper) has the constant
		// MySQLiteHelper.TABLE_COMMENTS = "comments" (the table name)
		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
				values);

		Log.v("log","test2");
		
		// set the cursor set to the record that was just added
		// MySQLiteHelper.TABLE_COMMENTS = "comments"
		// allColumns = array of column names
		// MySQLiteHelper.COLUMN_ID = _id
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);

		Log.v("log","test3");
		
		// move to the first (only) record in the cursor set
		cursor.moveToFirst();

		Log.v("log","test4");
		
		// call cursorToComment and pass the cursor pointing to the new record
		Comment newComment = cursorToComment(cursor);

		Log.v("log","test5");
		
		// close the cursor
		cursor.close();

		// return the Comment object newComment
		return newComment;
	}

	public void deleteComment(Comment comment) {
		
		// call the "getter" method from the comment object
		long id = comment.getId();
		
		// log the action
		System.out.println("Comment deleted with id: " + id);
		
		// MySQLiteHelper.TABLE_COMMENTS = "comments" = table name
		// MySQLiteHelper.COLUMN_ID = _id = column name
		// where clause = "_id = id"
		// where arguments are not used
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Comment> getAllComments() {
		
		// create an array list of comment objects
		List<Comment> comments = new ArrayList<Comment>();

		Log.v("log","test1");

		// get a cursor set of all records in the table
		// table = MySQLiteHelper.TABLE_COMMENTS = "comments"
		// columns = allColumns = array of strings of column names
		// selection = null
		// selectionArgs = null
		// groupBy = null
		// having = null
		// orderBy = null
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);

		// move to the first record in the cursor set
		cursor.moveToFirst();
		
		// cycle through the entire cursor set and add each Comment
		// object to the comments list
		while (!cursor.isAfterLast()) {
			Log.v("log","cursor = " + cursor.getPosition());
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}

		// close the cursor
		cursor.close();
		
		// return the list of comments
		return comments;
	}

	private Comment cursorToComment(Cursor cursor) {

		// create a new Comment type object
		Comment comment = new Comment();

		// pull the id (the long number at zero based index 0) 
		comment.setId(cursor.getLong(0));
		
		// pull the comment string (the text at zero based index 1) 
		comment.setComment(cursor.getString(1));
		
		/*
		 *********************************************************************
		 * TODO: Check value of "approved" column.  If int is 0 then         *
		 * set boolean to false.  If int is 1 then set boolean to            *
		 * true.  Also prepend comment with flag.                            *
		 * example = comment.setComment("approved: " + cursor.getString(1)); *
		 *********************************************************************
		 */
		

		
		// return the Comment type object
		return comment;
	}
}