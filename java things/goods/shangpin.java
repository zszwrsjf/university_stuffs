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
		info="商品名："+ name + "\n" + "生产方:" + made + "\n" + "生产日期:" + date + "\n" + "保质期:" + deaddate + "\n" + "单价:" + price;
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
		info="商品名："+ name + "\n" + "生产方:" + made + "\n" + "生产日期:" + date + "\n" + "保质期:" + deaddate + "\n" + "单价:" + price;
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
		info="商品名："+ name + "\n" + "生产方:" + made + "\n" + "生产日期:" + date + "\n" + "保质期:" + deaddate + "\n" + "单价:" + price;
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
		System.out.println("买多少水：");
		shu=s.nextInt();
		f[0]=new shui("农夫山泉","大自然的搬运工","2018.10.10",12,shu,1.5);
		System.out.println(f[0].getinfo());
		System.out.println("buy " + f[0].getcout() + " is " + f[0].getmoney());
		System.out.println("买多少零食：");
		shu=s.nextInt();
		f[1]=new lingshi("果园老农坚果","大森林的搬运工","2018.10.20",15,shu,4.5);
		System.out.println(f[1].getinfo());
		System.out.println("buy " + f[1].getcout() + " is " + f[1].getmoney());
		System.out.println("买多少生活用品：");
		shu=s.nextInt();
		f[2]=new lingshi("飘柔","大化工厂的搬运工","2018.10.30",24,shu,24.3);
		System.out.println(f[1].getinfo());
		System.out.println("buy " + f[2].getcout() + " is " + f[2].getmoney());
		// TODO Auto-generated method stub

	}

}
