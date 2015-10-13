package com.members;
import java.io.*;
import java.util.Vector;
import com.tank.*;

public class Record
{
	//我的坦克数量
	public static int myTankNum=1;
	//敌人的坦克数量
	public static int enemTankNum=5;
	//总共消灭的敌人坦克数量
	public static int allEnemNum=0;
	//本关卡消灭的坦克数量
	public static int dieEnenNum=0;
	
	public static Vector<Node> nodes=new Vector<Node>();
	
	//定义一个敌人坦克向量
	private static Vector<EnemyTank> en=new Vector<EnemyTank>();
	//保存游戏。要记录下总共消灭的敌人坦克数量
	public static void saveAllEnem()
	{
		FileWriter fw=null;
		BufferedWriter bw=null;
	
		try {
			fw=new FileWriter("D:\\record.txt");
			bw=new BufferedWriter(fw);
			bw.write(allEnemNum+"\r\n");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			//先打开哪个文件流后关闭该文件流
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//读取上一局游戏信息
	public static void continueGame()
	{
		//读取上一局游戏allEnemNum
		openAllEnem();
		//读取上一局存活坦克的信息
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader("D:\\record.txt");
			br=new BufferedReader(fr);
			
			String s="";
			s=br.readLine();
			while((s=br.readLine())!=null)
			{
				//System.out.println(s);
				//spilt是分割字符串，将字符串以空格分为3段
				String [] xyz=s.split(" ", 3);
				//将字符数组转为int，交给节点记录
				Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				//将创建好的节点放到向量中管理
				Record.nodes.add(node);
				Node.flag=true;
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	
	//开始游戏时恢复游戏记录
	public static void openAllEnem()
	{
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr=new FileReader("D:\\record.txt");
			br=new BufferedReader(fr);
			String s=br.readLine();
//			System.out.print(s);
			if(s==null)
			{
				allEnemNum=0;
			}else {
				allEnemNum=Integer.parseInt(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	//保存游戏结束时的敌人坦克坐标方向数量
	public static void saveExit()
	{
		
		FileWriter fw=null;
		BufferedWriter bw=null;
		
		//保存消灭敌人坦克总数量
		saveAllEnem();
		//System.out.println(allEnemNum);
		//保存敌人坦克坐标和方向
		try {
			//FileWriter(String FileName,boolean append)
			//参数append为true，则表示对文件进行追加写
			//默认为false，表示直接从文件开始的地方写
			fw=new FileWriter("D:\\record.txt",true);
			bw=new BufferedWriter(fw);
			for (int i = 0; i < en.size(); i++) {
				EnemyTank et=en.get(i);
				if(et.state)
				{
					//System.out.println(et.x+" "+et.y+" "+et.direct+"\r\n");
					bw.write(et.x+" "+et.y+" "+et.direct+"\r\n");
				}
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//退出游戏
		System.exit(0);
		
		
	}
	
	
	public static void reEnem()
	{
		enemTankNum--;
	}
	public static void reMy()
	{
		myTankNum--;
	}
	public static void upAllEnem()
	{
		allEnemNum++;
	}
	public static void upDieEnem()
	{
		dieEnenNum++;
	}
	public static void upEnemTank(){
		enemTankNum++;
	}
	
	public static int getMyTankNum() {
		return myTankNum;
	}
	public static void setMyTankNum(int myTankNum) {
		Record.myTankNum = myTankNum;
	}
	public static int getEnemTankNum() {
		return enemTankNum;
	}
	public static void setEnemTankNum(int enemTankNum) {
		Record.enemTankNum = enemTankNum;
	}
	public static int getAllEnemNum() {
		return allEnemNum;
	}
	public static void setAllEnemNum(int allEnemNum) {
		Record.allEnemNum = allEnemNum;
	}
	public static int getDieEnenNum() {
		return dieEnenNum;
	}
	public static void setDieEnenNum(int dieEnenNum) {
		Record.dieEnenNum = dieEnenNum;
	}

	public static Vector<EnemyTank> getEn() {
		return en;
	}

	public static void setEn(Vector<EnemyTank> en) {
		Record.en = en;
	}
	
	
	
	
	
}

