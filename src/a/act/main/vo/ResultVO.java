package a.act.main.vo;

public class ResultVO {
	int seq;
	int bnu1;
	int bnu2;
	int bnu3;
	int bnu4;
	int bnu5;
	int bnu6;
	int bnu7;
	
	public ResultVO(int seq, int bnu1, int bnu2, int bnu3, int bnu4, int bnu5,
			int bnu6, int bnu7) {
		super();
		this.seq = seq;
		this.bnu1 = bnu1;
		this.bnu2 = bnu2;
		this.bnu3 = bnu3;
		this.bnu4 = bnu4;
		this.bnu5 = bnu5;
		this.bnu6 = bnu6;
		this.bnu7 = bnu7;
	}
	
	public int getSeq(){
		return seq;
	}
	
	public int get(int i){
		switch (i) {
		case 1:
			return bnu1;
		case 2:
			return bnu2;
		case 3:
			return bnu3;
		case 4:
			return bnu4;
		case 5:
			return bnu5;
		case 6:
			return bnu6;
		case 7:
			return bnu7;
		default:
			return -1;
		}
	}
	
	public boolean isIn(int v) {
		for(int j=1;j<=6;j++){
			if(get(j)==v){
				return true;
			}
		}
		return false;
	}
	public boolean isInBonus(int v) {
		for(int j=1;j<=7;j++){
			if(get(j)==v){
				return true;
			}
		}
		return false;
	}
	public void setNoBonus(){
		bnu7 = -1;
	}
	

	@Override
	public String toString() {
		return  seq + "\t\t" + bnu1 + "\t" + bnu2
				+ "\t" + bnu3 + "\t" + bnu4 + "\t" + bnu5
				+ "\t" + bnu6 + "\t" + bnu7;
	}
	
}
