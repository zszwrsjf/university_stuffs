package keshe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import jxl.*;
import jxl.read.biff.BiffException;

public class jiemian implements ActionListener
{
	JFrame f;
	JFileChooser fc;
	JButton b;
	JButton b1,b2,b3,b4,bok,but,pre,nex,pre1,nex1;
	JTable t;
	DefaultTableModel tm;
	JPanel p1,p2,p3,p4,p5,p6;
	JTextArea ta,taa;
	JButton you,zuo,exit;
	JLabel l,ll,lll;
	static int num[];
	static String kecheng[][][];
	static int now;
	static String name[];
	JPanel panel1,panel2,panel3,panel4,panel5,panel6;
	Container contentPane;
	CardLayout cardLayout;
	ImageIcon imageIcon;
	JTextField tf;
	jiemian(String name)
	{
		f=new JFrame(name);
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		panel6=new JPanel();
		contentPane=f.getContentPane();
		cardLayout=new CardLayout();
		contentPane.setLayout(cardLayout);
		Object zi[]= {"�γ�����","�γ�ѧ��","�γ̿�ʼ�ܴ�","�γ̽����ܴ�","�γ��Ͽ�����","�γ��Ͽ��մ�","�Ƿ���Ҫ����"};
		String zi2[][] = {{"","","","","","",""}};
		tm=new DefaultTableModel(zi2,zi);
		t=new JTable(tm);
		t.setEnabled(false);
		tm.setRowCount(10);
		JScrollPane sp=new JScrollPane(t);
		b=new JButton("�����רҵ�γ̱��");
		l=new JLabel("");
		b1=new JButton("����ʱ��");
		b2=new JButton("���ӿγ�");
		b3=new JButton("���ٿγ�");
		b4=new JButton("���Ŀγ���Ϣ");
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
		bok=new JButton("ok");
		bok.setEnabled(false);
		ta=new JTextArea(10,40);
		you=new JButton("��һ��רҵ");
		zuo=new JButton("��һ��רҵ");
		taa=new JTextArea(20,50);
		pre=new JButton("���ػ�ӭ����");
		pre1=new JButton("��һҳ");
		nex1=new JButton("��һҳ");
		exit=new JButton("�˳�");
        but=new JButton("��������ſ����ճ�ϵͳ");
        but.setBounds(110,50,200,100);
		JPanel panel7=new JPanel();
		panel7.setLayout(null);
		panel7.add(but,"button");
		ll=new JLabel("��ӭʹ���ſ����ճ�ϵͳ");
		ll.setBounds(100,20,300,20);
		ll.setFont(new Font("",1,20));
		panel7.add(ll);
		zuo.setEnabled(false);
		you.setEnabled(false);
		
		panel1.setLayout(null);
		panel1.add(b);
		panel1.add(l);
		l.setBorder(BorderFactory.createLineBorder(Color.black));
		panel1.add(b2);
		panel1.add(b3);
		panel1.add(b4);
		b.setBounds(250,30,150,30);
		lll=new JLabel("רҵ��");
		l.setBounds(500,30,110,30);
		lll.setBounds(450,30,50,30);
		b2.setBounds(690,30,120,30);
		b3.setBounds(920,30,120,30);
		b4.setBounds(1130, 30, 120, 30);
		
		panel2.setLayout(new GridLayout(1,2,80,0));
		panel2.add(sp);
		panel2.add(ta);
		panel2.setBounds(50,100,1200,200);
		panel1.add(panel2);
		panel1.add(lll);
		ta.setEditable(false);
		
		zuo.setBounds(50,350,100,30);
		you.setBounds(200,350,100,30);
		panel1.add(zuo);
		panel1.add(you);
		tf=new JTextField("��ʾ�ı���");
		tf.setEditable(false);
		tf.setBounds(400,350,200,30);
		panel1.add(tf);
		
		bok.setBounds(830,350,80,30);
		panel1.add(bok);
		
		b1.setBounds(1000,350,100,30);
		panel1.add(b1);
		
		panel3.add(taa,"");
		panel3.add(exit);
		exit.setBounds(350,400,100,50);
		f.add(panel7,"panel7");
		f.add(panel1,"panel1");
		f.add(panel3,"panel3");
		cardLayout.show(contentPane,"panel7");
		
		imageIcon=new ImageIcon("time.jpg");
		JLabel lbBg = new JLabel(imageIcon);
		lbBg.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
		panel7.add(lbBg, new Integer(Integer.MIN_VALUE));
		JPanel imagePanel=new JPanel();
		imagePanel.setLayout(null);
		imagePanel.setOpaque(false);
		panel7.add(imagePanel);
		
		pre.setBounds(50,30,150,30);
		panel1.add(pre);
		
		
		
		f.setSize(imageIcon.getIconWidth()+10,imageIcon.getIconHeight());
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		bok.addActionListener(this);
		zuo.addActionListener(this);
		you.addActionListener(this);
		exit.addActionListener(this);
		but.addActionListener(this);
		pre.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b)
			{
				tf.setText("");
				fc=new JFileChooser();
				fc.showOpenDialog(f);
				File file = fc.getSelectedFile();
				int i = 0;
		        try{
		            FileInputStream fis = new FileInputStream(file);   
		            jxl.Workbook rwb = Workbook.getWorkbook(fis);   
		            while(i<tm.getRowCount())	tm.removeRow(0);
		            kecheng=new String [10][20][7];
		            num=new int[rwb.getNumberOfSheets()];
		            name=new String [rwb.getNumberOfSheets()];
		            for(int i1=0;i1<rwb.getNumberOfSheets();i1++)
		            {
		            	Sheet rs = rwb.getSheet(i1);   
		                num[i1]=rs.getRows()-1;
		                name[i1]=rs.getName();
		                for (int j = 1; j < rs.getRows(); j++) {   
		                   Cell[] cells = rs.getRow(j);
		                   String con[]=new String [7];
		                   for(int k=0;k<7;k++)   
		                   {
		                	   con[k]=cells[k].getContents();
		                	   kecheng[i1][j-1][k]=con[k];
		                   }
		                }   
		            }
		            for(int q=0;q<num[0];q++)  tm.addRow(kecheng[0][q]);
		            now=0;
		            l.setText(name[now]);
		            fis.close();  
		            b1.setEnabled(true);
		            b2.setEnabled(true);
		            b4.setEnabled(true);
		            zuo.setEnabled(false);
		    		you.setEnabled(true);
		    		zuo.setText("û����");
		    		you.setText(name[1]);
		        }
		        catch(FileNotFoundException e1){
		        	tf.setText("δ�ҵ��ļ�");
		        } catch (IOException e2) {
		        	tf.setText("�������");
				} catch (BiffException e3) {
					tf.setText("��ѡ��һ��excel�ļ�");
				} catch (NullPointerException e4) {
					tf.setText("��ѡ��һ��excel�ļ�");
				}
		        
			}
		else if(e.getSource()==pre) {
			cardLayout.previous(contentPane);
			f.setSize(imageIcon.getIconWidth()+10,imageIcon.getIconHeight());
			f.setLocationRelativeTo(null);
		}
		else if(e.getSource()==but) {
			cardLayout.next(contentPane);
			f.setSize(1300,500);
			f.setLocationRelativeTo(null);
		}
		else if(e.getSource()==exit) {
			f.dispose();
		}
		else if(e.getSource()==b1)
		{
			cardLayout.next(contentPane);
			f.setSize(550,500);
			f.setLocationRelativeTo(null);
			tf.setText("");
			tu graph=new tu(kecheng,num,name.length);
			for(int i=3;graph.fl==0;i++)
			graph.graph(graph.vem, i, graph.arc);
			graph.fl=0;
			anpai an=new anpai(graph.vexx,graph.vem);
			shijian sj=new shijian(kecheng,num,name.length);
		    zheng z=new zheng(graph.color,sj.getDate(),an.findweek(),graph.kao,graph.vex,graph.vem);
		    z.fina();
		    for(int i=0;i<graph.vem;i++)
		    {
		    	taa.append(z.vex[i]+":��"+z.week[i]+"�ܵ�����"+z.jieguo[i][0]+"��"+z.get(z.jieguo[i][1])+"����   ����רҵ��");
		    	for(int j=0;graph.kao[i][j]!=0;j++) taa.append(' '+name[graph.kao[i][j]-1]); 
		    	taa.append("\n");
		    }
		    taa.append('\n'+"��лʹ�ñ�ϵͳ������˳����˳�ϵͳ");
		}
		else if(e.getSource()==zuo)
		{
			tf.setText("");    
			zuo.setEnabled(true);
			    you.setEnabled(true);
				int i=0;
				while(i<tm.getRowCount())	tm.removeRow(0);
				now--;
				 for(int q=0;q<num[now];q++)  tm.addRow(kecheng[now][q]);
				 l.setText(name[now]);
				 if(now==0) {zuo.setEnabled(false);zuo.setText("û����");you.setText(name[1]);}
				 else {zuo.setText(name[now-1]); you.setText(name[now+1]);}
				 
		}else if(e.getSource()==you)
		{
			tf.setText("");	
			zuo.setEnabled(true);
			    you.setEnabled(true);
				int i1=0;
				while(i1<tm.getRowCount())	tm.removeRow(0);
				now++;
				 for(int q=0;q<num[now];q++)  tm.addRow(kecheng[now][q]);
				 l.setText(name[now]);
				 if(now==name.length-1) {you.setEnabled(false);you.setText("û����");zuo.setText(name[name.length-2]);}
				 else {zuo.setText(name[now-1]); you.setText(name[now+1]);}
				 
		}
		else if(e.getActionCommand()=="���ӿγ�")
		{
			tf.setText("");
			ta.setText("�������������ݣ�");
			bok.setEnabled(true);
			b.setEnabled(false);
			b3.setEnabled(false);
			zuo.setEnabled(false);
			you.setEnabled(false);
			b1.setEnabled(false);
			b4.setEnabled(false);
			ta.setEditable(true);
			b2.setText("ȡ��");
		}
		else if(e.getActionCommand()=="ȡ��")
		{
			tf.setText("");
			ta.setText("");
			bok.setEnabled(false);
			b.setEnabled(true);
			b3.setEnabled(true);
			zuo.setEnabled(true);
			you.setEnabled(true);
			b1.setEnabled(true);
			b4.setEnabled(true);
			ta.setEditable(true);
			b2.setText("���ӿγ�");
		}
		else if(e.getSource()==bok)
		{
			try
			{
				String s=ta.getText();
				s=s.substring(8);
				String[] temp;
				String deli = " ";
				temp = s.split(deli);
				if(new panduan(temp).ok()==false)  
					throw new error();
				//1 2 3 9 5 5 ��
				int i=0;
				tm.addRow(temp);
				for(String x : temp){
				kecheng[now][num[now]][i]=temp[i++];
				}
				ta.setText("");
				bok.setEnabled(false);
				num[now]++;
				bok.setEnabled(false);
				b.setEnabled(true);
				b3.setEnabled(true);
				zuo.setEnabled(true);
				you.setEnabled(true);
				b1.setEnabled(true);
				b4.setEnabled(true);
				ta.setEditable(true);
				b2.setText("���ӿγ�");
				tf.setText("");
			}
			catch (ArrayIndexOutOfBoundsException e2)
			{
				tf.setText("���ݹ��಻�Ϸ�");
			}
			catch (error e1)
			{
				tf.setText(e1.getme());
			}
		}
		else if(e.getSource()==b3)
		{
			tf.setText("");
			int[] se=t.getSelectedRows();
			for(int i=0;i<se.length;i++)
			{
				se[i]-=i; 
				tm.removeRow(se[i]);
			}
			for(int i=se.length-1;i>=0;i--)
			{
				for(int j=i;j<num[now];j++)
					kecheng[now][j]=kecheng[now][j+1];
				num[now]--;
			}
		}
		else if(e.getActionCommand()=="���Ŀγ���Ϣ")
		{
			tf.setText("");
			ta.setText("˫���޸ı��ϵ�����,�޸ĺ�س�"+'\n'+"��ѡ���е������");
			b4.setLabel("ȷ��");
			b3.setEnabled(true);
			t.setEnabled(true);
			b.setEnabled(false);
			b1.setEnabled(false);
			b2.setEnabled(false);
			zuo.setEnabled(false);
			you.setEnabled(false);
		}
		else if(e.getActionCommand()=="ȷ��")
		{
			ta.setText("");
			b4.setLabel("���Ŀγ���Ϣ");
			t.setEnabled(false);
			b.setEnabled(true);
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(false);
			zuo.setEnabled(true);
			you.setEnabled(true);
			tf.setText("");
			String ke[][]=new String [num[now]][7] ;
			for(int i=0;i<t.getRowCount();i++)
			{
				for(int j=0;j<t.getColumnCount();j++)
					{	
						ke[i][j]=(String) t.getValueAt(i, j);
					}
			}
			try {	for(int q=0;q<num[now];q++)
					if(new panduan(ke[q]).ok()==false)
						 throw new error();
						for(int i1=0;i1<t.getRowCount();i1++)
						{
							for(int j=0;j<t.getColumnCount()-1;j++)
								{	
									kecheng[now][i1][j]=ke[i1][j];
								}
						}
					}
					 catch (error e1){
						tf.setText(e1.getme());
						int i = 0;
						while(i<tm.getRowCount())	tm.removeRow(0);
						for(int q=0;q<num[now];q++)  tm.addRow(kecheng[now][q]);
					}
			}
		}
	}

