package a.act.main;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.calc.Gap2Calc;
import a.act.calc.vo.CalcVO;
import a.act.main.vo.ResultVO;

public class GGapMain {
	
	public static void main(String[] args) {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		int seq = 400;
		
		for(int i=30;i>=0;i--){
			Gap2Calc gap2=new Gap2Calc();
			ArrayList<CalcVO> gap2Result = gap2.calc(list, 1, seq, i);
			System.out.println(gap2Result.get(5).get(Gap2Calc.GAP));
		}
	}

	public class BigVO{
		int seq;
		int nextBnu;
		ArrayList<LineAnaVO> list;
		
		public BigVO(int seq, ArrayList<LineAnaVO> list) {
			this.seq=seq;
			this.list=list;
			this.nextBnu=0;
		}
		
		public LineAnaVO getAnaVO(int i){
			return list.get(i);
		}
		
		public void setNextBnu(int b){
			this.nextBnu=b;
		}
		
		public boolean isN(){
			if(nextBnu==0){
				return false;
			}
			return true;
		}
		
	}
	
}
