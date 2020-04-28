package com.example.appliandroid;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpAyncTask extends AsyncTask {

    private String url_string="";
    private HttpAsyncListenner httpAsyncListenner;

    interface HttpAsyncListenner {
        void webServiceDone(String result);
        void webServiceError(Exception e);
    }

    public HttpAyncTask(String url,HttpAsyncListenner httpAsyncListenner){
        this.url_string=url;
        this.httpAsyncListenner = httpAsyncListenner;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return call(url_string);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(o instanceof Exception){
            httpAsyncListenner.webServiceError((Exception)o);
        }
        else
            httpAsyncListenner.webServiceDone(o.toString());
    }

    private Object call(String url_string) {
        try {
            URL url = null;
            url = new URL(url_string);
            HttpURLConnection urlConnection;
            if(url_string.startsWith("https:")){
                urlConnection = (HttpsURLConnection) url.openConnection();
            }
            else{
                urlConnection = (HttpURLConnection) url.openConnection();
            }
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return convertStreamToString(in);
            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e;
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        }
    }

    private Object convertStreamToString(InputStream in) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            StringBuffer stringBuffer = new StringBuffer("");
            String line;

            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line + NL);
            }
            bufferedReader.close();
            return stringBuffer.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
