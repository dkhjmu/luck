package c.util.map;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import com.sun.management.OperatingSystemMXBean;


public class CpuCheck {
  
  public static void main(String[] args) {
    CpuCheck cc = new CpuCheck();
    cc.showCPU();
  }
  
  private void showCPU() {
    OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory
      .getOperatingSystemMXBean();
    RuntimeMXBean runbean = (RuntimeMXBean) ManagementFactory
      .getRuntimeMXBean();

    long bfprocesstime = osbean.getProcessCpuTime();
    long bfuptime = runbean.getUptime();
    long ncpus = osbean.getAvailableProcessors();

    for (int i = 0; i < 1000000; ++i) {
      ncpus = osbean.getAvailableProcessors();
    }

    long afprocesstime = osbean.getProcessCpuTime();
    long afuptime = runbean.getUptime();

    float cal = (afprocesstime - bfprocesstime)
      / ((afuptime - bfuptime) * 10000f);

    float usage = Math.min(99f, cal);

    System.out.println("Calculation: " + cal);
    System.out.println("CPU Usage: " + usage);

  }
 

  
}
