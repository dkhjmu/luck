package a.pick.ptn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;
import a.pick.AbstractPicker;
import a.pick.filter.BNUPtnFilter;
import a.pick.vo.PickVO;
import a.util.ArrayUtil;

public class PtnPicker  extends AbstractPicker{
	private static final int LIMITS = 6;

	@Override
	public ArrayList<PickVO> pick(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
		
		BNUPtnFilter filter=new BNUPtnFilter(seq);
		
		ArrayList<IntVO> lists=new ArrayList<IntVO>();
		for(LineAnaVO vo:result){
			if(
					//vo.getC45().val() < 6
					vo.getC13().val() < 5
					&&
					vo.getC100().val() > 10
					&&
					vo.getC100().val() < 18
					&&
					!(vo.getGap().val()>19 || vo.getC45().val() > 12)
					&&
					vo.getC6().val()<3
					&&
					(vo.getHindex() == 0 || vo.getGap().val() < 5)
//					(vo.getC6().val() == 1 || vo.getC6().val() == 2|| vo.getC6().val() == 3|| vo.getC6().val() == 4)
					
			){	
				lists.add(new IntVO(vo.getBnu()));
			}
		}
		System.out.println(lists.size());
		int ar[]=ArrayUtil.listToArray(lists);
		ar=ArrayUtil.sort(ar);
		ArrayUtil.print(ar);
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
		PatternMaker.getPtnMain(ar, 6, map);
		System.out.println("mapsize: "+map.size());
		Iterator<String> iter = map.keySet().iterator();
		int i=0;
		while(iter.hasNext()){
			PickVO pvo=new PickVO(seq, i+1);
			pvo.setLimit(LIMITS);
			i++;
			String str=iter.next();
			//System.out.println(str);
			pvo.setPickerByPTN(str);
			
			int[] rr = pvo.getArray();
			int sum=ArrayUtil.sumArray(rr);
			int even = ArrayUtil.checkEven(rr);
			int[] ff=ArrayUtil.fourFloorPtn(rr);
			if(filter.filtered(rr, 1)==true 
					|| sum<90
					|| sum>170
					|| even==6
					|| even==0
					|| ff[0] > 2
					|| ff[1] > 3
					|| ff[2] > 3
					|| ff[3] > 3
					|| ff[4] > 2
					){
				continue;
			}
			glist.add(pvo);
			//System.out.print("..");
		}
		
		System.out.println("END!["+glist.size()+"]");
		
		return glist;
		
	}
	
	public static void main(String[] args) {
		PtnPicker p=new PtnPicker();
		p.simulating(10);
		
	}
}
