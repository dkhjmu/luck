package a.act.ana.vo;

import java.util.ArrayList;
import java.util.Collections;

import a.act.main.vo.IntVO;

public class SeqStatVO {
	int seq;
	int bnuSum=0;
	int cnt13Sum=0;
	int cnt45Sum=0;
	int cnt100Sum=0;
	int evenCnt=0;
	int oddCnt=0;
	String gapFullPtn="";
	ArrayList<IntVO> gaps;
	ArrayList<IntVO> cnt45;
	int spg0=0;//짧은 패턴 0번째 대략 0
	int spg1=0;//짧은 패턴 1번째 대략 1
	int spg2=0;//짧은 패턴 2번째 대략 2~7
	int spg3=0;//짧은 패턴 2번째 대략 8이상
	int nspg0=0;
	int nspg1=0;
	int nspg2=0;
	int nspg3=0;
	int c0=0; //1~9
	int c10=0;//10~19
	int c20=0;
	int c30=0;
	int c40=0;
	
	int h100a=0;
	int h45a=0;
	int h13a=0;
	int h100c=0;
	int h45c=0;
	int h13c=0;
	
	int hta=0;
	int htc=0;
	
	int h13_g0a=0;
	int h13_g0c=0;
	
	
	public SeqStatVO(int seq){
		this.seq=seq;
		gaps=new ArrayList<IntVO>();
		cnt45=new ArrayList<IntVO>();
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getBnuSum() {
		return bnuSum;
	}
	public void setBnuSum(int bnuSum) {
		this.bnuSum = bnuSum;
	}
	public int getCnt13Sum() {
		return cnt13Sum;
	}
	public void setCnt13Sum(int cnt13Sum) {
		this.cnt13Sum = cnt13Sum;
	}
	public int getCnt45Sum() {
		return cnt45Sum;
	}
	public void setCnt45Sum(int cnt45Sum) {
		this.cnt45Sum = cnt45Sum;
	}
	public int getCnt100Sum() {
		return cnt100Sum;
	}
	public void setCnt100Sum(int cnt100Sum) {
		this.cnt100Sum = cnt100Sum;
	}
	public int getEvenCnt() {
		return evenCnt;
	}
	public void setEvenCnt(int evenCnt) {
		this.evenCnt = evenCnt;
	}
	public int getOddCnt() {
		return oddCnt;
	}
	public void setOddCnt(int oddCnt) {
		this.oddCnt = oddCnt;
	}
	public String getGapFullPtn() {
		Collections.sort(gaps);
		for(int i=0;i<gaps.size();i++){
			if((i+1)!=gaps.size()){
				gapFullPtn=gapFullPtn+gaps.get(i).toString()+"\t";
			}else{
				gapFullPtn=gapFullPtn+gaps.get(i).toString();
			}
		}
		return gapFullPtn;
	}
	public void setGapFullPtn(String gapFullPtn) {
		this.gapFullPtn = gapFullPtn;
	}
	public int getC0() {
		return c0;
	}
	public void setC0(int c0) {
		this.c0 = c0;
	}
	public int getC10() {
		return c10;
	}
	public void setC10(int c10) {
		this.c10 = c10;
	}
	public int getC20() {
		return c20;
	}
	public void setC20(int c20) {
		this.c20 = c20;
	}
	public int getC30() {
		return c30;
	}
	public void setC30(int c30) {
		this.c30 = c30;
	}
	public int getC40() {
		return c40;
	}
	public void setC40(int c40) {
		this.c40 = c40;
	}

	public void setBnuStat(int bnu) {
		this.bnuSum+=bnu;
		int div=bnu/10;
		switch (div) {
		case 0:
			c0++;
			break;
		case 1:
			c10++;
			break;
		case 2:
			c20++;
			break;
		case 3:
			c30++;
			break;
		case 4:
			c40++;
			break;
		default:
			break;
		}
		if(bnu%2==1){
			oddCnt++;
		}else{
			evenCnt++;
		}
	}

	public void setGapStat(IntVO gap) {
		gaps.add(gap);
		
		int v=gap.val();
		switch (v) {
		case 0:
			//gap 0, 1
			spg0++;
			break;
		case 1:
			//gap 0, 1
			spg1++;
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			//gap 2,3,4,5,6,7
			spg2++;
			break;
		default:
			//gap 2,3,4,5,6,7
			spg3++;
			break;
		}
		
	}
	
	public void setNspgStats(IntVO gap) {
		int v=gap.val();
		switch (v) {
		case 0:
			//gap 0, 1
			nspg0++;
			break;
		case 1:
			//gap 0, 1
			nspg1++;
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			//gap 2,3,4,5,6,7
			nspg2++;
			break;
		default:
			//gap 2,3,4,5,6,7
			nspg3++;
			break;
		}
		
	}
	
	public void setHindexStatus(LineAnaVO lineAnaVO) {
		switch(lineAnaVO.getHindex()){
		case 100:
			h100a=h100a+1;
			if(lineAnaVO.getNext()!=0){
				h100c=h100c+1;
			}
			break;
		case 45:
			h45a=h45a+1;
			if(lineAnaVO.getNext()!=0){
				h45c=h45c+1;
			}
			break;
		case 13:
			h13a=h13a+1;
			if(lineAnaVO.getNext()!=0){
				h13c=h13c+1;
			}
			break;
		}
		
		if(lineAnaVO.getHindex()!=0){
			hta=hta+1;
			if(lineAnaVO.getNext()!=0){
				htc=htc+1;
			}
		}
		
	}
	
	public void addCnts(LineAnaVO lineAnaVO) {
		
		cnt45.add(new IntVO(lineAnaVO.getC45()));
		
	}
	
	
	public ArrayList<IntVO> getGaps() {
		return gaps;
	}

	public void setGaps(ArrayList<IntVO> gaps) {
		this.gaps = gaps;
	}

	public ArrayList<IntVO> getCnt45() {
		return cnt45;
	}

	public void setCnt45(ArrayList<IntVO> cnt45) {
		this.cnt45 = cnt45;
	}

	public int getSpg0() {
		return spg0;
	}

	public void setSpg0(int spg0) {
		this.spg0 = spg0;
	}

	public int getSpg1() {
		return spg1;
	}

	public void setSpg1(int spg1) {
		this.spg1 = spg1;
	}

	public int getSpg2() {
		return spg2;
	}

	public void setSpg2(int spg2) {
		this.spg2 = spg2;
	}

	public int getSpg3() {
		return spg3;
	}

	public void setSpg3(int spg3) {
		this.spg3 = spg3;
	}

	public int getNspg0() {
		return nspg0;
	}

	public void setNspg0(int nspg0) {
		this.nspg0 = nspg0;
	}

	public int getNspg1() {
		return nspg1;
	}

	public void setNspg1(int nspg1) {
		this.nspg1 = nspg1;
	}

	public int getNspg2() {
		return nspg2;
	}

	public void setNspg2(int nspg2) {
		this.nspg2 = nspg2;
	}

	public int getNspg3() {
		return nspg3;
	}

	public void setNspg3(int nspg3) {
		this.nspg3 = nspg3;
	}

	public int getH100a() {
		return h100a;
	}

	public int getH45a() {
		return h45a;
	}

	public int getH13a() {
		return h13a;
	}

	public int getH100c() {
		return h100c;
	}

	public int getH45c() {
		return h45c;
	}

	public int getH13c() {
		return h13c;
	}

	public void setH100a(int h100a) {
		this.h100a = h100a;
	}

	public void setH45a(int h45a) {
		this.h45a = h45a;
	}

	public void setH13a(int h13a) {
		this.h13a = h13a;
	}

	public void setH100c(int h100c) {
		this.h100c = h100c;
	}

	public void setH45c(int h45c) {
		this.h45c = h45c;
	}

	public void setH13c(int h13c) {
		this.h13c = h13c;
	}


	public int getH13_g0a() {
		return h13_g0a;
	}

	public void setH13_g0a(int h13_g0a) {
		this.h13_g0a = h13_g0a;
	}

	public int getH13_g0c() {
		return h13_g0c;
	}

	public void setH13_g0c(int h13_g0c) {
		this.h13_g0c = h13_g0c;
	}

	public static void printHeader(){
		String str=
				"seq"+"\t"+
				"bnuSum"+"\t"+
				"cnt13Sum"+"\t"+
				"cnt45Sum"+"\t"+
				"cnt100Sum"+"\t"+
				"evenCnt"+"\t"+
				"oddCnt"+"\t"+
				"100a"+"\t"+
				"100c"+"\t"+
				"45a"+"\t"+
				"45c"+"\t"+
				"13a"+"\t"+
				"13c"+"\t"+
				"hta"+"\t"+
				"htc"+"\t"+
				"h13_gap0a"+"\t"+
				"h13_gap0c"+"\t"+
				"c0"+"\t"+ //1~9
				"c10"+"\t"+//10~19
				"c20"+"\t"+
				"c30"+"\t"+
				"c40"+"\t"+
				"spg0"+"\t"+//짧은 패턴 0번째 대략 0~1
				"spg1"+"\t"+//짧은 패턴 1번째 대략 2~7
				"spg2"+"\t"+//짧은 패턴 2번째 대략 8이상
				"spg3"+"\t"+//짧은 패턴 2번째 대략 8이상
				"nspg0"+"\t"+//짧은 패턴 0번째 대략 0~1
				"nspg1"+"\t"+//짧은 패턴 1번째 대략 2~7
				"nspg2"+"\t"+//짧은 패턴 2번째 대략 8이상
				"nspg3"+"\t"+//짧은 패턴 2번째 대략 8이상
				"gapFullPtn"
		;
		System.out.println(str);
	}
	
	@Override
	public String toString() {
		Collections.sort(gaps);
		for(int i=0;i<gaps.size();i++){
			if((i+1)!=gaps.size()){
				gapFullPtn=gapFullPtn+gaps.get(i).toString()+"\t";
			}else{
				gapFullPtn=gapFullPtn+gaps.get(i).toString();
			}
		}
		
		String str=
				seq+"\t"+
				bnuSum+"\t"+
				cnt13Sum+"\t"+
				cnt45Sum+"\t"+
				cnt100Sum+"\t"+
				evenCnt+"\t"+
				oddCnt+"\t"+
				h100a+"\t"+
				h100c+"\t"+
				h45a+"\t"+
				h45c+"\t"+
				h13a+"\t"+
				h13c+"\t"+
				hta+"\t"+
				htc+"\t"+
				h13_g0a+"\t"+
				h13_g0c+"\t"+
				c0+"\t"+ //1~9
				c10+"\t"+//10~19
				c20+"\t"+
				c30+"\t"+
				c40+"\t"+
				spg0+"\t"+//짧은 패턴 0번째 대략 0~1
				spg1+"\t"+//짧은 패턴 1번째 대략 2~7
				spg2+"\t"+//짧은 패턴 2번째 대략 8이상
				spg3+"\t"+//짧은 패턴 2번째 대략 8이상
				nspg0+"\t"+//짧은 패턴 0번째 대략 0~1
				nspg1+"\t"+//짧은 패턴 1번째 대략 2~7
				nspg2+"\t"+//짧은 패턴 2번째 대략 8이상
				nspg3+"\t"+//짧은 패턴 2번째 대략 8이상
				gapFullPtn
		;
		
		return str;
	}

	
}
