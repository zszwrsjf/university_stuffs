package keshe;

class zheng {
	int color[];
	int flag[][][][];
	int week[];
	int kao[][];
	String vex[];
	int vem;
	int jieguo[][];
	zheng(int color[],int flag[][][][],int week[],int kao[][],String vex[],int vem)
	{
		this.color=color;
		this.flag=flag;
		this.week=week;
		this.kao=kao;
		this.vex=vex;
		this.vem=vem;
	}
	void print()
	{
		for(int i=1;i<=vem;i++) {System.out.print(vex[i-1]+" "+week[i-1]+" "+color[i]); for(int j=0;kao[i-1][j]!=0;j++) System.out.print(" "+kao[i-1][j]); System.out.println();}
	}
	void fina()
	{
		int ri[][]= {{2,3},{4,3},{2,2},{3,3},{3,2},{4,2},{0,0}};
		jieguo=new int[vem][2];
		int t=0;
		int count=0;
		int t1 = 0;
		for(int w=1;count!=vem;w++)
		for(int i=0;i<vem;i++)
		{
			if(color[i+1]==w) {
			while(fine(i,t,ri,t1)==false) {t++;}
			jieguo[i][0]=ri[t][0];    jieguo[i][1]=ri[t][1];
			for(t1=0;kao[i][t1]!=0;t1++)
			flag[kao[i][t1]-1][week[i]-9][ri[t][0]][ri[t][1]]=1;
			t=0;
			t1=0;
			count++;	
			}
		}
	}
	boolean fine(int i,int t,int ri[][],int t1)
	{
		boolean pan=true;
		for(t1=0;kao[i][t1]!=0;t1++)
		{
			if(flag[kao[i][t1]-1][week[i]-9][ri[t][0]][ri[t][1]]==1) {pan=false; return pan;}
		}
		return pan;
	}
	String get(int a)
	{
		if(a==1) return "8£º00-10:00";
		else if(a==2) return "10£º00-12:00";
		else if(a==3) return "2£º00-4:00";
		else if(a==4) return "4£º00-6:00";
		else return "7£º00-9:00";
	}
}
