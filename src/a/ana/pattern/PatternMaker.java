package a.ana.pattern;

import java.util.ArrayList;

import a.act.main.vo.IntVO;
import a.act.main.vo.PtnVO;

public class PatternMaker {
	
	/**
	 * 
	 * 모든 타입의 갯수 세기
	 * 
	 * @param maxSize
	 * @param picks
	 * @return
	 */
	public static int getAllpattern(int maxSize, int picks){
		int up = 1;
		int down = 1;
		
		for(int i=0;i<picks;i++){
			up = up * (maxSize - i);
		}
		
		for(int i=0;i<picks;i++){
			down = down * (picks - i);
		}
		
		return (up / down);
	}
	
	
	public static void main(String[] args) {
		System.out.println("Start Making!");
		int t[]={1,2,3,4,5,6};
		
		System.out.println(getAllpattern(15,4));
		
		
		
		System.out.println("End Making");
	}
	
	
	private static void getPtn(PtnVO base) {
		base.setCurrentCnt(base.getCurrentCnt()+1);
		ArrayList<IntVO> list = base.getList();
		
		if(base.getCurrentCnt()==base.getPickCnt()){
			if(list.size()==1){
				System.out.println(list.get(0)+"\t");
				return;
			}else{
				System.out.println(list.get(0)+"\t");
				System.out.println();
				base.remove(list.get(0));
				PtnVO child = getPtnBase(list);
				child.setCurrentCnt(base.getCurrentCnt());
				child.setPickCnt(base.getPickCnt());
				getPtn(child);
			}
		}
		
	}


	public static PtnVO getPtnBase(ArrayList<IntVO> t) {
		PtnVO vo=new PtnVO();
		for(int i=0;i<t.size();i++){
			vo.add(new IntVO(t.get(i)));
		}
		return vo;
	}
	
	public static PtnVO getPtnBase(int[] t) {
		PtnVO vo=new PtnVO();
		for(int i=0;i<t.length;i++){
			vo.add(new IntVO(t[i]));
		}
		return vo;
	}


	
	
	
	
}
 