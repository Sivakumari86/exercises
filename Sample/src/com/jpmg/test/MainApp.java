/**
 * 
 */
package com.jpmg.test;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author y
 *
 */
public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TradingList trading=null;
		try {
			//read the input file in below specified path
			File inputFile = new File("src/com/jpmg/test/tradeprofile.xml");
			//We need to create JAXContext instance
            JAXBContext context = JAXBContext.newInstance(TradingList.class);
          //Use JAXBContext instance to create the Unmarshaller.
            Unmarshaller un = context.createUnmarshaller();
            trading = (TradingList) un.unmarshal(inputFile);
           
        } catch (JAXBException e) {
            e.printStackTrace();
        }
	
		TradingList tempList = TradingHelper.findDayAndAdjustSettlementOnweekDay(trading);
		// find total incoming and out going amount.
		TradingHelper.findTransactionAmount(tempList);
		
	}

}
