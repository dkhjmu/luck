package a.ana.main;

import java.util.ArrayList;

import a.act.ana.vo.ConditionStatVO;
import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqStatVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;

public class SeqHindexAnaMain {
	
	public static void main(String[] args) {
		ArrayList<SeqStatVO> list = getSeqStatList();
		SeqStatVO.printHeader();
		for(SeqStatVO vo : list){
			System.out.println(vo);
		}
		
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

		return getSeqStatList(list);
	}
	
	public static ArrayList<SeqStatVO> getSeqStatList(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);

		return getSeqStatList(list);
	}

	private static String ts(Object obj){
	  if(obj.toString().length()==1){
	    return "0"+obj.toString();
	  }
	  return obj.toString();
	}
	
	private static ArrayList<SeqStatVO> getSeqStatList(ArrayList<ResultVO> list) {
		ArrayList<ConditionStatVO> cvoList=new ArrayList<ConditionStatVO>();
		ArrayList<SeqStatVO> seqList=new ArrayList<SeqStatVO>();
		
		//세야할 대상
		for(int k=list.size()-200;k<list.size();k++){
			ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, k);
			SeqStatVO vo=new SeqStatVO(k+1);
			ConditionStatVO cvo= new ConditionStatVO();
			for(int i=0;i<lList.size();i++){
				LineAnaVO lineAnaVO = lList.get(i);
				if(lineAnaVO.getHindex() > 0){
				  //cvo.add(lineAnaVO.getHindex()+"_"+lineAnaVO.getGap().val()+"_"+lineAnaVO.getC13().val(), 1);
//				  cvo.add(lineAnaVO.getHindex()+"_"+ts(lineAnaVO.getGap()), 1);
				  if(lineAnaVO.getNext()>0){
//				    cvo.add(lineAnaVO.getHindex()+"_"+lineAnaVO.getGap().val()+"_"+lineAnaVO.getC13().val()+"a", 1);
//				    cvo.add(lineAnaVO.getHindex()+"_"+ts(lineAnaVO.getGap())+"a", 1);
				  }else{
//				    cvo.add(lineAnaVO.getHindex()+"_"+ts(lineAnaVO.getGap())+"a", 0);
				  }
				}
				cvo.add(ts(lineAnaVO.getGap().val())+"", 1);
				if(lineAnaVO.getNext()>0){
				  cvo.add(ts(lineAnaVO.getGap().val())+"a", 1);
				}else{
				  cvo.add(ts(lineAnaVO.getGap().val())+"a", 0);
				}
			}// for in
			System.out.println(k);
			System.out.println(cvo);
			cvoList.add(cvo);
			seqList.add(vo);
		}
		
		ConditionStatVO stat= new ConditionStatVO();
		for(ConditionStatVO cvo:cvoList){
		  ArrayList<String> keylist = cvo.getKeyList();
		  for(String key : keylist){
		    stat.add(key, cvo.get(key));		    
		  }
		}
		System.out.println(stat);
		
		return seqList;
	}
	
	
	

}
