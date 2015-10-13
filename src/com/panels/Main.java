/**
 *  * 功能：1.画出自己坦克和敌人坦克
 * 2.坦克可以移动，坦克移动方向要改变
 * 3.坦克按住c键可以发子弹,可以连续发射多颗子弹(创建向量，将子弹放入向量中去),做多发出5颗子弹（设置子弹死亡）
 * 4.击中敌人坦克，敌人坦克消失,产生爆炸效果
 * 5.敌人坦克可以随机移动,敌人坦克可以发射炮弹，击中坦克
 * 6.防止坦克重叠运动
 * 7.闪烁效果完成
 * 8.可以在玩游戏时候保存和退出
 * 9.可以记录玩家成绩
 * 10.增加2中墙，土墙和金属墙
 * 12.暂停功能已改进 
 * 13.死亡后使屏幕不动
 * 14.帮助界面改进
 * 15.增加双人对战
 * 16.游戏声音
 * 
 * 设想
 * 1.增加关卡
 * 2.双人对战各种问题：不能同时响应按下的键值
 * 4.游戏结束时不能跳出JOptionPane.showOptionDialog
 * 5.mytank复活效果不明显
 * 6.setMnemonic不能响应(可能和获得焦点有关系)
 * 7.玩家记录成绩改进
 * 8.javdoc
 * 10.设置坦克数目，金属墙土墙等
 */

/*
 * 出现的问题：
 * 
 * 3.myTank重新复活，显示效果不明显，应该怎么办?
 *   image.wait()这个函数应该怎么用？
 *  
 *   
 * 5.用imageIcon方法得到了图片，编译通过，
 *   能进入到函数中去，但是不显示图片，为什么？
 *   
 * 6.怎么在API中寻找我要的方法，
 *   比如我想让图片延迟显示，panel中内容清空
 *   
 * 7.还是不会看API，通过阅读API来写出代码可能么？
 * 比如dialog的创建，frame，window
 */

