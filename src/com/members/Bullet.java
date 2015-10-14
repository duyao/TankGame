package com.members;

import java.util.Vector;

import com.function.MyRules;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Bullet implements Runnable
{
	public int direct;
	public int x;
	public int y;
	public boolean state=true;
	public int speed=3;
	

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	
	//ÅÐ¶ÏÊÇ·ñÅöµ½Ç½
	public boolean isTouchWall()
	{
		boolean b=false;
		
		//if(this.x)
		
		
		
		return b;
	}
	
	
	
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			switch (this.getDirect()) {
			case 0:
				this.setY(this.getY()-this.speed);
				break;	
			case 1:
				this.setX(this.getX()+this.speed);
				break;
			case 2:
				this.setY(this.getY()+this.speed);
				break;
			case 3:
				this.setX(this.getX()-this.speed);
				break;
			}
		
			if(this.getX()>MyRules.panelX|| this.getX()<0 
					|| this.getY()>MyRules.panelY || this.getY()<0)
			{
				this.state=false;
				break;
			}
			
		}
		
	}
	
}

