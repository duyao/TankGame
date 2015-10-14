package com.members;

public class Wall implements Runnable
{
	public int x,y;
	//0Õ¡«Ω£¨1Ω Ù«Ω
	public int type;
	public boolean state=true;
	
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	
	
	public Wall(int x,int y,int type)
	{
		this.x=x;
		this.y=y;
		this.type=type;
		
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(50);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
}

