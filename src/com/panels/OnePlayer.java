package com.panels;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.function.*;
import com.members.*;
import com.tank.*;

public class OnePlayer extends JPanel implements KeyListener,Runnable
{
	
	public MyFun myfun=null;
	
	public static boolean state=true;
	
	//外面定义，构造函数中初始化
	public MyTank myTank=null;
	public Vector<EnemyTank> eVector=null;

	Vector<Bomb> bombVector=null;
	//土墙
	Vector<Wall>tuVector=null;
	//金属墙
	Vector<Wall>jinshuVector=null;
	//屏幕产生的坦克数目
	int enemyTankSize=5;
	
	Image image1=null;
	Image image2=null;
	Image image3=null;
	Image image4=null;
	Image image5=null;
	Image image6=null;
	
	
	public OnePlayer()
	{

		//播放开战声音
		PlayWave pw=new PlayWave("./111.wav");
		pw.start();
		//mytank是在MyPanel中受控制的，而不只是在Demo
		//Demo是JFrame，它只控制MyPanel
		myTank=new MyTank(MyRules.myTankX,MyRules.myTankY);
		
		
		
		//此处不应该是
		//MyTank myTank=new MyTank(30,30);
		//这样后面就不能获得myTank的参数了
		
		
		//设置敌人坦克
		//后面必须加小括号，因为创建的是集合类对象
		//和普通对象的创建相同，但是后面再构造时候需要再次创建
		eVector=new Vector<EnemyTank>();
		
		
		tuVector=new Vector<Wall>();
		jinshuVector=new Vector<Wall>();
		int wallSize=18;
		
		//初始化墙
		for (int j=0; j<3; j++) {
			
			for(int i=0;i<wallSize;i++)
			{
				Wall w=new Wall(35+(MyRules.wallX+1)*i, 300+(MyRules.wallY+1)*j, 0);
				tuVector.add(w);
				Thread wThread=new Thread(w);
				wThread.start();
				
				
				Wall w1=new Wall(250+(MyRules.wallX+1)*i, 100+(MyRules.wallY+1)*j, 1);
				jinshuVector.add(w1);
				Thread wThread1=new Thread(w1);
				wThread1.start();
				
			}
				
			
		}
		
		//首先判断是否是上一局游戏
		if(Node.flag)
		{
			//恢复保存的坦克数据
			for(int i=0;i<Record.nodes.size();i++)
			{
				
				Node n=Record.nodes.get(i);
				EnemyTank et=new EnemyTank(n.x, n.y);
				et.setDirect(n.direct);
				eVector.add(et);
				
				//启动敌人坦克线程
				Thread thread=new Thread(et);
				thread.start();
				//启动敌人子弹线程
				Thread thread2=new Thread(et.bullet);
				thread2.start();
				//同步eVector属性
				et.setEnemyTank(eVector);
				
			}
		}else{
			//开始新一局游戏
			for (int i=0;i<enemyTankSize;i++)
			{
				//创建敌人坦克
				EnemyTank eTank=new EnemyTank((i+1)*100,0);
				eVector.add(eTank);
				//启动敌人坦克线程
				Thread thread=new Thread(eTank);
				thread.start();
				//启动敌人子弹线程
				Thread thread2=new Thread(eTank.bullet);
				thread2.start();
				//同步eVector属性
				eTank.setEnemyTank(eVector);
				
			}
			
		}
		
		
		
		
		bombVector=new Vector<Bomb>();
		
		
		try {
			image1=ImageIO.read(new File("./bomb_1.gif"));
			image2=ImageIO.read(new File("./bomb_2.gif"));
			image3=ImageIO.read(new File("./bomb_3.gif"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		myfun=new MyFun(myTank, eVector, bombVector, tuVector, jinshuVector,enemyTankSize);
		
	}
	
	
	
	
	
	
	public void paint(Graphics g)
	{
		
		super.paint(g);
		//画出提示信息
		myfun.drawInfo(g);
		g.setColor(Color.black);
		g.fillRect(0, 0,MyRules.panelX,MyRules.panelY);
		
		//画出自己的坦克
		if(myTank.state)
		{
			myfun.drawTank(myTank.getX(),myTank.getY(),myTank.getDirect(),myTank.getType(),g);
			
		}else{
			if(Record.getMyTankNum()>0)
			{
				myTank.state=true;
			}else{
				//使界面暂停
				myfun.stop();
				state=!state;
				g.setColor(Color.black);
				g.fillRect(0,0,550,430);
				
				g.setColor(Color.white);	
				
				g.setFont(MyRules.dazi);
				g.drawString("光荣牺牲", 200, 200);
				g.drawString("游戏结束", 250, 250);			
				
			}
			
		}

		
		
	
		//画敌人坦克
		
		for (int i=0;i<eVector.size();i++)
		{
			EnemyTank et=eVector.get(i);
			if(et.state )
			{
				myfun.drawTank(et.getX(),et.getY(),
					et.getDirect(),et.getType(),g);
			}else {
				eVector.remove(et);
			}
			//画出敌人子弹
			for(int j=0;j<et.enbu.size();j++)
			{
				//取出子弹
				Bullet bullet=et.enbu.get(j);
				if(bullet.state)
				{
					g.setColor(Color.red);
					g.fillOval(bullet.getX(), bullet.getY(),3, 3);	
				}else {
					et.enbu.remove(bullet);
				}
			}
		}

		
		
		
		//画出每一颗子弹，应先从向量中取出
		for(int i=0;i<myTank.bu.size();i++)
		{
			Bullet bullet=myTank.bu.get(i);
			
			if(myTank.state){
				
				if (bullet!=null&& bullet.state)
				{
					g.setColor(Color.cyan);
					g.fillOval(bullet.getX(), bullet.getY(),3, 3);	
				}
				if (!bullet.state)
				{
					//不能删除i而要删除bullet的原因是
					//一边用i，一边删i，会产生错误
					myTank.bu.remove(bullet);
				}
			}
			
		}
		

		//画土墙
		for (int i = 0; i < tuVector.size(); i++) {
			
			Wall wall=tuVector.get(i);
			
			if(wall.state)
			{
				myfun.drawWall(wall.getX(), wall.getY(), wall.getType(), g);
				
			}else {
				tuVector.remove(wall);
			}
			
		}
		//画金属墙
		for (int i = 0; i < jinshuVector.size(); i++) {
			
			Wall wall=jinshuVector.get(i);
			
			if(wall.state)
			{
				myfun.drawWall(wall.getX(), wall.getY(), wall.getType(), g);
				
			}else {
				jinshuVector.remove(wall);
			}
			
		}
		
		//画炸弹
		for(int i=0;i<bombVector.size();i++)
		{
			
			Bomb bomb=bombVector.get(i);
			if(bomb.life>6)
			{
				g.drawImage(image1, bomb.x, bomb.y, 30, 30, this);
			}else if (bomb.life>3) {
				g.drawImage(image2, bomb.x, bomb.y, 30, 30, this);
			}else if (bomb.life>0) {
				g.drawImage(image3, bomb.x, bomb.y, 30, 30, this);
			}
			
			bomb.lifeDown();
			
			if(bomb.life==0)
			{
				bombVector.remove(bomb);
			}
		
		}
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
		

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		 
		
		
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			this.myTank.setDirect(0);
			
			if(myTank.y<=MyRules.panelY&&myTank.y>=0 && !myfun.myTankTouchWall(myTank, tuVector)&& !myfun.myTankTouchWall(myTank, jinshuVector))
			{
				this.myTank.moveUp();
					
			}
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_D)
		{
			myTank.setDirect(1);
			if(myTank.x<=MyRules.panelX-30&&myTank.x>=-2 && !myfun.myTankTouchWall(myTank, tuVector)&& !myfun.myTankTouchWall(myTank, jinshuVector))
			{
				myTank.moveRight();
			}
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			myTank.setDirect(2);
			if(myTank.y<=MyRules.panelY-30&&myTank.y>=-2 && !myfun.myTankTouchWall(myTank, tuVector)&& !myfun.myTankTouchWall(myTank, jinshuVector))
			{
				myTank.moveDown();
			}
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			myTank.setDirect(3);
			if(myTank.x<=MyRules.panelX&&myTank.x>=0 && !myfun.myTankTouchWall(myTank, tuVector)&& !myfun.myTankTouchWall(myTank, jinshuVector))
			{
				this.myTank.moveLeft();
			}
		}
		
		
		if (e.getKeyCode()==KeyEvent.VK_C)
		{
			if(myTank.bu.size()<5)
			{
				this.myTank.myShoot();
			}
			
		
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			
			if(state){
				myfun.stop();
			}else{
				myfun.restart();
			}
			state=!state;
			
			
		}
		
		this.repaint();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		int time=0;
		while(true)
		{
			if(state){
				//判断是否击中敌人坦克
				myfun.hitEnemyTank();
				//判断是否被敌人坦克击中
				myfun.hitMyTank();
				//判断是否敌人子弹是否击中土墙
				myfun.hitWall(eVector, tuVector);
				//判断是否敌人子弹是否击中金属墙
				myfun.hitWall(eVector, jinshuVector);
				//判断我的坦克是否击中土墙
				myfun.myHitWall(myTank, tuVector);
				//判断我的坦克是否击中金属墙
				myfun.myHitWall(myTank, jinshuVector);
				//判断敌人坦克是否碰到土墙
				myfun.enemyTankTouchWall(eVector, tuVector);
				//判断敌人坦克是否碰到金属墙
				myfun.enemyTankTouchWall(eVector,jinshuVector);
				
			}
			
//			
			
			
//			time++;
//			if(time%80==0)
//			{
//				reliveMyTank();
//				System.out.println(time);
//			}
			
			
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
				}
			
			
			//System.out.println("myTank"+myTank.x+","+myTank.y+","+myTank.direct);
//			for(int i=0;i<eVector.size();i++)
//			{
//				EnemyTank e=eVector.get(i);
//				System.out.println("haha"+e.x+","+e.y+","+e.direct);
//			}
			
			
//			//设置time是为了使刷新时间变长，这样才可以观察到连续发射多颗子弹
//			time++;
//			//每30*50=1500ms即1.5秒重画一次
//			if(time%30==0)
//			{
//				//判断敌人坦克是否需要添加子弹
//			for(int i=0;i<eVector.size();i++)
//			{
//				EnemyTank et=eVector.get(i);
//				Bullet enBullet=null;
//				//设置一个坦克最多能发射子弹的数目
//				if(et.enbu.size()<2)
//				{
//					enBullet=new Bullet();
//					enBullet.setDirect(et.direct);
//					if(et.state)
//					{
//						 switch (enBullet.direct) {
//						case 0:
//							enBullet.setX(et.getX()+9);
//							enBullet.setY(et.getY()-6);
//							et.enbu.add(enBullet);
//							break;
//						case 1:
//							enBullet.setX(et.getX()+31);
//							enBullet.setY(et.getY()+8);
//							et.enbu.add(enBullet);
//							break;
//						case 2:
//							enBullet.setX(et.getX()+8);
//							enBullet.setY(et.getY()+31);
//							et.enbu.add(enBullet);
//							break;
//						case 3:
//							enBullet.setX(et.getX()-6);
//							enBullet.setY(et.getY()+9);
//							et.enbu.add(enBullet);
//							break;
//						
//						
//						}
//						 
//						 Thread thread=new Thread(enBullet);
//						 thread.start();
//						 
//					}
//		
//				}
//			}
//
//			}
			
			if(state){
				this.repaint();
			}
			
		}
	}



	
}


