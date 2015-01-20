package service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Дмитрий on 31.07.14.
 */
public class Playground {

    private List<Element> pGroundList;

    private int counter = 0;

    private double playGroundPrise = 0;
    private int elemNumber = 0;
    private int workPricePercent = 15;
    private double priceDelivery = 0;
    private double discount = 0;
    private double fullPrice = 0;
    private String clientName = "Клиент";
    private String position = "Место установки";

    public List<Element> getpGroundList() {
        return pGroundList;
    }

    public void setpGroundList(List<Element> pGroundList) {
        this.pGroundList = pGroundList;
    }

    public double getPlayGroundPrise() {
        playGroundPrise = 0;
        while(pGroundList.size() > counter) {
            playGroundPrise = playGroundPrise + pGroundList.get(counter).getPrice()*pGroundList.get(counter).getCount();
            counter++;
        }
        counter = 0;
        return playGroundPrise;
    }

    public void setPlayGroundPrise(double playGroundPrise) {
        this.playGroundPrise = playGroundPrise;
    }

    public int getElemNumber() {
        elemNumber = 0;
        while(pGroundList.size() > counter) {
            elemNumber = elemNumber + pGroundList.get(counter).getCount();
            counter++;
        }
        counter = 0;
        return elemNumber;
    }

    public void setElemNumber(int elemNumber) {
        this.elemNumber = elemNumber;
    }

    public double getWorkPricePercent() {
        return workPricePercent;
    }

    public void setWorkPricePercent(int workPricePercent) {
        this.workPricePercent = workPricePercent;
    }

    public double getFullPrice() {
        if (discount != 0 && workPricePercent != 0) {
            fullPrice = playGroundPrise + priceDelivery -
                    (playGroundPrise * discount/100) + (playGroundPrise * workPricePercent /100);
        } else if (discount == 0 &&  workPricePercent != 0) {
            fullPrice = playGroundPrise + priceDelivery + (playGroundPrise * workPricePercent /100);
        }  else if (discount != 0 &&  workPricePercent == 0) {
            fullPrice = playGroundPrise + priceDelivery - (playGroundPrise * discount/100);
        } else {
            fullPrice = playGroundPrise + priceDelivery;
        }
        BigDecimal bd = new BigDecimal(fullPrice).setScale(2, RoundingMode.HALF_EVEN);
        fullPrice = bd.doubleValue();
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(double priceDelivery) {
        this.priceDelivery = priceDelivery;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
