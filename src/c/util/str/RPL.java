package c.util.str;

import java.io.File;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RPL {
  public static void main(String[] args) {
    String path = "C:"+File.separator+"aa"+File.separator+"bbb"+File.separator;
    System.out.println(path);
    
    path = path.replace("\\", "/");
    
    System.out.println(path);
    
    String version = "10.0.0";
    
    System.out.println(version);
    
    version = version.replace(".", "&-");
    
    System.out.println(version);
    
    System.out.println(makePath("1", new BigDecimal("10")).replace("\\", "/"));
    
    
    String pjtSCMRepoUrl = "http://70.50.168.83/gitlab/alm/petclinic.git";
    
    
    System.out.println(pjtSCMRepoUrl.substring(pjtSCMRepoUrl.lastIndexOf("/")+1).replace(".git", "")); 
    
    int li=pjtSCMRepoUrl.lastIndexOf("/");
    System.out.println(pjtSCMRepoUrl.substring(0, li));

    Pattern pattern = Pattern.compile("(http://*/)(.*?)(\\.git)");
    Matcher matcher = pattern.matcher(pjtSCMRepoUrl);
    
    if(matcher.matches()){
      System.out.println("wow");
      System.out.println(matcher.group());
      System.out.println(matcher.group(1));
      System.out.println(matcher.group(2));
    }else{
      System.out.println("mom");
      
    }

    
  }
  
  
  private static String makePath(String pjtCode, BigDecimal versionId) {
    StringBuffer path = new StringBuffer();
    path.append("C:\\ROOt").append(File.separator);
    path.append(pjtCode).append(File.separator);
    path.append("release").append(File.separator);
    path.append(versionId).append(File.separator);
    return path.toString();
  }
}
