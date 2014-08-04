package a.act.main;

import java.util.ArrayList;

import a.act.calc.vo.CalcVO;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;

public class GapCountMain {
	
	public static void main(String[] args) {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		ArrayList<CalcVO> clist = new ArrayList<CalcVO>();
		for(int i=300;i<list.size()-1;i++){
			ResultVO vo=list.get(i);
			for(int v=1;v<46;v++){
				CalcVO cvo=new CalcVO(vo.getSeq(), v);
				ResultVO tvo;
				for(int k=1;k<8;k++){
					tvo=list.get(i-k);
					if(tvo.isInBonus(v)){
						cvo.add("G"+k, 1);
					}
				}
				
				clist.add(cvo);
			}
		}//for
		
		for(int i=0;i<clist.size();i++){
			System.out.println(getPrintFormGapCnt(list, clist.get(i)));
		}
	}
	
	public static String getPrintFormGapCnt(ArrayList<ResultVO> list, CalcVO vo){
		ResultVO rvo = list.get(vo.getSeq());
		String str;
		if(rvo.isInBonus(vo.getBnu())){
			str = (vo.getSeq()+1)+"\t"+vo.getBnu()+"\t";
		}else{
			str = ""+"\t"+vo.getBnu()+"\t";
		}
		for(int k=1;k<8;k++){
			IntVO s=vo.get("G"+k);
			if(s!=null){
				str=str+s+"\t";
			}else{
				str=str+" "+"\t";
			}
		}
		return str;
	}
}
