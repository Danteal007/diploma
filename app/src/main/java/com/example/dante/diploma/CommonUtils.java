package com.example.dante.diploma;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CommonUtils {
    public static class JDoodleAPI extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String[] scripts) {
            return CallAPI(scripts[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


    }
    public static String CallAPI(String script){
        String clientId = "ba69bd4e93d1491f5a85f6c229799993"; //Replace with your client ID
        String clientSecret = "5875ea69736ef71fd1b549ff03ad5dd7781e987368bc945d9c0f5816205d691b"; //Replace with your client Secret
        String language = "csharp";
        String versionIndex = "1";

        String res = "";
        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\",\"clientSecret\":\"" + clientSecret + "\",\"script\":\"" + ModifyScript(script) +
                    "\",\"language\":\"" + language + "\",\"versionIndex\":\"" + versionIndex + "\"} ";

            System.out.println(input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                    (connection.getInputStream())));

            String output;

            System.out.println("Output from JDoodle .... \n");
            while ((output = bufferedReader.readLine()) != null) {
                System.out.println(output);
                res = res+output;
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static String ModifyScript(String script){
        String result = script.replace(System.getProperty("line.separator"),"");
        result = result.replace("\"","\\\"");
        return result;
    }
}
