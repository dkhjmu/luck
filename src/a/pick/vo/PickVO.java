package a.pick.vo;


import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.vo.IntVO;

public class PickVO {
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
		if(isIn(v)){
			return false;
		}
		return list.add(new IntVO(v));
	}
	
	public int getGame() {
		return game;
	}

	public int[] getArray(){
		int[] result=new int[list.size()];
		for(int i=0;i<list.size();i++){
			result[i]=list.get(i).val();
		}
		return result;
	}

	
}
