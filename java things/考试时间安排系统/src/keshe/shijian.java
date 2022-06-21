package keshe;

class shijian {
	String[][][] course = new String[10][20][7];
	int[] num = new int[10];
	
	int n;
	shijian(String[][][] course, int[] num,int n) {
		this.course = course;
		this.num = num;
		this.n=n;
	}
	int [][][][] getDate() {
		int[][][][] flag = new int[n][11][5][5];
		int week0,week1,hour,k;
		int[] day = new int[4];
		for(int i = 0 ; i<n ; i++)
			for(int j = 0 ;j < num[i] ;j++) {
				week0 = Integer.parseInt(course[i][j][2]);
				week1 = Integer.parseInt(course[i][j][3]);
				day[0] = Integer.parseInt(course[i][j][4]);
				hour = Integer.parseInt(course[i][j][5]);
				day[1] = day[0] / 100;
				day[2] = (day[0] - day[1]*100) / 10;
				day[3] = (day[0] - day[1]*100 - day[2]*10) % 10;
				for(k=ding(week0); k <= week1 ; k++) {
					if(day[1] != 0) flag[i][k-9][day[1]-1][hour-1] = 1;
					if(day[2] != 0) flag[i][k-9][day[2]-1][hour-1] = 1;
					if(day[3] != 0) flag[i][k-9][day[3]-1][hour-1] = 1;
				}
			}
			return flag;
	}
	void print(int [][][][]flag)
	{
		for(int i=0;i<n;i++)
			{for(int j=0;j<11;j++)	
				{for(int k=0;k<5;k++)
					{for(int q=0;q<5;q++)
					System.out.print(flag[i][j][q][k]);
					System.out.println();
					}
				System.out.println();
				}
					System.out.println('\n');
			}
	}
	int ding(int a)
	{
		if(a<=9) return 9;
		else return a;
	}
}
