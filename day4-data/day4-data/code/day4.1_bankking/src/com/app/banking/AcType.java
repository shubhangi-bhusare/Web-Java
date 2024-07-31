package com.app.banking;

public enum AcType { // give validation for each acc min balance condition
	SAVINGS(10000), CURRENT(5000), FD(50000), 
	LOAN(25000), DMAT(40000), NRE(150000);

	// state
	private double minBalance;

	// ctor
	AcType(double minBalance) {
		//super(name and ordinal position add by compiler)
		this.minBalance = minBalance;
	}
	//can you override toString ?
	@Override
	public String toString() { // if we not overide then it call from its superclass enum
		return name()+" : "+minBalance;
	}
	//can you add methods ? Yes
	public double getMinBalance() {
		return minBalance;
	}
	
	
}
