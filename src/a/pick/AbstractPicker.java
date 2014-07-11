package a.pick;

import java.util.ArrayList;

import a.pick.vo.PickVO;

public abstract class AbstractPicker {
	
	protected int tryN=100;
	
	public void setTryN(int n){
		this.tryN=n;
	}
	
	public abstract ArrayList<PickVO> pick(int seq);
	
	public boolean checkDuplicate(ArrayList<PickVO> glist, PickVO pvo){
		for(PickVO v:glist){
			if(v.equals(pvo)){
				return true;
			}
		}
		return false;
	}
	
	public void print(ArrayList<PickVO> list){
		for(PickVO vo:list){
			System.out.println(vo);
		}
	}
	
	public boolean normalCheck(PickVO v){
		int[] g=v.getArray();
		int sum=0;
		for(int j:g){
			sum+=j;
		}
		
		if(sum<95 || sum > 180){
			return false;
		}
		
		return true;
	}
}

