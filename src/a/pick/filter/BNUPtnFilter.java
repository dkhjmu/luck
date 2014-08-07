package a.pick.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;

public class BNUPtnFilter {

	private static final int NORMAL_VALUE = 3;
	
	HashMap<String, IntVO> bnuMap=null;
	
	public BNUPtnFilter(int seq) {
		bnuMap=getFilter(seq);
	}
	
	public HashMap<String, IntVO> getFilter(int seq){
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
		ArrayList<ResultVO> list = AnaVOMain.getResultList(seq);
		for(int i=0;i<list.size();i++){
			int[] t=list.get(i).getArray(false);
			Arrays.sort(t);
			PatternMaker.getPtnMain(t, NORMAL_VALUE, map);
		}
		
		//PatternMaker.printKeyNVal(map);
		return map;
	}
	
	public boolean filtered(int[] rr, int limit){
		
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
		PatternMaker.getPtnMain(rr, NORMAL_VALUE, map);
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			IntVO ptn = bnuMap.get(key);
			if(ptn!=null && ptn.val()>limit){
				return true;
			}
		}
		
		return false; 
	}

}
