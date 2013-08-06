package com.szwx.yht.job;

import com.szwx.yht.bean.User;
import com.szwx.yht.common.Config;
import com.szwx.yht.util.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimerTask;

/**
 * 用户错误次数清零任务
 * @author zhangyj
 * @date Mar 22, 2012
 */
public class ResetUserTask extends TimerTask {

	private Logger log= Logger.getLogger(ResetUserTask.class);
	private User user;
	
	public ResetUserTask() {
	}
	public ResetUserTask(User user) {
		this.user = user;
	}

	@Override
	public void run() {
		if(user.getLogErrCount()>=5){
			user.setLogErrCount(0);
			String sql="update YHT_USER set LOG_ERR_COUNT = "+0+" where id = "+user.getId();
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
				log.error("ResetUserTask.run系统异常1："+e1.getMessage());
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error("ResetUserTask.run系统异常2："+e.getMessage());
				}
			}finally{
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					log.error("ResetUserTask.run系统异常3："+e.getMessage());
				}
			}
		}
	}
}
