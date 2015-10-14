package com.tank;

import java.util.Vector;

import com.function.MyRules;
import com.members.*;


public //为了使敌人坦克可以随机走动，因此需要将其设置为一个线程
class EnemyTank extends Tank implements Runnable
{
	boolean isTouchTuWall;
	boolean isTouchJinshuWall;
	//创建子弹向量管理子弹
	public Vector <Bullet> enbu=new Vector<Bullet>();
	//子弹应该在敌人子弹死亡和刚刚创建敌人坦克时创建
	public Bullet bullet=null;
	//创建向量使得该向量一直与MyPanel中的eVector的属性相同
	Vector<EnemyTank> enemyTank=new Vector<EnemyTank>();
	//得到MyPanel的属性
	public void setEnemyTank(Vector<EnemyTank> vector)
	{
		this.enemyTank=vector;
	}
	//得到敌人坦克是否碰撞到墙的属性
	public void setTuWall(boolean b)
	{
		this.isTouchTuWall=b;
	}
	public void setJinshuWall(boolean b)
	{
		this.isTouchJinshuWall=b;
	}
	
	public EnemyTank(int x,int y)
	{
		super(x,y);
		this.setType(1);
		this.setDirect(2);
		//设置子弹
		bullet=new Bullet();
		bullet.setX(this.x+15);
		bullet.setY(this.y+35);
		bullet.setDirect(2);
		enbu.add(bullet);
	}
	
	
	//判断敌人坦克是否相撞
	public boolean isTouchEnemyTank()
	{
		boolean isTouchEnemyTank=false;
		
		for(int i=0;i<enemyTank.size();i++)
		{
			EnemyTank et=enemyTank.get(i);
			switch (this.direct) {
			case 0:
				//该坦克向上
				//判断取出坦克是否为自己
				if(et!=this)
				{
					//取出坦克向上或者向下
					if(et.direct==0||et.direct==2)
					{
						//判断坦克左上点是否进入
						if(x>=et.x && x<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						//判断坦克右上点是否进入
						if(x+20>=et.x && x+20<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						
					}
					
					//取出坦克向左或者向右
					if(et.direct==1||et.direct==3)
					{
						//判断坦克左上点是否进入
						if(x>=et.x && x<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						//判断坦克右上点是否进入
						if(x+20>=et.x && x+20<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						
					}
				}
				
				break;
			case 1:
				//该坦克向右
				//判断取出坦克是否为自己
				if(et!=this)
				{
					//取出坦克向上或者向下
					if(et.direct==0||et.direct==2)
					{
						//判断坦克右上点是否进入
						if(x+30>=et.x && x+30<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						//判断坦克右下点是否进入
						if(x+30>=et.x && x+30<=et.x+20 && y+20>=et.y && y+20<=et.y+30)
							return true;
						
					}
					
					//取出坦克向左或者向右
					if(et.direct==1||et.direct==3)
					{
						//判断坦克右上点是否进入
						if(x+30>=et.x && x+30<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						//判断坦克右下点是否进入
						if(x+30>=et.x && x+30<=et.x+30 && y+20>=et.y && y+20<=et.y+20)
							return true;
						
					}
				}
				break;
				
			case 2:
				//该坦克向下
				//判断取出坦克是否为自己
				if(et!=this)
				{
					//取出坦克向上或者向下
					if(et.direct==0||et.direct==2)
					{
						//判断坦克左下点是否进入
						if(x>=et.x && x<=et.x+20 && y+30>=et.y && y+30<=et.y+30)
							return true;
						//判断坦克右下点是否进入
						if(x+20>=et.x && x+20<=et.x+20 && y+30>=et.y && y+30<=et.y+30)
							return true;
						
					}
					
					//取出坦克向左或者向右
					if(et.direct==1||et.direct==3)
					{
						//判断坦克左下点是否进入
						if(x>=et.x && x<=et.x+30 && y+30>=et.y && y+30<=et.y+20)
							return true;
						//判断坦克右下点是否进入
						if(x+20>=et.x && x+20<=et.x+30 && y+30>=et.y && y+30<=et.y+20)
							return true;
						
					}
				}
				
				break;
			case 3:
				
				//该坦克向左
				//判断取出坦克是否为自己
				if(et!=this)
				{
					//取出坦克向上或者向下
					if(et.direct==0||et.direct==2)
					{
						//判断坦克左上点是否进入
						if(x>=et.x && x<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						//判断坦克左下点是否进入
						if(x>=et.x && x<=et.x+20 && y+20>=et.y && y+20<=et.y+30)
							return true;
						
					}
					
					//取出坦克向左或者向右
					if(et.direct==1||et.direct==3)
					{
						//判断坦克左上点是否进入
						if(x>=et.x && x<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						//判断坦克左下点是否进入
						if(x>=et.x && x<=et.x+30 && y+20>=et.y && y+20<=et.y+20)
							return true;
						
					}
				}
				
				break;
				

			
			}
		}
		
		return isTouchEnemyTank;
		
	}
	
	 
	
	public void run() {
		// TODO Auto-generated method stub
		int time=0;
		while(true)
		{
			
			
			switch (this.direct) {
			case 0:
				
				//设置for循环是为了不出现幽灵坦克
				//使得坦克有足够长的时间走动，能看得清楚
				for(int i=0;i<30;i++)
				{    
					//判断是否到达边界
					if(y>0 && !this.isTouchEnemyTank() && !this.isTouchJinshuWall &&!this.isTouchTuWall){
						y-=speed;
					}
					
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
				break;
			case 1:
				
				for(int i=0;i<30;i++)
				{
					if(x<MyRules.panelX-35 && !this.isTouchEnemyTank() && !this.isTouchJinshuWall &&!this.isTouchTuWall)
					{
						x+=speed;
					}	
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
				break;
			case 2:
			
				for(int i=0;i<30;i++)
				{
					
					if(y<MyRules.panelY-35 && !this.isTouchEnemyTank()&& !this.isTouchJinshuWall &&!this.isTouchTuWall)
					{
						y+=speed;
					}try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 3:
				
				for(int i=0;i<30;i++)
				{
					
					if(x>0 && !this.isTouchEnemyTank()&& !this.isTouchJinshuWall &&!this.isTouchTuWall){
						x-=speed;
					}try {
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				
				}
				break;
				}
			
			//走动之后应该产生一个新方向
			this.direct=(int)(Math.random()*4);
			
			
			time++;
			if(time%2==0)
			{
				//判断敌人坦克是否需要添加子弹
				if(this.state)
				{
					Bullet enBullet=new Bullet();
					//每个坦克每次可以发射炮弹的数目
					if(enbu.size()<5)
					{
						enBullet.setDirect(direct);
						switch (enBullet.direct) {
							case 0:
								enBullet.setX(this.getX()+9);
								enBullet.setY(this.getY()-6);
								enbu.add(enBullet);
								break;
							case 1:
								enBullet.setX(this.getX()+31);
								enBullet.setY(this.getY()+8);
								enbu.add(enBullet);
								break;
							case 2:
								enBullet.setX(this.getX()+8);
								enBullet.setY(this.getY()+31);
								enbu.add(enBullet);
								break;
							case 3:
								enBullet.setX(this.getX()-6);
								enBullet.setY(this.getY()+9);
								enbu.add(enBullet);
								break;
							}
							 Thread thread=new Thread(enBullet);
							 thread.start();
					}
					
				}
				
			}//if(time%2==0)
			
			
			
			//判断坦克是否死亡
			if(this.state==false)
			{
				break;
			}
			
			
		}//whlie
	}//run
}

