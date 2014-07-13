package a.pick.anti;

import java.util.ArrayList;

import a.pick.AbstractPicker;
import a.pick.vo.PickVO;

public class AntiPicker extends AbstractPicker{

	@Override
	public ArrayList<PickVO> pick(int seq) {
		return null;
	}
	
	public static void main(String[] args) {
		AntiPicker p = new AntiPicker();
		p.setTryN(10);
		p.print(p.pick(300));
	}
	
}
