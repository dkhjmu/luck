package c.util.map;


//import java.util.Set;
//
//import sun.jvmstat.monitor.HostIdentifier;
//import sun.jvmstat.monitor.MonitorException;
//import sun.jvmstat.monitor.MonitoredHost;
//import sun.jvmstat.monitor.MonitoredVm;
//import sun.jvmstat.monitor.MonitoredVmUtil;
//import sun.jvmstat.monitor.VmIdentifier;


public class GetOwnPid {

    public static void main(String[] args) {
        new GetOwnPid().run();
    }

    public void run() {
//        System.out.println(getPid(this.getClass()));
    }

//    public Integer getPid(Class<?> mainClass) {
//        MonitoredHost monitoredHost;
//        Set<Integer> activeVmPids;
//        try {
//            monitoredHost = MonitoredHost.getMonitoredHost(new HostIdentifier((String) null));
//            activeVmPids = monitoredHost.activeVms();
//            MonitoredVm mvm = null;
//            for (Integer vmPid : activeVmPids) {
//                try {
//                    mvm = monitoredHost.getMonitoredVm(new VmIdentifier(vmPid.toString()));
//                    String mvmMainClass = MonitoredVmUtil.mainClass(mvm, true);
//                    if (mainClass.getName().equals(mvmMainClass)) {
//                        return vmPid;
//                    }
//                } finally {
//                    if (mvm != null) {
//                        mvm.detach();
//                    }
//                }
//            }
//        } catch (java.net.URISyntaxException e) {
//            throw new InternalError(e.getMessage());
//        } catch (MonitorException e) {
//            throw new InternalError(e.getMessage());
//        }
//        return null;
//    }
}