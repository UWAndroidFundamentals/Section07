package com.example.solution0703;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;

public class Solution0703 extends Activity {

	// shared preference 
	SharedPreferences myPreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);
	
		/* TODO: Pull the "ConfigList" preference from myPreference. 
		 * Create a new editor and link to myPreference.
		 * Put a string, boolean, and integer value into the editor.
		 * Commit the editor to the preference.
		 * Create a new explicit intent and start a new activity.
		 * Don't forget to create the entry in the manifest file for
		 * the second activity.
		 */
		
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

		// create explicit intent to call second activity
		startActivity(new Intent(getApplicationContext(), PullPreference.class));
		
	}



}
