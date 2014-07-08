package a.pick.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqStatVO;
import a.act.main.vo.IntVO;
import a.ana.main.SeqCntSumAnaMain;
import a.ana.pattern.PatternMaker;

public class PTNFilter {
	public static HashMap<String, IntVO> getFilter(String filterCode, int seq){
		ArrayList<SeqStatVO> stat = SeqCntSumAnaMain.getSeqStatList(seq);
		
		HashMap<String, IntVO> map=new HashMap<String, IntVO>(); //gap
		
		for(SeqStatVO vo : stat){
			ArrayList<LineAnaVO> list = vo.getGood();
			ArrayList<IntVO> vlist = new ArrayList<IntVO>();
			IntVO v;
			for(int j=0;j<5;j++){
				if("GAP".equals(filterCode)){
					v = list.get(j).getGap();
				}else if("GAP2".equals(filterCode)){
					v = list.get(j).getGap2();
				}else if("CNT45".equals(filterCode)){
					v = list.get(j).getC45();
				}else{
					v = list.get(j).getGap();
				}
				vlist.add(v);
			}
			
			if("GAP".equals(filterCode)){
				Collections.sort(vlist);
			}else if("GAP2".equals(filterCode)){
				Collections.sort(vlist);
			}else if("CNT45".equals(filterCode)){
				sortDesc(vlist);
			}else{
				Collections.sort(vlist);
			}
			
			getPtnMap(map, vlist);
			
		}
		
		return map;
	}
	
	public static void sortDesc(ArrayList<IntVO> list){
		Comparator<IntVO> c=new Comparator<IntVO>() {

			public int compare(IntVO o1, IntVO o2) {
				return o2.val()-o1.val();
			}
			
		};

		Collections.sort(list, c);
	}
	
	public static void getPtnMap(HashMap<String, IntVO> map, ArrayList<IntVO> gl) {
		String gPtn = "";
		for(int j=0;j<5;j++){
			IntVO v=gl.get(j);
			gPtn=gPtn+v.toString()+"\t";
		}
		PatternMaker.addMap(gPtn, map);
	}
	
}
