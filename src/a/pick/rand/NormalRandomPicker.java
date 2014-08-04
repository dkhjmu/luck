package a.pick.rand;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class NormalRandomPicker extends AbstractPicker{

	
	private static final int LIMITS = 6;

	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		for(int i=0;i<tryN;i++){
			ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
			PickVO pvo=new PickVO(seq, i+1);
			pvo.setLimit(LIMITS);
			for(int j=0;j<LIMITS;j++){
				int r=getRand(result.size());
//				pvo.add(result.get(r).getBnu());
				result.remove(r);
			}
			
			for(int j=0;j<6;j++){
				int r=getRand(result.size());
				pvo.add(result.get(r).getBnu());
				result.remove(r);
			}
			
			if(checkDuplicate(glist, pvo)){
				System.out.print("D");
				i--;
			}else{
				glist.add(pvo);
				System.out.print("..");
			}
		}
		System.out.println("END!");
		
		return glist;
		
	}
	
	public static void main(String[] args) {
		NormalRandomPicker p=new NormalRandomPicker();
		p.setTryN(10);
		p.simulating(10);
	}

	
}
