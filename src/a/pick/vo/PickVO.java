package a.pick.vo;


import java.util.ArrayList;
import java.util.Collections;

import a.act.main.vo.IntVO;

public class PickVO {
	int seq;
	int game;
	int limit=6;
	ArrayList<IntVO> list;
	
	public PickVO(int seq, int game) {
		this.seq = seq;
		this.game = game;
		list=new ArrayList<IntVO>();
	}
	
	public int get(int i){
		return list.get(i).val();
	}
	
	public boolean isIn(int v) {
		for(IntVO vo:list){
			if(vo.val()==v){
				return true;
			}
		}
		return false;
	}
	
	public boolean add(int v) {
		if(isIn(v) || list.size()>=limit){
			return false;
		}
		return list.add(new IntVO(v));
	}
	
	public int getGame() {
		return game;
	}

	public int[] getArray(){
		Collections.sort(list);
		int[] result=new int[list.size()];
		for(int i=0;i<list.size();i++){
			result[i]=list.get(i).val();
		}
		return result;
	}

	public boolean equals(PickVO v){
		int[] l=this.getArray();
		int[] l2=v.getArray();
		for(int i=0;i<limit;i++){
			if(l[i]!=l2[i]){
				return false;
			}
		}
		return true;
	}

	public void setLimit(int l){
		this.limit=l;
	}
	
	public static void printHeader(){
		System.out.println("SEQ\tGame\tn1\tn2\tn3\tn4\tn5\tn6");
	}
	
	@Override
	public String toString() {
		String str=seq + "\t" + game ;
		int[] a=getArray();
		for(int i=0;i<a.length;i++){
			str=str+"\t"+a[i];
		}
		return  str;
	}
	
	
}
