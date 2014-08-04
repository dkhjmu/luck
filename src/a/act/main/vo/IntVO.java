package a.act.main.vo;

public class IntVO implements Comparable<IntVO>{
	int val=0;
	
	public IntVO() {
		val=0;
	}
	public IntVO(String v) {
		val=0;
		val=Integer.parseInt(v);
	}
	public IntVO(int v) {
		val=v;
	}
	public IntVO(IntVO v) {
		val=v.val();
	}
	public int val(){
		return val;
	}
	public int add(IntVO vo){
		this.val=this.val+vo.val();
		return this.val;
	}
	public int add(int v){
		this.val=this.val+v;
		return this.val;
	}
	public int minus(IntVO vo){
		this.val=this.val-vo.val();
		return this.val;
	}
	public int setVal(IntVO v){
		this.val=v.val();
		return this.val;
	}
	@Override
	public String toString() {
		return val+"";
	}
	@Override
	public int compareTo(IntVO o) {
		return this.val()-o.val();
	}
}
