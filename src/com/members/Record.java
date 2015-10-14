package com.members;
import java.io.*;
import java.util.Vector;
import com.tank.*;

public class Record
{
	//�ҵ�̹������
	public static int myTankNum=1;
	//���˵�̹������
	public static int enemTankNum=5;
	//�ܹ�����ĵ���̹������
	public static int allEnemNum=0;
	//���ؿ������̹������
	public static int dieEnenNum=0;
	
	public static Vector<Node> nodes=new Vector<Node>();
	
	//����һ������̹������
	private static Vector<EnemyTank> en=new Vector<EnemyTank>();
	//������Ϸ��Ҫ��¼���ܹ�����ĵ���̹������
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
			//�ȴ��ĸ��ļ�����رո��ļ���
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//��ȡ��һ����Ϸ��Ϣ
	public static void continueGame()
	{
		//��ȡ��һ����ϷallEnemNum
		openAllEnem();
		//��ȡ��һ�ִ��̹�˵���Ϣ
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
				//spilt�Ƿָ��ַ��������ַ����Կո��Ϊ3��
				String [] xyz=s.split(" ", 3);
				//���ַ�����תΪint�������ڵ��¼
				Node node=new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				//�������õĽڵ�ŵ������й���
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
	
	
	//��ʼ��Ϸʱ�ָ���Ϸ��¼
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
	
	//������Ϸ����ʱ�ĵ���̹�����귽������
	public static void saveExit()
	{
		
		FileWriter fw=null;
		BufferedWriter bw=null;
		
		//�����������̹��������
		saveAllEnem();
		//System.out.println(allEnemNum);
		//�������̹������ͷ���
		try {
			//FileWriter(String FileName,boolean append)
			//����appendΪtrue�����ʾ���ļ�����׷��д
			//Ĭ��Ϊfalse����ʾֱ�Ӵ��ļ���ʼ�ĵط�д
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
		
		//�˳���Ϸ
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

