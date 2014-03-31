package a.act.ana.vo;

import a.act.main.vo.FloatVO;
import a.act.main.vo.IntVO;

/**
 * @author SDS
 *
 */
/**
 * @author SDS
 *
 */
public class SeqAnaVO {
	int seq;
	
	public tta g01;
	public tta g26;
	public tta g711;
	public tta g12;
	
	public SeqAnaVO(String str) {
		setData(str);
	}
		
	public SeqAnaVO setData(String str){
		if(str==null || str.equals("")){
			return null;
		}
		String[] spStr=str.split("\t");
		//spStr[0]
		this.seq=getInt(spStr[0]);
		
		this.g01=new tta("01",   spStr[1], spStr[2], spStr[3]);
		this.g26=new tta("26",   spStr[4], spStr[5], spStr[6]);
		this.g711=new tta("711", spStr[7], spStr[8], spStr[9]);
		this.g12=new tta("12",   spStr[10], spStr[11], spStr[12]);
		
		return this;
	}
	
	public float getFloat(String str) {
		FloatVO vo = new FloatVO(str);
		return vo.val();
	}

	public int getInt(String str){
		IntVO vo = new IntVO(str);
		return vo.val();
	}

	@Override
	public String toString() {
		return "SeqAnaVO [seq=" + seq + ", g01=" + g01 + ", g26=" + g26
				+ ", g711=" + g711 + ", g12=" + g12 + "]";
	}

	public String getParttern(){
		return "P "+g01.gapA+"-"+g26.gapA+"-"+(g711.gapA+g12.gapA);
	}


	public class tta{
		public String name="";
		public int gap;
		public int gapA;
		public float gapPER;
		
		public tta(String name, String g1, String gA, String gP){
			this.name = name;
			this.gap=getInt(g1);
			this.gapA=getInt(gA);
			this.gapPER=getFloat(gP);
		}

		@Override
		public String toString() {
			return "tta [name=" + name + ", gap=" + gap + ", gapA=" + gapA
					+ ", gapPER=" + gapPER + "]";
		}
		
	}
}
