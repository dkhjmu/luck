package a.pick.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;

public class PickMain {

	public static void main(String[] args) {
		System.out.println("! start !");

		ArrayList<ResultVO> list = AnaVOMain.getResultList();

		//pattern
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
		for(int i=0;i<list.size();i++){
			int[] t=list.get(i).getArray(true);
			PatternMaker.getPtnMain(t, 5, map);
		}
		
		
		ArrayList<IntVO> major=new ArrayList<IntVO>();
		ArrayList<IntVO> minor=new ArrayList<IntVO>();
		
		//list
		ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, 501);
		for(int i=0;i<lList.size();i++){
			//System.out.println();
			LineAnaVO vo = lList.get(i);
			
			// hindex 0 인경우
			if(vo.getHindex()==0 && (vo.getGap().val()==1)){
				major.add(new IntVO(vo.getBnu()));
			}
			
			
		}
		
		
		System.out.println("! end   !");
	}

}
