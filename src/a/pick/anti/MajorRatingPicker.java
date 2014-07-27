package a.pick.anti;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.ResultVO;
import a.pick.AbstractPicker;
import a.pick.filter.PTNFilter;
import a.pick.vo.PickVO;

public class MajorRatingPicker extends AbstractPicker{

	@Override
	public ArrayList<PickVO> pick(int seq) {
		
		PTNFilter filter=new PTNFilter(seq);
		
		ArrayList<PickVO> glist=new ArrayList<PickVO>();
		int trys=0;
		for(int i=0;i<tryN;i++){
			trys++;
			ResultPair rp = getVresult(seq);
			ArrayList<LineAnaVO> result = rp.getResult();
			ArrayList<LineAnaVO> vresult = rp.getVresult();
			ArrayList<LineAnaVO> temp = new ArrayList<LineAnaVO>();
			PickVO pvo=new PickVO(seq, i+1);
			pvo.setLimit(6);
//			for(int j=0;j<3;j++){
//				int r=getRand(result.size()-1);
//				pvo.add(result.get(r).getBnu());
//				temp.add(result.get(r));
//				result.remove(r);
//			}
//			
			for(int j=pvo.size();j<6;j++){
				int r=getRand(vresult.size()-1);
				pvo.add(vresult.get(r).getBnu());
				temp.add(vresult.get(r));
				vresult.remove(r);
			}
			
			if(filter.filtered(temp, seq)==true || trys>100){
				if(checkDuplicate(glist, pvo)){
					System.out.print("D");
					i--;
				}else{
					trys=0;
					System.out.print("..");
					glist.add(pvo);
				}
			}else{
				System.out.print("F");
				i--;
			}
			
		}
		System.out.println("END!");
		
		return glist;
	}
	
	public ResultPair getVresult(int seq){
		ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
		ArrayList<LineAnaVO> vresult = new ArrayList<LineAnaVO>();
		ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);
		
		
		for(int j=0;j<15;j++){
			int r=getRand(result.size());
			result.remove(r);
		}
		

		//major
		for(LineAnaVO vo:result){
			if(
//					vo.getGap().val() < 8 && vo.getC45().val() > 4
					(vo.getC6().val() == 1 || vo.getC6().val() == 2|| vo.getC6().val() == 3|| vo.getC6().val() == 4)
					&&
					(vo.getGap().val() < 7 && vo.getC45().val() > 5)
					&&
					vo.getGap().val() < 24
					){
				vresult.add(vo);
			}
		}
		
		for(LineAnaVO vo:vresult){
			result.remove(vo);
		}
		
		ResultPair r=new ResultPair();
		r.setResult(result);
		r.setVresult(vresult);
		return r;
	}
	
	public class ResultPair{
		ArrayList<LineAnaVO> vresult;
		ArrayList<LineAnaVO> result;
		public ArrayList<LineAnaVO> getVresult() {
			return vresult;
		}
		public void setVresult(ArrayList<LineAnaVO> vresult) {
			this.vresult = vresult;
		}
		public ArrayList<LineAnaVO> getResult() {
			return result;
		}
		public void setResult(ArrayList<LineAnaVO> result) {
			this.result = result;
		}
	}
	
	public static void main(String[] args) {
		MajorRatingPicker p = new MajorRatingPicker();
		p.setTryN(100);
		p.print(p.pick(300));
		
	}
	
}
