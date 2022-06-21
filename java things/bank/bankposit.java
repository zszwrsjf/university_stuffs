package bank;

class InsufficientFundsException extends Exception{
	private Acount acount;
	private double dmount;
	InsufficientFundsException(Acount acount,double dmount)
	{
		this.acount=acount;
		this.dmount=dmount;
	}
	public String getinfo() {
		String str="you want to withdraw " + dmount +" in your " + acount.getsum() + " only bank failed!!!";
		return str;
	}
}

class cunqianguoduoyinqiyichang extends Exception{
	private Acount acount;
	private double fmount;
	cunqianguoduoyinqiyichang(Acount acount,double fmount)
	{
		this.acount=acount;
		this.fmount=fmount;
	}
	public String getinfo() {
		String str="you want to deposit " + fmount + " in your " + acount.getsum() + " too much to save in their";
		return str;
	}
}

class Acount{
	double sum=500;
	public void bankdeposit(double fmount) throws cunqianguoduoyinqiyichang{
		if(sum+fmount>2000) throw new cunqianguoduoyinqiyichang(this,fmount);
		sum+=fmount;
		System.out.println("success");
	}
	public void bankposita(double dmount) throws InsufficientFundsException{
		if(sum<dmount) throw new InsufficientFundsException(this,dmount);
		sum-=dmount;
		System.out.println("success");
	} 
	public double getsum()
	{
		return sum;
	}
}

public class bankposit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Acount a=new Acount();
			a.bankdeposit(1501);
			a.bankposita(1000);
		
		}catch(InsufficientFundsException e)
		{
			System.out.println(e.getinfo());
		}
	
}
}
