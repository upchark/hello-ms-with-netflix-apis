package com.uk.ms.lcs.domain;

public class LoanCalculatorResponse extends LoanCalculatorRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoanCalculatorResponse() {super();}

	private double interestRate;

	private double emi;

	private double amountPayable;
	
	private double interestAmount;

	public LoanCalculatorResponse(double loanAmount, int tenure, String loanType, double interestRate, double emi,
			double amountPayable, double interestAmount) {
		super(loanAmount, tenure, loanType);
		this.interestRate = interestRate;
		this.emi = emi;
		this.amountPayable = amountPayable;
		this.interestAmount = interestAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public double getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public double getInterestAmount() {
		return interestAmount;
	}

	public void setInterestAmount(double interestAmount) {
		this.interestAmount = interestAmount;
	}

}
