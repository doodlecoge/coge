package com.szwx.yht.socket;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

@Component("runMain")
public class RunMain implements Runnable{
	private ServerSocket server=null;
	// Vector 类提供了实现可增长数组的功能，随着更多元素加入其中，数组变的更大。
	public static Vector sManager = new Vector(); // 管理套接字的Vector
	//Random rnd = new Random(); // 创建随机数的发生器

	private static RunMain runMain=null;
	
	private RunMain(){}
	
	public  static RunMain instance(){
		if(null==runMain){
			runMain=new RunMain();
		}
		return runMain;
	}
	
	public void distoryServer(){
		try {
			server.close();
			sManager.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		startServer() ;		
	}

	private void startServer() // 运行服务器
	{
		try {
			server = new ServerSocket(8888);
			System.out.println("服务器套接字已创建成功！");
			while (true) {
				Socket socket = server.accept();
				System.out.println("已经与客户机连接");
//				new KBBCom_Thread(socket).start();
				sManager.add(socket);
				System.out.println("当前客户机连结数：" + sManager.size());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RunMain server = RunMain.instance();
		new Thread(new SecurityXMLServer()).start();
		server.startServer();
	}
	public void init () {
		RunMain server = new RunMain();
		new Thread(new SecurityXMLServer()).start();
		new Thread(server).start();
	}

	class KBBCom_Thread extends Thread // 与客户机进行通信的线程累
	{
		Socket socket; // 套接字引用变量
		private DataInputStream reader; // 套接字输入流
		private DataOutputStream writer;

		KBBCom_Thread(Socket socket) { // 构造函数
			this.socket = socket;
		}

		public void run() {
			try {
				// 获取套接字的输入输出流
				reader = new DataInputStream(socket.getInputStream());
				writer = new DataOutputStream(socket.getOutputStream());
				// 如果收到客户端发来的数据
				while (true) {
					// 向客户机传送0-2之间的整型随机数
					writer.writeUTF(1 + "\n");
					writer.flush();
					System.out.println("来自客户机：");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					sManager.remove(socket); // 删除套接字
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
					System.out.println("客户机离开");
					System.out.println("当前客户机的连接数：" + sManager.size());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}

/**
 * FLex 安全 沙箱
 * @author zyjllz
 *
 */
class SecurityXMLServer implements Runnable {
	private ServerSocket server;
	private BufferedReader reader;
	private BufferedWriter writer;
	private String xml;

	public SecurityXMLServer() {
		xml = "<cross-domain-policy> "
				+ "<allow-access-from domain=\"*\" to-ports=\"*\"/>"
				+ "</cross-domain-policy> ";
		// 启动843端口
		createServerSocket(843);
		new Thread(this).start();
	}

	// 启动服务器
	private void createServerSocket(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("服务监听端口：" + port);
		} catch (IOException e) {
			System.exit(1);
		}
	}

	// 启动服务器线程
	public void run() {
		while (true) {
			Socket client = null;
			try {
				// 接收客户端的连接
				client = server.accept();

				InputStreamReader input = new InputStreamReader(
						client.getInputStream(), "UTF-8");
				reader = new BufferedReader(input);
				OutputStreamWriter output = new OutputStreamWriter(
						client.getOutputStream(), "UTF-8");
				writer = new BufferedWriter(output);

				// 读取客户端发送的数据
				StringBuilder data = new StringBuilder();
				int c = 0;
				while ((c = reader.read()) != -1) {
					if (c != '\0')
						data.append((char) c);
					else
						break;
				}
				String info = data.toString();
				System.out.println("输入的请求: " + info);

				// 接收到客户端的请求之后，将策略文件发送出去
				if (info.indexOf("<policy-file-request/>") >= 0) {
					writer.write(xml + "\0");
					writer.flush();
					System.out
							.println("将安全策略文件发送至: " + client.getInetAddress());
				} else {
					writer.write("请求无法识别\0");
					writer.flush();
					System.out.println("请求无法识别: " + client.getInetAddress());
				}
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
				try {
					// 发现异常关闭连接
					if (client != null) {
						client.close();
						client = null;
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					// 调用垃圾收集方法
					System.gc();
				}
			}
		}
	}

}