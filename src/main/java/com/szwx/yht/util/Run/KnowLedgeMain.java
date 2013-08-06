package com.szwx.yht.util.Run;

import com.szwx.yht.common.Config;
import com.szwx.yht.util.DBConnection;
import com.szwx.yht.util.ThreadPool;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.sql.*;
import java.util.Vector;

public class KnowLedgeMain {
	
    public static void imporKnowExcel(String fileName){   
        try {   
            Workbook book = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = book.getSheet(0);        // 获得第一个工作表对象
            int rows = sheet.getRows();   
               
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());  
            
            for(int i = 0; i<rows; i++) {   
                Cell[] cell = sheet.getRow(i);
                if(cell.length == 0)   
                    continue;   
//                   System.out.println(sheet.getCell(1, i).getContents()+","+sheet.getCell(2, i).getContents());
                
                InExcleThread thread=new InExcleThread();
                thread.setCell1(sheet.getCell(1, i).getContents());
                thread.setCell2(sheet.getCell(2, i).getContents());
                thread.setCell3(sheet.getCell(3, i).getContents());
                Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@172.25.130.240:1521:yht", "yht", "lZIKTj7nZddk7Q"); 
                thread.setCon(con);
                ThreadPool.execute(thread);
                
//                   String temp=null;
//					if(sheet.getCell(1, i).getContents().endsWith("000")){
//						temp=sheet.getCell(1, i).getContents().substring(0, 6)+ "000000";
//					}
//					if(sheet.getCell(1, i).getContents().endsWith("000000")){
//						temp=sheet.getCell(1, i).getContents().substring(0, 3)+ "000000000";
//					}
//					if(sheet.getCell(1, i).getContents().endsWith("000000000")){
//						temp="000000000000";
//					}
//                
//                   String sqlSelect="select t.id from YHT_KNOWLEDGE_MOUDLE t  where t.MOUDLE_CODE='"+temp+"'";
//                   
//       			Connection  con=null;
//       			PreparedStatement ps=null;
//       			ResultSet rs=null;
//       			try {
//       				
//       				long moudle=0;
//       				
//       				con=DBConnection.getConnection(Config.dbService);
//       				con.setAutoCommit(false);
//       				
//       				ps=con.prepareStatement(sqlSelect);
//       				rs=ps.executeQuery();
//
//       				if(!rs.next()){
//       					
//           				v.add(sheet.getCell(1, i).getContents()+","+sheet.getCell(2, i).getContents()+","+sheet.getCell(3, i).getContents());
//       					
//       					continue ;
//       					
//       				}
//       				
//       				moudle=rs.getInt(1);
//       				
//       				String sql="select * from YHT_KNOWLEDGE_BASE t  where t.CODE= '"+sheet.getCell(1, i).getContents()+"' and t.QUESTION='"+sheet.getCell(2, i).getContents()+"'"  ;
//       				ps=con.prepareStatement(sql);
//       				
//       				rs=ps.executeQuery();
//
//       				if(!rs.next()){
//   	       				String	sqlInsert="insert into YHT_KNOWLEDGE_BASE (ID,MODULE,QUESTION," +
//       							"ASKWER,ADD_TIME,CODE) values(SEQ_KNOWLEDGEBASE_YHT.nextval,"+
//       							moudle+",?,?,sysdate,'"+sheet.getCell(1, i).getContents()+"')";
//   					
//       					
//       					ps=con.prepareStatement(sqlInsert);
//       					ps.setString(1,sheet.getCell(2, i).getContents());
//       					ps.setString(2,sheet.getCell(3, i).getContents());
//       					int ii=ps.executeUpdate();
//       					if(ii==1){
//       						con.commit();
//       					}
//       					
//       				}else{
//       					String sqlUpdate="update YHT_KNOWLEDGE_BASE t set t.ADD_TIME=sysdate , t.QUESTION=? , t.ASKWER=? where t.CODE='"+sheet.getCell(1, i).getContents()+"'";
//       					ps=con.prepareStatement(sqlUpdate);
//       					ps.setString(1,sheet.getCell(2, i).getContents());
//       					ps.setString(2,sheet.getCell(3, i).getContents());
//       					int ii=ps.executeUpdate();
//       					if(ii==1){
//       						con.commit();
//       					}
//       				}
//       				}
//       				
//       			catch (Exception e1) {
//       				e1.printStackTrace();
//       				v.add(sheet.getCell(1, i).getContents()+","+sheet.getCell(2, i).getContents()+","+sheet.getCell(3, i).getContents());
//       				try {
//       					con.rollback();
//       				} catch (SQLException e) {
//       					e.printStackTrace();
//       				}
//       			}finally{
//       				try {
//       					ps.close();
//       					con.close();
//       				} catch (SQLException e) {
//       					e.printStackTrace();
//       				}
//       			}
                   
            }   
   
            book.close();   
        }catch(Exception e) {}    
    }  
	
	
    public static Vector imporBasetExcel(String fileName){   
        Vector v = new Vector();   
        try {   
            Workbook book = Workbook.getWorkbook(new File(fileName));
            Sheet sheet = book.getSheet(0);        // 获得第一个工作表对象
            int rows = sheet.getRows();   
               
            for(int i = 0; i<rows; i++) {   
                Cell[] cell = sheet.getRow(i);
                if(cell.length == 0)   
                    continue;   
//                   System.out.println(sheet.getCell(0, i).getContents()+","+sheet.getCell(1, i).getContents());
                String sql="select * from YHT_KNOWLEDGE_MOUDLE t  where t.moudle_code= "+sheet.getCell(0, i).getContents();
       			Connection  con=null;
       			PreparedStatement ps=null;
       			ResultSet rs=null;
       			try {
       				con=DBConnection.getConnection(Config.dbService);
       				con.setAutoCommit(false);
       				ps=con.prepareStatement(sql);
       				
       				rs=ps.executeQuery();

       				if(!rs.next()){
       					String sqlInsert=null;
       					if("000000000000".equals(sheet.getCell(0, i).getContents())){
       						
       						String sqlSelect="select count(0) from YHT_KNOWLEDGE_MOUDLE t  where t.PARENT=1";
       						ps=con.prepareStatement(sqlSelect);
       	       				
       	       				rs=ps.executeQuery(sqlSelect);
       	       				
       						
       						rs.next();
   							sqlInsert="insert into YHT_KNOWLEDGE_MOUDLE (ID,MOUDLE_CODE,MOUDLE_NAME," +
           							"ORDER_NO,PARENT) values(SEQ_KLGMOUDLE_YHT.nextval,'"+
           							sheet.getCell(0, i).getContents()+"','"+sheet.getCell(1, i).getContents()+"',"+rs.getInt(1)+1+",1)";
   						
       						
       					}else {
       						
       						String temp=null;
       						
       						if(sheet.getCell(0, i).getContents().endsWith("000")){
       							temp=sheet.getCell(0, i).getContents().substring(0, 6)+ "000000";
       						}
       						if(sheet.getCell(0, i).getContents().endsWith("000000")){
       							temp=sheet.getCell(0, i).getContents().substring(0, 3)+ "000000000";
       						}
       						if(sheet.getCell(0, i).getContents().endsWith("000000000")){
       							temp="000000000000";
       						}
       						
       						String sqlSelect2="select t.id from YHT_KNOWLEDGE_MOUDLE t  where t.MOUDLE_CODE='"+temp+"'";
       						ps=con.prepareStatement(sqlSelect2);
       						rs=ps.executeQuery(sqlSelect2);
       						
       						String sqlSelect1=null;
       						if(rs.next()){
       							sqlSelect1="select count(0) from YHT_KNOWLEDGE_MOUDLE t  where t.PARENT='"+rs.getInt(1)+"'";
       						}
       						
       						PreparedStatement ps2=con.prepareStatement(sqlSelect1);
       	       				ResultSet rs2=ps2.executeQuery(sqlSelect1);
       	       				
       	       				if(rs2.next()){
       	       					sqlInsert="insert into YHT_KNOWLEDGE_MOUDLE (ID,MOUDLE_CODE,MOUDLE_NAME," +
           							"ORDER_NO,PARENT) values(SEQ_KLGMOUDLE_YHT.nextval,'"+
           							sheet.getCell(0, i).getContents()+"','"+sheet.getCell(1, i).getContents()+"',"
           							+rs2.getInt(1)+1+","+
           							rs.getInt(1)+")";
       	       				}
						}
       					
       					
       					ps=con.prepareStatement(sqlInsert);
       					int ii=ps.executeUpdate(sqlInsert);
       					if(ii==1){
       						con.commit();
       					}
       					
       				}else{
       					String sqlUpdate="update YHT_KNOWLEDGE_MOUDLE t set t.MOUDLE_NAME='"+sheet.getCell(1, i).getContents()+
       							"' where t.MOUDLE_CODE='"+sheet.getCell(0, i).getContents()+"'";
       					ps=con.prepareStatement(sqlUpdate);
       					int ii=ps.executeUpdate(sqlUpdate);
       					if(ii==1){
       						con.commit();
       					}
       				}
       				}
       				
       			catch (Exception e1) {
       				e1.printStackTrace();
       				v.add(sheet.getCell(0, i).getContents()+","+sheet.getCell(1, i).getContents());
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
   
            book.close();   
        }catch(Exception e) {}    
        return v;   
    }  
	
	
	
  public static void main(String [] args){ 
  System.out.println("开始录入。。。。。。。。");
	String baseFile="D:\\12320\\Basetree.xls";
	String knowFile="D:\\12320\\12320know.xls";
  
  
  System.out.println("录入中。。。。。。。。");
  System.out.println("录入moudle中。。。。。。。。");
	Vector v1=imporBasetExcel(baseFile);
  System.out.println("录入知识内容中。。。。。。。。");
	imporKnowExcel(knowFile);
	try {
		Thread.currentThread().join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("录入知识完成。。。。。。。。");
//  Person p0 = new Person("姓名","字","武力","智力","政治","魅力","英雄事迹");   
//  Person p1 = new Person("赵云","子龙","98","84","83","87","单骑救主!!!");   
//  Person p2 = new Person("马超","孟起","98","62","40","88","杀得曹操割须弃袍!!!");   
//  Person p3 = new Person("诸葛亮","孔明","55","100","92","93","死后木偶退兵，锦囊杀魏延!!!");   

} 
	
}

//013002011000,B型嗜血流感杆菌疫苗（HIB）和流感疫苗有什么区别？
//java.sql.SQLException: ORA-01461: 仅能绑定要插入 LONG 列的 LONG 值
//
//at oracle.jdbc.driver.DatabaseError.throwSqlException(DatabaseError.java:112)
//at oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:331)
//at oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:288)
//at oracle.jdbc.driver.T4C8Oall.receive(T4C8Oall.java:743)
//at oracle.jdbc.driver.T4CPreparedStatement.doOall8(T4CPreparedStatement.java:216)
//at oracle.jdbc.driver.T4CPreparedStatement.executeForRows(T4CPreparedStatement.java:955)
//at oracle.jdbc.driver.OracleStatement.doExecuteWithTimeout(OracleStatement.java:1168)
//at oracle.jdbc.driver.OraclePreparedStatement.executeInternal(OraclePreparedStatement.java:3316)
//at oracle.jdbc.driver.OraclePreparedStatement.executeUpdate(OraclePreparedStatement.java:3400)
//at org.apache.commons.dbcp.DelegatingPreparedStatement.executeUpdate(DelegatingPreparedStatement.java:102)
//at com.szwx.yht.util.Run.KnowLedgeMain.imporKnowExcel(KnowLedgeMain.java:83)
//at com.szwx.yht.util.Run.KnowLedgeMain.main(KnowLedgeMain.java:250)
