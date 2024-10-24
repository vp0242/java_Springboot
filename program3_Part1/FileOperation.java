package program3_Part1;

import java.util.*;

class Gain {
    Double gain;
    String symbol;
    Double totalTradedVal;

    Gain(String s, Double g, Double t) {
        this.symbol = s;
        this.gain = g;
        this.totalTradedVal = t;
    }
}

public class FileOperation {
    private int choice;
    private Scanner sc = new Scanner(System.in);
    List<BhavCopyData> datalist = new ArrayList<>();

    FileOperation(int choice) {
        this.choice = choice;
        BhavCopyData data = new BhavCopyData();
        datalist = data.storeDataIntoMemory();
    }

    public void startFileOperation() {
        // String Output = "";
        switch (choice) {
            case 1:
                getParticularSymbol();
                break;
            case 2:
                getCountSeries();
                break;
            case 3:
                getSymbolWithParticularGain();
                break;
            case 4:
                getSymbolWithParticularTopBot();
                break;
            case 5:
                getStandardDeviation();
                break;
            case 6:
                getTopNGainersForGivenSymbol();
                break;
            case 7:
                getTopNLosersForGivenSymbol();
                break;
            case 8:
                getTopTradedByVolumeSymbol();
                break;
            case 9:
                getLeastTradedByVolumeSymbol();
                break;
            case 10:
                getHighestAndLowestTradedForSeries();
                break;
            default:
                System.out.println("Invalid Choice!!!");
                break;
        }
    }

    boolean isDatalistEmpty() {
        if (datalist == null || datalist.isEmpty()) {
            System.out.println("datalist is Empty..");
            return true;
        }
        return false;
    }

    void printRecord(BhavCopyData record) {
        System.out.println("Given Record:");
        System.out.println(record.symbol + " " + record.series + " " + record.open + " " + record.high + " "
                + record.low + " " + record.close + " " + record.last + " " + record.prevClose + " "
                + record.totalTradedQty + " " + record.totalTradedVal + " " + record.timestamp + " "
                + record.totalTrades + " " + record.isin);
    }

