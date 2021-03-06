package a.act.main;

import java.util.ArrayList;

import a.act.ana.vo.LineAnaVO;
import a.act.calc.CountCalc;
import a.act.calc.Gap2Calc;
import a.act.calc.GapCalc;
import a.act.calc.vo.CalcVO;
import a.act.main.dao.ResultDAO;
import a.act.main.vo.ResultVO;

public class AnaVOMain {
	
	private static final String T_VAL = "TVAL";

	public static void main(String[] args) {
		ArrayList<ResultVO> list = getResultListNoBonus();
//		ArrayList<ResultVO> list = getResultList();
		printCols();
		ArrayList<LineAnaVO> tempList=null;
		for(int k=400;k<501;k++){
			ArrayList<LineAnaVO> lList = getAnaVOList(list, k);
			for(int i=0;i<lList.size();i++){
				if(tempList!=null){
					lList.get(i).calc(tempList.get(i));
				}
				System.out.println(lList.get(i));
			}
			tempList=lList;
		}
		
//		ArrayList<LineAnaVO> lList = getAnaVOList(list, 499);
//		for(int i=0;i<lList.size();i++){
//			System.out.println(lList.get(i));
//		}
		
	}

	public static void printCols() {
		String []cols={
				"NEXT",
				"seq",
				"bnu",
				"isBonus",
				"고저",
				"상하",
				"총량",
				"h-index",
				"gap",
				"45",
				"gap2",
				"gap3",
				"max",
				"xn",
				"min",
				"nn",
				"avg",
				"gs",
				"last-avg",
				"last-42",
				"AAA",
				"100",
				"42",
				"13",
				"last",
				"updn100",
				"updn42",
				"updn13",
				"updnLast",
		};
		for(int i=0;i<cols.length;i++){
			System.out.print(cols[i]+"\t");
		}
		System.out.println();
	}

	public static ArrayList<LineAnaVO> getAnaVOList(ArrayList<ResultVO> list, int seq) {
		GapCalc gap=new GapCalc();
		ArrayList<CalcVO> gapResult = gap.calc(list, 1, seq);
		
		Gap2Calc gap2=new Gap2Calc();
		ArrayList<CalcVO> gap2Result = gap2.calc(list, 1, seq, 2);
		
		ArrayList<CalcVO> gap3Result = gap2.calc(list, 1, seq, 3);

		//1. 45섬을 3개 단계로 더하기
		ArrayList<ArrayList<CalcVO>> bigResult=new ArrayList<ArrayList<CalcVO>>();
		for(int i=0;i<50;i++){
			CountCalc cal=new CountCalc();
			bigResult.add(cal.calc(list, seq-45+1-(3*i), seq-(3*i)));
		}
		
		CountCalc cal2=new CountCalc();
		int gSe = 2;
		int tSe = 7;
		ArrayList<CalcVO> cn1 = cal2.calc(list, seq-tSe+1, seq);
		CountCalc cal3=new CountCalc();
		ArrayList<CalcVO> cn2 = cal3.calc(list, seq-tSe+1-gSe, seq-gSe);
		
		// 42 를 기준으로 한 합계
		CountCalc cal42=new CountCalc();
		ArrayList<CalcVO> c42 = cal42.calc(list, seq-(45-gSe)+1, seq);

		// 45 를 기준으로 한 합계
		CountCalc cal45=new CountCalc();
		ArrayList<CalcVO> c45 = cal45.calc(list, seq-(45)+1, seq);

		// 13 를 기준으로 한 합계
		CountCalc cal13=new CountCalc();
		ArrayList<CalcVO> c13 = cal13.calc(list, seq-(13)+1, seq);

		// 6 를 기준으로 한 합계
		CountCalc cal6=new CountCalc();
		ArrayList<CalcVO> c6 = cal6.calc(list, seq-(6)+1, seq);		
		
		// 100 를 기준으로 한 합계
		CountCalc cal100=new CountCalc();
		ArrayList<CalcVO> c100 = cal100.calc(list, seq-(100)+1, seq);
		
		CountCalc cal=new CountCalc();
		ArrayList<CalcVO> total = cal.calc(list, seq-135+1, seq);
		int sum=0;
		for(int i=0;i<total.size();i++){
			sum+=total.get(i).get(CountCalc.CNT).val();
		}
		float avg=sum/total.size();
		float varSum=0;
		for(int i=0;i<total.size();i++){
			int tval=total.get(i).get(CountCalc.CNT).val();
			varSum=varSum+(tval-avg)*(tval-avg);//분산
		}
		Double var = Math.sqrt((varSum/total.size()));//표준편차
		for(int i=0;i<total.size();i++){
			int tval=total.get(i).get(CountCalc.CNT).val();
			if(avg+Math.ceil(var)<=tval){
				total.get(i).add(T_VAL, 1);//고점
			}else if(avg-Math.floor(var)>=tval){
				total.get(i).add(T_VAL, 3);//저점
			}else{
				total.get(i).add(T_VAL, 2);//중립
			}
		}
		
		
		ArrayList<LineAnaVO> lList=new ArrayList<LineAnaVO>();
		for(int i=0;i<45;i++){
			LineAnaVO tvo=new LineAnaVO(seq, i+1);
			
			if(list.size()>=(seq+1) && list.get(seq).isInBonus(i+1)){
				tvo.setNext(seq+1);
				if((i+1)==list.get(seq).get(7)){
					tvo.setBonus(true);
				}else{
					tvo.setBonus(false);
				}
			}			
			
			//gap 
			tvo.setGap(gapResult.get(i).get(GapCalc.GAP));
			tvo.setGap2(gap2Result.get(i).get(Gap2Calc.GAP));
			tvo.setGap3(gap3Result.get(i).get(Gap2Calc.GAP));
			
			//count 크기 구하여 상승/유지/하강 정하는 부분
			tvo.setCn1(cn1.get(i).get(CountCalc.CNT));
			tvo.setCn2(cn2.get(i).get(CountCalc.CNT));
			tvo.setC42(c42.get(i).get(CountCalc.CNT));
			tvo.setC45(c45.get(i).get(CountCalc.CNT));
			tvo.setC13(c13.get(i).get(CountCalc.CNT));
			tvo.setC6(c6.get(i).get(CountCalc.CNT));
			tvo.setC100(c100.get(i).get(CountCalc.CNT));
			tvo.setTval(total.get(i).get(T_VAL));
			
			for(int j=0;j<bigResult.size();j++){
				CalcVO temp = bigResult.get(j).get(i);
				tvo.add(temp.get(CountCalc.CNT));
			}
			tvo.calc(null);
			lList.add(tvo);
		}
		
		lList=setHindex(lList, 100);
		lList=setHindex(lList, 45);
		lList=setHindex(lList, 13);
		
		return lList;
	}

