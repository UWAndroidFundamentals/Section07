package com.example.solution0702;

/* Description: This example copies a pre-loaded SQLite database from 
 * the assets folder.
 * 
 * Based loosely on http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/ 
 * 
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

// extend from the abstract class SQLiteOpenHelper
public class DataBaseHelper extends SQLiteOpenHelper {

	// get the full path to where the database will live when it is copied
	private static String DB_PATH = "/data/data/com.example.solution0702/databases/";

	// set the name of the database
	private static String DB_NAME = "Database0702";

	// create a database private to this class - note that this means you
	// will not directly access it outside of the class
	private SQLiteDatabase myDataBase;

	// create a holder for the activity context when you create an instance
	// of this class
	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			// do nothing - database already exist
		} else {
			// By calling this method an empty database will be created into
			// the default system path of your application. We will overwrite
			// that database with our database. See DB_PATH
			this.getReadableDatabase();
			try {
				// invoke the method in this class
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			// try to create a read only database
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// database does't exist yet.
		}
		if (checkDB != null) {
			checkDB.close();
		}
		// return a boolean value if the db exists or not
		return checkDB != null ? true : false;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {
		
		// open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		
		// path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		
		// open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		
		// close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {
		
		// open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	// method to return a cursor object
	public Cursor retCursor() {
		
		// define a new cursor
		Cursor c = null;
		
		/* Todo: Define a string array that contains two field names, "FirstName"
		 * and "LastName".   Invoke the query method from myDataBase.  For the first
		 * two parameters, pass in "Table0702" and the string array of field names. 
		 * For the remaining five parameters, pass in "null".
		 */
		
		String[] fields = { "FirstName", "LastName" };

		// query the table with the field list and create a cursor
		c = myDataBase.query("Table0702", fields, null, null, null, null, null);
		
		// return the cursor
		return c;
	}

	// close the database
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
