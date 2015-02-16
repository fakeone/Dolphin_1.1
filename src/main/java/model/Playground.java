package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Playground {

    private Set<Element> elements = Collections.<Element>emptySet();

    private double playGroundPrise = 0;
    private int workPricePercent = 15;
    private double workPrice = 0;
    private double priceDelivery = 0;
    private double discount = 0;
    private double discountPrice = 0;
    private String clientName = "Клиент";
    private String position = "Место установки";

    public List<Element> getElements() {
        return new ArrayList<>(elements);
    }

    public void setElements(Collection<Element> elements) {
        this.elements = new HashSet<>(elements);
    }

//    public void addElements(List<Element> elements) {
//        this.elements.addAll(elements);
//    }

    public double getPlayGroundPrise() {
        playGroundPrise = 0;
        for (Element element : elements) {
            playGroundPrise = playGroundPrise + element.getPrice() * element.getCount();
        }
        return playGroundPrise;
    }

    public double getWorkPricePercent() {
        return workPricePercent;
    }

    public void setWorkPricePercent(int workPricePercent) {
        this.workPricePercent = workPricePercent;
    }

    public double getFullPrice() {
        double fullPrice;
        if (discount != 0 && workPricePercent != 0) {
            fullPrice = playGroundPrise + priceDelivery -
                    (playGroundPrise * discount / 100) + (playGroundPrise * workPricePercent / 100);
        } else if (discount == 0 && workPricePercent != 0) {
            fullPrice = playGroundPrise + priceDelivery + (playGroundPrise * workPricePercent / 100);
        } else if (discount != 0 && workPricePercent == 0) {
            fullPrice = playGroundPrise + priceDelivery - (playGroundPrise * discount / 100);
        } else {
            fullPrice = playGroundPrise + priceDelivery;
        }
        BigDecimal bd = new BigDecimal(fullPrice).setScale(0, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
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

    public double getWorkPrice() {
        workPrice = playGroundPrise * workPricePercent / 100;
        BigDecimal bd = new BigDecimal(workPrice).setScale(0, RoundingMode.HALF_EVEN);
        workPrice = bd.doubleValue();
        return workPrice;
    }

    public void setWorkPrice(double workPrice) {
        this.workPrice = workPrice;
    }

    public double getDiscountPrice() {
        discountPrice = playGroundPrise * discount / 100;
        BigDecimal bd = new BigDecimal(discountPrice).setScale(0, RoundingMode.HALF_EVEN);
        discountPrice = bd.doubleValue();
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
