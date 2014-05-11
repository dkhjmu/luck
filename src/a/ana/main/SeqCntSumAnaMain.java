package a.ana.main;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqStatVO;
import a.act.calc.vo.CalcVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;

public class SeqCntSumAnaMain {
	
	public static void main(String[] args) {
		getSeqStatList();
		
	}

	public static ArrayList<SeqStatVO> getSeqStatList() {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus();

		ArrayList<LineAnaVO> tempList=null;
		ArrayList<SeqStatVO> seqList=new ArrayList<SeqStatVO>();

		SeqStatVO.printHeader();
		
		//세야할 대상
		for(int k=201;k<list.size();k++){
			ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, k);
			SeqStatVO vo=new SeqStatVO(k);
			for(int i=0;i<lList.size();i++){
				LineAnaVO lineAnaVO = lList.get(i);
				if(tempList!=null){
					lineAnaVO.calc(tempList.get(i));
				}
				if( lineAnaVO.getNext()!=0){
					//bnu
					vo.setBnuStat(lineAnaVO.getBnu());
					//gap
					vo.setGapStat(lineAnaVO.getGap());
					vo.setCnt13Sum(vo.getCnt13Sum()+lineAnaVO.getC13().val());
					vo.setCnt45Sum(vo.getCnt45Sum()+lineAnaVO.getC45().val());
					vo.setCnt100Sum(vo.getCnt100Sum()+lineAnaVO.getC100().val());
				}
				
			}// for in
			seqList.add(vo);
			System.out.println(vo);
		}	
		
		return seqList;
	}
	

}