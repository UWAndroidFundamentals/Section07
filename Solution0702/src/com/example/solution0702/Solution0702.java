package com.example.solution0702;

import java.io.IOException;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class Solution0702 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);  

		DataBaseHelper myDbHelper = new DataBaseHelper(this); 
		myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.createDataBase(); 
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			myDbHelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
		
		Cursor cursor = myDbHelper.retCursor();
		cursor.moveToFirst();
		
		
		while (!cursor.isAfterLast()) {

			/* Todo: pull in two fields, "FirstName" and "LastName" using the cursor.getColmnIndex 
			 * and cursor.getString
			 */
			

			Log.v("log", "full name = " + cursor.getString(cursor.getColumnIndex("FirstName")) + " " +  cursor.getString(cursor.getColumnIndex("LastName")));
			cursor.moveToNext();
		}

		cursor.close();

	}


}
