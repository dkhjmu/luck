package c.util.date;

import java.util.Date;

public class Test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Date d = new Date();
    
    d.setDate(8);
    d.setYear(2002);
    d.setMonth(6);
    d.setHours(1);
//    d.setTime(1025409600000l);
    d.setTime(60984202200230l);
    
    
    System.out.println(d);
    
    
  }

}
