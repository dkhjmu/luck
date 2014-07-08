package a.pick.rand;

import java.util.ArrayList;
import java.util.HashMap;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;

public class RandomPicker{

	
	int tryN=100;
	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}

	
	public void setTryN(int n){
		this.tryN=n;
	}
	
	public void pick(int seq) {
		
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
		
		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
 		
		for(LineAnaVO vo : result){
			PatternMaker.addMap(vo.getGap().toString(), map);
		}
		
		PatternMaker.printKeyNVal(map);
	}
	
	public static void main(String[] args) {
		RandomPicker p=new RandomPicker();
		p.pick(300);
	}

	
}
