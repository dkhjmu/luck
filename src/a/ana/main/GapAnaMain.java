package a.ana.main;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.calc.vo.CalcVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;

public class GapAnaMain {
	
	
	public static void printCols(){
		String []cols={
				"SEQ",
				"gap0,1",
				"gapA0,1",
				"gap확률",
				"gap2,6",
				"gapA2,6",
				"gap확률",
				"gap7,11",
				"gapA7,11",
				"gap확률",
				"gap12",
				"gapA12",
				"gap확률",
				"updn100",
				"updnA100",
				"updn확률",
				"updn42",
				"updnA42",
				"updn확률",
				"updn13",
				"updnA13",
				"updn확률",
				"updnLast",
				"updnALast",
				"updnLast확률",
				"updnCOMPO",
				"updnCOMPOA",
				"updnCOMPO확률",
				"카운트합",
		};
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+"\t");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		secFlow();
		
	}

	public static void secFlow() {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		AnaVOMain.printCols();
		ArrayList<LineAnaVO> tempList=null;
		//세야할 대상
		int bnu = 0;
		CalcVO gap2=new CalcVO(bnu, 0);
		for(int k=201;k<501;k++){
			CalcVO gap3=new CalcVO(bnu, 0);
			ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, k);
			for(int i=0;i<lList.size();i++){
				LineAnaVO lineAnaVO = lList.get(i);
				if(tempList!=null){
					lineAnaVO.calc(tempList.get(i));
				}
//				if(lineAnaVO.getGap().val()==bnu){
//					if( lineAnaVO.getNext()!=0){
//						gap2.add(lineAnaVO.getGap().val()+"", 1);
//					}
//					gap3.add(lineAnaVO.getGap().val()+"", 1);
//				}
				if(list.get(lineAnaVO.getSeq()-1).get(7)==lineAnaVO.getBnu() && lineAnaVO.getNext()!=0){
					gap2.add("Bonus", 1);
				}
			}// for in
			
			
		}// for out
		System.out.println(gap2.get("Bonus"));
	}
	

}