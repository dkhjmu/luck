package a.act.main.vo;

import java.util.ArrayList;
import java.util.Collections;

public class PtnVO {
	ArrayList<IntVO> list;
	
	public PtnVO() {
		list=new ArrayList<IntVO>();
	}
	
	public void add(IntVO vo){
		list.add(vo);
		Collections.sort(list);
	}
	
	public ArrayList<IntVO> getList(){
		return list;
	}
}
