package com.tank;

import java.util.Vector;

import com.function.MyRules;
import com.members.*;


public //Ϊ��ʹ����̹�˿�������߶��������Ҫ��������Ϊһ���߳�
class EnemyTank extends Tank implements Runnable
{
	boolean isTouchTuWall;
	boolean isTouchJinshuWall;
	//�����ӵ����������ӵ�
	public Vector <Bullet> enbu=new Vector<Bullet>();
	//�ӵ�Ӧ���ڵ����ӵ������͸ոմ�������̹��ʱ����
	public Bullet bullet=null;
	//��������ʹ�ø�����һֱ��MyPanel�е�eVector��������ͬ
	Vector<EnemyTank> enemyTank=new Vector<EnemyTank>();
	//�õ�MyPanel������
	public void setEnemyTank(Vector<EnemyTank> vector)
	{
		this.enemyTank=vector;
	}
	//�õ�����̹���Ƿ���ײ��ǽ������
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
		//�����ӵ�
		bullet=new Bullet();
		bullet.setX(this.x+15);
		bullet.setY(this.y+35);
		bullet.setDirect(2);
		enbu.add(bullet);
	}
	
	
	//�жϵ���̹���Ƿ���ײ
	public boolean isTouchEnemyTank()
	{
		boolean isTouchEnemyTank=false;
		
		for(int i=0;i<enemyTank.size();i++)
		{
			EnemyTank et=enemyTank.get(i);
			switch (this.direct) {
			case 0:
				//��̹������
				//�ж�ȡ��̹���Ƿ�Ϊ�Լ�
				if(et!=this)
				{
					//ȡ��̹�����ϻ�������
					if(et.direct==0||et.direct==2)
					{
						//�ж�̹�����ϵ��Ƿ����
						if(x>=et.x && x<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						//�ж�̹�����ϵ��Ƿ����
						if(x+20>=et.x && x+20<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						
					}
					
					//ȡ��̹�������������
					if(et.direct==1||et.direct==3)
					{
						//�ж�̹�����ϵ��Ƿ����
						if(x>=et.x && x<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						//�ж�̹�����ϵ��Ƿ����
						if(x+20>=et.x && x+20<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						
					}
				}
				
				break;
			case 1:
				//��̹������
				//�ж�ȡ��̹���Ƿ�Ϊ�Լ�
				if(et!=this)
				{
					//ȡ��̹�����ϻ�������
					if(et.direct==0||et.direct==2)
					{
						//�ж�̹�����ϵ��Ƿ����
						if(x+30>=et.x && x+30<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						//�ж�̹�����µ��Ƿ����
						if(x+30>=et.x && x+30<=et.x+20 && y+20>=et.y && y+20<=et.y+30)
							return true;
						
					}
					
					//ȡ��̹�������������
					if(et.direct==1||et.direct==3)
					{
						//�ж�̹�����ϵ��Ƿ����
						if(x+30>=et.x && x+30<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						//�ж�̹�����µ��Ƿ����
						if(x+30>=et.x && x+30<=et.x+30 && y+20>=et.y && y+20<=et.y+20)
							return true;
						
					}
				}
				break;
				
			case 2:
				//��̹������
				//�ж�ȡ��̹���Ƿ�Ϊ�Լ�
				if(et!=this)
				{
					//ȡ��̹�����ϻ�������
					if(et.direct==0||et.direct==2)
					{
						//�ж�̹�����µ��Ƿ����
						if(x>=et.x && x<=et.x+20 && y+30>=et.y && y+30<=et.y+30)
							return true;
						//�ж�̹�����µ��Ƿ����
						if(x+20>=et.x && x+20<=et.x+20 && y+30>=et.y && y+30<=et.y+30)
							return true;
						
					}
					
					//ȡ��̹�������������
					if(et.direct==1||et.direct==3)
					{
						//�ж�̹�����µ��Ƿ����
						if(x>=et.x && x<=et.x+30 && y+30>=et.y && y+30<=et.y+20)
							return true;
						//�ж�̹�����µ��Ƿ����
						if(x+20>=et.x && x+20<=et.x+30 && y+30>=et.y && y+30<=et.y+20)
							return true;
						
					}
				}
				
				break;
			case 3:
				
				//��̹������
				//�ж�ȡ��̹���Ƿ�Ϊ�Լ�
				if(et!=this)
				{
					//ȡ��̹�����ϻ�������
					if(et.direct==0||et.direct==2)
					{
						//�ж�̹�����ϵ��Ƿ����
						if(x>=et.x && x<=et.x+20 && y>=et.y && y<=et.y+30)
							return true;
						//�ж�̹�����µ��Ƿ����
						if(x>=et.x && x<=et.x+20 && y+20>=et.y && y+20<=et.y+30)
							return true;
						
					}
					
					//ȡ��̹�������������
					if(et.direct==1||et.direct==3)
					{
						//�ж�̹�����ϵ��Ƿ����
						if(x>=et.x && x<=et.x+30 && y>=et.y && y<=et.y+20)
							return true;
						//�ж�̹�����µ��Ƿ����
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
				
				//����forѭ����Ϊ�˲���������̹��
				//ʹ��̹�����㹻����ʱ���߶����ܿ������
				for(int i=0;i<30;i++)
				{    
					//�ж��Ƿ񵽴�߽�
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
			
			//�߶�֮��Ӧ�ò���һ���·���
			this.direct=(int)(Math.random()*4);
			
			
			time++;
			if(time%2==0)
			{
				//�жϵ���̹���Ƿ���Ҫ����ӵ�
				if(this.state)
				{
					Bullet enBullet=new Bullet();
					//ÿ��̹��ÿ�ο��Է����ڵ�����Ŀ
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
			
			
			
			//�ж�̹���Ƿ�����
			if(this.state==false)
			{
				break;
			}
			
			
		}//whlie
	}//run
}

