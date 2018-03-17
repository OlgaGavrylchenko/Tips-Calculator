package com.example.android.tipcalculator;

/**
 * Created by ig097 on 2/15/2018.
 */

public class Tips {

    private double mUserAmount;
    private int mTipPercent;
    private double mTipAmount;
    private double mTotalAmount;

    public Tips(){
        this.mTipPercent = 0;
        this.mUserAmount = 0;
    }

    public Tips(double amount, int percent){
        this.mUserAmount = amount;
        this.mTipPercent = percent;

        this.mTipAmount = calcTipsAmount();
        this.mTotalAmount = calcTotalAmount();
    }


    private double calcTipsAmount(){
        double result = 0;

        if(this.mTipPercent > 0){
            result = this.mTipPercent * this.mUserAmount / (double)100;
        }
        return result;
    }


    private double calcTotalAmount(){
        return (this.mUserAmount + this.mTipAmount);
    }


    //define accessor methods
    public double getmUserAmount(){
        return this.mUserAmount;
    }

    public int getmTipPercent(){
        return this.mTipPercent;
    }

    public double getmTipAmount(){
        return this.mTipAmount;
    }

    public double getmTotalAmount(){
        return this.mTotalAmount;
    }


    //define mutator methods
    public void setmUserAmount(double value){
        this.mUserAmount = value;
    }

    public void setmTipPercent(int value){
        this.mTipPercent = value;
    }

    public void setmTipAmount(){
        this.mTipAmount = calcTipsAmount();
    }

    public void setmTotalAmount(){
        this.mTotalAmount = calcTotalAmount();
    }

}
