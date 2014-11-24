package c.util.str;

import java.io.File;
import java.math.BigDecimal;

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
