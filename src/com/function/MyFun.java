package com.function;

import java.awt.*;
import java.util.Vector;
import com.members.*;
import com.tank.*;

public class MyFun {
	
	
	MyTank myTank=null;
	Vector<EnemyTank> eVector=null;
	//炸弹
	Vector<Bomb> bombVector=null;
	//土墙
	Vector<Wall>tuVector=null;
	//金属墙
	Vector<Wall>jinshuVector=null;
	//屏幕上产生的坦克数目
	int enem;
	//得到当前是否为双人对战模式,drawinfo参数变化	
	public int model;
	
	public MyFun(MyTank myTank,Vector<EnemyTank> eVector,Vector<Bomb> bombVector,Vector<Wall>tuVector,Vector<Wall>jinshuVector,int enemyTankSize){
		this.myTank=myTank;
		this.eVector=eVector;
		this.bombVector=bombVector;
		this.tuVector=tuVector;
		this.jinshuVector=jinshuVector;
		this.enem=enemyTankSize;
	}
	
	
	//画出提示信息
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
			g.drawString("总共消灭坦克 "+Record.getAllEnemNum(), 560, 100);
			g.drawString("本局消灭坦克 "+Record.getDieEnenNum(), 560, 150);
		}else{
			drawTank(400, 450, 0, 2, g);
			g.setColor(Color.green);
			g.drawString(Record.getMyTankNum()+"",450,475);
		}
		
		
	}
	//画墙
	public void drawWall(int x, int y,int type, Graphics g)
	{

		
		//0代表土墙，1代表金属墙
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
	
	//画坦克
	public void drawTank(int x,int y,int direct,int type,Graphics g)
	{
		switch (type) {
		//0表示玩家1的坦克，1表示敌人的坦克,2表示玩家2的坦克
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
		//0表示上方，1表示右方,2表示下方,3表示左方
		case 0:
			//画左矩形
			g.fill3DRect(x, y, 5,30,false);
			//画中矩形
			g.draw3DRect(x+5, y+5, 10,20,true);
			//画右矩形
			g.fill3DRect(x+15, y,5,30,false);
			//画中圆
			g.fillOval(x+5, y+10,10,10);
			//画直线
			g.fillRect(x+10, y,2,15);
			
			break;
		case 1:
			//画左矩形
			g.fill3DRect(x, y, 30,5,false);
			//画中矩形
			g.draw3DRect(x+5, y+5,20,10,true);
			//画右矩形
			g.fill3DRect(x, y+15,30,5,false);
			//画中圆
			g.fillOval(x+10, y+5,10,10);
			//画直线
			g.fillRect(x+15, y+10,15,2);
		
			break;
		case 2:
			//画左矩形
			g.fill3DRect(x, y, 5,30,false);
			//画中矩形
			g.draw3DRect(x+5, y+5, 10,20,true);
			//画右矩形
			g.fill3DRect(x+15, y,5,30,false);
			//画中圆
			g.fillOval(x+5, y+10,10,10);
			//画直线
			g.fillRect(x+10, y+15,2,15);
			
			break;
		case 3:
			//画左矩形
			g.fill3DRect(x, y, 30,5,false);
			//画中矩形
			g.draw3DRect(x+5, y+5,20,10,true);
			//画右矩形
			g.fill3DRect(x, y+15,30,5,false);
			//画中圆
			g.fillOval(x+10, y+5,10,10);
			//画直线
			g.fillRect(x, y+10,15,2);
			
			break;

		}
		
		
	}
	
	
	public void hitEnemyTank()
	{
		//判断我的子弹是否击中敌人坦克
		for(int i=0;i<myTank.bu.size();i++)
		{
			//取出子弹
			Bullet bu=myTank.bu.get(i);
			//判断子弹状态，否则会出现死亡子弹仍然具有攻击性
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
						//可以防止我的坦克死了，仍然出现爆炸状况
						if(myTank.state)
						{
							hit(myTank,bullet);
						}
						
					}
				}
			}
		}
	}
	
	//判断我的坦克是否击中墙
	public void myHitWall(MyTank mt, Vector<Wall> wVector)
	{
		if(mt.state)
		{
			for(int i=0;i<mt.bu.size();i++)
			{
				Bullet bullet=mt.bu.get(i);
				if(bullet.state)
				{
					
					//取出每一块墙
					for(int k=0;k<wVector.size();k++)
					{
						Wall wall=wVector.get(k);
						if(wall.state)
						{
							//判断子弹是否击中墙
							if(bullet.getX()>wall.getX() && bullet.getX()<wall.getX()+10 && bullet.getY()>wall.getY() && bullet.getY()<wall.getY()+13)
							{
								//判断墙的种类
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
	
	
	
	//敌人坦克是否击中墙
	public void hitWall(Vector<EnemyTank> eTanks,Vector<Wall> wVector)
	{
		//取出每一辆敌人坦克
		for(int i=0;i<eTanks.size();i++)
		{
			EnemyTank et=eTanks.get(i);
			if(et.state)
			{
				//取出该敌人坦克的每一颗子弹
				for(int j=0;j<et.enbu.size();j++)
				{
					Bullet bullet=et.enbu.get(j);
					if(bullet.state)
					{
						//取出每一块墙
						for(int k=0;k<wVector.size();k++)
						{
							Wall wall=wVector.get(k);
							if(wall.state)
							{
								//判断子弹是否击中墙
								if(bullet.getX()>wall.getX() && bullet.getX()<wall.getX()+10 && bullet.getY()>wall.getY() && bullet.getY()<wall.getY()+13)
								{
									//判断墙的种类
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
		
		//判断坦克方向
		switch (t.getDirect()) {
		case 0:
		case 2:
			//判断是否击中
			if(bu.getX()>t.getX()&&bu.getX()<t.getX()+20
				&&bu.getY()>t.getY()&&bu.getY()<t.getY()+30)
			{
				//击中后，坦克消失，子弹死亡
				//击中的是我的坦克
				if(t.type==0)
				{
					Record.reMy();
					
				}else if(t.type==1)
				{
					Record.reEnem();
					Record.upAllEnem();
					Record.upDieEnem();
					
					System.out.println(Record.getAllEnemNum());
					//产生新的敌人坦克
					if(Record.getEnemTankNum()>enem)
						//这里3是oneplayer中的enemyTankSize
					{
			
						//创建敌人坦克
						EnemyTank eTank=new EnemyTank(t.getX(), t.getY());
						eVector.add(eTank);
						//启动敌人坦克线程
						Thread thread=new Thread(eTank);
						thread.start();
						//启动敌人子弹线程
						Thread thread2=new Thread(eTank.bullet);
						thread2.start();
						//同步eVector属性
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
					//创建敌人坦克
					EnemyTank eTank=new EnemyTank(MyRules.enemTankX,MyRules.enemTankY);
					eVector.add(eTank);
					//启动敌人坦克线程
					Thread thread=new Thread(eTank);
					thread.start();
					//启动敌人子弹线程
					Thread thread2=new Thread(eTank.bullet);
					thread2.start();
					//同步eVector属性
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
	
	
	//判断我的坦克是否撞到墙
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
					//判断墙的四个点是否在坦克区域内
					//左上,右上，左下，右下
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
	
	
	//判断敌人坦克是否撞到墙
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
						//判断墙的四个点是否在坦克区域内
						//左上,右上，左下，右下
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
	
		
	
	//按住空格之后，游戏暂停
	public void stop()
	{
		//设置所有坦克速度为0.方向不在变化
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
	
	//再次按住空格后，恢复游戏
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
