package goods;

import java.util.*;

interface shuxing
{
	int getcout();
	String getinfo();
	double getmoney();
}

class shui implements shuxing
{
	String name;
	String made;
	String date;
	int deaddate;
	int count;
	double price;
	public shui(String name,String made,String date,int deaddate,int count,double price)
	{
		this.name=name;
		this.made=made;
		this.date=date;
		this.deaddate=deaddate;
		this.price=price;
		setcount(count);
	}
	void setcount(int count)
	{
		if(count<=0)
		{
			System.out.println("error");
			return ;
		}
		this.count=count;
	}
	public double getmoney() {
		return count*price;
	}
	public String getinfo()
	{
		String info;
		info="��Ʒ����"+ name + "\n" + "������:" + made + "\n" + "��������:" + date + "\n" + "������:" + deaddate + "\n" + "����:" + price;
		return info;
	}
	public int getcout()
	{
		return count;
	}
}

class lingshi implements shuxing
{
	String name;
	String made;
	String date;
	int deaddate;
	int count;
	double price;
	public lingshi(String name,String made,String date,int deaddate,int count,double price)
	{
		this.name=name;
		this.made=made;
		this.date=date;
		this.deaddate=deaddate;
		this.price=price;
		setcount(count);
	}
	void setcount(int count)
	{
		if(count<=0)
		{
			System.out.println("error");
			return ;
		}
		this.count=count;
	}
	public double getmoney() {
		return count*price;
	}
	public String getinfo()
	{
		String info;
		info="��Ʒ����"+ name + "\n" + "������:" + made + "\n" + "��������:" + date + "\n" + "������:" + deaddate + "\n" + "����:" + price;
		return info;
	}
	public int getcout()
	{
		return count;
	}
}

class shenghuo implements shuxing
{
	String name;
	String made;
	String date;
	int deaddate;
	int count;
	double price;
	public shenghuo(String name,String made,String date,int deaddate,int count,double price)
	{
		this.name=name;
		this.made=made;
		this.date=date;
		this.deaddate=deaddate;
		this.price=price;
		setcount(count);
	}
	void setcount(int count)
	{
		if(count<=0)
		{
			System.out.println("error");
			return ;
		}
		this.count=count;
	}
	public double getmoney() {
		return count*price;
	}
	public String getinfo()
	{
		String info;
		info="��Ʒ����"+ name + "\n" + "������:" + made + "\n" + "��������:" + date + "\n" + "������:" + deaddate + "\n" + "����:" + price;
		return info;
	}
	public int getcout()
	{
		return count;
	}
}

public class shangpin {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		shuxing[] f =new shuxing[3];
		int shu;
		System.out.println("�����ˮ��");
		shu=s.nextInt();
		f[0]=new shui("ũ��ɽȪ","����Ȼ�İ��˹�","2018.10.10",12,shu,1.5);
		System.out.println(f[0].getinfo());
		System.out.println("buy " + f[0].getcout() + " is " + f[0].getmoney());
		System.out.println("�������ʳ��");
		shu=s.nextInt();
		f[1]=new lingshi("��԰��ũ���","��ɭ�ֵİ��˹�","2018.10.20",15,shu,4.5);
		System.out.println(f[1].getinfo());
		System.out.println("buy " + f[1].getcout() + " is " + f[1].getmoney());
		System.out.println("�����������Ʒ��");
		shu=s.nextInt();
		f[2]=new lingshi("Ʈ��","�󻯹����İ��˹�","2018.10.30",24,shu,24.3);
		System.out.println(f[1].getinfo());
		System.out.println("buy " + f[2].getcout() + " is " + f[2].getmoney());
		// TODO Auto-generated method stub

	}

}
