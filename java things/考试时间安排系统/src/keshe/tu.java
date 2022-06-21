package keshe;

class tu {
	String vex[];
	double vexx[][];
	int kao[][]=new int[100][10];
	int arc[][];
	static int vem=0;
	static int arm=0;
	String [][][]kecheng;
	int flag[][][][];
	int num[];
	int n;
	int color[]=new int [100];
	int fl=0;
	tu(String [][][]kecheng,int num[],int n)
	{
		this.kecheng=kecheng;
		this.num=num;
		this.n=n;
		vex=new String [100];
		vexx=new double [100][2];
		arc=new int[100][100];
		int m=0,lm=0;
		int lian[];
		for(int i=0;i<n;i++)
		{
			lian=new int [num[i]];
			for(int j=0;j<num[i];j++)
			{
				if(kecheng[i][j][6].equals("·ñ")) continue;
				while(m<vem&&!kecheng[i][j][0].equals(vex[m])) {m++;}
				if(m==vem)
				{
					vex[vem]=kecheng[i][j][0];
					vexx[vem][1]=Double.parseDouble(kecheng[i][j][3]);
					vexx[vem][0]=Double.parseDouble(kecheng[i][j][1]);
					lian[lm++]=vem;
					kao[vem][0]=i+1;
					for(int i1=1;i1<10;i1++) kao[vem][i1]=0;
					vem++;
				}
				else 
				{
					lian[lm++]=m;
					if(Integer.parseInt(kecheng[i][j][3])>vexx[m][1]) vexx[m][1]=Integer.parseInt(kecheng[i][j][3]);
					int t=0; 
					while(kao[m][t]!=0) {t++;}
					kao[m][t]=i+1;
				}
				m=0;
			}
			for(int q=0;q<num[i]&&q<lm;q++)
			{
				for(int w=q+1;w<num[i]&&w<lm;w++)
					if(arc[lian[q]][lian[w]]==0&&anpaiok(lian[q],lian[w])) 
						{
							arc[lian[q]][lian[w]]=1;
							arc[lian[w]][lian[q]]=1;
							arm++;
						}
			}
			lm=0;
			}
			lian=null;
	}
	boolean anpaiok(int a,int b)
	{
		if(fan(vexx[a][0])+vexx[a][1]==fan(vexx[b][0])+vexx[b][1])
		return true;
		else return false;
	}
	double fan(double t)
	{
		if(t>=5) 
			return 3;
		else if(t>=3) 
			return 2;
		else  return 1;
	}
	void print()
	{
		for(int j=0;j<vem;j++) {System.out.print(vex[j]); System.out.print('\t');}
		for(int i=0;i<vem;i++)
		{
			for(int j=0;j<vem;j++)
				System.out.print(arc[i][j]);
			System.out.println();
		}
	}
	boolean ok(int k,int c[][])
	{
		int i;
		for(i=1;i<k;i++)
		{
			if(c[k-1][i-1]==1&&color[i]==color[k]) return false;
		}
		return true;
	}
	
	void graph(int n,int m,int c[][])
	{
		int i,k;
		for(i=1;i<=n;i++)
			color[i]=0;
		k=1;
		while(k>=1&&fl==0)
		{
			color[k]+=1;
			while(color[k]<=m) 
				{if(ok(k,c)) break;
			else color[k]+=1;}
			if(color[k]<=m&&k==n)
			{
				fl=1;
			}
			else if(color[k]<=m&&k<n) k+=1;
			else
			{
				color[k]=0;
				k-=1;
			}
		}
	}
}
