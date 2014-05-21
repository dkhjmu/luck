package a.ana.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;

public class PatternMain {
	public static void main(String[] args) {
		
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		for(int i=0;i<list.size();i++){
			int[] t=list.get(i).getArray(false);
			Arrays.sort(t);
			PatternMaker.getPtnMain(t, 5, map);
		}
		
		PatternMaker.printKeyNVal(map);
		
		
		
		
		
	}
}
