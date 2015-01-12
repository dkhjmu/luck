package c.util.map;

import java.math.BigDecimal;


public class DEVRLSTest {
  
  public static void main(String[] args) {
    long mb = 1024 * 1024;

    // get Runtime instance
    Runtime instance = Runtime.getRuntime();
    StringBuffer sb = new StringBuffer();
    long totalMemory = instance.totalMemory();
    long freeMemory = instance.freeMemory();

    String processName =java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
    
    String pid = processName.split("@")[0];
    sb.append("ProcessName:"+processName+", ");
    sb.append("PID:"+pid+", ");
    
    // Memory Usage
    sb.append("Memory Usage:" + round(((totalMemory - freeMemory) * 1.0f / totalMemory) * 100.0f, 2) + "%");

    // available memory
    sb.append("(Total: " + totalMemory / mb + "MB ");

    // free memory
    sb.append("Free: " + freeMemory / mb + "MB ");

    // used memory
    sb.append("Used: " + (totalMemory - freeMemory) / mb + "MB ");

    // Maximum available memory
    sb.append("System Max: " + instance.maxMemory() / mb + "MB)");

    System.out.println(sb.toString());
    
  }
  
  public static float round(float d, int decimalPlace) {
    BigDecimal bd = new BigDecimal(Float.toString(d));
    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
    return bd.floatValue();
  }
}
