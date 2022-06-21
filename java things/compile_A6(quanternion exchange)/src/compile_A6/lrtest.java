package compile_A6;

/*lr form
 *STATE	        ACTION	                GOTO
      |a	；	i	e	w   {	}	#	S	s
	0 |	S3		S5		S9	S11			1	14
	1 |	S3		S5		S9	S11		acc		2
	2 |	r1	r1	r1	r1	r1	r1	r1	r1		
	3 |		S4								
	4 |	r3	r3	r3	r3	r3	r3	r3	r3		
	5 |	S3		S5		S9	S11				6
	6 |	r4	r4	r4	S7	r4	r4	r4	r4		
	7 |	S3		S5		S9	S11				8
	8 |	r5	r5	r5	r5	r5	r5	r5	r5		
	9 |	S3		S5		S9	S11				10
	10|	r6	r6	r6	r6	r6	r6	r6	r6		
	11|	S3		S5		S9	S11			12	14
	12|	S3		S5		S9	S11	S13			2
	13|	r7	r7	r7	r7	r7	r7	r7	r7		
	14| r2  r2  r2  r2  r2  r2  r2  r2
 */

/*
 * parsing using lr analyze
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

class lrtest {
	int[][] lraction = new int[15][8];
	int[][] lrgoto = new int[15][2];
	char[] grammar_left = new char[7];
	String[] grammar_right = new String[7];
	int[] back = new int[7];
	int t = 1;
	Map jumpmap;

	Vector<String> lrtest(Vector<Character> input, Vector<String> s) throws IOException {
		InputStreamReader read = new InputStreamReader(new FileInputStream("lrform.txt"));
		@SuppressWarnings("resource")
		BufferedReader in = new BufferedReader(read);
		String line = null;
		String[] text = null;
		int x = 0;
		int y = 0;
		while ((line = in.readLine()) != null) {
			text = line.split(",");
			while (x < 10) {
				if (x < 8) {
					lraction[y][x] = Integer.parseInt(text[x]);
				} else {
					lrgoto[y][x - 8] = Integer.parseInt(text[x]);
				}
				x++;
			}
			x = 0;
			y++;
		}

		// grammar_left
		grammar_left[0] = 'S';
		grammar_left[1] = 'S';
		grammar_left[2] = 's';
		grammar_left[3] = 's';
		grammar_left[4] = 's';
		grammar_left[5] = 's';
		grammar_left[6] = 's';
		// grammar_right

		grammar_right[0] = "Ss";
		grammar_right[1] = "s";
		grammar_right[2] = "a;";
		grammar_right[3] = "is";
		grammar_right[4] = "ises";
		grammar_right[5] = "ws";
		grammar_right[6] = "{S}";

		// pop times
		back[0] = 2;
		back[1] = 1;
		back[2] = 2;
		back[3] = 2;
		back[4] = 4;
		back[5] = 2;
		back[6] = 3;

		Stack status = new Stack();
		Stack instr = new Stack();
		Stack<String> infinstr = new Stack<String>();
		status.push(0);
		instr.push('#');

		Enumeration<String> element = s.elements();
		String snow = "";

		Enumeration elementinput = input.elements();
		boolean jump = true;

		elementinput.nextElement();
		char cnow = (char) elementinput.nextElement();

		while (jump) {
//			System.out.println(cnow);

			Prior_and_find_num p = new Prior_and_find_num();
			int action = lraction[(int) status.peek()][p.cnownum(cnow)];
			if (action == -1)
				break;

			if (action == 0) {// error
				System.out.println("error");
				return null;
			}

			else if (action / 100 == 1) {// S
				action -= 100;
				status.push(action);
				instr.push(cnow);
				cnow = (char) elementinput.nextElement();
				if (element.hasMoreElements())
					snow = element.nextElement();
				if (snow.length() > 0)
					infinstr.push(snow);
			}

			else if (action / 100 == 2) {// r
				action -= 200;
				int backnum = back[action - 1];
				while (backnum > 0) {
					backnum--;
					status.pop();
					instr.pop();
				}
				instr.push(grammar_left[action - 1]);

				int nowstatus = (int) status.peek();
				if ((char) instr.peek() == 'S')
					status.push(lrgoto[nowstatus][0]);

				else if ((char) instr.peek() == 's')
					status.push(lrgoto[nowstatus][1]);

				switch (action) {
				case 1: {//S->s
					String s1 = infinstr.pop();
					String s2 = infinstr.pop();
					infinstr.push(s2 + s1);
					break;
				}
				case 4: {//if
					String s1 = infinstr.pop();
					String s2 = infinstr.pop();
					String finbool = "";
					for (int i = s2.length() - 1; s2.charAt(i) != ','; i--) {
						if (s2.charAt(i) != ')' && s2.charAt(i) != '`')
							finbool = s2.charAt(i) + finbool;
					}
					String s3 = 	s2 
									+ "(j!, " + finbool + ", ,jump" + t + ")`" 
									+ s1 
									+ "[j" + t + "]`";
					infinstr.push(s3);
					t++;
					break;
				}
				case 5: {//if-else
					String s1 = infinstr.pop();
					String s2 = infinstr.pop();
					String s3 = infinstr.pop();
					String finbool = "";
					for (int i = s3.length() - 1; s3.charAt(i) != ','; i--) {
						if (s3.charAt(i) != ')' && s3.charAt(i) != '`')
							finbool = s3.charAt(i) + finbool;
					}
					String s4 = 	s3 
									+ "(j!, " + finbool + ", ,jump" + t + ")`" 
									+ s2 
									+ "(j, , ,jump" + t + 1 + ")`" 
									+ "[j" + t + "]`" 
									+ s1 
									+ "[j" + t + 1 + "]`";
					infinstr.push(s4);
					t += 2;
					break;
				}
				case 6: {//while

					String s1 = infinstr.pop();
					String s2 = infinstr.pop();
					String finbool = "";
					for (int i = s2.length() - 1; s2.charAt(i) != ','; i--) {
						if (s2.charAt(i) != ')' && s2.charAt(i) != '`')
							finbool = s2.charAt(i) + finbool;
					}
					String s3 = 	s2 
									+ "(j!, " + finbool + ", ,jump" + t + ")`" 
									+ "[j" + t + 1 + "]`" 
									+ s1 
									+ s2 
									+ "(j, "+ finbool + ", ,jump" + t + 1 + ")`" 
									+ "[j" + t + "]`";
					infinstr.push(s3);
					t += 2;
					break;
				}
				}
			}

		}
		String finstr = infinstr.pop();

		jumpmap = new HashMap();
		t = 101;
		String[] fin_4 = finstr.split("`");
		Vector<String> finshow = new Vector<String>();
		s.clear();

		for (int i = 0; i < fin_4.length; i++) {
			if (fin_4[i].charAt(0) == '(') {
				s.addElement(t++ + ": " + fin_4[i]);
			} else if (fin_4[i].charAt(0) == '[') {
				int jumpnum = Integer.parseInt(fin_4[i].substring(2, fin_4[i].length() - 1));
				jumpmap.put(jumpnum, t);
			}
		}
		s.addElement(t++ + ": (EXIT)");
		element = s.elements();
		while (element.hasMoreElements()) {
			String jumpfix = element.nextElement();
			if (jumpfix.indexOf("jump") != -1) {
				int num;
				char[] charjumpfix = jumpfix.toCharArray();
				String strnum = "";
				for (int i = charjumpfix.length - 1; charjumpfix[i] != 'p'; i--) {
					if (charjumpfix[i] != ')')
						strnum = charjumpfix[i] + strnum;
				}
				num = Integer.parseInt(strnum);
				num = (int) jumpmap.get(num);
				String temp = "" + num;
				finshow.addElement(jumpfix.replaceAll("jump" + strnum, temp));
			} else
				finshow.addElement(jumpfix);
		}

		return finshow;
	}

}
