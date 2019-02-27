package com.zjgsu.shuidiansys.pojo.Statistics;

public class TopSecondCmp {

    private int topUse=0;

    private double paymentPrice=0;

    private int secondUse=0;

    private double recylePrice=0;

    private int useDif;

    private double paymentDif;

    public int getTopUse() {
        return topUse;
    }

    public void setTopUse(int topUse) {
        this.topUse += topUse;
    }

    public double getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(double paymentPrice) {
        this.paymentPrice += paymentPrice;
    }

    public int getSecondUse() {
        return secondUse;
    }

    public void setSecondUse(int secondUse) {
        this.secondUse += secondUse;
    }

    public double getRecylePrice() {
        return recylePrice;
    }

    public void setRecylePrice(double recylePrice) {
        this.recylePrice += recylePrice;
    }

    public int getUseDif() {
        return useDif;
    }

    public void setUseDif() {
        this.useDif = this.topUse-this.secondUse;
    }

    public double getPaymentDif() {
        return paymentDif;
    }

    public void setPaymentDif() {
        this.paymentDif = this.paymentPrice-this.recylePrice;
    }
}
