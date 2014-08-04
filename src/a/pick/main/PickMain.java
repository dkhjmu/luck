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
		
		
		ArrayList<LineAnaVO> major=new ArrayList<LineAnaVO>();
		ArrayList<LineAnaVO> minor=new ArrayList<LineAnaVO>();
		
		//list
		ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, 474);
		for(int i=0;i<lList.size();i++){
			//System.out.println();
			LineAnaVO vo = lList.get(i);
			
			// hindex 0 인경우
			if(vo.getHindex()==0){
				
				
				
				major.add(vo);
			}
			
			
		}
		printArray(major);
		
		HashMap<String, IntVO> map2=new HashMap<String, IntVO>();
		PatternMaker.getPtnMain(listToArray(major), 5, map2);
		PatternMaker.printKeyNVal(map2);
		
		System.out.println("! end   !");
	}
	
	public static void printArray(ArrayList<LineAnaVO> list){
		int a=0;
		for(LineAnaVO vo : list){
			a++;
			System.out.println(vo);
		}
		System.out.println(a);
	}
	
	public static int[] listToArray(ArrayList<LineAnaVO> list){
		int[] result=new int[list.size()];
		for(int i=0;i<list.size();i++){
			LineAnaVO vo = list.get(i);
			result[i]=vo.getBnu();
		}
		return result;
	}

}
