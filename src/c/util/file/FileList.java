package c.util.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

/**
 * Contains some methods to list files and folders from a directory
 *
 * @author Loiane Groner http://loiane.com (Portuguese) http://loianegroner.com (English)
 */
public class FileList {

  /**
   * List all the files and folders from a directory
   * 
   * @param directoryName to be listed
   */
  public void listFilesAndFolders(String directoryName) {

    File directory = new File(directoryName);

    // get all the files from a directory
    File[] fList = directory.listFiles();

    for (File file : fList) {
      System.out.println(file.getName());
    }
  }

  /**
   * List all the files under a directory
   * 
   * @param directoryName to be listed
   */
  public void listFiles(String directoryName) {

    File directory = new File(directoryName);

    // get all the files from a directory
    File[] fList = directory.listFiles();

    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
    for (File file : fList) {
      if (file.isFile()) {
        Date d = new Date(file.lastModified());
        String fileSizeDisplay = FileUtils.byteCountToDisplaySize(file.length());
        System.out.println(file.getName()  +"\t"+ sdf.format(d) +"\t" + fileSizeDisplay );
      }
    }
  }

  /**
   * List all the folder under a directory
   * 
   * @param directoryName to be listed
   */
  public void listFolders(String directoryName) {

    File directory = new File(directoryName);

    // get all the files from a directory
    File[] fList = directory.listFiles();

    for (File file : fList) {
      if (file.isDirectory()) {
        System.out.println(file.getName() );
      }
    }
  }

  /**
   * List all files from a directory and its subdirectories
   * 
   * @param directoryName to be listed
   */
  public void listFilesAndFilesSubDirectories(String directoryName) {

    File directory = new File(directoryName);

    // get all the files from a directory
    File[] fList = directory.listFiles();

    for (File file : fList) {
      if (file.isFile()) {
        System.out.println(file.getAbsolutePath());
      } else if (file.isDirectory()) {
        listFilesAndFilesSubDirectories(file.getAbsolutePath());
      }
    }
  }

  public static void main(String[] args) {

    FileList listFilesUtil = new FileList();

    //final String directoryLinuxMac = "/Users/loiane/test";

    // Windows directory example
    final String directoryWindows = "C:/tmp/temp";

    listFilesUtil.listFiles(directoryWindows);
  }
}
