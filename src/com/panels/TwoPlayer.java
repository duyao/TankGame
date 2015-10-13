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


public class TwoPlayer extends JPanel implements KeyListener, Runnable {

	public MyFun myfun = null;
	public MyFun myfun2 = null;
	public static boolean state = true;

	// ���涨�壬���캯���г�ʼ��
	public MyTank myTank = null;
	public MyTank myTank2 = null;
	public Vector<EnemyTank> eVector = null;

	Vector<Bomb> bombVector = null;
	// ��ǽ
	Vector<Wall> tuVector = null;
	// ����ǽ
	Vector<Wall> jinshuVector = null;

	Image image1 = null;
	Image image2 = null;
	Image image3 = null;
	Image image4 = null;
	Image image5 = null;
	Image image6 = null;

	public TwoPlayer() {

		
	
		//���ſ�ս����
		PlayWave pw=new PlayWave("./111.wav");
		pw.start();
		// mytank����MyPanel���ܿ��Ƶģ�����ֻ����Demo
		// Demo��JFrame����ֻ����MyPanel
		myTank = new MyTank(MyRules.myTankX, MyRules.myTankY);
		myTank2 = new MyTank(MyRules.myTankX + 110, MyRules.myTankY, 2);
		
		// �˴���Ӧ����
		// MyTank myTank=new MyTank(30,30);
		// ��������Ͳ��ܻ��myTank�Ĳ�����

		// ���õ���̹��
		// ��������С���ţ���Ϊ�������Ǽ��������
		// ����ͨ����Ĵ�����ͬ�����Ǻ����ٹ���ʱ����Ҫ�ٴδ���
		eVector = new Vector<EnemyTank>();
		int enemyTankSize = 5;

		tuVector = new Vector<Wall>();
		jinshuVector = new Vector<Wall>();
		int wallSize = 18;

		// ��ʼ��ǽ
		for (int j = 0; j < 3; j++) {

			for (int i = 0; i < wallSize; i++) {
				Wall w = new Wall(35 + (MyRules.wallX + 1) * i, 300+ (MyRules.wallY + 1) * j, 0);
				tuVector.add(w);
				Thread wThread = new Thread(w);
				wThread.start();

				Wall w1 = new Wall(250 + (MyRules.wallX + 1) * i, 100+ (MyRules.wallY + 1) * j, 1);
				jinshuVector.add(w1);
				Thread wThread1 = new Thread(w1);
				wThread1.start();

			}

		}

		// ��ʼ��һ����Ϸ
		for (int i = 0; i < enemyTankSize; i++) {
			// ��������̹��
			EnemyTank eTank = new EnemyTank((i + 1) * 100, 0);
			eVector.add(eTank);
			// ��������̹���߳�
			Thread thread = new Thread(eTank);
			thread.start();
			// ���������ӵ��߳�
			Thread thread2 = new Thread(eTank.bullet);
			thread2.start();
			// ͬ��eVector����
			eTank.setEnemyTank(eVector);

		}

		bombVector = new Vector<Bomb>();

		try {
			image1 = ImageIO.read(new File("./bomb_1.gif"));
			image2 = ImageIO.read(new File("./bomb_2.gif"));
			image3 = ImageIO.read(new File("./bomb_3.gif"));

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myfun2 = new MyFun(myTank2, eVector, bombVector, tuVector, jinshuVector,enemyTankSize);
		myfun = new MyFun(myTank, eVector, bombVector, tuVector, jinshuVector,enemyTankSize);
		//����˫��ģʽ
		myfun.model=2;
	}

	public void paint(Graphics g) {

		super.paint(g);
		// ������ʾ��Ϣ
		myfun.drawInfo(g);
		myfun2.drawInfo(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, MyRules.panelX, MyRules.panelY);

		// �����1��̹��
		if (myTank.state) {
			myfun.drawTank(myTank.getX(), myTank.getY(), myTank.getDirect(),
					myTank.getType(), g);

		} else {
			// ʹ������ͣ
			myfun.stop();
			state = !state;
			g.setColor(Color.black);
			g.fillRect(0, 0, MyRules.panelX, MyRules.panelY);

			g.setColor(Color.white);
			g.setFont(MyRules.dazi);
			g.drawString("���2��ʤ",  200, 200);
			g.drawString("��Ϸ����", 250, 250);
		}
		//�����2��̹��
		if (myTank2.state) {
			myfun2.drawTank(myTank2.getX(), myTank2.getY(), myTank2.getDirect(),
					myTank2.getType(), g);

		} else {
			// ʹ������ͣ
			//myfun2.stop();
			state = !state;
			g.setColor(Color.black);
			g.fillRect(0, 0, MyRules.panelX, MyRules.panelY);

			g.setColor(Color.white);
			g.setFont(MyRules.dazi);
			g.drawString("���1��ʤ", 200, 200);
			g.drawString("��Ϸ����", 250, 250);
		}

		// ������̹��

		for (int i = 0; i < eVector.size(); i++) {
			EnemyTank et = eVector.get(i);
			if (et.state) {
				myfun.drawTank(et.getX(), et.getY(), et.getDirect(), et
						.getType(), g);
			} else {
				eVector.remove(et);
			}
			// ���������ӵ�
			for (int j = 0; j < et.enbu.size(); j++) {
				// ȡ���ӵ�
				Bullet bullet = et.enbu.get(j);
				if (bullet.state) {
					g.setColor(Color.red);
					g.fillOval(bullet.getX(), bullet.getY(), 3, 3);
				} else {
					et.enbu.remove(bullet);
				}
			}
		}

		// ����ÿһ���ӵ���Ӧ�ȴ�������ȡ��
		for (int i = 0; i < myTank.bu.size(); i++) {
			Bullet bullet = myTank.bu.get(i);

			if (myTank.state) {

				if (bullet != null && bullet.state) {
					g.setColor(Color.cyan);
					g.fillOval(bullet.getX(), bullet.getY(), 3, 3);
				}
				if (!bullet.state) {
					// ����ɾ��i��Ҫɾ��bullet��ԭ����
					// һ����i��һ��ɾi�����������
					myTank.bu.remove(bullet);
				}
			}

		}
		for (int i = 0; i < myTank2.bu.size(); i++) {
			Bullet bullet = myTank2.bu.get(i);

			if (myTank2.state) {

				if (bullet != null && bullet.state) {
					g.setColor(Color.green);
					g.fillOval(bullet.getX(), bullet.getY(), 3, 3);
				}
				if (!bullet.state) {
					// ����ɾ��i��Ҫɾ��bullet��ԭ����
					// һ����i��һ��ɾi�����������
					myTank2.bu.remove(bullet);
				}
			}

		}

		// ����ǽ
		for (int i = 0; i < tuVector.size(); i++) {

			Wall wall = tuVector.get(i);

			if (wall.state) {
				myfun.drawWall(wall.getX(), wall.getY(), wall.getType(), g);

			} else {
				tuVector.remove(wall);
			}

		}
		// ������ǽ
		for (int i = 0; i < jinshuVector.size(); i++) {

			Wall wall = jinshuVector.get(i);

			if (wall.state) {
				myfun.drawWall(wall.getX(), wall.getY(), wall.getType(), g);

			} else {
				jinshuVector.remove(wall);
			}

		}

		// ��ը��
		for (int i = 0; i < bombVector.size(); i++) {

			Bomb bomb = bombVector.get(i);
			if (bomb.life > 6) {
				g.drawImage(image1, bomb.x, bomb.y, 30, 30, this);
			} else if (bomb.life > 3) {
				g.drawImage(image2, bomb.x, bomb.y, 30, 30, this);
			} else if (bomb.life > 0) {
				g.drawImage(image3, bomb.x, bomb.y, 30, 30, this);
			}

			bomb.lifeDown();

			if (bomb.life == 0) {
				bombVector.remove(bomb);
			}

		}

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			
			this.myTank.setDirect(0);
			if (myTank.y <= MyRules.panelY && myTank.y >= 0
					&& !myfun.myTankTouchWall(myTank, tuVector)
					&& !myfun.myTankTouchWall(myTank, jinshuVector)) {
				this.myTank.moveUp();

			}

		}
		else if(e.getKeyCode() == KeyEvent.VK_I) {
			
			this.myTank2.setDirect(0);
			if (myTank2.y <= MyRules.panelY && myTank2.y >= 0
					&& !myfun2.myTankTouchWall(myTank2, tuVector)
					&& !myfun2.myTankTouchWall(myTank2, jinshuVector)) {
				this.myTank2.moveUp();

			}

		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			myTank.setDirect(1);
			if (myTank.x <= MyRules.panelX - 30 && myTank.x >= -2
					&& !myfun.myTankTouchWall(myTank, tuVector)
					&& !myfun.myTankTouchWall(myTank, jinshuVector)) {
				myTank.moveRight();
			}

		} 
		else if (e.getKeyCode() == KeyEvent.VK_L) {
			myTank2.setDirect(1);
			if (myTank2.x <= MyRules.panelX - 30 && myTank2.x >= -2
					&& !myfun2.myTankTouchWall(myTank2, tuVector)
					&& !myfun2.myTankTouchWall(myTank2, jinshuVector)) {
				myTank2.moveRight();
			}

		}
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			myTank.setDirect(2);
			if (myTank.y <= MyRules.panelY - 30 && myTank.y >= -2
					&& !myfun.myTankTouchWall(myTank, tuVector)
					&& !myfun.myTankTouchWall(myTank, jinshuVector)) {
				myTank.moveDown();
			}

		} 
		else if (e.getKeyCode() == KeyEvent.VK_K) {
			myTank2.setDirect(2);
			if (myTank2.y <= MyRules.panelY - 30 && myTank2.y >= -2
					&& !myfun2.myTankTouchWall(myTank2, tuVector)
					&& !myfun2.myTankTouchWall(myTank2, jinshuVector)) {
				myTank2.moveDown();
			}

		}
		else if (e.getKeyCode() == KeyEvent.VK_A) {
			myTank.setDirect(3);
			if (myTank.x <= MyRules.panelX && myTank.x >= 0
					&& !myfun.myTankTouchWall(myTank, tuVector)
					&& !myfun.myTankTouchWall(myTank, jinshuVector)) {
				this.myTank.moveLeft();
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_J) {
			myTank2.setDirect(3);
			if (myTank2.x <= MyRules.panelX && myTank2.x >= 0
					&& !myfun2.myTankTouchWall(myTank2, tuVector)
					&& !myfun2.myTankTouchWall(myTank2, jinshuVector)) {
				this.myTank2.moveLeft();
			}
		}

		else if (e.getKeyCode() == KeyEvent.VK_C) {
			if (myTank.bu.size() < 5) {
				this.myTank.myShoot();
			}

		}
		else if (e.getKeyCode() == KeyEvent.VK_N) {
			if (myTank2.bu.size() < 5) {
				this.myTank2.myShoot();
			}

		}

		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

			if (state) {
				myfun.stop();
				myfun2.stop();
			} else {
				myfun.restart();
				myfun2.restart();
			}
			state = !state;

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
		int time = 0;
		while (true) {
			if (state) {
				// �ж��Ƿ���е���̹��
				myfun.hitEnemyTank();
				myfun2.hitEnemyTank();
				// �ж��Ƿ񱻵���̹�˻���
				myfun.hitMyTank();
				myfun2.hitMyTank();
				// �ж��Ƿ�����ӵ��Ƿ������ǽ
				myfun.hitWall(eVector, tuVector);
				myfun2.hitWall(eVector, tuVector);
				// �ж��Ƿ�����ӵ��Ƿ���н���ǽ
				myfun.hitWall(eVector, jinshuVector);
				myfun2.hitWall(eVector, jinshuVector);
				// �ж��ҵ�̹���Ƿ������ǽ
				myfun.myHitWall(myTank, tuVector);
				myfun2.myHitWall(myTank2, jinshuVector);
				// �ж��ҵ�̹���Ƿ���н���ǽ
				myfun.myHitWall(myTank, jinshuVector);
				myfun2.myHitWall(myTank2, jinshuVector);
				// �жϵ���̹���Ƿ�������ǽ
				myfun.enemyTankTouchWall(eVector, tuVector);
				// �жϵ���̹���Ƿ���������ǽ
				myfun.enemyTankTouchWall(eVector, jinshuVector);

			}

			if (state) {
				this.repaint();
			}

		}
	}

}
