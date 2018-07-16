package com.trms.beans;

public class GradeFormat {
	//    formatID number(6),
	//    passingGrade number(3),

	int ID, passingGrade;

	public GradeFormat() {
		super();
	}

	public GradeFormat(int iD, int passingGrade) {
		super();
		ID = iD;
		this.passingGrade = passingGrade;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(int passingGrade) {
		this.passingGrade = passingGrade;
	}

	@Override
	public String toString() {
		return "GradeFormat [ID=" + ID + ", passingGrade=" + passingGrade + "]";
	}
}
