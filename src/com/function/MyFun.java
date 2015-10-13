package com.function;

import java.awt.*;
import java.util.Vector;
import com.members.*;
import com.tank.*;

public class MyFun {
	
	
	MyTank myTank=null;
	Vector<EnemyTank> eVector=null;
	//ը��
	Vector<Bomb> bombVector=null;
	//��ǽ
	Vector<Wall>tuVector=null;
	//����ǽ
	Vector<Wall>jinshuVector=null;
	//��Ļ�ϲ�����̹����Ŀ
	int enem;
	//�õ���ǰ�Ƿ�Ϊ˫�˶�սģʽ,drawinfo�����仯	
	public int model;
	
	public MyFun(MyTank myTank,Vector<EnemyTank> eVector,Vector<Bomb> bombVector,Vector<Wall>tuVector,Vector<Wall>jinshuVector,int enemyTankSize){
		this.myTank=myTank;
		this.eVector=eVector;
		this.bombVector=bombVector;
		this.tuVector=tuVector;
		this.jinshuVector=jinshuVector;
		this.enem=enemyTankSize;
	}
	
	
	//������ʾ��Ϣ
	public void drawInfo(Graphics g)
	{
		
		drawTank(200, 450, 0, 0, g);
		
		drawTank(300, 450, 0, 1, g);
		g.setFont(MyRules.dazi);
		g.drawString(Record.getEnemTankNum()+"",350,475);
		
		if(model!=2){
			g.setColor(Color.cyan);
			g.setFont(MyRules.dazi);
			g.drawString(Record.getMyTankNum()+"",250,475);
			
			g.setFont(MyRules.xiaozi);
			g.setColor(Color.black);
			g.drawString("�ܹ�����̹�� "+Record.getAllEnemNum(), 560, 100);
			g.drawString("��������̹�� "+Record.getDieEnenNum(), 560, 150);
		}else{
			drawTank(400, 450, 0, 2, g);
			g.setColor(Color.green);
			g.drawString(Record.getMyTankNum()+"",450,475);
		}
		
		
	}
	//��ǽ
	public void drawWall(int x, int y,int type, Graphics g)
	{

		
		//0������ǽ��1�������ǽ
		switch (type) {
		case 0:
			
			g.setColor(Color.yellow);
			g.fill3DRect(x, y, MyRules.wallX, MyRules.wallY, true);
			break;

		case 1:
			g.setColor(Color.lightGray);
			g.fill3DRect(x, y,MyRules.wallX,MyRules.wallY, true);
			break;
		case 2:
			
			break;
		}

	}
	
	//��̹��
	public void drawTank(int x,int y,int direct,int type,Graphics g)
	{
		switch (type) {
		//0��ʾ���1��̹�ˣ�1��ʾ���˵�̹��,2��ʾ���2��̹��
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.red);
			break;
		case 2:
			g.setColor(Color.green);
		}
		
