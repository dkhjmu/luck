package a.pick.rand;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;
import a.pick.filter.PTNFilter;
import a.pick.vo.PickVO;

public class NormalRandomFilteredPicker extends AbstractPicker{

	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}
	
	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		int trys=0;
		for(int i=0;i<tryN;i++){
			trys++;
			ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
			ArrayList<LineAnaVO> temp = new ArrayList<LineAnaVO>();
			PickVO pvo=new PickVO(seq, i+1);
			for(int j=0;j<6;j++){
				int r=getRand(result.size()-1);
				temp.add(result.get(r));
				pvo.add(result.get(r).getBnu());
				result.remove(r);
			}
			
			if(PTNFilter.filtered(temp, seq)==true || trys>10000){
				if(checkDuplicate(glist, pvo)){
					System.out.print("D");
					i--;
				}else{
					System.out.print("..");
					glist.add(pvo);
				}
			}else{
				System.out.print("F");
				i--;
			}
		}
		System.out.println("END!");
		return glist;
		
	}
	
	public static void main(String[] args) {
		NormalRandomFilteredPicker p=new NormalRandomFilteredPicker();
		p.pick(300);
	}

	
}
