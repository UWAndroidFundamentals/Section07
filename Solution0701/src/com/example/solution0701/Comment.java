package com.example.solution0701;

public class Comment {

	// create an id number and comment text
	// as private members of the class
	private long id;
	private String comment;

	/*
	 * *************************************
	 * TODO: Add "approved" boolean field  *
	 ***************************************
	 */
	
	private boolean approved;
	
	// create a "getter" for the id
	public long getId() {
		return id;
	}

	// create a "setter" for the id
	public void setId(long id) {
		this.id = id;
	}

	// create a "getter" for the comment
	public String getComment() {
		return comment;
	}

	// create a "setter" for the comment
	public void setComment(String comment) {
		this.comment = comment;
	}

	/*
	 * **************************************************************
	 * TODO: Add getters and setters for "approved" boolean field  *
	 ***************************************************************
	 */
	

	// create a "getter" for the approved boolean instance variable
	public boolean isApproved() {
		return approved;
	}

	// create a "setter" for the "approved" boolean instance variable
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	
	
	// alternate way to get the comment for the arrayadapter
	@Override
	public String toString() {
		return comment;
	}
}
