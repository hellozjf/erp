package com.hellozjf.test.jacob;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App {
    
    public static String getRequestXML(String filename) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String str = bufferedReader.readLine();
            if (str == null) {
                break;
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
    
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://192.168.3.165/u8eai/import.asp");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(3000000);
        con.setReadTimeout(3000000);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setAllowUserInteraction(false);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        // 发送Request消息
        OutputStream out = con.getOutputStream();
        DataOutputStream dos = new DataOutputStream(out);
        dos.write(getRequestXML("sendreceivetype/query.xml").getBytes("UTF-8"));

        // 获取Response消息
        InputStream in = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String responseXml = sb.toString();

        System.out.println(responseXml);
    }
}
