package com.example.solution0701;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// we extend (subclass) the abstract SQLiteOpenHelper class
public class MySQLiteHelper extends SQLiteOpenHelper {

	// create constants for the table columns
	public static final String TABLE_COMMENTS = "comments";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_COMMENT = "comment";

	/*
	 ********************************************************************
	 * TODO: Add constant COLUMN_APPROVED for "approved" boolean field  *
	 ********************************************************************
	 */

	public static final String COLUMN_APPROVED = "approved";
	
	// create database specific constants
	private static final String DATABASE_NAME = "commments.db";

	/*
	 ************************************************************************
	 * TODO: Change DATABASE_VERSION to 2 so database recreate is triggered *
	 ************************************************************************
	 */
	
	private static final int DATABASE_VERSION = 2;

	// build a new constant for the database create statement
	// table "comments" has two fields:
	// integer "_id"
	// comment "text"

	/*
	 ********************************************************************
	 * TODO: Expand DATABASE_CREATE constant to include "approved" field*
	 * note that it is created as an integer since SQLite knows only    *
	 * TEXT, INTEGER, and REAL values                                   *
	 ********************************************************************
	 */
	
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_COMMENTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_COMMENT
			+ " text not null," + COLUMN_APPROVED + " integer not null);";

	// constructor for MySQLiteHelper passing in context
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// use the execSQL method of the concrete onCreate method 
	// implemented from the abstract SQLiteOpenHelper class
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	// when the version changes, drop the old table and create a new one
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
		onCreate(db);
	}

}
