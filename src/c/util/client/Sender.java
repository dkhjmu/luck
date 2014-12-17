package c.util.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;



public class Sender {
  public static void main(String[] args) {
    System.out.println("Start!");
    String url = "http://localhost:8080/scout/buildfile/uploadFile";
    
    String input = "{\"fileName\":\"test.txt\",\"fileLength\":\"1934766080\"}";
    
    sendRequestPostToGoServer(url, input);
    try {
      //sendRequestGetToGoServer(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("END!");
  }
  

  public static void sendRequestPostToGoServer(String url, String input){
    try {
      URL urlObj = new URL(url);
      HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      OutputStream os = conn.getOutputStream();
      //input = input.replace("\\", "\\\\");
      os.write(input.getBytes());
      os.flush();
//      if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//          throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//      }
      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
      String output;
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }
      conn.disconnect();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
  
  public static String sendRequestGetToGoServer(String urlStr) throws Exception{
    StringBuffer result = new StringBuffer();
    HttpURLConnection conn = null;
    try {
      URL url = new URL(urlStr);
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      if (conn.getResponseCode() != 200) {
          throw new RuntimeException("Failed : HTTP error code : "
                  + conn.getResponseCode());
      }
      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
      String output = null;
      while ((output = br.readLine()) != null) {
          System.out.println(output);
          result.append(output);
      }
      conn.disconnect();
    } catch (Exception e) {
      System.out.println(e.toString());
      throw e;
    }finally{
      if(conn!=null){
        conn.disconnect();
      }
    }
    return result.toString();
  }
    
}
