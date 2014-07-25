package a.pick.ana;

import java.util.ArrayList;

import a.act.calc.vo.CalcVO;
import a.pick.rand.NormalRandomPicker;
import a.pick.vo.PickVO;

public class Analizer {
	public static void main(String[] args) {
		NormalRandomPicker picker=new NormalRandomPicker();
		picker.setTryN(100);
		ArrayList<PickVO> list = picker.pick(300);
		CalcVO cvo = new CalcVO(300, 1);
		for (int i = 0; i < list.size(); i++) {
			PickVO vo=list.get(i);
			int[] bnus=vo.getArray();
			for(int j=0;j<bnus.length;j++){
				cvo.add(bnus[j]+"", 1);;
			}
		}
		
		cvo.printResult();

	}
}