package com.panels;
import javax.swing.*;
import com.members.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main extends JFrame implements ActionListener{

	
	OnePlayer panel1=null;
	TwoPlayer panel2=null;
	StartPanel sp=null;
	//定义菜单栏
	JMenuBar jmb=null;
	//游戏
	JMenu jm=null;
	//退出游戏
	JMenuItem jmi2=null;
	//存盘退出游戏
	JMenuItem jmi3=null;
	//继续上一局游戏
	JMenuItem jmi4=null;
	//查看记录
	JMenuItem jmi5=null;
	//帮助
	JMenu help=null;
	//帮助item
	JMenuItem helpItem=null;
	//开始游戏选择模式
	JMenu start=null;
	JMenuItem s1=null;
	JMenuItem s2=null;
	//得到最最初始的的我坦克数量，
	//目的是当游戏结束重新开始游戏要恢复到该数值
	int tempMy=Record.myTankNum;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main=new Main();
	}
	/**
	 * @
	 */
	public Main ()
	{
		//定义菜单栏
		jmb=new JMenuBar();
		jm=new JMenu("游戏");
		start=new JMenu("开始游戏(n)");
		start.setMnemonic(KeyEvent.VK_N);
		
		s1=new JMenuItem("人机对战");
		s1.addActionListener(this);
		s1.setActionCommand("s1");
		s2=new JMenuItem("双人对战");
		s2.addActionListener(this);
		s2.setActionCommand("s2");
		start.add(s1);
		start.add(s2);
		
		jmi2=new JMenuItem("直接退出(e)");
		jmi2.setMnemonic('e');
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		
		jmi3=new JMenuItem("存盘退出(s)");
		jmi3.setMnemonic('s');
		jmi3.addActionListener(this);
		jmi3.setActionCommand("save&exit");
		
		jmi4=new JMenuItem("继续上一局(c)");
		jmi4.setMnemonic('c');
		jmi4.addActionListener(this);
		jmi4.setActionCommand("continue");
		
		jmi5=new JMenuItem("查看记录(r)");
		jmi5.setMnemonic('r');
		jmi5.addActionListener(this);
		jmi5.setActionCommand("record");
		
		help=new JMenu("帮助");
		helpItem=new JMenuItem("帮助(h)");
		helpItem.addActionListener(this);
		helpItem.setActionCommand("help");
		helpItem.setMnemonic('h');
		
		jmb.add(jm);
		jmb.add(help);
		help.add(helpItem);
		jm.add(start);
		jm.add(jmi4);
		jm.addSeparator();
		jm.add(jmi5);
		jm.addSeparator();
		jm.add(jmi3);
		jm.add(jmi2);		
		
		sp=new StartPanel();
		this.add(sp);
		
		Thread thread=new Thread(sp);
		thread.start();
		
		this.setJMenuBar(jmb);
		this.setTitle("坦克大战");
		this.setSize(700,550);
		this.setLocation(200,150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("s1"))
		{
			
			if(panel1!=null)
			{
				
				remove(panel1);
				Record.setDieEnenNum(0);
				//游戏结束后，可以重新开始游戏
				Record.setMyTankNum(tempMy);
			}
			else {
				//恢复原来游戏记录
				Record.openAllEnem();
			}
			panel1=new OnePlayer();
			panel1.state=true;
			Thread mpThread=new Thread(panel1);
			mpThread.start();
			
			this.remove(sp);
			if(panel2!=null){
				this.remove(panel2);
			}
			this.add(panel1);
			this.addKeyListener(panel1);
			this.setVisible(true);
			
			
			
		}
		else if (arg0.getActionCommand().equals("s2")) {
			
			if(panel1!=null){
				this.remove(panel1);
			}else if(panel2!=null){
				this.remove(panel2);
			}
			panel2=new TwoPlayer();
			panel2.state=true;
			Thread mpThread=new Thread(panel2);
			mpThread.start();
			
			this.remove(sp);
			
			this.add(panel2);
			this.addKeyListener(panel2);
			this.setVisible(true);
			
			
		}
		else if (arg0.getActionCommand().equals("exit")) {
			
			//保存游戏
			Record.saveAllEnem();
			//再退出
			System.exit(0);
			
		}else if(arg0.getActionCommand().equals("save&exit")){
			
			//获取敌人坦克信息
			Record.setEn(panel1.eVector);
			//保存和退出
			Record.saveExit();
			
			
			
		}else if(arg0.getActionCommand().equals("continue")){
			//得到上一局游戏的数据
			Record.continueGame();
			
			panel1=new OnePlayer();
			Thread mpThread=new Thread(panel1);
			mpThread.start();
			
			this.remove(sp);
			this.add(panel1);
			
			this.addKeyListener(panel1);
			this.setVisible(true);
		
//			for(int i=0;i<mp.eVector.size();i++)
//			{
//				EnemyTank n=mp.eVector.get(i);
//				System.out.println("enemy"+n.x+","+n.y+","+n.direct);
//			}
			
		}else if(arg0.getActionCommand().equals("help")){
			
			if(panel1!=null){
				panel1.state=false;
				panel1.myfun.stop();
			}
			String s="按住空格键暂停恢复游戏\n每个坦克可以连发5颗子弹\n" +
					"玩家一:\n按住 w 键可以向上运动\n按住 s 键可以向下运动\n按住 a 键可以向左运动\n按住 d 键可以向右运动\n按住 c 键可以发射子弹\n" +
					"玩家二:\n按住 i 键可以向上运动\n按住 k 键可以向下运动\n按住 j 键可以向左运动\n按住 l 键可以向右运动\n按住 n 键可以发射子弹\n";
			
			JOptionPane.showMessageDialog(this, s,
					"帮助",JOptionPane.INFORMATION_MESSAGE);
			
			if(panel1!=null){
				panel1.state=true;
				panel1.myfun.restart();
			}
			
		}
		else if(arg0.getActionCommand().equals("record")){
			
			if(panel1!=null){
				panel1.state=false;
				panel1.myfun.stop();
			}
			//读取游戏记录
			FileReader fr=null;
			BufferedReader br=null;
			int all = 0;
			try {
				fr=new FileReader("D:\\record.txt");
				br=new BufferedReader(fr);
				String s=br.readLine();
//				System.out.print(s);
				if(s==null)
				{
					all=0;
				}else {
					all=Integer.parseInt(s);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					br.close();
					fr.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			JOptionPane.showMessageDialog(this, "一共打死敌人坦克"+all+"辆","游戏记录",JOptionPane.INFORMATION_MESSAGE);
			if(panel1!=null){
				panel1.state=true;
				panel1.myfun.restart();
			}
			
		}
		
		
	}

}

