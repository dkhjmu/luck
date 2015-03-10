package a.pick.rand;

import java.util.ArrayList;
import java.util.HashMap;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;
import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class RandomPicker extends AbstractPicker{

	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}
	
	private static final int LIMITS = 12;

	@Override
	public ArrayList<PickVO> pick(int seq) {
		
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
 		
		for(LineAnaVO vo : result){
			PatternMaker.addMap(vo.getGap().toString(), map);
		}
		
		PatternMaker.printKeyNVal(map);
		
		
		
		return glist;
	}
	
	public static void main(String[] args) {
		RandomPicker p=new RandomPicker();
//		p.pick(300);
		p.setTryN(10);
		p.simulating(10);
	}

	
}
