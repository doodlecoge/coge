package com.szwx.yht.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

@Component("reFursh")
public class Refursh {
	@Autowired
	private RunMain runMain;

	/**
	 * 
	 * @param type  1:电话呼入  2:待定
	 */
	public void fursh(int type) {
		Vector sManager = runMain.sManager;
		Vector temp=new Vector();
		for(int i=0;i<sManager.size();i++){
			Socket socket = (Socket) sManager.get(i); // 套接字引用变量
			DataOutputStream writer = null;
			DataInputStream reader = null; // 套接字输入流

			try {
				// 获取套接字的输入输出流
				reader = new DataInputStream(socket.getInputStream());
				writer = new DataOutputStream(socket.getOutputStream());
				// 如果收到客户端发来的数据
				// 向客户机传送0-2之间的整型随机数
				writer.writeUTF(type + "\n");
				writer.flush();
				System.out.println("来自客户机：");
			} catch (Exception e) {
				try {
					//sManager.remove(socket); // 删除套接字
					temp.add(socket);
					// 关闭输入输出流及套接字
					if (reader != null)
						reader.close();
					if (writer != null)
						writer.close();
					if (socket != null)
						socket.close();
					reader = null;
					writer = null;
					socket = null;
					// 向屏幕输出相关信息
					System.out.println("当前客户机的连接数：" + sManager.size());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		sManager.removeAll(temp);
		System.out.println("当前客户机的连接数========================" + sManager.size());
	}
}
