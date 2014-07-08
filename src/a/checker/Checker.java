package a.checker;

//import a.pick.AbstractPicker;

public class Checker {
	public static int checkResult(int result[], int right[], int bonus){
		int vv=0;
		for(int i=0;i<right.length;i++){
			for(int j=0;j<result.length;j++){
				if(right[i]==result[j]){
					vv++;
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
//	
//	public static int[] check(AbstractPicker picker, int seq, int rightA[], int bonus){
//		
//		return null;
//		
//	}
	
	public static void printArray(int[] resultA){
		for(int i=0;i<resultA.length;i++){
			System.out.print(resultA[i]+"\t");
		}
		System.out.println();
	}
	
	public static int getSum(int[] result) {
		return result[3]*5000+result[4]*50000+result[5]*1200000+result[6]*100000000+result[7]*50000000;
	}
	
}
