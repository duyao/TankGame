package com.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.function.MyRules;

//开始面板，使之闪烁就要变为线程
public class StartPanel extends JPanel implements Runnable
{
	int time=0;
	public void paint(Graphics g)
	{
		
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0,0,MyRules.panelX,MyRules.panelY);
		
		//设置闪烁效果
		if(time%2==0)
		{
			//设置字体
			Font font=new Font("华文新魏",Font.BOLD,75);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("坦克大战",100, 230);
			
		}
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			time++;
			try {
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			this.repaint();
			
			
			
		}
		
	}
}

