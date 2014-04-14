package a.ana.pattern;

import java.util.Collections;

import a.act.main.vo.IntVO;
import a.act.main.vo.PtnVO;

public class PatternMaker {
	
	public static void main(String[] args) {
		System.out.println("Start Making!");
		int t[]={1,2,3,4,5,6};
		
		System.out.println(t.length);
		
		System.out.println(getAllpattern(6, 3));
		
		PtnVO no1=getPtnBase(t);
		
		
		System.out.println("End Making");
	}
	
	
	private static PtnVO getPtnBase(int[] t) {
		PtnVO vo=new PtnVO();
		for(int i=0;i<t.length;i++){
			vo.add(new IntVO(t[i]));
		}
		return vo;
	}


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
	
	
	
}
