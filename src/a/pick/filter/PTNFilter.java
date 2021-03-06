package a.pick.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqStatVO;
import a.act.main.vo.IntVO;
import a.ana.main.SeqCntSumAnaMain;
import a.ana.pattern.PatternMaker;

public class PTNFilter {
	private static final int FILTERED_INDEX_NUM = 5;

	HashMap<String, IntVO> bnuMap=null;
	HashMap<String, IntVO> gapMap=null;
	HashMap<String, IntVO> gap2Map=null;
	HashMap<String, IntVO> c45Map=null;
	
	public PTNFilter(int seq) {
		gapMap=getFilter("GAP", seq);
		gap2Map=getFilter("GAP2", seq);
		c45Map=getFilter("CNT45", seq);
	}
	
	public HashMap<String, IntVO> getFilter(String filterCode, int seq){
		if("GAP".equals(filterCode) && gapMap!=null){
			return gapMap;
		}else if("GAP2".equals(filterCode) && gap2Map!=null){
			return gap2Map;
		}else if("CNT45".equals(filterCode) && c45Map!=null){
			return c45Map;
		}else if("BNU".equals(filterCode) && bnuMap!=null){
			return bnuMap;
		}
		
		ArrayList<SeqStatVO> stat = SeqCntSumAnaMain.getSeqStatList(seq);
		
		HashMap<String, IntVO> map=new HashMap<String, IntVO>(); //gap
		
		for(SeqStatVO vo : stat){
			ArrayList<LineAnaVO> list = vo.getGood();
			ArrayList<IntVO> vlist = new ArrayList<IntVO>();
			IntVO v;
			for(int j=0;j<FILTERED_INDEX_NUM;j++){
				if("GAP".equals(filterCode)){
					v = list.get(j).getGap();
				}else if("GAP2".equals(filterCode)){
					v = list.get(j).getGap2();
				}else if("BNU".equals(filterCode)){
					v = new IntVO(list.get(j).getBnu());
				}else if("CNT45".equals(filterCode)){
					v = list.get(j).getC45();
				}else{
					v = list.get(j).getGap();
				}
				vlist.add(v);
			}
			
			if("CNT45".equals(filterCode)){
				sortDesc(vlist);
			}else{
				Collections.sort(vlist);
			}
			
			getPtnMap(map, vlist);
			
		}
		
		return map;
	}
	
	public boolean filtered(ArrayList<LineAnaVO> list, int seq){
		return checkPTNFilter(list, seq, "GAP") && checkPTNFilter(list, seq, "CNT45") ; 
	}

	private boolean checkPTNFilter(ArrayList<LineAnaVO> list, int seq, String filterCode) {
		HashMap<String, IntVO> gmap = getFilter(filterCode, seq);
//		System.out.println("###################################");
//		PatternMaker.printKeyNVal(gmap);
//		System.out.println("###################################");
		
		ArrayList<IntVO> glist=getVlist(list, filterCode);
		HashMap<String, IntVO> newGmap = new HashMap<String, IntVO>();
		getPtnMap(newGmap, glist);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		PatternMaker.printKeyNVal(newGmap);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Iterator<String> iter = newGmap.keySet().iterator();
		while(iter.hasNext()){
			String key=iter.next();
			if(gmap.get(key)!=null && gmap.get(key).val()>1){
				return false;
			}
		}
		return true;
	}

	private ArrayList<IntVO> getVlist(ArrayList<LineAnaVO> list, String filterCode) {
		ArrayList<IntVO> vlist = new ArrayList<IntVO>();
		IntVO v;
		for(int j=0;j<6;j++){
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
		
		return vlist;
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
