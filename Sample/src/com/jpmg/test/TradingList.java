package com.jpmg.test;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tradingList")
public class TradingList {
private List<Trade> trade;

/**
 * @return the trade
 */
public List<Trade> getTrade() {
	return trade;
}

/**
 * @param trade the trade to set
 */
public void setTrade(List<Trade> trade) {
	this.trade = trade;
}


}
