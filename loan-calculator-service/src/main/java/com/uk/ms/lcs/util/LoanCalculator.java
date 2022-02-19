package com.uk.ms.lcs.util;

import com.uk.ms.lcs.domain.LoanCalculatorResponse;

public class LoanCalculator {
	
	private static final int YEARLY_COMPOUND = 1;
	private static final int MONTH_IN_YEAR = 12;

	public static LoanCalculatorResponse calculateLoan(double principle, int tenure, double interestRate,
			String loanType) {
		double interestAmount = principle * (Math.pow((1 + (interestRate/100)), (tenure * YEARLY_COMPOUND))) - principle;
		double totalPayableAmount = principle + interestAmount;
		double emi = totalPayableAmount/(tenure * MONTH_IN_YEAR);
		return new LoanCalculatorResponse(principle, tenure, loanType, interestRate, emi, totalPayableAmount, interestAmount);
	}
	
	private LoanCalculator() {}

}
