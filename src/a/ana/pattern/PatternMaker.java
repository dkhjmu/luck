package a.ana.pattern;

import java.util.HashMap;
import java.util.Iterator;

import a.act.main.vo.IntVO;


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
		
//		HashMap<String, IntVO> map=new HashMap<String, IntVO>();
//		
//		getPtnMain(t, 2, map);
//		
//		printKeyNVal(map);
		
		System.out.println(getAllpattern(20,4));
		
		System.out.println("end");
	}

	public static void printKeyNVal(HashMap<String, IntVO> map) {
		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key=iter.next();
			System.out.println(key+"\t"+map.get(key));
		}
	}

	public static void printKeyNValOver1(HashMap<String, IntVO> map) {
		Iterator<String> iter = map.keySet().iterator();
		int total=0;
		while(iter.hasNext()){
			String key=iter.next();
			if(map.get(key).val()>1){
				System.out.println(key+"\t"+map.get(key));
			}
			total++;
		}
		System.out.println("Total:"+total);
	}

	public static HashMap<String, IntVO> getPtnMain(int[] t, int picks, HashMap<String, IntVO> map) {
		int[] temp=t.clone();
		
		if(picks==1){
			for(int i=0;i<t.length;i++){
				//System.out.println((i+1)+"\t"+t[i]);
				map=addMap(t[i]+"", map);
			}
			return map;
		}else{
			for(int i=0;i<t.length-picks+1;i++){
				temp=removeArray(temp,t[i]);
				getPtnSub(t[i]+"-", temp, 1, picks, map);
				
			}
		}//if-else
		return map;
	}

	private static HashMap<String, IntVO> addMap(String key, HashMap<String, IntVO> map) {
		IntVO vo = map.get(key);
		if(vo!=null){
			vo.add(1);
		}else{
			map.put(key, new IntVO(1));
		}
		return map;
	}
	
	private static HashMap<String, IntVO> getPtnSub(String selected, int[] t, int num, int picks, HashMap<String, IntVO> map) {
		//System.out.println(selected);
		num=num+1;
		int[] temp=t.clone();
		if(num==picks){
			for(int i=0;i<t.length;i++){
				vv++;
//				System.out.println(vv+"\t"+selected+t[i]);
				map=addMap(selected+t[i]+"", map);
			}
		}else{
			for(int i=0;i<t.length-(picks-num);i++){
				temp=removeArray(temp,t[i]);
				getPtnSub(selected+t[i]+"-", temp, num, picks, map);
			}
		}
		return map;
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
 