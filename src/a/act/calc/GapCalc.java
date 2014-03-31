package a.act.calc;

import java.util.ArrayList;

import a.act.calc.vo.CalcVO;
import a.act.main.vo.ResultVO;

public class GapCalc {
	public static String GAP="GAP"; //count
	ArrayList<CalcVO> clist;
	
	public ArrayList<CalcVO> calc(ArrayList<ResultVO> list, int start, int end){
		if(clist==null){
			clist=new ArrayList<CalcVO>();
		}
		for(int i=1;i<=45;i++){
			CalcVO vo=new CalcVO(end, i);
			vo.add(GAP, 0);
			for(int j=end-1;j>=start-1;j--){
				ResultVO rvo=list.get(j);
				if(!rvo.isInBonus(i)){
					vo.add(GAP, 1);
					continue;
				}else{
					break;
				}
			}//for j
			clist.add(vo);
		}//for i
		return clist;
	}
}
