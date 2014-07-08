package a.pick.rand;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class TirdRandomPicker extends AbstractPicker{

	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}
	
	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		for(int i=0;i<tryN;i++){
			ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
			ArrayList<LineAnaVO> temp = new ArrayList<LineAnaVO>();
			PickVO pvo=new PickVO(seq, i+1);
			int size = result.size();
			for(int v=0;v<size;v++ ){
				if(result.get(v).getGap().val()>15){
					result.remove(v);
				}
				size = result.size();
			}
			
			for(int j=0;j<6;j++){
				if(getRand(45)<6){
					size = result.size();
					for(int v=0;v<size;v++ ){
						if(result.get(v).getGap().val()<=6){
							temp.add(result.get(v));
						}
					}
					int r=getRand(temp.size()-1);
					pvo.add(temp.get(r).getBnu());
					result.remove(temp.get(r));
				}else{
					int r=getRand(result.size()-1);
					pvo.add(result.get(r).getBnu());
					result.remove(r);
				}
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
		TirdRandomPicker p=new TirdRandomPicker();
		p.pick(300);
	}

	
}
