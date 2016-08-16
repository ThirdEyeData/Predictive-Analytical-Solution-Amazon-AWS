package com.amazonbyod.stockdata;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;

import au.com.bytecode.opencsv.CSVWriter;

/*System.out.println(allRows.next());
System.out.println(allRows.next().getString(0));
System.out.println(allRows.next().getString(1));
System.out.println(allRows.next().getString(2));
System.out.println(allRows.next().getString(3));
System.out.println(allRows.next().getString(4));
System.out.println(allRows.next().getString(5));
System.out.println(allRows.next().getString(6));
System.out.println(allRows.next().getString(7));
System.out.println(allRows.next().getString(8));
System.out.println(allRows.next().getString(9));
System.out.println(allRows.next().getString(10));
System.out.println(allRows.next().getString(11));
System.out.println(allRows.next().getString(12));*/

public class CollectStockData {

	public static void main(String args[]) throws IOException {
        int count = 0;
		CSVWriter writer = new CSVWriter(new FileWriter("/home/abhinandan/Projects/AmazonByod/stockdata.csv"));
		String[] csvHeader = "Date,Open,High,Low,Close,Volume,Ex-Dividend,Split Ratio,Adj. Open,Adj. High,Adj. Low,Adj. Close,Adj. Volume"
				.split(",");
		writer.writeNext(csvHeader);
		String[] symbol = { "AAPL", "AMAT", "AMGN", "AAL", "ADBE", "MSFT", "AVGO", "CSCO", "FOX", "INTC", "NVDA", "WDC",
				"TSLA", "PYPL", "EXPE", "CTSH", "CA", "CMCSA", "CTXS", "MAR", "QCOM", "YHOO", "VOD", "SYMC", "LLTC",
				"FB", "INTU", "MAT", "NFLX", "ORLY" };
		for (int i = 0; i < symbol.length; i++) {
			QuandlSession session = QuandlSession.create();
			TabularResult tabularResult = session.getDataSet(DataSetRequest.Builder.of("WIKI/" + symbol[i]).build());
			Iterator<Row> allRows = tabularResult.iterator();
			while (allRows.hasNext()) {
				String csvRow = allRows.next().getString(0) + "," + allRows.next().getString(1) + ","
						+ allRows.next().getString(2) + "," + allRows.next().getString(3) + ","
						+ allRows.next().getString(4) + "," + allRows.next().getString(5) + ","
						+ allRows.next().getString(6) + "," + allRows.next().getString(7) + ","
						+ allRows.next().getString(8) + "," + allRows.next().getString(9) + ","
						+ allRows.next().getString(10) + "," + allRows.next().getString(11) + ","
						+ allRows.next().getString(12);
				writer.writeNext(csvRow.split(","));
				System.out.println(count++);
			}

		}
		writer.close();

	}

}
