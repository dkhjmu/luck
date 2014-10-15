package c.util.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class TestClient {
  public static void main(String[] args) {
    String url = "http://localhost:8153/go/rest/alm/pipelines/creates";
    String input = "{\"name\":\"BAT_UST\",\"code\":\"11\",\"pipelines\":[{\"name\":\"feature-BAT-1-deleteME\",\"environments\":{\"DEST\":\"C:/tmp/temp\"},\"materials\":[{\"type\":\"GitMaterial\",\"url\":\"http://alm:platform0!@70.50.8.111/alm/batman.git\",\"branch\":\"feature-BAT-1-deleteME\"}],\"stages\":[{\"name\":\"build\",\"type\":\"auto\",\"jobs\":[{\"name\":\"build\",\"tasks\":[{\"name\":\"build\",\"taskOptions\":\"exec\",\"command\":\"java\",\"argList\":\"-version\"}]}]}]}]}";
    sendRequestPostToGoServer(url, input);
  }
  
  public static void sendRequestPostToGoServer(String url, String input){
    try {
      URL urlObj = new URL(url);
      HttpURLConnection conn = (HttpURLConnection)urlObj.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      OutputStream os = conn.getOutputStream();
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
  
}
