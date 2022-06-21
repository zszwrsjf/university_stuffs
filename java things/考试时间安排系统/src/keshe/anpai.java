package keshe;

class anpai
{
	double [][]a;
	int week[];
	int vem;
	anpai(double [][]a,int vem)
	{
		this.a=a;
		this.vem=vem;
	}
	int[] findweek()
	{
		week=new int[vem];
		for(int i=0;i<vem;i++)
		{
				if(a[i][0]>=5) 
					week[i]=(int) (3+a[i][1]);
				else if(a[i][0]>=3) 
					week[i]=(int) (2+a[i][1]);
				else  week[i]=(int) (1+a[i][1]);
		}
		return week;
	}
}

