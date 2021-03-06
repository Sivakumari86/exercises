/**
 * 
 */
package com.jpmg.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author y
 *
 */
public class TradingHelper {

	public static Map<LocalDate, Double> buyMap = new HashMap<LocalDate, Double>();
	public static Map<LocalDate, Double> sellMap = new HashMap<LocalDate, Double>();

	/**
	 * This method is used to check the given date is falling on weekend and
	 * change the settlement date to next working day
	 *
	 */
	public static TradingList findDayAndAdjustSettlementOnweekDay(TradingList trading) {
		TradingList tradingList = new TradingList();
		List<Trade> tempObj = new ArrayList<Trade>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		// Locale specifies human language for translating
		formatter = formatter.withLocale(Locale.UK);
		for (Trade trade : trading.getTrade()) {

			// calculate the trading amount and store it in object.
			trade.setTradingAmount(calculateTradeAmount(trade));
			// double tradeAmunt = calculateTradeAmount(trade);
			LocalDate ldate = LocalDate.parse(trade.getSettlementDate(), formatter);
			DayOfWeek day = ldate.getDayOfWeek();
			
			
			// check if the day is saturday then move the settlement date to
			// next working day.
			if (day.equals(DayOfWeek.SATURDAY)) {
				// check if the currency is AED or SAR then move the settlement
				// date to next working day which is sunday else monday.
				if (("AED".equalsIgnoreCase(trade.getCurrencyCode())
						|| "SAR".equalsIgnoreCase(trade.getCurrencyCode()))) {
					ldate = ldate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
					System.out.println("trading settlement date is adjusted to " + ldate + " from "
							+ trade.getSettlementDate() + "  since the day is.." + day + " and the currency code is "
							+ trade.getCurrencyCode());
					trade.setTempDate(ldate);
					tempObj.add(trade);
				} else {
					ldate = ldate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
					System.out.println("trading settlement date is adjusted to " + ldate + " from "
							+ trade.getSettlementDate() + " since the day is.." + day + "  and the currency code is "
							+ trade.getCurrencyCode());
					trade.setTempDate(ldate);
					tempObj.add(trade);
				}

			}

			// check if the day is friday and currency is AED or SAR then move
			// the settlement date to next working day.
			else if (day.equals(DayOfWeek.FRIDAY) && ("AED".equalsIgnoreCase(trade.getCurrencyCode())
					|| "SAR".equalsIgnoreCase(trade.getCurrencyCode()))) {
				ldate = ldate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
				System.out.println(
						"trading settlement date is adjusted to " + ldate + " from " + trade.getSettlementDate()
								+ " since the day is.." + day + " and the currency code is " + trade.getCurrencyCode());
				trade.setTempDate(ldate);
				tempObj.add(trade);

			}
			// check if the day is saterday and currency is not of AED or SAR
			// then move the settlement date to next working day.
			else if (day.equals(DayOfWeek.SUNDAY) && !("AED".equalsIgnoreCase(trade.getCurrencyCode())
					|| "SAR".equalsIgnoreCase(trade.getCurrencyCode()))) {
				ldate = ldate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

				System.out.println(
						"trading settlement date is adjusted to " + ldate + " from " + trade.getSettlementDate()
								+ " Since the day is.." + day + " and the currency code is " + trade.getCurrencyCode());
				trade.setTempDate(ldate);
				tempObj.add(trade);
			} else {
				// just add same day in localdate format.
				trade.setTempDate(ldate);
				tempObj.add(trade);
			}
		}
		tradingList.setTrade(tempObj);
		return tradingList;
	}

	/**
	 * This method is used to calculate the trading amount using then formulae.
	 */
	public static double calculateTradeAmount(Trade trade) {
		double tradeamunt = 0;
		tradeamunt = trade.getUnitPrice() * trade.getFxRate() * trade.getNoOfUnits();
		return tradeamunt;

	}

	public static void findTransactionAmount(TradingList tempList) {
		List<Trade> tempObj = tempList.getTrade();
		// just sort to display te report in ascending order..
//		tempObj.sort((h1, h2) -> h1.getSettlementDate().compareTo(h2.getSettlementDate()));
//		for (Trade trade : tempObj) {
//			if ("B".equalsIgnoreCase(trade.getIndicator())) {
//				calculateTotalOutGoingTradeAmount(trade.getTempDate(), trade.getTradingAmount());
//			} else {
//				calculateTotalIncomingTradeAmount(trade.getTempDate(), trade.getTradingAmount());
//			}
//		}
		// just to work on streams..
		 tempObj.stream().filter(p -> p.getIndicator().equalsIgnoreCase("B"))
				.forEach(p -> calculateTotalOutGoingTradeAmount(p.getTempDate(), p.getTradingAmount())
						);
		
		System.out.println("                                           ");
		System.out.println("******************  TRADING REPORT   ***************************");
		// print total out going amount each day
		buyMap.forEach((k, v) -> System.out.println("Total OutGoing Amount settled on " + k + " is, " + v + " USD"));
		// print total incoming amount each day
		sellMap.forEach((k, v) -> System.out.println("Total InComing Amount settled on " + k + " is, " + v + " USD"));
		// find the ranking for out going...
		tempObj.stream().filter(i -> i.getIndicator().equalsIgnoreCase("B"))
				.max(Comparator.comparing(Trade::getTradingAmount))
				.ifPresent(max -> System.out.println(max.getCompanyName() + "  is rank 1 for outGoing"));

		// find the ranking for incoming...
		tempObj.stream().filter(i -> i.getIndicator().equalsIgnoreCase("S"))
				.max(Comparator.comparing(Trade::getTradingAmount))
				.ifPresent(max -> System.out.println(max.getCompanyName() + "  is rank 1 for inComing"));

	}

	/**
	 * This method is used to calculate out Going amount
	 * 
	 * @param date
	 * @param amount
	 */
	private static void calculateTotalOutGoingTradeAmount(LocalDate date, double amount) {
		double d = 0;
		if (buyMap.containsKey(date)) {
			d = buyMap.entrySet().iterator().next().getValue();

		}
		buyMap.put(date, amount + d);

	}

	/**
	 * This method is used to calculate Incoming amount
	 * 
	 * @param date
	 * @param amount
	 */
	private static void calculateTotalIncomingTradeAmount(LocalDate date, double amount) {
		double d = 0;
		if (sellMap.containsKey(date)) {
			d = sellMap.entrySet().iterator().next().getValue();

		}
		sellMap.put(date, amount + d);

	}

}
