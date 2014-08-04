package a.act.main.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PtnVO {
	ArrayList<IntVO> list;
	
	HashMap<String, IntVO> result; 
	
	int currentCnt = 0;

	int pickCnt = 0;
	
	public PtnVO() {
		list=new ArrayList<IntVO>();
		result=new HashMap<String, IntVO>();
	}
	
	public boolean add(IntVO vo){
		boolean result=list.add(vo);
		Collections.sort(list);
		return result;
	}
	
	public boolean remove(IntVO vo){
		boolean result=list.remove(vo);
		Collections.sort(list);
		return result;
	}
	
	public ArrayList<IntVO> getList(){
		return list;
	}
	
	public boolean isIn(String key){
		IntVO vo = result.get(key);
		if(vo==null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean addResult(String key){
		if(isIn(key)){
			return false;
		}
		result.put(key, new IntVO(1));
		
		return true;
	}

	public int getCurrentCnt() {
		return currentCnt;
	}

	public void setCurrentCnt(int currentCnt) {
		this.currentCnt = currentCnt;
	}

	public int getPickCnt() {
		return pickCnt;
	}
	

	public void setPickCnt(int pickCnt) {
		this.pickCnt = pickCnt;
	}
}
