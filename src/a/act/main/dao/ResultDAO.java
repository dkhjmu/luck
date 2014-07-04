package a.act.main.dao;

import java.util.ArrayList;

import a.act.main.vo.ResultVO;

public class ResultDAO {
	ArrayList<ResultVO> list;

	public ResultDAO() {
		super();
		list=new ArrayList<ResultVO>();
	}
	
	public boolean add(ResultVO vo){
		return list.add(vo);
	}

	public boolean add(int seq, int bnu1, int bnu2, int bnu3, int bnu4, int bnu5, int bnu6, int bnu7) {
		ResultVO vo = new ResultVO(seq, bnu1, bnu2, bnu3, bnu4, bnu5, bnu6, bnu7);
		return add(vo);
	}

	public ArrayList<ResultVO> getResultList() {
		if(list.size()==0){
			return null;
		}
		return list;
	}
	
	public ArrayList<ResultVO> getResultList(int seq) {
		if(list.size()<seq){
			return null;
		}
		
		ArrayList<ResultVO> result=new ArrayList<ResultVO>();
		for(int i=0;i<seq-1;i++){
			result.add(list.get(i));
		}
		
		return result;
	}
	
}
