package a.checker;

import java.util.ArrayList;

import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;
import a.pick.ptn.PtnPicker;
import a.pick.vo.PickVO;

//import a.pick.AbstractPicker;

public class Checker {
	public static int checkResult(int result[], int right[], int bonus){
		int vv=0;
		for(int j=0;j<result.length;j++){
			for(int i=0;i<right.length;i++){
				if(right[i]==result[j]){
					vv++;
					right[i]=-1;
				}
			}
		}
		
		if(vv==5){
			for(int j=0;j<result.length;j++){
				if(bonus==result[j]){
					return 7;
				}
			}
		}
		
		return vv;
	}
	
	public static int[][] check(AbstractPicker picker, int seq, int rightA[], int bonus){
		
		
		//int[] rightClone=rightA.clone();
		
		ArrayList<PickVO> glist = picker.pick(seq-1);
		int input=0;
		int resultV[][] = {{0},{0,0,0,0,0,0,0,0}};
		for (int i = 0; i < glist.size(); i++) {
			int[] rightClone=rightA.clone();
			int[] resultA = glist.get(i).getArray();
//			ArrayUtil.print(resultA);
//			ArrayUtil.print(rightA);
			int pv=Checker.checkResult(resultA, rightClone, bonus);
			resultV[1][pv]++;
			if (pv >= 5) {
				System.out.print("right:");
				printArray(rightA);
				System.out.println("bonus:"+bonus);
				System.out.println("");
				System.out.print("input:");
				printArray(resultA);
			}
			input+=1000;
		}
		
		moneySum(resultV[1], input, getSum(resultV[1]));
		
		resultV[0][0]=input;
		
		return resultV;
		
	}
	
	
	public static void simulating(AbstractPicker picker) {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		int maxSeq=list.size();
		int startSeq = maxSeq - SERV_SEQ ;
		int[] total={0,0,0,0,0,0,0,0};
		int input=0;
		int gets=0;
		for(int i=startSeq;i<maxSeq;i++){
			System.out.println("seq:"+(i+1)+" !! ");
			int[][] rrr=Checker.check(picker, i+1, list.get(i).getArray(false), list.get(i).get(7));
			
			total=addResult(total, rrr[1]);
			input+=rrr[0][0];
			gets+=getSum(rrr[1]);
		}
		System.out.println("##################");
		moneySum(total, input, gets);
		System.out.println("inputs : \t"+input);
		System.out.println("gets :   \t"+gets);
	}
	
	public static void moneySum(int[] result, int input, int gets){
		for(int i=0;i<result.length;i++){
			System.out.print(result[i]+"\t");
		}
		int sum=getSum(result);
		String ss ="";
		if(input>gets){
			ss ="DN";
		}else{
			ss ="UP";
		}
		System.out.println("SUM:"+sum+"\t"+ss);
		try{
			Thread.sleep(33);
		}catch(Exception e){
		}
	}
	
	private static int[] addResult(int[] total, int[] rrr) {
		for(int i=0;i<rrr.length;i++){
			total[i]+=rrr[i];
		}
		return total;
	}

	public static void printArray(int[] resultA){
		for(int i=0;i<resultA.length;i++){
			System.out.print(resultA[i]+"\t");
		}
		System.out.println();
	}
	
	public static int getSum(int[] result) {
		return result[3]*5000+result[4]*50000+result[5]*1200000+result[6]*100000000+result[7]*50000000;
	}
	
	public static int SERV_SEQ = 10;
	
	public static void main(String[] args) {
//		NormalRandomPicker picker=new NormalRandomPicker();
//		NormalRandomFilteredPicker picker=new NormalRandomFilteredPicker();
//		RatingRandomPicker picker=new RatingRandomPicker();
//		AntiPicker picker = new AntiPicker();
//		TirdRandomPicker picker=new TirdRandomPicker();
//		MajorRatingPicker picker = new MajorRatingPicker();
		PtnPicker picker = new PtnPicker();
		
		picker.setTryN(10);
		Checker.simulating(picker);
		
	}

	
}
