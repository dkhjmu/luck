package c.util.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class TestClient {
  public static void main(String[] args) {
//    String url = "http://localhost:8153/go/rest/alm/pipelines/creates";
//    String url = "http://localhost:8153/go/rest/alm/check";
//    String url = "http://localhost:8153/go/api/server.xml";
//    String url = "http://70.7.31.49:8153/go/rest/alm/pipelines/creates";
//    String url = "http://182.192.68.213:8153/go/rest/alm/pipelines/creates";
//    String url = "http://182.192.68.213:8153/go/home";
//    String url = "http://localhost:8153/go/rest/alm/user/add";
//    String url = "http://182.192.68.213:8153/go/rest/alm/user/delete";
//    String url = "http://70.121.224.24:8153/go/rest/alm/user/add";
//    String url = "http://localhost:8080/scout/buildfile/uploadFile";
//    String url = "http://localhost:8153/go/rest/alm/1/pipelineHistory/app-common123123";
//    String url = "http://localhost:8153/go/rest/alm/testt/pipelineGroupStatus/test";
//    String url = "http://70.121.244.19:8153/go/rest/alm/pipeline/scout33_RLS_build/modify/env";
//    String url = "http://localhost:8153/go/rest/alm/pipeline/TESTTTT_build/modify/env";
//    String url = "http://localhost:8153/go/mini/health/check";
//    String url = "http://localhost:8080/scout/mini/health/check";
//    String url = "http://localhost:8080/scout/mini";
//    String url = "http://70.7.31.49:8153/go/home";
    String url = "http://70.7.31.49:8153/go/mini";
//    String url = "http://localhost:8153/go/home";
//    String url = "http://localhost:8153/go/mini";
//    String input = "{\"name\":\"scout33_RC_5.0.0\",\"code\":\"1\",\"pipelines\":[{\"name\":\"scout38_RC_5.0.0_build\",\"materials\":[{\"type\":\"GitMaterial\",\"url\":\"http://alm:platform0!@70.50.168.82/alm/scout33.git\",\"branch\":\"scout33_RC_5.0.0\"}],\"stages\":[{\"name\":\"build\",\"type\":\"auto\",\"jobs\":[{\"name\":\"build\",\"tasks\":[{\"name\":\"build\",\"taskOptions\":\"exec\",\"command\":\"mvn\",\"argList\":\"compile\"},{\"name\":\"clean\",\"taskOptions\":\"exec\",\"command\":\"delete\",\"argList\":\"%DEST%\"},{\"name\":\"deploy_copy\",\"taskOptions\":\"exec\",\"command\":\"copy\",\"argList\":\"*.* %DEST%\"}]}]}]}]}";
//    String input = "{\"name\":\"DEST\",\"value\":\"C:/fff/aee23333\"}";
    String input = "{\"id\":\"testuser\",\"password\":\"tttttttt\",\"displayName\":\"kimje\"}";
//    String input = "{\"name\":\"$groupName$\",\"code\":\"$pjtCode$\",\"pipelines\":[{\"name\":\"$pipelineName$_build\",\"materials\":[{\"type\":\"GitMaterial\",\"url\":\"$repoUrl$\",\"branch\":\"$branchName$\"}],\"environments\":{\"DEST\":\"$destPath$\"},\"stages\":[{\"name\":\"build\",\"type\":\"auto\",\"jobs\":[{\"name\":\"build\",\"tasks\":[{\"name\":\"build\",\"taskOptions\":\"exec\",\"command\":\"mvn\",\"argList\":\"-version\"}, {\"name\":\"uploadFile\",\"taskOptions\":\"exec\",\"command\":\"uploadFile\",\"argList\":\"http://70.121.224.24:9081/scout/buildfile %DEST% %cd%/target\"}]}]}]},{\"name\":\"$pipelineName$_package\",\"materials\":[{\"name\":\"packaging\",\"type\":\"DependencyMaterial\",\"pipelineName\":\"$pipelineName$_build\",\"stageName\":\"build\"}],\"stages\":[{\"name\":\"package\",\"type\":\"auto\",\"jobs\":[{\"name\":\"package\",\"tasks\":[{\"name\":\"package\",\"taskOptions\":\"exec\",\"command\":\"java\",\"argList\":\"-version\"}]}]}]}]}";
//    input = input.replace("$pjtCode$", "1");
//    input = input.replace("$repoUrl$", "http://alm:platform0!@70.121.224.25/gitlab/alm/testpjt01.git");
//    input = input.replace("$groupName$", "TEST");
//    input = input.replace("$pipelineName$", "TESTTTT");
//    input = input.replace("$branchName$", "development");
//    input = input.replace("$destPath$", "C:/Temp/2/rls");
//    String input = "{\"name\":\"alm_RC_1.0.0\",\"code\":\"14\",\"pipelines\":[{\"name\":\"alm_RC_1.0.0_build\",\"materials\":[{\"type\":\"GitMaterial\",\"url\":\"http://alm:platform0!@70.50.168.82/alm/alm.git\",\"branch\":\"alm_RC_1.0.0\"}],\"environments\":{\"DEST\":\"C:\\root\\14\\release\\36\\\"},\"stages\":[{\"name\":\"build\",\"type\":\"manual\",\"jobs\":[{\"name\":\"build\",\"tasks\":[{\"name\":\"build\",\"taskOptions\":\"exec\",\"command\":\"java\",\"argList\":\"-version\"}]}]}]},{\"name\":\"build\",\"environments\":{\"DEST\":\"C:\\root\\14\\release\\36\\\"},\"materials\":[{\"name\":\"deploying\",\"type\":\"DependencyMaterial\",\"pipelineName\":\"alm_RC_1.0.0_build\",\"stageName\":\"alm_RC_1.0.0\"}],\"stages\":[{\"name\":\"deploy\",\"type\":\"auto\",\"jobs\":[{\"name\":\"deploy\",\"tasks\":[{\"name\":\"deploy\",\"taskOptions\":\"exec\",\"command\":\"copy\",\"argList\":\"*.*;%DEST%\"}]}]}]}]}";
//    String input  ="{\"name\":\"TTT2_DEV\",\"code\":\"99\",\"pipelines\":[{\"name\":\"TTT2_DEV_build\",\"environments\":{\"DEST\":\"C:\\temp\\29\\dev\"},\"materials\":[{\"type\":\"GitMaterial\",\"url\":\"$repoUrl$\",\"branch\":\"development\"}],\"stages\":[{\"name\":\"build\",\"type\":\"manual\",\"jobs\":[{\"name\":\"build\",\"tasks\":[{\"name\":\"build\",\"taskOptions\":\"exec\",\"command\":\"mvn\",\"argList\":\"compile site cobertura:cobertura -Dcobertura.report.format=xml sonar:sonar -Dsonar.branch=development\"}],\"options\":\"ALMsonar\"}]}]},{\"name\":\"TTT2_DEV_deploy\",\"materials\":[{\"name\":\"deploying\",\"type\":\"DependencyMaterial\",\"pipelineName\":\"TTT2_DEV_build\",\"stageName\":\"build\"}],\"stages\":[{\"name\":\"deploy\",\"type\":\"auto\",\"jobs\":[{\"name\":\"deploy\",\"tasks\":[{\"name\":\"fetchTask\",\"taskOptions\":\"exec\",\"command\":\"java\",\"argList\":\"-version\"}]}]}]}]}";
//    System.out.println(input);
//    sendRequestPostToGoServer(url, input);
    try {
      sendRequestGetToGoServer(url);
    } catch (Exception e) {
      e.printStackTrace();
    }
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
      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
                + conn.getResponseCode());
      }
      
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
