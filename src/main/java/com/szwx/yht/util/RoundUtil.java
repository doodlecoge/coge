package com.szwx.yht.util;

import java.math.BigDecimal;

/**
 * 取精度工具类
 * @author zhangyj
 * @date Mar 21, 2012
 */
public class RoundUtil {
	private static RoundUtil round=null;
	
	private RoundUtil(){
		
	}
	public static RoundUtil newInstance(){
		if(null==round){
			round= new RoundUtil();
		}
		return round;
	}
	/**
	 * 四舍5入保存3位小数
	 * @author zhangyj
	 * @date Mar 21, 2012
	 */
	public String getYield(int trues,int alls){
		if(trues==0){
			return "暂无数据";
		}
		
		if(trues==alls){
			return "100%";
		}
		
		return new BigDecimal(((double)(trues))/alls*100).
					setScale(2,BigDecimal.ROUND_HALF_EVEN)+"%";
	}
	public BigDecimal  getThree(int trues,int alls){
		if(trues==0){
			return BigDecimal.valueOf(0);
		}
		return new BigDecimal(((double)(trues))/alls).
					setScale(3,BigDecimal.ROUND_HALF_EVEN);
	}
	public BigDecimal  getThree(float trues,int alls){
		if(trues==0){
			return BigDecimal.valueOf(0);
		}
		return new BigDecimal(((double)(trues))/alls).
					setScale(3,BigDecimal.ROUND_HALF_EVEN);
	}
}
