package a.act.calc.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import a.act.main.vo.IntVO;

public class CalcVO {
	int seq;
	int bnu;
	HashMap<String, IntVO> map;
	
	public CalcVO(int seq, int bnu) {
		map=new HashMap<String, IntVO>();
		this.seq=seq;
		this.bnu=bnu;
	}
	
	public void add(String key, IntVO vo){
		IntVO kvo = map.get(key);
		if(kvo==null){
			map.put(key, new IntVO(vo));
		}else{
			map.get(key).add(vo);
		}
	}
	public void add(String key, int v){
		add(key, new IntVO(v));
	}
	
	public int getSeq(){
		return seq;
	}
	
	public int getBnu(){
		return bnu;
	}
	
	@Override
	public String toString() {
		return seq+"\t\t"+bnu+mapStr();
	}

	private String mapStr() {
		String result="";
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key=iter.next(); 
			result=result+"\t"+key+":\t"+map.get(key);
		}
		return result;
	}

	public IntVO get(String key) {
		return map.get(key);
	}

	public void reset(String key, int i) {
		map.put(key, new IntVO(i));
	}

	public void printResult() {
		Iterator<String> iter = map.keySet().iterator();
		ArrayList<Object> list=new ArrayList<Object>();
		while(iter.hasNext()){
			String key=iter.next(); 
			list.add(key);
		}
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"\t"+map.get(list.get(i).toString()));
		}
	}
	
	public void printResultInt() {
		Iterator<String> iter = map.keySet().iterator();
		ArrayList<IntVO> list=new ArrayList<IntVO>();
		while(iter.hasNext()){
			String key=iter.next(); 
			list.add(new IntVO(key));
		}
		Collections.sort(list);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i)+"\t"+map.get(list.get(i).toString()));
		}
	}
	
}
