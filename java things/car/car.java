package car;

interface price
{
	int getprice();
}

class typea implements price
{
	String name;
	int num;
	public typea(String name,int num)
	{
		setname(name);
		setnum(num);
	}
	typea()
	{
		this("no",0);
	}
	void setname(String name)
	{
		if(name == null || name.isEmpty() || (name != "CRV" && name != "ESC" && name != "HRV"))
		{
			System.out.println("jiaoname fail");
			return ;
		}
		this.name=name;
	}
	void setnum(int  num)
	{
		if(num<0)
		{
			System.out.println("jiaonum fail");
			return ;
		}
		this.num=num;
	}
	public int getprice()
	{
		if(name == "CRV") return 600*num;
		else if(name == "ESC") return 500*num;
		else if(name == "HRV") return 400*num;
		else return 0;
	}
}

class typeb implements price
{
	int seat;
	int num;
	public typeb(int seat,int num)
	{
		setseat(seat);
		setnum(num);
	}
	typeb()
	{
		this(16,0);
	}
	void setseat(int seat)
	{
		if(seat<0)
		{
			System.out.println("huoseat fail");
			return ;
		}
		this.seat=seat;
	}
	void setnum(int num) {
		if(num<0)
		{
			System.out.println("huonum fail");
			return ;
		}
		this.num=num;
	}
	public int getprice()
	{
		if(seat<=16) return 800*num;
		else if(seat>16) return 1000*num;
		else  return 0;
	}
}

public class car {
	public static void main(String[] args) {
			price [] f=new price[5];
			f[0]=new typea("CRV",2);
			f[1]=new typea("ESC",1);
			f[2]=new typea("HRV",3);
			f[4]=new typeb(6,1);
			System.out.println("CRV is " + f[0].getprice() + " RMB");
			System.out.println("ESC is " + f[1].getprice() + " RMB");
			System.out.println("HRV is " + f[2].getprice() + " RMB");
			System.out.println("litter than 16 is "+ f[4].getprice() + "RMB");
			int sum=f[0].getprice()+f[1].getprice()+f[2].getprice()+f[4].getprice();
			System.out.println("total is " + sum + " RMB");
	}

}
