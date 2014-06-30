package a.ana.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqStatVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;

public class SeqCntSumAnaMain {
	
	public static void main(String[] args) {
		ArrayList<SeqStatVO> list = getSeqStatList();
		int size=list.size();
		HashMap<String, IntVO> map=new HashMap<String, IntVO>(); //gap
		HashMap<String, IntVO> map45=new HashMap<String, IntVO>(); //45c
		HashMap<String, IntVO> mapGood=new HashMap<String, IntVO>(); //45c
		for(int i=0;i<size;i++){
			SeqStatVO vo=list.get(i);
			ArrayList<IntVO> gl = vo.getGaps();
			ArrayList<IntVO> l2 = vo.getCnt45();
			Collections.sort(gl);
			getPtnMap(map, gl);
			sortDesc(l2);
			getPtnMap(map45, l2);
			
			ArrayList<LineAnaVO> l3=vo.getGood();
			
			for(LineAnaVO v:l3){
				PatternMaker.addMap(v.getHindex()+"_"+v.getGap()+"_"+v.getC45(), mapGood);
			}
			
			
			
			//System.out.println(vo.getGapFullPtn());
		}
//		gap.printResult();
//		PatternMaker.printKeyNVal(map);
//		PatternMaker.printKeyNVal(map45);
		PatternMaker.printKeyNVal(mapGood);
	}
	
	public static void sortDesc(ArrayList<IntVO> list){
		Comparator<IntVO> c=new Comparator<IntVO>() {

			public int compare(IntVO o1, IntVO o2) {
				return o2.val()-o1.val();
			}
			
		};

		Collections.sort(list, c);
	}

	private static void getPtnMap(HashMap<String, IntVO> map,
			ArrayList<IntVO> gl) {
		String gPtn = "";
		for(int j=0;j<3;j++){
			IntVO v=gl.get(j);
			gPtn=gPtn+v.toString()+"\t";
		}
		PatternMaker.addMap(gPtn, map);
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
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus();

		ArrayList<LineAnaVO> tempList=null;
		ArrayList<SeqStatVO> seqList=new ArrayList<SeqStatVO>();

		SeqStatVO.printHeader();
		
		//세야할 대상
		for(int k=301;k<501;k++){
			ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, k);
			SeqStatVO vo=new SeqStatVO(k+1);
			for(int i=0;i<lList.size();i++){
				LineAnaVO lineAnaVO = lList.get(i);
				if(tempList!=null){
					lineAnaVO.calc(tempList.get(i));
				}
				
				vo.setNspgStats(lineAnaVO.getGap());
				vo.setHindexStatus(lineAnaVO);
				
				if( lineAnaVO.getNext()!=0){
					vo.addCnts(lineAnaVO);
					//bnu
					vo.setBnuStat(lineAnaVO.getBnu());
					//gap
					vo.setGapStat(lineAnaVO.getGap());
					vo.setCnt13Sum(vo.getCnt13Sum()+lineAnaVO.getC13().val());
					vo.setCnt45Sum(vo.getCnt45Sum()+lineAnaVO.getC45().val());
					vo.setCnt100Sum(vo.getCnt100Sum()+lineAnaVO.getC100().val());
				}
				
				if(lineAnaVO.getGap().val()==0 && lineAnaVO.getHindex()==13){
					vo.setH13_g0a(vo.getH13_g0a()+1);
					if( lineAnaVO.getNext()!=0){
						vo.setH13_g0c(vo.getH13_g0c()+1);
					}
				}
				
			}// for in
			seqList.add(vo);
			//System.out.println(vo);
		}	
		
		return seqList;
	}
	

}
