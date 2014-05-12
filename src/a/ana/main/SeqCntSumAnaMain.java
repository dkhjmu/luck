package a.ana.main;

import java.util.ArrayList;
import java.util.Collections;

import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqStatVO;
import a.act.calc.vo.CalcVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;

public class SeqCntSumAnaMain {
	
	public static void main(String[] args) {
		ArrayList<SeqStatVO> list = getSeqStatList();
		int size=list.size();
		
		CalcVO gap=new CalcVO(0, 0);
		for(int i=0;i<size;i++){
			SeqStatVO vo=list.get(i);
			ArrayList<IntVO> gl = vo.getGaps();
			Collections.sort(gl);
			for(int j=0;j<gl.size();j++){
				IntVO v=gl.get(j);
				if((j-1)>-1 && v.val()==gl.get(j-1).val()){
					continue;
				}
				gap.add(v.toString(), 1);
			}
			System.out.println(vo.getGapFullPtn());
		}
		gap.printResult();
	}

	public static int getMaxSeq(ArrayList<SeqStatVO> list) {
		int max=-1;
		for (SeqStatVO vo : list) {
			if(vo.getSeq() > max){
				max = vo.getSeq();
			}
		}
		return max;
	}

	public static ArrayList<SeqStatVO> getSeqStatList() {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();

		ArrayList<LineAnaVO> tempList=null;
		ArrayList<SeqStatVO> seqList=new ArrayList<SeqStatVO>();

		//SeqStatVO.printHeader();
		
		//세야할 대상
		for(int k=401;k<501;k++){
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
			//System.out.println(vo);
		}	
		
		return seqList;
	}
	

}