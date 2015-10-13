package com.tank;

public class Tank
{

	public int x=0;
	public int y=0;
	//0w上,1d右,2s下,3a左
	public int direct=0;
	public int speed=2;
	//0表示玩家1的坦克，1表示敌人的坦克,2表示玩家2的坦克
	public int type;
	public boolean state=true;
	public void moveUp()
	{
		this.y-=this.speed;
	}
	public void moveDown()
	{
		this.y+=this.speed;
	}
	public void moveLeft()
	{
		this.x-=this.speed;
	}
	public void moveRight()
	{
		this.x+=this.speed;
	}
	public  Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
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
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
}