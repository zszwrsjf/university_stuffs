package keshe;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
	import java.util.Iterator;
	import java.util.List;
	import java.util.Vector;

	public class test {
	    

	    public static void main(String[] args) {
	    	FileInputStream in = null;
			try {
				in = new FileInputStream("C:\\Users\\Administrator\\Documents\\ajavaconvert\\b.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	int data=0;
	    	try {
				while((data=in.read())!=-1){
				System.out.print(Integer.toBinaryString(data));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
	    }
	}
