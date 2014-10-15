package a.act.ana.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import a.act.main.vo.IntVO;

public class ConditionStatVO {
  public HashMap<String, IntVO> map;

  public ConditionStatVO() {
    map = new HashMap<String, IntVO>();
  }

  public void add(String key, IntVO vo) {
    IntVO kvo = map.get(key);
    if (kvo == null) {
      map.put(key, new IntVO(vo));
    } else {
      map.get(key).add(vo);
    }
  }

  public void add(String key, int v) {
    add(key, new IntVO(v));
  }

  public IntVO get(String key) {
    if (map.get(key) == null) {
      return new IntVO(0);
    }
    return map.get(key);
  }

  
  public Iterator<String> getKeyIterator(){
    Iterator<String> iter = map.keySet().iterator();
    return iter;
  }
  
  public ArrayList<String> getKeyList(){
    ArrayList<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    return keys;
  }
  
  public String mapStr() {
    String result = "";
    ArrayList<String> keyList = getKeyList();
    for(String key : keyList){
      result = result + "\t" + key + ":\t" + map.get(key) + "\n";
    }
    return result;
  }

  @Override
  public String toString() {
    return mapStr();
  }

}
