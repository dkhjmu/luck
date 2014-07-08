package a.pick.rand;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.vo.PickVO;

public class NormalRandomPicker{

	
	int tryN=100;
	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}
	
	public void setTryN(int n){
		this.tryN=n;
	}
	
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		
		
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		for(int i=0;i<tryN;i++){
			ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
			PickVO pvo=new PickVO(seq, i+1);
			for(int j=0;j<6;j++){
				int r=getRand(result.size()-1);
				pvo.add(result.get(r).getBnu());
				result.remove(r);
			}
			glist.add(pvo);
		}
		
		return glist;
		
	}
	
	public static void main(String[] args) {
		NormalRandomPicker p=new NormalRandomPicker();
		p.pick(300);
	}

	
}