    //getting information about input symboll
    void getParticularSymbol() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: SYMBOL <Symbol>");
        System.out.print("SYMBOL ");
        String symbol = sc.nextLine().trim();
        boolean isPresent = false;
        for (BhavCopyData record : datalist) {
            if (record.symbol.equalsIgnoreCase(symbol)) {
                printRecord(record);
                isPresent = true;
            }
        }
        if (!isPresent) {
            System.out.println("No Data Found For Particular Symbol");
        }
    }

    //getting count of series
    void getCountSeries() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: COUNT <series>");
        System.out.print("COUNT ");
        String series = sc.nextLine().trim();
        int count = 0;
        for (BhavCopyData record : datalist) {
            if (record.series.equalsIgnoreCase(series)) {
                count++;
            }
        }
        if (count <= 0) {
            System.out.println("No Data Found For Particular Series");
        } else {
            System.out.println("Count the number of symbols for a given SERIES:\t" + count);

        }
    }

    //getting symbol with particular gain input
    void getSymbolWithParticularGain() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: GAIN <N>");
        System.out.print("GAIN ");
        try {
            Double gain = Double.parseDouble(sc.nextLine().trim());
            for (BhavCopyData record : datalist) {
                if (record.open != 0 || record.open != null) {
                    Double calPercaentageGain = (record.close - record.open) / (record.open);
                    if (calPercaentageGain > (gain / 100)) {
                        System.out.println(record.symbol);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getSymbolWithParticularGain : While parsing String to Double.");
        }

    }

    
    //getting symbol with particular Topbot input
    void getSymbolWithParticularTopBot() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: TOPBOT <N>");
        System.out.print("TOPBOT ");

        try {
            Double topbot = Double.parseDouble(sc.nextLine().trim());
            for (BhavCopyData record : datalist) {
                if (record.low != 0 || record.low != null) {
                    Double calPercaentageTopBot = (record.high - record.low) / (record.low);
                    if (calPercaentageTopBot > (topbot / 100)) {
                        System.out.println(record.symbol);
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Error in getSymbolWithParticularTopBot : While parsing String to Double.");
        }
    }

    //getting standard deviation for given series
    void getStandardDeviation() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: STDDEV <N>");
        System.out.print("STDDEV ");
        String series = sc.nextLine().trim();
        List<Double> prices = new ArrayList<>();
        for (BhavCopyData record : datalist) {
            if (record.series.equalsIgnoreCase(series)) {
                prices.add(record.close);
            }
        }
        if (prices.isEmpty()) {
            System.out.println("No Data found..");
            return;
        }
        Double mean = getMean(prices);
        Double sum = 0d;
        for (Double price : prices) {
            sum = sum + Math.pow(price - mean, 2);
        }
        System.out.printf("Standard deviation for all symbols of %s is %.4f ",series,Math.sqrt(sum / prices.size()));

    }


    Double getMean(List<Double> prices) {
        Double sum = 0d;
        for (Double price : prices) {
            sum = sum + price;
        }
        return sum / prices.size();
    }

    //top records symbol with highest gain 
    void getTopNGainersForGivenSymbol() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: TOPGAINER <N>");
        System.out.print("TOPGAINER ");

        try {
            int number = Integer.parseInt(sc.nextLine().trim());
            List<Gain> gainList = getGain(new ArrayList<>());
            gainList.sort(Comparator.comparingDouble(sg -> -sg.gain));
            if (gainList.isEmpty()) {
                System.out.println("No trades found..");
                return;
            }
            for (int index = 0; index < number; index++) {
                Gain myGain = gainList.get(index);
                System.out.println(myGain.symbol + " : " + myGain.gain);
            }
        } catch (Exception e) {
            System.out.println("Error in getTopNGainersForGivenSymbol : While parsing String to Int.");
        }
    }

    List<Gain> getGain(List<Gain> gainList) {
        for (BhavCopyData record : datalist) {
            if (record.open != 0 || record.open != null) {
                Double calPercaentageGain = ((record.close - record.open) / record.open) * 100;
                System.out.println(calPercaentageGain);
                gainList.add(new Gain(record.symbol, calPercaentageGain, null));
            }
        }
        return gainList;
    }

    //top records symbol with lowest gain 
    void getTopNLosersForGivenSymbol() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: TOPLAGGARDS <N>");
        System.out.print("TOPLAGGARDS ");

        try {
            int number = Integer.parseInt(sc.nextLine().trim());
            List<Gain> gainList = new ArrayList<>();
            gainList = getGain(gainList);
            gainList.sort(Comparator.comparingDouble(sg -> sg.gain));
            if (gainList.isEmpty()) {
                System.out.println("No trades found..");
                return;
            }
            for (int index = 0; index < number; index++) {
                Gain myGain = gainList.get(index);
                System.out.println(myGain.symbol + " : " + myGain.gain);
            }
        } catch (Exception e) {
            System.out.println("Error in getTopNLosersForGivenSymbol : While parsing String to Int.");
        }

    }
   
    //top records symbol with highest trade 
    void getTopTradedByVolumeSymbol() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: TOPTRADED <N>");
        System.out.print("TOPTRADED ");

        try {
            int number = Integer.parseInt(sc.nextLine().trim());
            Long[] trades = new Long[datalist.size()];
            int i = 0;
            for (BhavCopyData record : datalist) {
                trades[i] = record.totalTradedQty;
                i++;
            }

            Arrays.sort(trades);
            int Count = 0;
            for (int index = trades.length - 1; index >= 0; index--) {
                for (BhavCopyData record : datalist) {
                    if (record.totalTradedQty == trades[index]) {
                        if (Count == number) {
                            break;
                        }
                        System.out.println(record.symbol + ":" + record.totalTradedQty);
                        Count++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getTopTradedByVolumeSymbol : While parsing String to Int.");
        }

    }

    //top records symbol with lowest trade 
    void getLeastTradedByVolumeSymbol() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: BOTTRADED <N>");
        System.out.print("BOTTRADED ");
        try {
            int number = Integer.parseInt(sc.nextLine().trim());
            Long[] trades = new Long[datalist.size()];
            int i = 0;
            for (BhavCopyData record : datalist) {
                trades[i] = record.totalTradedQty;
                i++;
            }

            Arrays.sort(trades);
            int Count = 0;
            for (int index = 0; index < trades.length; index++) {
                for (BhavCopyData record : datalist) {
                    if (record.totalTradedQty == trades[index]) {
                        if (Count == number) {
                            break;
                        }
                        System.out.println(record.symbol + ":" + record.totalTradedQty);
                        Count++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getLeastTradedByVolumeSymbol : While parsing String to Int.");
        }

    }

    //record symbol with highest and lowest trade for given series 
    void getHighestAndLowestTradedForSeries() {
        if (isDatalistEmpty()) {
            return;
        }
        System.out.println("\n<Usage>: HIGHLOW <N>");
        System.out.print("HIGHLOW ");
        String series = sc.nextLine().trim();
        List<Gain> trades = new ArrayList<>();
        for (BhavCopyData record : datalist) {
            if (record.series.equalsIgnoreCase(series)) {
                trades.add(new Gain(record.symbol, null, record.totalTradedVal));

            }
        }

        if (trades.isEmpty()) {
            System.out.println("No trades found for given series");
            return;
        }

        Gain highesttrade = trades.get(0);
        Gain lowesttrade = trades.get(0);

        for (Gain trade : trades) {
            if (trade.totalTradedVal > highesttrade.totalTradedVal) {
                  highesttrade=trade;
            }
            if (trade.totalTradedVal < lowesttrade.totalTradedVal) {
                lowesttrade=trade;
          }

        }

        System.out.printf("Highest and lowest traded for given series '%s' are: %s and %s\n",
                series, highesttrade.symbol,
               lowesttrade.symbol);

    }

}
