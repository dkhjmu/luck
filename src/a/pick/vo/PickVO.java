package a.pick.vo;


import java.util.ArrayList;
import java.util.Collections;

import a.act.main.vo.IntVO;

public class PickVO {
	int limit=6;
	int seq;
	int game;
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
		return list.contains(new IntVO(v));
	}
	
	public boolean add(int v) {
		if(isIn(v) && list.size()>=limit){
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
		for(int i=0;i<6;i++){
			if(l[i]!=l2[i]){
				return false;
			}
		}
		return true;
	}
	
}
