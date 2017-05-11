package com.jpmg.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TradeJava8 {

	public static TradingList findDayAndAdjustSettlementOnweekDay(TradingList trading) { 
		List<Trade> tempObj = trading.getTrade();
		TradingList tempObjlist = new TradingList();
		//formatte date
		tempObj.stream().forEach(p -> formateDate(p));
		
		tempObj.stream().forEach(p -> System.out.println(p.getTempDate()));
		System.out.println("just checking purpose");
			
		// calculate trading amount
		tempObj.stream().forEach(p -> calculateTradeAmount(p));
		tempObj.stream().forEach(p -> System.out.println(p.getTradingAmount()));
		// check the trading date is on weekend and move the date forward
		tempObj.stream()
				.filter((p) -> ("SAR".equalsIgnoreCase(p.getCurrencyCode())
						|| "AED".equalsIgnoreCase(p.getCurrencyCode())))
				.filter((q) -> (q.getTempDate().getDayOfWeek().equals(DayOfWeek.FRIDAY)
						|| q.getTempDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)))
				.forEach(r -> r.setTempDate(r.getTempDate().with(TemporalAdjusters.next(DayOfWeek.SUNDAY))));
		tempObj.stream()
				.filter((p) ->  !"AED".equalsIgnoreCase(p.getCurrencyCode()) || "SAR".equalsIgnoreCase(p.getCurrencyCode()))
				.filter((q) -> (q.getTempDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)
						|| q.getTempDate().getDayOfWeek().equals(DayOfWeek.SATURDAY)))
				.forEach(r -> r.setTempDate(r.getTempDate().with(TemporalAdjusters.next(DayOfWeek.MONDAY))));
		
		tempObj.stream().forEach(p -> System.out.println(p.getSettlementDate()+" date adjusted"+p.getTempDate()));
		tempObjlist.setTrade(tempObj);
		//generateReport(tempObj);
		return tempObjlist;
	}
	public static void generateReport(List<Trade> tempObj) {
		// calculate total incoming amount
		Map<LocalDate, Double> newMap = new HashMap<>();
		
				newMap =	tempObj.stream().filter((p) -> "S".equalsIgnoreCase(p.getIndicator()))
						.collect(Collectors.groupingBy(Trade:: getTempDate, Collectors.summingDouble(Trade :: getTradingAmount)));
								newMap.forEach((k, v) -> System.out.println("Total Incomin Amount settled on " + k + " is, " + v + " USD"));
				//calculate total outgoing amount
								newMap =	tempObj.stream().filter((p) -> "B".equalsIgnoreCase(p.getIndicator()))
										.collect(Collectors.groupingBy(Trade:: getTempDate, Collectors.summingDouble(Trade :: getTradingAmount)));
												newMap.forEach((k, v) -> System.out.println("Total OutGoing Amount settled on " + k + " is, " + v + " USD"));
		//find rank one in selling
												
	tempObj.stream().filter((p) -> "S".equalsIgnoreCase(p.getIndicator()))
	.max(Comparator.comparingDouble(Trade :: getTradingAmount))
	.ifPresent(s -> System.out.println("rank 1 of selling is" +s.getTradingAmount()));
	//find rank one in selling
	
		tempObj.stream().filter((p) -> "B".equalsIgnoreCase(p.getIndicator()))
		.max(Comparator.comparingDouble(Trade :: getTradingAmount))
		.ifPresent(s -> System.out.println("rank 1 of selling is" +s.getTradingAmount()));
	}
	private static Object calculateTradeAmount(Trade p) {
		double tradeamunt = 0;
		tradeamunt = p.getUnitPrice() * p.getFxRate() * p.getNoOfUnits();
		p.setTradingAmount(tradeamunt);
		return p;
		
	}
	private static Object formateDate(Trade p) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		// Locale specifies human language for translating
		formatter = formatter.withLocale(Locale.UK);
		LocalDate ldate = LocalDate.parse(p.getSettlementDate(), formatter);
		p.setTempDate(ldate);
		return p;
	}

}
