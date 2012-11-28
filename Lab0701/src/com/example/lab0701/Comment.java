package com.example.lab0701;

public class Comment {

	// create an id number and comment text
	// as private members of the class
	private long id;
	private String comment;

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

	// alternate way to get the comment for the arrayadapter
	@Override
	public String toString() {
		return comment;
	}
}
