package math;

import java.util.Scanner;

interface tuxing{
	double getarea();
	double getc();
}

 class sijiaoxing implements tuxing{
	double width;
	double height;
	public sijiaoxing (double width,double height) {
		this.height=height;
		this.width=width;
	}
	public double getarea()
	{
		return width*height;
	}
	
	public double getc()
	{
		return 2*width+2*height;
	}
}

 class yuan implements tuxing{
	double r;
	public yuan(double r)
	{
		this.r=r;
	}
	public double getarea()
	{
		return 3.14*r*r;
	}
	public double getc()
	{
		return 2*3.14*r;
	}
}

public class mian {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a,b,c;
		Scanner sc=new Scanner(System.in);
		a=sc.nextDouble();
		b=sc.nextDouble();
		c=sc.nextDouble();
		tuxing[] fs=new tuxing[2];
		fs[0]=new sijiaoxing(a,b);
		fs[1]=new yuan(c);
		System.out.println("sijiaoxing:"+ fs[0].getarea() + " " + fs[0].getc() + "/ yuanxing:" + fs[1].getarea() + " " + fs[1].getc());
	}

}
