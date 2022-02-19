package com.uk.ms.lcs.domain;

import java.io.Serializable;

/**
 * @author pranika
 *
 */
public class LoanCalculatorRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanCalculatorRequest() {}
	
	private double loanAmount;
	
	private int tenure;
	
	private String loanType;

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public LoanCalculatorRequest(double loanAmount, int tenure, String loanType) {
		super();
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.loanType = loanType;
	}

}
