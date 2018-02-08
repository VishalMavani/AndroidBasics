package com.easternts.thenewboston;

import android.os.AsyncTask;

//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;

/**
 * Created by Bansi..!! on 09-07-2016.
 */
public class HttpDataHandler {
//    static final String uri = "http://www.mybringback.com";
//    String data;
//    public String getInternetData() throws Exception {
//        new LoadData().execute(uri);
//        return data;
//    }
//
//    public class LoadData extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            BufferedReader bufferedReader = null;
//            try {
//                InetAddress i = InetAddress.getByName(uri);
//                URI uri1 = new URI(params[0]);
//                HttpClient httpClient = new DefaultHttpClient();
//                HttpGet request = new HttpGet();
//                request.setURI(uri1);
//                HttpResponse response = httpClient.execute(request);
//                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//                StringBuffer stringBuffer = new StringBuffer("");
//                String line = "";
//                String newLine = System.getProperty("line.separator");
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuffer.append(line + newLine);
//                    System.out.println(stringBuffer.toString());
//                }
//                bufferedReader.close();
//                data = stringBuffer.toString();
//                return data;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
}
