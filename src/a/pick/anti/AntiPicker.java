package a.pick.anti;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.checker.Checker;
import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class AntiPicker extends AbstractPicker{

	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		
		int limit = 12;
		
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
		
//		ArrayList<LineAnaVO> removeList = new ArrayList<LineAnaVO>();
//		for (int v = 0; v < result.size(); v++) {
//			if (result.get(v).getGap().val() > 12) {
//				removeList.add(result.get(v));
//			}
//		}//for
//		
//		for(LineAnaVO v : removeList){
//			result.remove(v);
//		}
//		
//		while(removeList.size()<=limit){
//			int r=getRand(result.size()-1);
//			removeList.add(result.get(r));
//		}
//		
//			
//		PickVO pvo=new PickVO(seq, 1);
//		pvo.setLimit(limit);
//		for(int j=0;j<limit;j++){
//			int r=getRand(removeList.size()-1);
//			pvo.add(removeList.get(r).getBnu());
//			removeList.remove(r);
//		}
//			
//		glist.add(pvo);
		
		
		
		PickVO pvo3=new PickVO(seq, 2);
		pvo3.setLimit(limit);
		for(int j=0;j<limit;j++){
			int r=getRand(result.size());
			pvo3.add(result.get(r).getBnu());
			result.remove(r);
		}
			
		//glist.add(pvo3);
		
		PickVO pvo2=new PickVO(seq, 3);
		for(LineAnaVO vv:result){
			pvo2.setLimit(45);
			pvo2.add(vv.getBnu());
		}
		glist.add(pvo2);
		
		
		System.out.println("END!");
		
		return glist;
	}
	
	public static void main(String[] args) {
		AntiPicker p = new AntiPicker();
		p.setTryN(2);
		ArrayList<PickVO> g = p.pick(300);
		p.print(g);
		
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		int[] right=list.get(299).getArray(false);
		int bonus=list.get(299).get(7);
		for(PickVO v: g){
			System.out.println(Checker.checkResult(v.getArray(), right, bonus));
		}
		
	}
	
}
