package com.members;

public class Bomb
{
	public int x;
	public  int y;
	public int life=9;
	boolean state=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}else {
			state=false;
		}
	}
}
