package a.pick.rand;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class RatingRandomPicker extends AbstractPicker {

	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}

	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);

		ArrayList<PickVO> glist = new ArrayList<PickVO>();
		for (int i = 0; i < tryN; i++) {
			ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
			ArrayList<LineAnaVO> hta = new ArrayList<LineAnaVO>();
			ArrayList<LineAnaVO> h13g0 = new ArrayList<LineAnaVO>();
			ArrayList<LineAnaVO> ht0 = new ArrayList<LineAnaVO>();

			for(LineAnaVO vo:result){
				if(vo.getHindex()==0){
					ht0.add(vo);
				}else{
					if(vo.getHindex()==13 && vo.getGap().val()==0){
						h13g0.add(vo);
					}else{
						hta.add(vo);
					}
				}//if
			}
			
			int hindex=0;
			if(getRand(200)<10){
				hindex=0;
			}else{
				if(getRand(200)>180){
					hindex=5;
				}
			}
			
			PickVO pvo = new PickVO(seq, i + 1);
			
			int c=0;
			if(h13g0.size()>0 && getRand(174)<=21){
				pvo.add(h13g0.get(getRand(h13g0.size()-1)).getBnu());
				c++;
			}
			while(hindex>c){
				int r=getRand(hta.size()-1);
				pvo.add(hta.get(r).getBnu());
				hta.remove(r);
				c++;
			}
			
			for(int j=0;j<6-hindex;j++){
				int r=getRand(ht0.size()-1);
				pvo.add(ht0.get(r).getBnu());
				ht0.remove(r);
			}

			if (normalCheck(pvo) || checkDuplicate(glist, pvo)) {
				System.out.print("D");
				i--;
			} else {
				glist.add(pvo);
				System.out.print("..");
			}
		}//for
		System.out.println("END!");

		return glist;

	}

	public static void main(String[] args) {
		RatingRandomPicker p = new RatingRandomPicker();
		p.setTryN(10);
		p.print(p.pick(300));
	}

}
