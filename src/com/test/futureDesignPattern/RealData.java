package com.test.futureDesignPattern;

/**
 * Created by Johnson on 2017/3/28.
 */
public class RealData implements Data {
    String data;
    public RealData(String data){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10;i++){
            sb.append(data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data=sb.toString();
        }
    }
    @Override
    public String getResult() {
        return this.data;
    }
}
