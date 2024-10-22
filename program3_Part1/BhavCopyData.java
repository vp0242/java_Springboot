package program3_Part1;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class BhavCopyData {

    String symbol;
    String series;
    Double open;
    Double high;
    Double low;
    Double close;
    Double last;
    Double prevClose;
    Long totalTradedQty;
    Double totalTradedVal;
    String timestamp;
    Integer totalTrades;
    String isin;

    BhavCopyData() {
    }

    BhavCopyData(String symbol, String series, Double open, Double high, Double low, Double close, Double last,
            Double prevClose, Long totalTradedQty, Double totalTradedVal, String timestamp, Integer totalTrades,
            String isin) {
        this.symbol = symbol;
        this.series = series;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.last = last;
        this.prevClose = prevClose;
        this.totalTradedQty = totalTradedQty;
        this.totalTradedVal = totalTradedVal;
        this.timestamp = timestamp;
        this.totalTrades = totalTrades;
        this.isin = isin;
    }

    public List<BhavCopyData> storeDataIntoMemory() {
        String Filepath = "/home/vishwap/Rebirth/java_assessment/program3_Part1/20241007_NSE.csv";
        List<BhavCopyData> datalist = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Filepath));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length < 13) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                try {
                    BhavCopyData copyData = new BhavCopyData(
                            fields[0].trim(),
                            fields[1].trim(),
                            parseIntoDouble(fields[2].trim()),
                            parseIntoDouble(fields[3].trim()),
                            parseIntoDouble(fields[4].trim()),
                            parseIntoDouble(fields[5].trim()),
                            parseIntoDouble(fields[6].trim()),
                            parseIntoDouble(fields[7].trim()),
                            parseIntoLong(fields[8].trim()),
                            parseIntoDouble(fields[9].trim()),
                            fields[10].trim(),
                            parseIntoInt(fields[11].trim()),
                            fields[12].trim());
                    datalist.add(copyData);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error While Reading Data From BhavCopy CSV..");
        }
        return datalist;

    }

    public Integer parseIntoInt(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        try {
            int value = Integer.parseInt(data);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public Double parseIntoDouble(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        try {
            Double value = Double.parseDouble(data);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public Long parseIntoLong(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        try {
            Long value = Long.parseLong(data);
            return value;
        } catch (Exception e) {
            return null;
        }
    }

}
