package com.example.solution0703;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class PullPreference extends Activity {

	// shared preference 
	SharedPreferences myPreference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		/* TODO: Pull string, boolean, and integer from shared preference.
		 * Write values to log file.
		 */
		
		// create an reference in the shared preference
		myPreference = getSharedPreferences("ConfigList",
				Context.MODE_PRIVATE);
		
		// pull string from the preference
		String pulledString = myPreference.getString("myString", "empty");
		
		// pull boolean from the preference
		boolean pulledBoolean = myPreference.getBoolean("myBoolean", false);
		
		// pull integer from the preference  
		int pulledInteger = myPreference.getInt("myInteger", 0);
		
		// log all pulled values	
		Log.v("log","Pulled String from second activity = " + pulledString);
		Log.v("log","Pulled Boolean from second activity = " + pulledBoolean);
		Log.v("log","Pulled Integer from second activity = " + pulledInteger);
		
	}
}
