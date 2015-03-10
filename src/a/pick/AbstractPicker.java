package a.pick;

import java.util.ArrayList;

import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.checker.Checker;
import a.pick.vo.PickVO;
import a.util.ArrayUtil;

public abstract class AbstractPicker {
	
	protected int tryN=100;
	
	public static int getRand(int max) {
		int rand = (int) (Math.floor(Math.random() * 100) % max);
		return rand;
	}
	
	public void setTryN(int n){
		this.tryN=n;
	}
	
	public abstract ArrayList<PickVO> pick(int seq);
	
	public boolean checkDuplicate(ArrayList<PickVO> glist, PickVO pvo){
		for(PickVO v:glist){
			if(v.equals(pvo)){
				return true;
			}
		}
		return false;
	}
	
	public void print(ArrayList<PickVO> list){
		for(PickVO vo:list){
			System.out.println(vo);
		}
	}
	
	public void printN(ArrayList<PickVO> list){
		for(PickVO vo:list){
			System.out.println(vo.getArray().length+"\t"+vo);
		}
	}
	
	public boolean normalCheck(PickVO v){
		int[] g=v.getArray();
		int sum=0;
		for(int j:g){
			sum+=j;
		}
		
		if(sum<95 || sum > 180){
			return false;
		}
		
		return true;
	}
	
	public void simulating(int SERV_SEQ){
	  simulating(SERV_SEQ, false);
	}
	
	public void simulating(int SERV_SEQ, boolean printable){
		ArrayList<ResultVO> mlist = AnaVOMain.getResultList();
		int maxSeq=mlist.size();
		int start = maxSeq - SERV_SEQ ;
		int end = maxSeq;
		int zero[]={0,0,0,0,0,0,0,0};
		int total=0;
		for(;start<end;start++ ){
			ArrayList<PickVO> g = this.pick(start);
//			printN(g);
			
			ArrayList<ResultVO> list = AnaVOMain.getResultList();
			int bonus=list.get(start).get(7);
			for(PickVO v: g){
				int[] right=list.get(start).getArray(false);
				int val=Checker.checkResult(v.getArray(), right, bonus);
//				System.out.println(val);
				if(v.getArray().length>0){
					total++;
					zero[val]++;
					if(val>3){
					  if(printable){
	                    System.out.println("##############################");
	                    System.out.println(v);
	                    System.out.println("##############################");
	                  }
					}
				}
			}
		}
		System.out.println("!!!!!!!!!!!!!!@@@@@@@@@@!!!!!!!!!!!!!!!!!");
		for(int i=0;i<zero.length;i++){
			System.out.println(i+":\t"+zero[i]);
		}
		
		System.out.println((total*1000)+" 원");
		
	}
}

