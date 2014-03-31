package a.act.calc;

import java.util.ArrayList;

import a.act.calc.vo.CalcVO;
import a.act.main.vo.ResultVO;

public class CountCalc {
	public static String CNT="CNT"; //count
	ArrayList<CalcVO> clist;
	
	public ArrayList<CalcVO> calc(ArrayList<ResultVO> list, int start, int end){
		if(clist==null){
			clist=new ArrayList<CalcVO>();
		}
		for(int i=1;i<=45;i++){
			CalcVO vo=new CalcVO(end, i);
			vo.add(CNT, 0);
			for(int j=start-1;j<=end-1;j++){
				ResultVO rvo=list.get(j);
				if(rvo.isInBonus(i)){
					vo.add(CNT, 1);
					continue;
				}
			}//for j
			clist.add(vo);
		}//for i
		return clist;
	}
}
