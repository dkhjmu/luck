package a.pick.rand;

import java.util.ArrayList;

import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;

public class RandomPicker extends AbstractPicker {

	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}

	@Override
	public int getSize() {
		return super.getSize();
	}

	@Override
	public int[][] pick(int seq) {
		
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus();
		AnaVOMain.getAnaVOList(list, seq);
		
		return null;
	}

	@Override
	public int[][] getList() {
		return super.getList();
	}

	
}
