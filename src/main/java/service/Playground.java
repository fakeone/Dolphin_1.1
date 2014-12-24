package service;

import java.util.List;

/**
 * Created by Дмитрий on 31.07.14.
 */
public class Playground {

    private List<Element> pGroundList;

    private int counter = 0;

    private double playGroundPrise = 0;
    private int elemNumber = 0;
    private double workPrice = 0;
    private double fullPrice = playGroundPrise + workPrice;
    private String clientName = "Клиент";
    private String position = "Место установки";
    private String info = "";

    public String getInfo() {
        info = "";
        while(pGroundList.size() > counter) {
            info = info + pGroundList.get(counter).getName() + " (" + pGroundList.get(counter).getCode() +  "): " +
                    pGroundList.get(counter).getPrice()+ "грн. * " + pGroundList.get(counter).getCount() + "шт. = ";
            info = info + pGroundList.get(counter).getPrice() * pGroundList.get(counter).getCount() + " грн." + "\n";
            counter++;
        }
        counter = 0;
        return info;
    }

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

    public double getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(double workPrice) {
        this.workPrice = workPrice;
    }

    public double getFullPrice() {
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
}
