package com.test.jsontest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnson on 2017/3/10.
 */
public class TestCaseInfo {
    private String packageName=null;
    private  List<String> methods= new ArrayList<>();
    public TestCaseInfo(String packageName,List<String> methods){
        this.packageName=packageName;
        this.methods=methods;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
}
