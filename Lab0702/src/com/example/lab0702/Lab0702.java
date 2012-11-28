package com.example.lab0702;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.view.Menu;

public class Lab0702 extends Activity {

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
			Log.v("log", "name = " + cursor.getString(cursor.getColumnIndex("Name")));
			cursor.moveToNext();
		}


		cursor.close();

	}

}
