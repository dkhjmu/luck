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
	
	
	static int vv=0;
	
	public static void main(String[] args) {
		System.out.println("start");
//		int t[]={1,2,3,4,5,6,7,8,9,10};
		int t[]={
				4 ,
				13,
				19,
				20,
				32,
				38,
				42
		};
		
		getPtnMain(t, 1);
		
//		System.out.println(getAllpattern(45, 4));
		
		System.out.println("end");
	}

	public static void getPtnMain(int[] t, int picks) {
		int[] temp=t.clone();
		
		if(picks==1){
			for(int i=0;i<t.length;i++){
				System.out.println((i+1)+"\t"+t[i]);
			}
			return;
		}else{
			for(int i=0;i<t.length-picks+1;i++){
				temp=removeArray(temp,t[i]);
				getPtnSub(t[i]+"-", temp, 1, picks);
				
			}
		}//if-else
	}
	
	private static int getPtnSub(String selected, int[] t, int num, int picks) {
		//System.out.println(selected);
		num=num+1;
		int[] temp=t.clone();
		if(num==picks){
			for(int i=0;i<t.length;i++){
				vv++;
				System.out.println(vv+"\t"+selected+t[i]);
			}
		}else{
			for(int i=0;i<t.length-(picks-num);i++){
				temp=removeArray(temp,t[i]);
				getPtnSub(selected+t[i]+"-", temp, num, picks);
			}
		}
		return 1;
	}

	public static int[] removeArray(int[] t, int b){
		int[] r=new int[t.length-1];
		int j=0;
		for(int i=0;i<t.length;i++){
			if(t[i]!=b){
				r[j++] = t[i];
			}
		}
		
		return r;
	}


	
	
	
	
}
 