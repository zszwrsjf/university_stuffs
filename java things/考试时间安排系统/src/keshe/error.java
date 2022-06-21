package keshe;

class error extends Exception
{
	String a[];
	error(){}
	String getme()
	{
		return "输入数据少或其他不合法";
	}
}
