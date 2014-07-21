package a.pick.anti;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.checker.Checker;
import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class AntiRatingPicker extends AbstractPicker{

	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<PickVO> glist = new ArrayList<PickVO>();
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);

		PickVO pvo1=new PickVO(seq, 1);
		pvo1.setLimit(45);
		PickVO pvo2=new PickVO(seq, 2);
		pvo2.setLimit(45);
		PickVO pvo3=new PickVO(seq, 3);
		pvo3.setLimit(45);
//		for(LineAnaVO vo:result){
//			if(vo.getHindex()==0 && vo.getGap().val()==0){
//				pvo2.add(vo.getBnu());
//			}else{
//				if(vo.getC13().val()>0){
//					pvo1.add(vo.getBnu());
//				}else{
//					pvo3.add(vo.getBnu());
//				}
//			}
//		}
		
		//minor
//		for(LineAnaVO vo:result){
//			if(
//					vo.getGap().val()>19 
//					|| 
//					vo.getC45().val() > 12
//					){
//				pvo3.add(vo.getBnu());
//			}
//		}
		
		//major
		for(LineAnaVO vo:result){
			if(
//					vo.getGap().val() < 7
//					&& 
//					vo.getC45().val() > 5
//					&&
//					(vo.getC6().val() == 1 || vo.getC6().val() == 2|| vo.getC6().val() == 3|| vo.getC6().val() == 4)
//					&&
					(vo.getGap().val() < 7 && vo.getC45().val() > 5)
					&&
					(vo.getHindex()!=0)
					&&
					(vo.getC13().val()==1)
//					&&
//					vo.getHindex() !=0
					){
				pvo3.add(vo.getBnu());
			}
		}
		
//		glist.add(pvo1);
//		glist.add(pvo2);
		glist.add(pvo3);
		
		return glist;
	}
	
	public static void main(String[] args) {
		AntiRatingPicker p = new AntiRatingPicker();
		int start=200;
		int end  =500;
		int zero[]={0,0,0,0,0,0,0,0};
		int total=0;
		for(;start<end;start++ ){
			ArrayList<PickVO> g = p.pick(start);
			p.printN(g);
			
			ArrayList<ResultVO> list = AnaVOMain.getResultList();
			int[] right=list.get(start).getArray(false);
			int bonus=list.get(start).get(7);
			for(PickVO v: g){
				int val=Checker.checkResult(v.getArray(), right, bonus);
				System.out.println(val);
				if(v.getArray().length>0){
					total++;
					zero[val]++;
				}
			}
		}
		System.out.println("!!!!!!!!!!!!!!@@@@@@@@@@!!!!!!!!!!!!!!!!!");
		for(int i=0;i<zero.length;i++){
			System.out.println(i+":\t"+zero[i]);
		}
		
		System.out.println(total);
		
	}
	
}
