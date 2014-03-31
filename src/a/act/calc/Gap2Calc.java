package a.act.calc;

import java.util.ArrayList;

import a.act.calc.vo.CalcVO;
import a.act.main.vo.ResultVO;

public class Gap2Calc {
	public static final String GAP = "GAP2";
	ArrayList<CalcVO> clist;
	
	public ArrayList<CalcVO> calc(ArrayList<ResultVO> list, int start, int end, int stack){
		clist=new ArrayList<CalcVO>();
		for(int i=1;i<=45;i++){
			CalcVO vo=new CalcVO(end, i);
			vo.add(GAP, 0);
			int k=0;
			for(int j=end-1;j>=start-1;j--){
				ResultVO rvo=list.get(j);
				if(!rvo.isInBonus(i)){
					vo.add(GAP, 1);
					continue;
				}else{
					if(k>=stack){
						break;
					}else{
						vo.reset(GAP, 0);
						k++;
					}
				}//if rvo
			}//for j
			clist.add(vo);
		}//for i
		return clist;
	}
}
