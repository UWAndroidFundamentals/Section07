package com.example.lab0701;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class Lab0701 extends ListActivity {

	// make a local copy of the data source object
	private CommentsDataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen001);

		// construct the new object and invoke the open method
		datasource = new CommentsDataSource(this);
		datasource.open();

		// load all comments into the "values" list
		List<Comment> values = datasource.getAllComments();

		// link the values list to the listview through the adapter
		ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	// direct onClick attribute call from the XML level
	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		
		// since this is a ListActivity, pull in the activities listview
		ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
		
		// create a Comment type object
		Comment comment = null;
		
		// since both buttons access the same onClick method, detect which
		// button called the method
		switch (view.getId()) {
		
		// this method was called from the add button
		case R.id.add:
			
			// create three strings
			String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
			
			// randomly select one of the comments
			int nextInt = new Random().nextInt(3);
			
			// add the randomly selected string to the database 
			// and return a new Comment type object
			comment = datasource.createComment(comments[nextInt]);
			
			// add the comment to our local adapter (and the listview)
			adapter.add(comment);
			
			break;
			
		// this method was called from the delete button	
		case R.id.delete:
			
			// check to make sure the activity's adapter has an entry
			if (getListAdapter().getCount() > 0) {
				
				// get the first Comment type object from the adapter
				comment = (Comment) getListAdapter().getItem(0);
				
				// delete the comment from the database through our 
				// data source object
				datasource.deleteComment(comment);
				
				// remove the item from the adapter
				adapter.remove(comment);
			}
			break;
		}
		
		// notify the adapter the data has changed and repaint the
		// list view UI element
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		// open the datasource when the activity is active
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		
		// close the datasource when the activity is in the background
		datasource.close();
		super.onPause();
	}

}
