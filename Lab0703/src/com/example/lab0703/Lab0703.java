package com.example.lab0703;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;

public class Lab0703 extends Activity {

	// shared preference 
	SharedPreferences myPreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);
	
	// create an entry in the shared preference
	myPreference = getSharedPreferences("ConfigList",
			Context.MODE_PRIVATE);
	
	// get a new editor for the preference
	Editor editor = myPreference.edit();

	// write string to the preference
	editor.putString("myString", "my String Value");
	
	// write boolean to the preference
	editor.putBoolean("myBoolean", true);
	
	// write integer to the preference
	editor.putInt("myInteger", 1);
	
	// commit the changes to the editor
	editor.commit();
	
	// pull string from the preference
	String pulledString = myPreference.getString("myString", "empty");
	
	// pull boolean from the preference
	boolean pulledBoolean = myPreference.getBoolean("myBoolean", false);
	
	// pull integer from the preference  
	int pulledInteger = myPreference.getInt("myInteger", 0);
	
	// log all pulled values	
	Log.v("log","Pulled String = " + pulledString);
	Log.v("log","Pulled Boolean = " + pulledBoolean);
	Log.v("log","Pulled Integer = " + pulledInteger);
	
	}

}
