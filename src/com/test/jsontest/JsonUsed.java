package com.test.jsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Johnson on 2017/3/10.
 */
public class JsonUsed {
    public String getJsonResoures(String path){
        String json="{}";
        InputStream is =this.getClass().getResourceAsStream(path);
        byte[] buff=new byte[1024];
        try {
            int i=-1;
            while((i=is.read(buff))!=-1){
                json=new String(buff).trim();
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }
    @Test
    public void testReadRes(){
        getJsonResoures("/json/1.json");


    }
    @Test
    public void testJsonCreate(){
        String test = " { } ";
        System.out.println(test.length());
        System.out.println(test.trim().length());
        String jsonStr= getJsonResoures("/json/1.json");
        System.out.println(jsonStr);
        Person person = JSON.parseObject(jsonStr,  Person.class);
        System.out.println(person.age);
        System.out.println(person.sex);
        System.out.println(person.name);
        JSONArray array =JSON.parseArray("[\"method1\",\"method2\",\"method3\",\"method4\"]");
        System.out.println(array.isEmpty());
        Object object=JSON.parse("[\"method1\",\"method2\",\"method3\",\"method4\"]");
        System.out.println(object instanceof JSONArray);
    }

    @Test
    public  void testObject2Json(){
        ArrayList<String> methods= new ArrayList<>();
        methods.add("method1");
        methods.add("method2");
        methods.add("method3");
        methods.add("method4");
        String packageName = "com.test.aa";
        TestCaseInfo  testCaseInfo = new TestCaseInfo(packageName,methods);
        String testcaseInfoStr= JSON.toJSON(testCaseInfo).toString();
        String arrayStr= JSON.toJSON(methods).toString();
        System.out.println(testcaseInfoStr);
        System.out.println(arrayStr);


        JSONObject jsonObject =JSON.parseObject("{\"methods\":[\"method1\",\"method2\",\"method3\",\"method4\"],\"packageName\":\"com.test.aa\"}");
        Object object=jsonObject.get("methods");
        System.out.println(object instanceof  JSONArray);
        System.out.println("====================测试JSONObject中的方法===================");
        Set<String> keyset=jsonObject.keySet();
        for(String key : keyset){
            System.out.println(key);
        }


        //map to json
        System.out.println("=====================map to json");
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("key1","value1");
        hashMap.put("key2","value2");
        hashMap.put("key3","value3");
        hashMap.put("key4","value4");
        System.out.println(JSON.toJSON(hashMap));


    }


}
