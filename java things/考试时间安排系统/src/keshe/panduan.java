package keshe;

class panduan
{
	String temp[];
	panduan(String temp[])
	{
		this.temp=temp;
	}
	boolean ok()
	{
		if(temp.length==7)
			{
			if(temp[6].equals("ÊÇ")||temp[6].equals("·ñ"))
			  if(Double.parseDouble(temp[1])>0&&Double.parseDouble(temp[1])<=6)
				if(Integer.parseInt(temp[2])>0&&Integer.parseInt(temp[2])<16)
					if(Integer.parseInt(temp[3])>8&&Integer.parseInt(temp[3])<=16)
						if(Integer.parseInt(temp[5])>=1&&Integer.parseInt(temp[5])<=5)
								return true;
		}
		return false;
	}
}
