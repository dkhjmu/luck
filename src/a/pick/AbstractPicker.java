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
}

