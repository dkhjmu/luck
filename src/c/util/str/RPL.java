package c.util.str;

import java.io.File;

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
  }
}
