package edu.cmu.hw8htiruvee;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;


public class HttpClientPost {

    public static void main(String[] Args){
        String result = null;
        System.out.println(postmessage(result));

    }
    //url ="http://35.164.249.96:8080/Beacon/getLocation"

    public static String postmessage(String url) {

        String output;
        String finalOutput = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url);
            String uid = "B92E1BCC-BE26-45AB-ACA2-27F9AF627306";
            String json = "{\"uid\":\"" + uid + "\",\"accuracy\":\"fine\"}";
            StringEntity input = new StringEntity(json);
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                finalOutput = output.substring(output.indexOf(':') + 1,output.length()-2);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return finalOutput;
    }
}
