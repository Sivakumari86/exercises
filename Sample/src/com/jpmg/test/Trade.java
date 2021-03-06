/**
 * 
 */
package com.jpmg.test;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author y
 *
 */
public class Trade {
	
	private String companyName;
	private String indicator;
	private String currencyCode;
	private double fxRate;
	private String instructionDate;
	private String settlementDate;
	private int noOfUnits;
	private double unitPrice;
	private LocalDate tempDate;
	private double tradingAmount;
	/**
	 * @return the tradingAmount
	 */
	public double getTradingAmount() {
		return tradingAmount;
	}
	/**
	 * @param tradingAmount the tradingAmount to set
	 */
	public void setTradingAmount(double tradingAmount) {
		this.tradingAmount = tradingAmount;
	}
	/**
	 * @return the tempDate
	 */
	public LocalDate getTempDate() {
		return tempDate;
	}
	/**
	 * @param tempDate the tempDate to set
	 */
	public void setTempDate(LocalDate tempDate) {
		this.tempDate = tempDate;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the indicator
	 */
	public String getIndicator() {
		return indicator;
	}
	/**
	 * @param indicator the indicator to set
	 */
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	/**
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * @param currencyCode the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	/**
	 * @return the fxRate
	 */
	public double getFxRate() {
		return fxRate;
	}
	/**
	 * @param fxRate the fxRate to set
	 */
	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}
	/**
	 * @return the instructionDate
	 */
	public String getInstructionDate() {
		return instructionDate;
	}
	/**
	 * @param instructionDate the instructionDate to set
	 */
	public void setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
	}
	/**
	 * @return the settlementDate
	 */
	public String getSettlementDate() {
		return settlementDate;
	}
	/**
	 * @param settlementDate the settlementDate to set
	 */
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * @return the noOfUnits
	 */
	public int getNoOfUnits() {
		return noOfUnits;
	}
	/**
	 * @param noOfUnits the noOfUnits to set
	 */
	public void setNoOfUnits(int noOfUnits) {
		this.noOfUnits = noOfUnits;
	}
	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	

}
