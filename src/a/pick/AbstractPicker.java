package a.pick;

public abstract class AbstractPicker {
	protected int[][] list;
	public int getSize() {
		return list.length;
	}
	
	public abstract int[][] pick(int seq);
	public int[][] getList(){
		return list;
	}
}

