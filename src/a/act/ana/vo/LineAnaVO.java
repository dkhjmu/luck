package a.act.ana.vo;

import java.util.ArrayList;

import a.act.main.vo.IntVO;

public class LineAnaVO implements Comparable<LineAnaVO> {
	
	int bnu;
	
	ArrayList<IntVO> list;
	
	String updownType="";// 상승 하강 유지
	String topbotType="";// 고점 저점 중립
	String totalUPType=""; //전체 상승하강 타입
	
	IntVO max;
	IntVO min;
	
	IntVO gap;
	IntVO gap2;
	IntVO gap3;
	
	IntVO cn1;
	IntVO cn2;
	
	IntVO c42; // 42까지의 sum
	IntVO c45; // 45까지의 sum
	IntVO c13; // 13까지의 sum
	IntVO c100;// 100까지의 sum
	
	float avg;

	float var;

	private int maxHit;

	private int minHit;

	private int seq;

	private int next;

	int updn100;
	int updn42;
	int updn13;
	int updnLast;
	
	int h_index;
	int h_order;
	
	boolean bonus;

	public boolean isBonus() {
		return bonus;
	}

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}

	public LineAnaVO(int seq, int bnu){
		this.seq = seq;
		this.bnu=bnu;
		max=new IntVO(0);
		min=new IntVO(99);
		avg=0;
		list=new ArrayList<IntVO>();
	}
	
	public int add(int i){
		return add(new IntVO(i));
	}
	
	public int add(String s){
		return add(new IntVO(s));
	}
	
	public int add(IntVO v){
		if(list==null){
			list = new ArrayList<IntVO>();
		}
		list.add(v);
		return list.size();
	}
	
	public void calc(LineAnaVO tvoTemp){
		/**
		 * 1. 최대/최소
		 * 2. 평균
		 * 3. 저점/고점
		 * 4. 상승/하강
		 * 
		 */
		int sum=0;
		for(int i=0;i<list.size();i++){
			if(min.val() > list.get(i).val()){
				min.setVal(list.get(i));
			}
			if(max.val() < list.get(i).val()){
				max.setVal(list.get(i));
			}
			sum+=list.get(i).val();
		}
		
		avg = (sum/list.size());
		float vSum=0;
		var = 0;
		
		maxHit = 0;
		minHit = 0;
		
		for(int i=0;i<list.size();i++){
			int val = list.get(i).val();
			vSum=vSum+ (val-avg)*(val-avg);
			if(val==max.val()){
				maxHit++;
			}
			if(val==min.val()){
				minHit++;
			}
		}
		var=vSum/list.size();
		
		int val = list.get(0).val();
		double v1 = (Math.sqrt(var));
//		if(Math.floor(Math.sqrt(var))<1){
//			v1=1;
//		}
		double v2 = (Math.sqrt(var));
//		if(Math.ceil(Math.sqrt(var))<1){
//			v2=1;
//		}
		if(avg+v1<= val){
			topbotType="고점";
		}else if( avg - v2 >= val){
			topbotType="저점";
		}else{
			topbotType="중립";
		}
		
		
		if((cn1.val()-cn2.val())==0){
			updownType="유지";
		}
		if((cn1.val()-cn2.val())>0){
			updownType="상승";
		}
		if((cn1.val()-cn2.val())<0){
			updownType="하강";
		}
		
		if(tvoTemp==null){
			return;
		}
		
		updn100=checkUPDN(tvoTemp.getUpdn100(), tvoTemp.getC100(), this.c100);
		updn42=checkUPDN(tvoTemp.getUpdn42(), tvoTemp.getC42(), this.c42);
		updn13=checkUPDN(tvoTemp.getUpdn13(), tvoTemp.getC13(), this.c13);
		updnLast=checkUPDN(tvoTemp.getUpdnLast(), tvoTemp.getLast(), this.getLast());		
	}

	private int checkUPDN(int updn, IntVO tC, IntVO c) {
		if(updn==1){
			if(tC.val()-c.val() < 0){
				return 0;
			}else{
				return 1;
			}
		}else{
			if(tC.val()-c.val() > 0){
				return 1;
			}else if(tC.val()-c.val() == 0){
				return updn;
			}else{
				return 0;
			}
		}
	}
	
	@Override
	public String toString() {
		
		String gs="";
		if(gap.val()<7){
			gs="2";
		}else if(gap.val()<12){
			gs="1";
		}else{
			gs="0";
		}
		
		int val = list.get(0).val();
		String str=  next+"\t"
				    +seq+"\t"
				    +bnu+"\t"
				    +bonus+"\t"
					+topbotType+"\t"
					+updownType+"\t"
					+totalUPType+"\t"
					+h_index+"\t"
					+gap+"\t"
					+c45+"\t"
					+gap2+"\t"
					+gap3+"\t"
					+max+"\t"
					+maxHit+"t\t"
					+min+"\t"
					+minHit+"t\t"
					+avg+"\t"
					+gs+"\t"
					+(avg-c42.val())+"\t"
					+(val-c42.val())+"\t"
					+gap+topbotType+updownType+(val-c42.val())+totalUPType+val+"\t"
					+c100+"\t"
					+c42+"\t"
					+c13+"\t"
					+list.get(0)+"\t"
					+updn100+"\t"
					+updn42+"\t"
					+updn13+"\t"
					+updnLast+"\t"
					;
		return str;
	}

	public void setGap(IntVO intVO) {
		this.gap=new IntVO(intVO);
	}
	
	public void setGap2(IntVO intVO) {
		this.gap2=new IntVO(intVO);
	}
	
	public void setCn1(IntVO c6){
		this.cn1=new IntVO(c6);
	}
	
	public void setCn2(IntVO c9){
		this.cn2=new IntVO(c9);
	}
	
	public void setC42(IntVO c42){
		this.c42=new IntVO(c42);
	}

	public void setTval(IntVO intVO) {
		if(intVO.val()==1){
			totalUPType="총고";
		}else if(intVO.val()==2){
			totalUPType="총중";
		}else if(intVO.val()==3){
			totalUPType="총저";
		}
	}

	public String toString(String string) {
		String str=toString();
		for(int i=1;i<list.size();i++){
			str=str+list.get(i)+"\t";
		}
		return str;
	}

	public void setNext(int seq2) {
		this.next=seq2;
	}

	public void setC100(IntVO intVO) {
		this.c100 = intVO;
	}

	public IntVO getC42() {
		return c42;
	}

	public IntVO getC45() {
		return c45;
	}

	public void setC45(IntVO c45) {
		this.c45 = c45;
	}

	public IntVO getC100() {
		return c100;
	}
	
	public IntVO getLast(){
		return list.get(0);
	}

	public int getUpdn100() {
		return updn100;
	}

	public int getUpdn42() {
		return updn42;
	}

	public int getUpdnLast() {
		return updnLast;
	}

	public int getBnu() {
		return bnu;
	}

	public void setBnu(int bnu) {
		this.bnu = bnu;
	}

	public ArrayList<IntVO> getList() {
		return list;
	}

	public void setList(ArrayList<IntVO> list) {
		this.list = list;
	}

	public String getUpdownType() {
		return updownType;
	}

	public void setUpdownType(String updownType) {
		this.updownType = updownType;
	}

	public String getTopbotType() {
		return topbotType;
	}

	public void setTopbotType(String topbotType) {
		this.topbotType = topbotType;
	}

	public String getTotalUPType() {
		return totalUPType;
	}

	public void setTotalUPType(String totalUPType) {
		this.totalUPType = totalUPType;
	}

	public IntVO getMax() {
		return max;
	}

	public void setMax(IntVO max) {
		this.max = max;
	}

	public IntVO getMin() {
		return min;
	}

	public void setMin(IntVO min) {
		this.min = min;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}

	public float getVar() {
		return var;
	}

	public void setVar(float var) {
		this.var = var;
	}

	public int getMaxHit() {
		return maxHit;
	}

	public void setMaxHit(int maxHit) {
		this.maxHit = maxHit;
	}

	public int getMinHit() {
		return minHit;
	}

	public void setMinHit(int minHit) {
		this.minHit = minHit;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public IntVO getGap() {
		return gap;
	}
	
	public IntVO getGap2() {
		return gap2;
	}

	public IntVO getCn1() {
		return cn1;
	}

	public IntVO getCn2() {
		return cn2;
	}

	public int getNext() {
		return next;
	}

	public void setUpdn100(int updn100) {
		this.updn100 = updn100;
	}

	public void setUpdn42(int updn42) {
		this.updn42 = updn42;
	}

	public void setUpdnLast(int updnLast) {
		this.updnLast = updnLast;
	}

	public IntVO getC13() {
		return c13;
	}

	public void setC13(IntVO c13) {
		this.c13 = c13;
	}

	public int getUpdn13() {
		return updn13;
	}

	public void setUpdn13(int updn13) {
		this.updn13 = updn13;
	}
	
	public IntVO getGap3() {
		return gap3;
	}

	public void setGap3(IntVO gap3) {
		this.gap3 = gap3;
	}

	public int getHindex() {
		return h_index;
	}

	public void setHindex(int h_index) {
		this.h_index = h_index;
	}

	public int getHorder() {
		return h_order;
	}

	public void setHorder(int h_order) {
		this.h_order = h_order;
	}

	@Override
	public int compareTo(LineAnaVO o) {
		return o.getC100().val()-this.getC100().val();
	}

	public int getCodeVal(int code) {
		if(code==100){
			return c100.val();
		}else if(code==45){
			return c45.val();
		}else{
			return c13.val();
		}
	}
}
