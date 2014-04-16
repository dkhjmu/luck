package a.ana.pattern;

public class PlanB {
	
	static int vv=0;
	
	public static void main(String[] args) {
		System.out.println("start");
		int t[]={1,2,3,4,5,6,7,8,9,10};
		
		getPtnMain(t, 4);
		
		System.out.println("end");
	}

	public static void getPtnMain(int[] t, int picks) {
		int[] temp=t.clone();
		for(int i=0;i<t.length-picks+1;i++){
			temp=removeArray(temp,t[i]);
			getPtnSub(t[i]+"-", temp, 1, picks);
			
		}
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
