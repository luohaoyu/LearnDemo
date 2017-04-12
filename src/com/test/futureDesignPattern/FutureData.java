package com.test.futureDesignPattern;

/**
 * Created by Johnson on 2017/3/28.
 */
public class FutureData implements Data {
    protected  RealData realData;
    @Override
    public String getResult() {
        return null;
    }
    public void setRealData(RealData realData){
        this.realData=realData;
    }

}