		switch (direct) {
		//0��ʾ�Ϸ���1��ʾ�ҷ�,2��ʾ�·�,3��ʾ��
		case 0:
			//�������
			g.fill3DRect(x, y, 5,30,false);
			//���о���
			g.draw3DRect(x+5, y+5, 10,20,true);
			//���Ҿ���
			g.fill3DRect(x+15, y,5,30,false);
			//����Բ
			g.fillOval(x+5, y+10,10,10);
			//��ֱ��
			g.fillRect(x+10, y,2,15);
			
			break;
		case 1:
			//�������
			g.fill3DRect(x, y, 30,5,false);
			//���о���
			g.draw3DRect(x+5, y+5,20,10,true);
			//���Ҿ���
			g.fill3DRect(x, y+15,30,5,false);
			//����Բ
			g.fillOval(x+10, y+5,10,10);
			//��ֱ��
			g.fillRect(x+15, y+10,15,2);
		
			break;
		case 2:
			//�������
			g.fill3DRect(x, y, 5,30,false);
			//���о���
			g.draw3DRect(x+5, y+5, 10,20,true);
			//���Ҿ���
			g.fill3DRect(x+15, y,5,30,false);
			//����Բ
			g.fillOval(x+5, y+10,10,10);
			//��ֱ��
			g.fillRect(x+10, y+15,2,15);
			
			break;
		case 3:
			//�������
			g.fill3DRect(x, y, 30,5,false);
			//���о���
			g.draw3DRect(x+5, y+5,20,10,true);
			//���Ҿ���
			g.fill3DRect(x, y+15,30,5,false);
			//����Բ
			g.fillOval(x+10, y+5,10,10);
			//��ֱ��
			g.fillRect(x, y+10,15,2);
			
			break;

		}
		
		
	}
	
	
	public void hitEnemyTank()
	{
		//�ж��ҵ��ӵ��Ƿ���е���̹��
		for(int i=0;i<myTank.bu.size();i++)
		{
			//ȡ���ӵ�
			Bullet bu=myTank.bu.get(i);
			//�ж��ӵ�״̬���������������ӵ���Ȼ���й�����
			if(bu.state)
			{
				for(int j=0;j<eVector.size();j++)
				{
					EnemyTank et=eVector.get(j);
					if(et.state)
					{
			
						if(myTank.state){
							hit(et, bu);
						}
						
					}
					
				}
				
			}
			
		}
	}
	
	
	public void hitMyTank()
	{
		for(int i=0;i<eVector.size();i++)
		{
			EnemyTank eTank=eVector.get(i);
			if(eTank.state)
			{
				for(int j=0;j<eTank.enbu.size();j++)
				{
					Bullet bullet=eTank.enbu.get(j);
					if(bullet.state)
					{
						//���Է�ֹ�ҵ�̹�����ˣ���Ȼ���ֱ�ը״��
						if(myTank.state)
						{
							hit(myTank,bullet);
						}
						
					}
				}
			}
		}
	}
	
	//�ж��ҵ�̹���Ƿ����ǽ
	public void myHitWall(MyTank mt, Vector<Wall> wVector)
	{
		if(mt.state)
		{
			for(int i=0;i<mt.bu.size();i++)
			{
				Bullet bullet=mt.bu.get(i);
				if(bullet.state)
				{
					
					//ȡ��ÿһ��ǽ
					for(int k=0;k<wVector.size();k++)
					{
						Wall wall=wVector.get(k);
						if(wall.state)
						{
							//�ж��ӵ��Ƿ����ǽ
							if(bullet.getX()>wall.getX() && bullet.getX()<wall.getX()+10 && bullet.getY()>wall.getY() && bullet.getY()<wall.getY()+13)
							{
								//�ж�ǽ������
								switch (wall.type) {
								
								case 0:
									wall.state=false;
									break;
								case 1:
									break;

								}
								bullet.state=false;
								
							}
						}
						
					}
				}
			}
		}
	}
	
	
	
	//����̹���Ƿ����ǽ
	public void hitWall(Vector<EnemyTank> eTanks,Vector<Wall> wVector)
	{
		//ȡ��ÿһ������̹��
		for(int i=0;i<eTanks.size();i++)
		{
			EnemyTank et=eTanks.get(i);
			if(et.state)
			{
				//ȡ���õ���̹�˵�ÿһ���ӵ�
				for(int j=0;j<et.enbu.size();j++)
				{
					Bullet bullet=et.enbu.get(j);
					if(bullet.state)
					{
						//ȡ��ÿһ��ǽ
						for(int k=0;k<wVector.size();k++)
						{
							Wall wall=wVector.get(k);
							if(wall.state)
							{
								//�ж��ӵ��Ƿ����ǽ
								if(bullet.getX()>wall.getX() && bullet.getX()<wall.getX()+10 && bullet.getY()>wall.getY() && bullet.getY()<wall.getY()+13)
								{
									//�ж�ǽ������
									switch (wall.type) {
									
									case 0:
										wall.state=false;
										break;
									case 1:
										break;

									}
									bullet.state=false;
								}
							}
						}	
					}
				}
			}
		}
	}
	

	public  void hit(Tank t,Bullet bu)
	{
		
		//�ж�̹�˷���
		switch (t.getDirect()) {
		case 0:
		case 2:
			//�ж��Ƿ����
			if(bu.getX()>t.getX()&&bu.getX()<t.getX()+20
				&&bu.getY()>t.getY()&&bu.getY()<t.getY()+30)
			{
				//���к�̹����ʧ���ӵ�����
				//���е����ҵ�̹��
				if(t.type==0)
				{
					Record.reMy();
					
				}else if(t.type==1)
				{
					Record.reEnem();
					Record.upAllEnem();
					Record.upDieEnem();
					
					System.out.println(Record.getAllEnemNum());
					//�����µĵ���̹��
					if(Record.getEnemTankNum()>enem)
						//����3��oneplayer�е�enemyTankSize
					{
			
						//��������̹��
						EnemyTank eTank=new EnemyTank(t.getX(), t.getY());
						eVector.add(eTank);
						//��������̹���߳�
						Thread thread=new Thread(eTank);
						thread.start();
						//���������ӵ��߳�
						Thread thread2=new Thread(eTank.bullet);
						thread2.start();
						//ͬ��eVector����
						eTank.setEnemyTank(eVector);
						Record.upEnemTank();
					}
					
				}
				
				t.state=false;
				bu.state=false;
				Bomb bomb=new Bomb(t.getX(),t.getY());
				bombVector.add(bomb);
			
				
			}
			break;
		case 1:
		case 3:
			if(bu.getX()>t.getX()&&bu.getX()<t.getX()+30
				&&bu.getY()>t.getY()&&bu.getY()<t.getY()+20)
			{
				if(t.type==0)
				{
					Record.reMy();
				}else if(t.type==1)
				{
					Record.reEnem();
					Record.upAllEnem();
					Record.upDieEnem();
				}
				if(Record.getAllEnemNum()>enem)
				{
					//��������̹��
					EnemyTank eTank=new EnemyTank(MyRules.enemTankX,MyRules.enemTankY);
					eVector.add(eTank);
					//��������̹���߳�
					Thread thread=new Thread(eTank);
					thread.start();
					//���������ӵ��߳�
					Thread thread2=new Thread(eTank.bullet);
					thread2.start();
					//ͬ��eVector����
					eTank.setEnemyTank(eVector);
					Record.upEnemTank();
				}
				t.state=false;
				bu.state=false;
				Bomb bomb=new Bomb(t.getX(),t.getY());
				bombVector.add(bomb);
			}
			break;
		}
	}
	
	
	//�ж��ҵ�̹���Ƿ�ײ��ǽ
	public boolean myTankTouchWall(MyTank mt, Vector<Wall> walls)
	{
		boolean b=false;
		
		for(int i=0;i<walls.size();i++)
		{
			Wall wall=walls.get(i);
			if(wall.state)
			{
				if(mt.state)
				{
					//�ж�ǽ���ĸ����Ƿ���̹��������
					//����,���ϣ����£�����
					switch (mt.direct) {
					
					case 0:
						if(wall.x>mt.x && wall.x<mt.x+20 && wall.y+MyRules.wallY>mt.y-mt.speed && wall.y+MyRules.wallY<mt.y+30-mt.speed  
								|| wall.x+MyRules.wallX>mt.x && wall.x+MyRules.wallX<mt.x+20 && wall.y+MyRules.wallY>mt.y-mt.speed && wall.y+MyRules.wallY<mt.y+30-mt.speed)
						{
							b=true;
						}
						break;
					case 1:
						if(wall.x>mt.x+mt.speed && wall.x<mt.x+30+mt.speed && wall.y>mt.y && wall.y<mt.y+20
								||wall.x>mt.x+mt.speed && wall.x<mt.x+30+mt.speed && wall.y+MyRules.wallY>mt.y && wall.y+MyRules.wallY<mt.y+20)
						{
							b=true;
						}
						break;
					case 2:
						if(wall.x>mt.x && wall.x<mt.x+20 && wall.y>mt.y+mt.speed && wall.y<mt.y+30+mt.speed  
								|| wall.x+MyRules.wallX>mt.x && wall.x+MyRules.wallX<mt.x+20 && wall.y>mt.y+mt.speed && wall.y<mt.y+30+mt.speed)
						{
							b=true;
						}
						break;
					case 3:
						if(wall.x+MyRules.wallX>mt.x-mt.speed && wall.x+MyRules.wallX<mt.x+30-mt.speed && wall.y>mt.y && wall.y<mt.y+20
								||wall.x+MyRules.wallX>mt.x-mt.speed && wall.x+MyRules.wallX<mt.x+30-mt.speed && wall.y+MyRules.wallY>mt.y && wall.y+MyRules.wallY<mt.y+20)
						{
							b=true;
						}
						break;
					
							
					}
				}
			}	
		}	
		return b;
	}
	
	
	//�жϵ���̹���Ƿ�ײ��ǽ
	public void enemyTankTouchWall(Vector<EnemyTank> eTanks, Vector<Wall> walls){
		
		boolean b=false;
		Wall wall=null;
		for(int j=0;j<eTanks.size();j++)
		{
			EnemyTank et=eTanks.get(j);
			for(int i=0;i<walls.size();i++)
			{
				wall=walls.get(i);
				if(wall.state)
				{
					if(et.state)
					{
						//�ж�ǽ���ĸ����Ƿ���̹��������
						//����,���ϣ����£�����
						switch (et.direct) {
						
						case 0:
							if(wall.x>et.x && wall.x<et.x+20 && wall.y+MyRules.wallY>et.y-et.speed && wall.y+MyRules.wallY<et.y+30-et.speed  
									|| wall.x+MyRules.wallX>et.x && wall.x+MyRules.wallX<et.x+20 && wall.y+MyRules.wallY>et.y-et.speed && wall.y+MyRules.wallY<et.y+30-et.speed)
							{
								b=true;
							}
							break;
						case 1:
							if(wall.x>et.x+et.speed && wall.x<et.x+30+et.speed && wall.y>et.y && wall.y<et.y+20
									||wall.x>et.x+et.speed && wall.x<et.x+30+et.speed && wall.y+MyRules.wallY>et.y && wall.y+MyRules.wallY<et.y+20)
							{
								b=true;
							}
							break;
						case 2:
							if(wall.x>et.x && wall.x<et.x+20 && wall.y>et.y+et.speed && wall.y<et.y+30+et.speed  
									|| wall.x+MyRules.wallX>et.x && wall.x+MyRules.wallX<et.x+20 && wall.y>et.y+et.speed && wall.y<et.y+30+et.speed)
							{
								b=true;
							}
							break;
						case 3:
							if(wall.x+MyRules.wallX>et.x-et.speed && wall.x+MyRules.wallX<et.x+30-et.speed && wall.y>et.y && wall.y<et.y+20
									||wall.x+MyRules.wallX>et.x-et.speed && wall.x+MyRules.wallX<et.x+30-et.speed && wall.y+MyRules.wallY>et.y && wall.y+MyRules.wallY<et.y+20)
							{
								b=true;
							}
							break;
						
								}//switch
						}//if(et.state)			
					}//if(wall.state)
				}//for(walls.size)
			
			switch (wall.type) {
			case 0:
				et.setTuWall(b);
				break;

			case 1:
				et.setJinshuWall(b);
				break;
			}	
			
			}//for(eTanks.size)
	}
	
		
	
	//��ס�ո�֮����Ϸ��ͣ
	public void stop()
	{
		//��������̹���ٶ�Ϊ0.�����ڱ仯
		if(myTank.state)
		{
			myTank.speed=0;
			for(int i=0;i<myTank.bu.size();i++)
			{
				Bullet bu=myTank.bu.get(i);
				bu.speed=0;
			}
		}
		
		for(int i=0;i<eVector.size();i++)
		{
			EnemyTank et=eVector.get(i);
			et.speed=0;
			et.direct=et.getDirect();
			for(int j=0;j<et.enbu.size();j++)
			{
				Bullet bu=et.enbu.get(j);
				bu.speed=0;
			}
		}
		
		
	}
	
	//�ٴΰ�ס�ո�󣬻ָ���Ϸ
	public void restart()
	{
		if(myTank.state)
		{
			myTank.speed=2;
			for(int i=0;i<myTank.bu.size();i++)
			{
				Bullet bu=myTank.bu.get(i);
				bu.speed=3;
			}
		}
		
		for(int i=0;i<eVector.size();i++)
		{
			EnemyTank et=eVector.get(i);
			et.speed=2;
			et.direct=et.getDirect();
			for(int j=0;j<et.enbu.size();j++)
			{
				Bullet bu=et.enbu.get(j);
				bu.speed=3;
			}
		}
	}
	
	
	
	

}
