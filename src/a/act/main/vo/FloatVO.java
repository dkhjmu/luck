package a.act.main.vo;

public class FloatVO {
	float val=0;
	
	public FloatVO() {
		val=0;
	}
	public FloatVO(String v) {
		val=0;
		val=Float.parseFloat(v);
	}
	public FloatVO(int v) {
		val=v;
	}
	public FloatVO(FloatVO v) {
		val=v.val();
	}
	public float val(){
		return val;
	}
	public float add(FloatVO vo){
		this.val=this.val+vo.val();
		return this.val;
	}
	public float add(int v){
		this.val=this.val+v;
		return this.val;
	}
	public float minus(FloatVO vo){
		this.val=this.val-vo.val();
		return this.val;
	}
	public float setVal(FloatVO v){
		this.val=v.val();
		return this.val;
	}
	@Override
	public String toString() {
		return val+"";
	}
}
