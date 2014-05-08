package a.act.main;

import java.math.BigDecimal;
import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.ana.vo.SeqAnaVO;
import a.act.calc.vo.CalcVO;
import a.act.main.vo.ResultVO;

public class SeqAnaMain {
	
	
	public static void printCols(){
		String []cols={
				"SEQ",
				"gap0,1",
				"gapA0,1",
				"gap확률",
				"gap2,6",
				"gapA2,6",
				"gap확률",
				"gap7,11",
				"gapA7,11",
				"gap확률",
				"gap12",
				"gapA12",
				"gap확률",
				"updn100",
				"updnA100",
				"updn확률",
				"updn42",
				"updnA42",
				"updn확률",
				"updn13",
				"updnA13",
				"updn확률",
				"updnLast",
				"updnALast",
				"updnLast확률",
				"updnCOMPO",
				"updnCOMPOA",
				"updnCOMPO확률",
				"카운트합",
		};
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+"\t");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		mainFlow();
		//secFlow();
		
	}

	public static void secFlow() {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus();
		AnaVOMain.printCols();
		ArrayList<LineAnaVO> tempList=null;
		//세야할 대상
		CalcVO gap2=new CalcVO(0, 0);
		for(int k=201;k<501;k++){
			ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, k);
			for(int i=0;i<lList.size();i++){
				LineAnaVO lineAnaVO = lList.get(i);
				if(tempList!=null){
					lineAnaVO.calc(tempList.get(i));
				}
				if( lineAnaVO.getNext()!=0){
					gap2.add(lineAnaVO.getGap2().val()+"", 1);
				}
//				System.out.println(lineAnaVO);
			}
		}
		gap2.printResultInt();
	}

	public static void mainFlow() {
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus();
		printCols();
		ArrayList<LineAnaVO> tempList=null;
		//세야할 대상
		
		ArrayList<SeqAnaVO> alist=new ArrayList<SeqAnaVO>();
		
		for(int k=201;k<501;k++){
			// gap1, 2~6, 7~11, 12이상
			int[] gapsC={0,0,0,0}; //전체경우
			int[] gapsA={0,0,0,0}; //나온경우
			// 계단 stair 1,1,1 인 수
			int[] starC={0,0,0,0,0}; //계단전체
			int[] starA={0,0,0,0,0}; //계단등장
			
			ArrayList<LineAnaVO> lList = AnaVOMain.getAnaVOList(list, k);
			
			int total_cnt=0;
			int total_cnt2=0;
			int total_cnt3=0;
			for(int i=0;i<lList.size();i++){
				LineAnaVO lineAnaVO = lList.get(i);
				if(lineAnaVO.getNext()!=0){
					total_cnt+=lineAnaVO.getC13().val();
					System.out.println(lineAnaVO.getBnu()+"\t"+lineAnaVO.getC13().val());
					total_cnt2+=lineAnaVO.getC42().val();
					total_cnt3+=lineAnaVO.getC100().val();
				}
				if(tempList!=null){
					lineAnaVO.calc(tempList.get(i));
				}
//				System.out.println(lineAnaVO);
				switch (lineAnaVO.getGap().val()) {
				case 0:
					//gap 0, 1
					gapsC[0]++;
					if(lineAnaVO.getNext()!=0){
						gapsA[0]++;
					}
					break;
				case 1:
					//gap 2,3,4,5,6
					gapsC[1]++;
					if(lineAnaVO.getNext()!=0){
						gapsA[1]++;
					}
					break;
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
				case 11:
					//gap 7,8,9,10,11
					gapsC[2]++;
					if(lineAnaVO.getNext()!=0){
						gapsA[2]++;
					}
					break;
				default:
					//gap 그외
					gapsC[3]++;
					if(lineAnaVO.getNext()!=0){
						gapsA[3]++;
					}
					break;
				}
				if(lineAnaVO.getUpdn100()==1){
					starC[0]++;
					if(lineAnaVO.getNext()!=0){
						starA[0]++;
					}
				}
				if(lineAnaVO.getUpdn42()==1){
					starC[1]++;
					if(lineAnaVO.getNext()!=0){
						starA[1]++;
					}
				}
				if(lineAnaVO.getUpdn13()==1){
					starC[2]++;
					if(lineAnaVO.getNext()!=0){
						starA[2]++;
					}
				}
				if(lineAnaVO.getUpdnLast()==1){
					starC[3]++;
					if(lineAnaVO.getNext()!=0){
						starA[3]++;
					}
				}
				if(lineAnaVO.getUpdn100()==1 && lineAnaVO.getUpdn42()==1 && lineAnaVO.getUpdn13()==1 && lineAnaVO.getUpdnLast()==1 ){
					starC[4]++;
					if(lineAnaVO.getNext()!=0){
						starA[4]++;
					}
				}
			}
			String str=printCA(k, gapsC, gapsA, starC, starA);
			System.out.print(k+"\t"+total_cnt+"\t"+total_cnt2+"\t"+total_cnt3+"\n");
			tempList=lList;
			alist.add(new SeqAnaVO(str));
		}// seq
		
		int cnt=0;
		CalcVO g01=new CalcVO(0, 0);
		CalcVO gPtn=new CalcVO(0, 0);
		for(int i=50;i<alist.size();i++){
			cnt++;
			SeqAnaVO vo = alist.get(i);
			//System.out.println(vo);
			g01.add(vo.g12.gapA+"", 1);
			gPtn.add(vo.getParttern(), 1);
		}
		System.out.println(cnt+"["+g01.toString()+"]");
		g01.printResultInt();
		System.out.println("PTN");
		gPtn.printResult();
	}

	private static String printCA(int seq, int[] gapsC, int[] gapsA, int[] starC, int[] starA) {
//		System.out.println(seq+"\t"+printListArray(gapsC)+printListArray(gapsA)+printListArray(starC)+printListArray(starA));
		String str = seq+"\t"+printListCA(gapsC, gapsA)+printListCA(starC, starA);
		//System.out.print(str);
		return str;
	}

	public static void printListResult(ArrayList<ResultVO> list) {
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	public static String printListArray(int[] list) {
		String str="";
		for(int i=0;i<list.length;i++){
			str = str + list[i] + "\t";
		}
		return str;
	}

	/**
	 * 평균값 출력
	 * @param c
	 * @param a
	 * @return
	 */
	public static String printListCA(int[] c, int [] a) {
		String str="";
		float avg=0.0f;
		for(int i=0;i<c.length;i++){
			if(c[i]==0 || a[i]==0){
				avg=0.0f;
			}else{
				avg = round(((float)a[i]/(float)c[i])*100, 2);
			}
			str = str + c[i] + "\t" + a[i] + "\t" + avg + "\t";
		}
		return str;
	}
	
	public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

	public static void printList(ArrayList<CalcVO> calResult) {
		for(int i=0;i<calResult.size();i++){
			System.out.println(calResult.get(i));
		}
	}

}