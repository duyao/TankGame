package com.tank;

import java.util.Vector;

import com.members.Bullet;


public class MyTank extends Tank 
{
	public Vector <Bullet> bu=new Vector<Bullet>();
	Bullet bullet=null;
	
	public MyTank(int x,int y)
	{
		//为什么是super，貌似是将父类的内容传进来
		super(x,y);
		this.setType(0);
	}
	public MyTank(int x,int y,int type)
	{
		//为什么是super，貌似是将父类的内容传进来
		super(x,y);
		this.setType(type);
	}
	
	public void myShoot()
	{
		bullet=new Bullet();
		Thread buThread=new Thread(bullet);
		buThread.start();
		
		bullet.setDirect(this.getDirect());
		//0表示上方，1表示右方,2表示下方,3表示左方
		switch (this.getDirect()) {
			case 0:
				bullet.setX(this.getX()+9);
				bullet.setY(this.getY()-6);
				bu.add(bullet);
				break;
			case 1:
				bullet.setX(this.getX()+31);
				bullet.setY(this.getY()+8);
				bu.add(bullet);
				break;
			case 2:
				bullet.setX(this.getX()+8);
				bullet.setY(this.getY()+31);
				bu.add(bullet);
				break;
			case 3:
				bullet.setX(this.getX()-6);
				bullet.setY(this.getY()+9);
				bu.add(bullet);
				break;

			}

		
	}

	
	
	
}