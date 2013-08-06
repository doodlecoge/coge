package com.szwx.yht.util.Run;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InExcleThread extends Thread{

	private String cell1;
	private String cell2;
	private String cell3;
	private Connection con;
	
	@Override
	public void run() {
		  String temp=null;
//			if(cell1.endsWith("000")){
//				temp=cell1.substring(0, 6)+ "000000";
//			}
//			if(cell1.endsWith("000000")){
//				temp=cell1.substring(0, 3)+ "000000000";
//			}
//			if(cell1.endsWith("000000000")){
//				temp="000000000000";
//			}
//      
         String sqlSelect="select t.id from YHT_KNOWLEDGE_MOUDLE t  where t.MOUDLE_CODE='"+cell1+"'";
         
//			Connection  con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try {
				
				long moudle=0;
				
//				con=DBConnection.getConnection(Config.dbService);

				con.setAutoCommit(false);
				
				ps=con.prepareStatement(sqlSelect);
				rs=ps.executeQuery();

				if(!rs.next()){
					
 				System.err.println("无moudle:"+cell1+","+cell2+","+cell3);
					
					return;
				}
				
				moudle=rs.getInt(1);
				
				String sql="select * from YHT_KNOWLEDGE_BASE t  where t.CODE= '"+cell1+"' and t.QUESTION='"+cell2+"'"  ;
				ps=con.prepareStatement(sql);
				
				rs=ps.executeQuery();

				if(!rs.next()){
    				String	sqlInsert="insert into YHT_KNOWLEDGE_BASE (ID,MODULE,QUESTION," +
							"ASKWER,ADD_TIME,CODE) values(SEQ_KNOWLEDGEBASE_YHT.nextval,"+
							moudle+",?,?,sysdate,'"+cell1+"')";
				
					
					ps=con.prepareStatement(sqlInsert);
					ps.setString(1,cell2);
					oracle.sql.CLOB newClob = oracle.sql.CLOB.createTemporary(con, false, oracle.sql.CLOB.DURATION_SESSION);  
			        newClob.putString(1,cell3);  
			        ps.setClob(2, newClob);  

					
					int ii=ps.executeUpdate();
					if(ii==1){
						con.commit();
						
//						String sqlUpdate="update YHT_KNOWLEDGE_BASE_TEST t set t.ADD_TIME=sysdate , t.ASKWER=? where t.CODE='"+cell1+"' and t.QUESTION='"+cell2+"'" ;
//
//						ps=con.prepareStatement(sqlUpdate);
//						ByteArrayInputStream byteArray = new ByteArrayInputStream(cell3.getBytes()); 
//						ps.setBinaryStream(1, byteArray);
//						byteArray.close();
//						int iii=ps.executeUpdate();
//						if(iii==1){
//							con.commit();
//						}
					}
					
					
					
				}else{
					String sqlUpdate="update YHT_KNOWLEDGE_BASE t set t.ADD_TIME=sysdate , t.ASKWER=? where t.CODE='"+cell1+"' and t.QUESTION='"+cell2+"'" ;
					ps=con.prepareStatement(sqlUpdate);
					oracle.sql.CLOB newClob = oracle.sql.CLOB.createTemporary(con, false, oracle.sql.CLOB.DURATION_SESSION);  
			        newClob.putString(1,cell3);  
			        ps.setClob(1, newClob);  
					int ii=ps.executeUpdate();
					if(ii==1){
						con.commit();
					}
				}
				}
				
			catch (Exception e1) {
				e1.printStackTrace();
				System.err.println("异常："+cell1+","+cell2+","+cell3);
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}finally{
				try {
					ps.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}

	public String getCell1() {
		return cell1;
	}

	public void setCell1(String cell1) {
		this.cell1 = cell1;
	}

	public String getCell2() {
		return cell2;
	}

	public void setCell2(String cell2) {
		this.cell2 = cell2;
	}

	public String getCell3() {
		return cell3;
	}

	public void setCell3(String cell3) {
		this.cell3 = cell3;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