	private static ArrayList<LineAnaVO> setHindex(ArrayList<LineAnaVO> lList, int code) {
		
		ArrayList<LineAnaVO> newOne=new ArrayList<LineAnaVO>();
		
		int size=lList.size();
		
		while(newOne.size()<size){
			int max=-1;
			int tmp=-1;
			int index=-1;
			
			for(int i=0;i<lList.size();i++){
				LineAnaVO vo = lList.get(i);
				tmp=vo.getCodeVal(code);
				
				if(max<tmp){
					max=tmp;
					index=i;
				}
			}
			newOne.add(lList.get(index));
			lList.remove(index);
		}
		
		int h_index = 0;
		
		for(int i=0;i<newOne.size();i++){
			LineAnaVO vo = newOne.get(i);
			vo.setHorder(i+1);
			
			if(vo.getCodeVal(code) >= (i+1)){
				h_index = i;
			}
		}
		
		for(int i=0;i<newOne.size();i++){
			LineAnaVO vo = newOne.get(i);
			if(vo.getHorder()<=h_index){
				vo.setHindex(code);
			}
		}
		
		
		return newOne;
	}
	
	public static ArrayList<ResultVO> getResultList() {
		return getResultList(0);
	}

	public static ArrayList<ResultVO> getResultList(int seq) {
		ResultDAO dao=new ResultDAO();
		
		dao.add(1,37,23,10,33,29,40,16);
		dao.add(2,42,21,9,25,32,13,2);
		dao.add(3,31,21,27,19,11,16,30);
		dao.add(4,40,30,14,42,31,27,2);
		dao.add(5,16,42,29,40,24,41,3);
		dao.add(6,14,15,26,27,40,42,34);
		dao.add(7,16,9,40,25,2,26,42);
		dao.add(8,25,8,39,19,37,34,9);
		dao.add(9,17,4,16,39,36,2,14);
		dao.add(10,30,33,25,41,9,44,6);
		dao.add(11,41,36,37,7,1,42,14);
		dao.add(12,25,39,21,2,45,11,44);
		dao.add(13,37,23,22,42,25,38,26);
		dao.add(14,6,12,40,31,33,2,15);
		dao.add(15,37,30,3,31,16,4,13);
		dao.add(16,37,38,40,7,24,6,33);
		dao.add(17,17,32,37,3,4,9,1);
		dao.add(18,19,3,12,13,35,32,29);
		dao.add(19,30,39,38,40,43,6,26);
		dao.add(20,23,14,10,20,18,30,41);
		dao.add(21,17,31,6,32,12,18,21);
		dao.add(22,17,5,39,8,4,6,25);
		dao.add(23,13,18,5,17,33,42,44);
		dao.add(24,43,8,36,7,27,29,6);
		dao.add(25,26,44,21,43,2,4,16);
		dao.add(26,4,20,5,18,25,7,31);
		dao.add(27,43,28,1,37,20,26,27);
		dao.add(28,23,18,37,35,25,9,1);
		dao.add(29,1,39,34,5,13,40,11);
		dao.add(30,17,36,44,35,8,20,4);
		dao.add(31,18,7,23,35,28,9,32);
		dao.add(32,19,6,14,44,34,25,11);
		dao.add(33,41,40,32,4,33,7,9);
		dao.add(34,9,35,42,40,26,37,2);
		dao.add(35,26,37,43,11,3,2,39);
		dao.add(36,40,1,23,26,10,28,31);
		dao.add(37,30,33,35,7,27,37,42);
		dao.add(38,22,17,37,30,43,16,36);
		dao.add(39,43,15,21,13,7,6,8);
		dao.add(40,19,7,26,18,13,25,6);
		dao.add(41,13,20,38,23,43,35,34);
		dao.add(42,21,23,17,32,18,19,1);
		dao.add(43,31,6,39,38,44,35,1);
		dao.add(44,21,11,3,30,38,45,39);
		dao.add(45,27,20,10,1,33,35,17);
		dao.add(46,13,31,8,38,15,23,39);
		dao.add(47,14,45,26,36,17,31,27);
		dao.add(48,26,37,10,6,18,38,3);
		dao.add(49,16,33,4,19,40,7,30);
		dao.add(50,2,22,15,12,10,44,1);
		dao.add(51,44,2,26,16,3,11,35);
		dao.add(52,15,4,20,16,2,29,1);
		dao.add(53,14,39,7,32,8,33,42);
		dao.add(54,39,27,8,36,21,1,37);
		dao.add(55,37,31,44,40,17,21,7);
		dao.add(56,31,30,14,33,10,37,19);
		dao.add(57,29,7,16,25,44,10,6);
		dao.add(58,25,44,24,10,40,33,1);
		dao.add(59,41,39,36,45,6,29,13);
		dao.add(60,25,8,39,42,36,2,11);
		dao.add(61,38,19,15,30,43,14,8);
		dao.add(62,29,8,15,27,3,35,21);
		dao.add(63,20,36,23,3,38,40,5);
		dao.add(64,15,26,21,36,18,14,39);
		dao.add(65,40,4,43,36,33,25,39);
		dao.add(66,22,2,17,7,3,24,45);
		dao.add(67,7,38,10,3,36,15,33);
		dao.add(68,15,39,12,26,10,16,38);
		dao.add(69,15,19,8,5,14,39,35);
		dao.add(70,28,43,5,22,25,19,26);
		dao.add(71,5,9,41,29,12,16,21);
		dao.add(72,26,2,4,27,11,17,1);
		dao.add(73,43,12,32,3,18,40,38);
		dao.add(74,15,6,40,35,18,17,23);
		dao.add(75,24,44,32,34,2,5,28);
		dao.add(76,37,25,1,15,22,3,43);
		dao.add(77,43,32,44,2,29,18,37);
		dao.add(78,35,29,13,33,10,25,38);
		dao.add(79,12,24,27,32,30,3,14);
		dao.add(80,25,17,24,30,26,18,1);
		dao.add(81,11,5,33,20,7,13,6);
		dao.add(82,3,2,1,14,27,42,39);
		dao.add(83,15,10,19,6,34,17,14);
		dao.add(84,27,34,23,42,45,16,11);
		dao.add(85,36,8,13,31,23,6,21);
		dao.add(86,39,37,2,41,12,45,33);
		dao.add(87,16,23,34,43,12,4,26);
		dao.add(88,41,30,17,24,1,20,27);
		dao.add(89,29,28,40,33,26,4,37);
		dao.add(90,38,44,35,29,20,17,10);
		dao.add(91,26,42,1,21,24,29,27);
		dao.add(92,3,33,35,36,14,24,17);
		dao.add(93,22,24,38,44,6,36,19);
		dao.add(94,41,45,5,32,40,34,6);
		dao.add(95,17,43,34,27,31,8,14);
		dao.add(96,3,31,8,1,21,22,20);
		dao.add(97,20,36,6,14,7,15,3);
		dao.add(98,24,23,32,6,9,16,43);
		dao.add(99,27,29,3,37,10,1,11);
		dao.add(100,1,42,11,23,37,7,6);
		dao.add(101,32,45,35,1,3,17,8);
		dao.add(102,35,22,17,24,40,26,42);
		dao.add(103,30,27,14,5,45,15,10);
		dao.add(104,17,42,44,34,33,32,35);
		dao.add(105,45,34,41,10,20,8,28);
		dao.add(106,4,12,10,24,33,22,29);
		dao.add(107,1,31,9,5,4,6,17);
		dao.add(108,23,18,7,29,22,44,12);
		dao.add(109,5,44,1,42,36,34,33);
		dao.add(110,29,7,23,20,43,22,1);
		dao.add(111,40,33,7,31,36,18,27);
		dao.add(112,30,41,33,29,42,26,43);
		dao.add(113,4,33,36,28,9,45,26);
		dao.add(114,11,19,26,41,14,28,2);
		dao.add(115,25,9,28,1,2,6,31);
		dao.add(116,31,34,25,37,4,2,17);
		dao.add(117,36,44,22,5,34,10,35);
		dao.add(118,17,4,22,3,10,19,38);
		dao.add(119,14,3,17,11,21,13,38);
		dao.add(120,6,10,11,4,37,32,30);
		dao.add(121,38,28,12,30,34,43,9);
		dao.add(122,11,1,16,36,40,17,8);
		dao.add(123,18,7,45,17,30,28,27);
		dao.add(124,16,29,4,23,42,25,1);
		dao.add(125,32,2,35,33,36,8,18);
		dao.add(126,20,22,40,43,7,27,1);
		dao.add(127,43,5,29,32,3,10,35);
		dao.add(128,36,30,45,12,37,34,39);
		dao.add(129,23,42,19,28,38,25,17);
		dao.add(130,42,7,27,45,19,24,31);
		dao.add(131,21,11,14,15,10,8,37);
		dao.add(132,23,3,41,34,17,45,43);
		dao.add(133,15,7,26,4,23,18,13);
		dao.add(134,3,35,31,23,12,20,43);
		dao.add(135,6,14,22,28,35,39,16);
		dao.add(136,2,41,16,36,40,42,11);
		dao.add(137,25,9,20,39,36,7,15);
		dao.add(138,10,37,28,11,39,27,19);
		dao.add(139,43,9,20,15,11,28,13);
		dao.add(140,28,19,18,3,13,17,8);
		dao.add(141,12,8,42,43,29,31,2);
		dao.add(142,12,16,44,34,30,40,19);
		dao.add(143,27,42,43,28,26,45,8);
		dao.add(144,4,15,17,26,36,37,43);
		dao.add(145,13,3,2,44,27,20,9);
		dao.add(146,42,27,35,19,41,2,25);
		dao.add(147,4,13,42,6,21,40,36);
		dao.add(148,33,34,36,21,35,25,17);
		dao.add(149,42,41,11,21,34,2,27);
		dao.add(150,39,28,25,37,2,18,16);
		dao.add(151,10,13,19,18,2,1,15);
		dao.add(152,26,29,34,1,5,13,43);
		dao.add(153,12,13,8,36,3,11,33);
		dao.add(154,19,21,35,40,45,6,20);
		dao.add(155,16,20,41,33,32,19,4);
		dao.add(156,45,18,30,42,28,5,2);
		dao.add(157,30,33,26,19,39,35,37);
		dao.add(158,18,4,34,9,21,13,7);
		dao.add(159,18,1,41,30,42,43,32);
		dao.add(160,3,41,34,8,7,39,1);
		dao.add(161,40,22,34,45,36,42,44);
		dao.add(162,38,25,41,21,1,5,24);
		dao.add(163,29,7,11,26,28,44,16);
		dao.add(164,6,39,11,10,41,9,27);
		dao.add(165,22,5,18,42,13,19,31);
		dao.add(166,39,9,12,45,27,36,14);
		dao.add(167,24,27,36,39,30,28,4);
		dao.add(168,43,3,40,31,10,42,30);
		dao.add(169,43,27,16,45,35,37,19);
		dao.add(170,42,13,31,2,15,11,10);
		dao.add(171,29,4,34,16,25,35,1);
		dao.add(172,4,21,24,26,19,41,35);
		dao.add(173,33,9,30,24,3,34,18);
		dao.add(174,14,13,39,35,22,18,16);
		dao.add(175,19,26,28,31,33,36,17);
		dao.add(176,30,34,33,32,17,4,15);
		dao.add(177,1,10,13,16,37,43,6);
		dao.add(178,1,5,11,12,18,23,9);
		dao.add(179,43,9,5,39,35,17,32);
		dao.add(180,20,34,29,15,21,2,22);
		dao.add(181,14,21,40,23,32,45,44);
		dao.add(182,15,13,29,27,40,34,35);
		dao.add(183,34,24,18,42,2,40,5);
		dao.add(184,1,33,6,2,20,16,41);
		dao.add(185,2,8,1,4,38,19,14);
		dao.add(186,14,4,10,45,19,21,9);
		dao.add(187,18,29,1,2,8,38,42);
		dao.add(188,19,24,27,31,34,30,36);
		dao.add(189,32,45,14,37,8,35,28);
		dao.add(190,14,31,44,30,18,8,15);
		dao.add(191,32,6,24,37,5,25,8);
		dao.add(192,37,8,45,4,18,11,33);
		dao.add(193,36,26,18,14,39,6,13);
		dao.add(194,26,44,39,15,20,23,28);
		dao.add(195,35,10,19,40,7,22,31);
		dao.add(196,37,45,41,36,44,35,30);
		dao.add(197,34,16,42,7,45,12,4);
		dao.add(198,25,45,12,19,20,41,2);
		dao.add(199,25,30,36,22,21,14,43);
		dao.add(200,6,5,20,14,13,17,7);
		dao.add(201,3,38,39,24,11,44,26);
		dao.add(202,39,33,14,12,44,27,17);
		dao.add(203,32,3,11,30,1,24,7);
		dao.add(204,3,40,14,35,12,45,5);
		dao.add(205,29,21,37,3,1,35,30);
		dao.add(206,25,2,3,20,1,15,43);
		dao.add(207,14,31,32,11,3,37,38);
		dao.add(208,34,31,25,44,14,40,24);
		dao.add(209,18,24,33,20,2,7,37);
		dao.add(210,22,37,23,10,19,25,39);
		dao.add(211,17,41,13,20,12,33,8);
		dao.add(212,21,38,31,18,11,12,8);
		dao.add(213,20,3,24,2,5,4,42);
		dao.add(214,25,28,37,7,5,20,32);
		dao.add(215,7,2,15,3,44,43,4);
		dao.add(216,7,36,16,40,33,17,1);
		dao.add(217,39,27,16,35,33,20,38);
		dao.add(218,29,14,1,18,8,44,20);
		dao.add(219,35,26,4,37,11,20,16);
		dao.add(220,11,21,43,19,5,34,31);
		dao.add(221,20,2,35,40,37,33,10);
		dao.add(222,7,39,43,5,29,28,44);
		dao.add(223,18,1,27,20,26,3,38);
		dao.add(224,4,42,19,30,27,26,7);
		dao.add(225,5,19,31,11,13,36,7);
		dao.add(226,21,2,8,14,6,22,34);
		dao.add(227,4,22,42,16,5,15,2);
		dao.add(228,17,36,25,35,44,39,23);
		dao.add(229,5,4,9,11,23,38,35);
		dao.add(230,32,33,11,14,5,29,12);
		dao.add(231,31,10,44,45,5,19,27);
		dao.add(232,9,8,10,12,24,44,35);
		dao.add(233,28,4,6,13,40,17,39);
		dao.add(234,24,21,13,26,37,22,4);
		dao.add(235,27,22,21,37,26,31,8);
		dao.add(236,37,39,13,4,1,8,7);
		dao.add(237,1,44,17,24,11,21,33);
		dao.add(238,4,31,2,28,34,15,35);
		dao.add(239,41,44,15,39,24,11,7);
		dao.add(240,6,10,41,16,43,40,21);
		dao.add(241,2,35,28,24,16,27,21);
		dao.add(242,19,4,34,21,32,20,43);
		dao.add(243,2,28,42,12,19,17,34);
		dao.add(244,38,16,25,36,13,37,19);
		dao.add(245,32,9,27,38,31,11,22);
		dao.add(246,26,23,21,18,13,39,15);
		dao.add(247,28,36,40,15,39,12,13);
		dao.add(248,38,45,17,8,3,23,13);
		dao.add(249,27,8,44,41,31,3,11);
		dao.add(250,23,37,43,30,19,45,38);
		dao.add(251,38,25,7,19,6,28,45);
		dao.add(252,23,31,45,26,14,39,28);
		dao.add(253,34,19,8,25,31,36,33);
		dao.add(254,24,1,20,5,30,19,27);
		dao.add(255,5,27,6,1,42,24,32);
		dao.add(256,14,11,43,4,23,21,32);
		dao.add(257,6,31,32,37,27,13,4);
		dao.add(258,40,31,27,14,38,30,17);
		dao.add(259,14,4,45,35,42,5,34);
		dao.add(260,7,24,40,12,15,37,43);
		dao.add(261,16,18,43,11,6,31,2);
		dao.add(262,31,12,25,24,9,29,36);
		dao.add(263,1,32,40,37,28,27,18);
		dao.add(264,16,36,9,41,44,27,5);
		dao.add(265,5,38,39,37,9,34,12);
		dao.add(266,42,3,11,22,4,9,37);
		dao.add(267,41,36,7,34,24,8,1);
		dao.add(268,24,32,19,45,10,3,12);
		dao.add(269,43,5,20,36,18,42,32);
		dao.add(270,12,5,21,9,26,20,27);
		dao.add(271,40,27,9,8,29,3,36);
		dao.add(272,12,9,7,43,39,27,28);
		dao.add(273,24,8,44,1,31,34,6);
		dao.add(274,35,14,15,26,39,13,25);
		
		dao.add(275,40,14,35,20,19,38,26);
		dao.add(276,39,33,4,41,15,21,25);
		dao.add(277,12,10,15,29,25,13,20);
		dao.add(278,37,41,11,43,39,3,13);
		dao.add(279,38,7,31,16,37,36,11);
		dao.add(280,37,24,23,11,10,36,35);
		dao.add(281,3,1,6,4,41,14,12);
		dao.add(282,5,31,18,32,2,10,30);
		dao.add(283,31,18,6,38,8,45,42);
		dao.add(284,7,15,2,30,24,45,28);
		dao.add(285,41,33,40,37,13,45,2);
		dao.add(286,19,44,42,1,15,40,17);
		dao.add(287,24,12,27,37,6,35,41);
		dao.add(288,35,28,1,17,41,12,10);
		dao.add(289,3,33,37,14,38,42,10);
		dao.add(290,39,32,18,8,45,13,7);
		dao.add(291,20,7,42,18,3,8,45);
		dao.add(292,34,31,17,18,32,33,10);
		dao.add(293,33,29,21,9,17,1,24);
		dao.add(294,10,6,17,37,38,30,40);
		dao.add(295,38,18,4,1,12,16,8);
		dao.add(296,15,30,3,27,45,8,44);
		dao.add(297,19,11,6,20,32,28,34);
		dao.add(298,5,27,29,9,37,40,19);
		dao.add(299,25,45,1,20,36,3,24);
		
		dao.add(300,9,7,26,38,10,12,39);
		dao.add(301,13,11,33,37,43,7,26);
		dao.add(302,19,13,20,38,32,42,4);
		dao.add(303,17,45,38,2,30,14,43);
		dao.add(304,26,4,41,10,16,33,38);
		dao.add(305,18,39,23,8,21,7,9);
		dao.add(306,4,23,41,34,30,18,19);
		dao.add(307,25,15,23,21,45,5,12);
		dao.add(308,17,45,37,15,19,14,40);
		dao.add(309,2,5,11,36,1,18,22);
		dao.add(310,19,1,5,34,28,41,16);
		dao.add(311,27,12,24,32,4,28,10);
		dao.add(312,2,6,5,20,12,3,25);
		dao.add(313,35,34,45,17,9,43,2);
		dao.add(314,15,19,41,38,17,34,2);
		dao.add(315,1,43,33,45,13,35,23);
		dao.add(316,11,31,21,27,10,39,43);
		dao.add(317,11,36,22,10,3,39,8);
		dao.add(318,19,20,34,17,2,45,21);
		dao.add(319,42,5,8,28,22,33,37);
		dao.add(320,16,23,45,25,19,41,3);
		dao.add(321,34,20,18,12,21,25,42);
		dao.add(322,38,32,29,18,9,43,20);
		dao.add(323,32,15,14,36,42,10,3);
		dao.add(324,25,21,33,4,36,2,17);
		dao.add(325,45,20,44,7,32,17,33);
		dao.add(326,33,39,25,16,23,36,40);
		dao.add(327,12,44,13,17,32,6,24);
		dao.add(328,1,28,9,16,17,6,24);
		dao.add(329,9,17,19,30,35,42,4);
		dao.add(330,3,16,19,17,4,20,23);
		dao.add(331,4,44,26,31,14,9,39);
		dao.add(332,45,17,36,16,34,42,3);
		dao.add(333,43,27,39,30,5,14,35);
		dao.add(334,21,39,29,15,13,43,33);
		dao.add(335,9,16,5,26,23,45,21);
		dao.add(336,5,20,34,44,3,35,16);
		dao.add(337,37,18,1,14,32,5,4);
		dao.add(338,13,42,2,45,34,38,16);
		dao.add(339,21,8,6,14,30,37,45);
		dao.add(340,26,18,38,24,34,29,32);
		dao.add(341,39,1,19,43,8,34,41);
		dao.add(342,33,14,13,43,34,1,25);
		dao.add(343,31,1,17,10,29,43,15);
		dao.add(344,15,28,1,34,2,45,38);
		dao.add(345,23,29,42,15,20,39,2);
		dao.add(346,14,22,13,5,44,45,33);
		dao.add(347,32,27,8,3,42,13,10);
		dao.add(348,14,31,24,17,3,20,34);
		dao.add(349,5,14,13,25,20,24,36);
		dao.add(350,33,29,24,8,18,1,35);
		dao.add(351,27,29,25,36,5,34,33);
		dao.add(352,17,20,41,16,26,5,24);
		dao.add(353,36,16,11,29,19,22,26);
		dao.add(354,45,44,36,19,43,14,1);
		dao.add(355,35,29,5,30,44,8,38);
		dao.add(356,8,29,45,14,2,25,24);
		dao.add(357,37,18,10,14,21,36,5);
		dao.add(358,21,1,9,12,40,10,37);
		dao.add(359,20,1,40,10,24,19,23);
		dao.add(360,25,35,40,16,4,23,27);
		dao.add(361,24,10,27,16,5,35,33);
		dao.add(362,30,2,22,27,3,40,29);
		dao.add(363,21,11,14,32,12,38,6);
		dao.add(364,2,16,7,5,14,40,4);
		dao.add(365,30,5,26,15,21,25,31);
		dao.add(366,19,27,44,12,26,5,38);
		dao.add(367,25,44,22,29,32,3,19);
		dao.add(368,24,11,21,39,45,30,26);
		dao.add(369,41,35,20,43,17,36,21);
		dao.add(370,45,16,18,24,42,44,17);
		dao.add(371,7,9,15,27,26,42,18);
		dao.add(372,6,18,16,14,11,21,13);
		dao.add(373,45,37,43,26,42,15,9);
		dao.add(374,11,17,25,34,13,15,26);
		dao.add(375,27,19,4,45,8,25,7);
		dao.add(376,40,28,24,11,13,1,7);
		dao.add(377,22,6,43,29,45,37,23);
		dao.add(378,29,22,39,34,5,31,43);
		dao.add(379,35,22,10,31,6,40,19);
		dao.add(380,37,1,17,2,8,26,27);
		dao.add(381,5,1,12,10,20,16,11);
		dao.add(382,15,22,10,42,24,27,19);
		dao.add(383,15,4,37,40,33,28,25);
		dao.add(384,32,38,36,11,24,22,7);
		dao.add(385,7,19,29,12,21,32,9);
		dao.add(386,7,10,31,4,40,19,26);
		dao.add(387,40,43,26,31,34,1,20);
		dao.add(388,29,1,8,17,32,9,45);
		dao.add(389,26,18,7,16,20,23,3);
		dao.add(390,16,28,17,40,37,39,15);
		dao.add(391,10,11,18,22,28,39,30);
		dao.add(392,8,1,42,3,24,7,43);
		dao.add(393,43,40,28,16,41,9,21);
		dao.add(394,20,13,22,28,25,1,15);
		dao.add(395,35,15,20,11,31,26,7);
		dao.add(396,18,34,45,31,20,40,30);
		dao.add(397,25,33,22,17,13,12,8);
		dao.add(398,44,15,20,10,23,42,7);
		dao.add(399,9,2,42,19,1,17,20);
		dao.add(400,34,21,9,43,27,41,2);
		
		dao.add(401,18,43,31,12,6,38,9);
		dao.add(402,36,9,15,22,19,5,32);
		dao.add(403,28,24,10,14,22,37,26);
		dao.add(404,40,33,20,21,24,5,36);
		
		// 순서없음
		dao.add(405,1,2,10,25,26,44,4);
		dao.add(406,7,12,21,24,27,36,45);
		dao.add(407,6,7,13,16,24,25,1);
		dao.add(408,9,20,21,22,30,37,16);
		dao.add(409,6,9,21,31,32,40,38);
		dao.add(410,1,3,18,32,40,41,16);
		dao.add(411,11,14,22,35,37,39,5);
		dao.add(412,4,7,39,41,42,45,40);
		dao.add(413,2,9,15,23,34,40,3);
		dao.add(414,2,14,15,22,23,44,43);
		dao.add(415,7,17,20,26,30,40,24);
		dao.add(416,5,6,8,11,22,26,44);
		dao.add(417,4,5,14,20,22,43,44);
		dao.add(418,11,13,15,26,28,34,31);
		dao.add(419,2,11,13,14,28,30,7);
		dao.add(420,4,9,10,29,31,34,27);
		dao.add(421,6,11,26,27,28,44,30);
		dao.add(422,8,15,19,21,34,44,12);
		dao.add(423,1,17,27,28,29,40,5);
		dao.add(424,10,11,26,31,34,44,30);
		dao.add(425,8,10,14,27,33,38,3);
		dao.add(426,4,17,18,27,39,43,19);
		dao.add(427,6,7,15,24,28,30,21);
		dao.add(428,12,16,19,22,37,40,8);
		dao.add(429,3,23,28,34,39,42,16);
		dao.add(430,1,3,16,18,30,34,44);
		dao.add(431,18,22,25,31,38,45,6);
		dao.add(432,2,3,5,11,27,39,33);
		dao.add(433,19,23,29,33,35,43,27);
		dao.add(434,3,13,20,24,33,37,35);
		dao.add(435,8,16,26,30,38,45,42);
		dao.add(436,9,14,20,22,33,34,28);
		dao.add(437,11,16,29,38,41,44,21);
		dao.add(438,6,12,20,26,29,38,45);
		dao.add(439,17,20,30,31,37,40,25);
		dao.add(440,10,22,28,34,36,44,2);
		dao.add(441,1,23,28,30,34,35,9);
		dao.add(442,25,27,29,36,38,40,41);
		dao.add(443,4,6,10,19,20,44,14);
		dao.add(444,11,13,23,35,43,45,17);
		dao.add(445,13,20,21,30,39,45,32);
		dao.add(446,1,11,12,14,26,35,6);
		dao.add(447,2,7,8,9,17,33,34);
		dao.add(448,3,7,13,27,40,41,36);
		dao.add(449,3,10,20,26,35,43,36);
		dao.add(450,6,14,19,21,23,31,13);
		dao.add(451,12,15,20,24,30,38,29);
		dao.add(452,8,10,18,30,32,34,27);
		dao.add(453,12,24,33,38,40,42,30);
		dao.add(454,13,25,27,34,38,41,10);
		dao.add(455,4,19,20,26,30,35,24);
		dao.add(456,1,7,12,18,23,27,44);
		dao.add(457,8,10,18,23,27,40,33);
		dao.add(458,4,9,10,32,36,40,18);
		dao.add(459,4,6,10,14,25,40,12);
		dao.add(460,8,11,28,30,43,45,41);
		dao.add(461,11,18,26,31,37,40,43);
		dao.add(462,3,20,24,32,37,45,4);
		dao.add(463,23,29,31,33,34,44,40);
		dao.add(464,6,12,15,34,42,44,4);
		dao.add(465,1,8,11,13,22,38,31);
		dao.add(466,4,10,13,23,32,44,20);
		dao.add(467,2,12,14,17,24,40,39);
		dao.add(468,8,13,15,28,37,43,17);
		dao.add(469,4,21,22,34,37,38,33);
		dao.add(470,10,16,20,39,41,42,27);
		dao.add(471,6,13,29,37,39,41,43);
		dao.add(472,16,25,26,31,36,43,44);
		dao.add(473,8,13,20,22,23,36,34);
		dao.add(474,4,13,18,31,33,45,43);
		dao.add(475,1,9,14,16,21,29,3);
		dao.add(476,9,12,13,15,37,38,27);
		dao.add(477,14,25,29,32,33,45,37);
		dao.add(478,18,29,30,37,39,43,8);
		dao.add(479,8,23,25,27,35,44,24);
		dao.add(480,3,5,10,17,30,31,16);
		dao.add(481,3,4,23,29,40,41,20);
		dao.add(482,1,10,16,24,25,35,43);
		dao.add(483,12,15,19,22,28,34,5);
		dao.add(484,1,3,27,28,32,45,11);
		dao.add(485,17,22,26,27,36,39,20);
		dao.add(486,1,2,23,25,38,40,43);
		dao.add(487,4,8,25,27,37,41,21);
		dao.add(488,2,8,17,30,31,38,25);
		dao.add(489,2,4,8,15,20,27,11);
		dao.add(490,2,7,26,29,40,43,42);
		dao.add(491,8,17,35,36,39,42,4);
		dao.add(492,22,27,31,35,37,40,42);
		dao.add(493,20,22,26,33,36,37,25);
		dao.add(494,5,7,8,15,30,43,22);
		dao.add(495,4,13,22,27,34,44,6);
		dao.add(496,4,13,20,29,36,41,39);
		dao.add(497,19,20,23,24,43,44,13);
		dao.add(498,13,14,24,32,39,41,3);
		dao.add(499,5,20,23,27,35,40,43);
		dao.add(500,3,4,12,20,24,34,41);
		dao.add(501,1,4,10,17,31,42,2);
		dao.add(502,6,22,28,32,34,40,26);
		dao.add(503,1,5,27,30,34,36,40);
		dao.add(504,6,14,22,26,43,44,31);
		dao.add(505,7,20,22,25,38,40,44);
		dao.add(506,6,9,11,22,24,30,31);
		dao.add(507,12,13,32,33,40,41,4);
		dao.add(508,5,27,31,34,35,43,37);
		dao.add(509,12,25,29,35,42,43,24);
		dao.add(510,12,29,32,33,39,40,42);
		dao.add(511,3,7,14,23,26,42,24);
		dao.add(512,4,5,9,13,26,27,1);
		dao.add(513,5,8,21,23,27,33,12);
		dao.add(514,1,15,20,26,35,42,9);
		dao.add(515,2,11,12,15,23,37,8);
		dao.add(516,2,8,23,41,43,44,30);
		dao.add(517,1,9,12,28,36,41,10);
		dao.add(518,14,23,30,32,34,38,6);
		dao.add(519,6,8,13,16,30,43,3);
		dao.add(520,4,22,27,28,38,40,1);
		dao.add(521,3,7,18,29,32,36,19);
		dao.add(522,4,5,13,14,37,41,11);
		dao.add(523,1,4,37,38,40,45,7);
		dao.add(524,10,11,29,38,41,45,21);
		dao.add(525,11,23,26,29,39,44,22);
		dao.add(526,7,14,17,20,35,39,31);
		dao.add(527,1,12,22,32,33,42,38);
		dao.add(528,5,17,25,31,39,40,10);
		dao.add(529,18,20,24,27,31,42,39);
		dao.add(530,16,23,27,29,33,41,22);
		dao.add(531,1,5,9,21,27,35,45);
		dao.add(532,16,17,23,24,29,44,3);
		dao.add(533,9,14,15,17,31,33,23);
		dao.add(534,10,24,26,29,37,38,32);
		dao.add(535,11,12,14,15,18,39,34);
		dao.add(536,7,8,18,32,37,43,12);
		dao.add(537,12,23,26,30,36,43,11);
		dao.add(538,6,10,18,31,32,34,11);
		dao.add(539,3,19,22,31,42,43,26);
		dao.add(540,3,12,13,15,34,36,14);
		dao.add(541,8,13,26,28,32,34,43);
		dao.add(542,5,6,19,26,41,45,34);

//		System.out.println("GAP2");
//		Gap2Calc gap2=new Gap2Calc();
//		ArrayList<CalcVO> gap2Result = gap2.calc(list, 311, 400);
//		System.out.println(gap2Result.size());
////		printList(gap2Result);
		
		if(seq == 0){
			ArrayList<ResultVO> list = dao.getResultList();
			return list;
		}else{
			ArrayList<ResultVO> list = dao.getResultList(seq);
			return list;
		}
	}
	
	public static ArrayList<ResultVO> getResultListNoBonus() {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		for(int i=0;i<list.size();i++){
			list.get(i).setNoBonus();
		}
		return list;
	}
	
	public static ArrayList<ResultVO> getResultListNoBonus(int seq) {
		ArrayList<ResultVO> list = AnaVOMain.getResultList();
		for(int i=0;i<list.size();i++){
			list.get(i).setNoBonus();
		}
		return list;
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
