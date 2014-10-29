package a.pick.ana;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import a.act.ana.vo.LineAnaVO;
import a.act.main.AnaVOMain;
import a.act.main.vo.IntVO;
import a.act.main.vo.ResultVO;
import a.ana.pattern.PatternMaker;
import a.pick.AbstractPicker;
import a.pick.filter.BNUPtnFilter;
import a.pick.vo.PickVO;
import a.util.ArrayUtil;

public class AnaPickerV2 extends AbstractPicker {
  private static final int LIMITS = 6;

  @Override
  public ArrayList<PickVO> pick(int seq) {
    ArrayList<ResultVO> list = AnaVOMain.getResultListNoBonus(seq);
    ArrayList<PickVO> glist = new ArrayList<PickVO>();
    ArrayList<LineAnaVO> result = AnaVOMain.getAnaVOList(list, seq);

    ArrayList<LineAnaVO> list13 = new ArrayList<LineAnaVO>();
    ArrayList<LineAnaVO> list45 = new ArrayList<LineAnaVO>();
    ArrayList<LineAnaVO> list100 = new ArrayList<LineAnaVO>();
    
    int i = 1;
    PickVO pvo = new PickVO(seq, i);
    pvo.setLimit(45);
    
    for (LineAnaVO vo : result) {
      if (vo.getHindex() == 13) {
        list13.add(vo);
      }
      if (vo.getHindex() == 45) {
        list45.add(vo);
      }
      if (vo.getHindex() == 100) {
        list100.add(vo);
      }
      
      
    }
    // System.out.println(list13.size());
    // System.out.println(list45.size());
    // System.out.println(list100.size());

    

    
    
    pvo = getList13PVO(pvo, list13);
    pvo = getList45PVO(pvo, list45);
    pvo = getList100PVO(pvo, list100);
    
    System.out.println(pvo.toString());

    glist.add(pvo);

    return glist;

  }

  private PickVO getList13PVO(PickVO pvo, ArrayList<LineAnaVO> list13) {
    if (list13.size() == 3) {
      LineAnaVO v = getGap(3, list13);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(2, list13);
      if (v != null) {
        pvo.add(v.getBnu());
      }
    } else if (list13.size() == 2) {
      LineAnaVO v = getGap(3, list13);
      if (v != null) {
        pvo.add(v.getBnu());
      } else {
        v = getGap(1, list13);
        if (v != null) {
          pvo.add(v.getBnu());
        } else {
          v = getGap(2, list13);
          if (v != null) {
            pvo.add(v.getBnu());
          }
        }
      }
    } else if (list13.size() == 1) {
      LineAnaVO v = getGap(1, list13);
      if (v != null) {
        pvo.add(v.getBnu());
      }
    }
    return pvo;
  }
  
  private PickVO getList45PVO(PickVO pvo, ArrayList<LineAnaVO> list45) {
    if (list45.size() > 4) {
      LineAnaVO v = getGap(0, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(1, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(2, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(4, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
    } else {
      LineAnaVO v = getGap(0, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(1, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(2, list45);
      if (v != null) {
        pvo.add(v.getBnu());
      }
    }
    return pvo;
  }
  
  private PickVO getList100PVO(PickVO pvo, ArrayList<LineAnaVO> list100) {
    if (list100.size() > 7) {
      LineAnaVO v = getGap(0, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(3, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(4, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(7, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
    } else {
      LineAnaVO v = getGap(3, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(1, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
      v = getGap(2, list100);
      if (v != null) {
        pvo.add(v.getBnu());
      }
     
    }
    return pvo;
  }

  public static LineAnaVO getGap(int gap, ArrayList<LineAnaVO> list) {
    for (LineAnaVO vo : list) {
      if (vo.getGap().val() == gap) {
        return vo;
      }
    }
    return null;
  }
  
  public static LineAnaVO getC13(int cnt, ArrayList<LineAnaVO> list) {
    for (LineAnaVO vo : list) {
      if (vo.getC13().val() == cnt) {
        return vo;
      }
    }
    return null;
  }

  public static boolean checkPVO(PickVO pvo, BNUPtnFilter filter) {
    int[] rr = pvo.getArray();
    int sum = ArrayUtil.sumArray(rr);
    int[] ff = ArrayUtil.fourFloorPtn(rr);
    if(filter.filtered(rr, 1)==true 
        || sum<90
        || sum>150
        || ff[0] > 2
        || ff[1] > 3
        || ff[2] > 3
        || ff[3] > 3
        || ff[4] > 2
    ){
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    AnaPickerV2 p = new AnaPickerV2();
    p.simulating(10, true);

  }
}
