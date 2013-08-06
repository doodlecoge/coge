package com.szwx.yht.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;

import com.szwx.yht.common.Config;
import com.szwx.yht.util.DBConnection;

public class PhoneResultJob {
	
	private Logger log= Logger.getLogger(PhoneResultJob.class);

	public void execJob(){
		
		Timer timer=new Timer();
		
		Date date=new Date();
		
		date.setHours(23);
		date.setMinutes(59);
		
		timer.schedule(new ResultPhoneTask(), date, 1000*60*60*24);
		System.out.println("重置软电话状态定时器启动！（每天23：59）");
	}
	

	private class ResultPhoneTask extends java.util.TimerTask{

		@Override
		public void run() {
			String sql="update YHT_SOFT_PHONE set IS_LOGIN = 0 , IS_CONNECT= 0 where  IS_LOGIN <> 0";
			Connection  con=null;
			PreparedStatement ps=null;
			try {
				con= DBConnection.getConnection(Config.dbService);
				con.setAutoCommit(false);
				ps=con.prepareStatement(sql);
				int i=ps.executeUpdate(sql);
					if(i==1){
						con.commit();
					}
				}
			catch (Exception e1) {
				e1.printStackTrace();
				log.error("ResultPhoneTask.run系统异常1："+e1.getMessage());
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error("ResultPhoneTask.run系统异常2："+e.getMessage());
				}
			}finally{
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error("ResultPhoneTask.run系统异常3："+e.getMessage());
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		
		date.setHours(23);
		date.setMinutes(59);
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		
	}
}
