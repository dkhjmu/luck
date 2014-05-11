package a.act.main;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.calc.vo.CalcVO;
import a.act.main.vo.ResultVO;

public class OnePointMain {
	
	public static void main(String[] args) {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		int seq=499;
		ArrayList<LineAnaVO> dd = AnaVOMain.getAnaVOList(list, seq);
		AnaVOMain.printCols();
		for(int i=0;i<dd.size();i++){
			if(seq+1>list.size()){
				break;
			}
			System.out.println(dd.get(i).toString("1"));
		}
	}

	public static void printListResult(ArrayList<ResultVO> list) {
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
	}

	public static void printList(ArrayList<CalcVO> calResult) {
		for(int i=0;i<calResult.size();i++){
			System.out.println(calResult.get(i));
		}
	}

}
