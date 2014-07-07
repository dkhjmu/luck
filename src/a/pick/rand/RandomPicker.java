package a.pick.rand;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;

public class RandomPicker extends AbstractPicker {

	
	int tryN=100;
	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}

	@Override
	public int getSize() {
		return super.getSize();
	}

	
	public void setTryN(int n){
		this.tryN=n;
	}
	
	@Override
	public int[][] pick(int seq) {
		
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
		
		
		
		
		
		
		
		
		return null;
	}

	@Override
	public int[][] getList() {
		return super.getList();
	}

	
}
