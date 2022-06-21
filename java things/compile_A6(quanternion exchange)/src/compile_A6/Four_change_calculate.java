package compile_A6;

import java.util.Stack;

/*
 * Get Qutarnion of non_control code 
 */

public class Four_change_calculate {
	int num;

	String get_change(String s, int number) {
		String vs = "";
		Stack<String> sop = new Stack<String>();
		char[] schar = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			String temp = "";
			int ar_lo = 0;
			temp = temp + schar[i];
			sop.push(temp);
			if (schar[i] == '>' || schar[i] == '<' || schar[i] == '[' || schar[i] == ']' || schar[i] == '?'
					|| schar[i] == '@' || schar[i] == '|' || schar[i] == '&' || schar[i] == '+' || schar[i] == '-'
					|| schar[i] == '*' || schar[i] == '/') {
				String op = sop.pop();// (*,,,)
				if (schar[i] == '|' || schar[i] == '&') {
					op = op + op;
					ar_lo = 1;
				} else if (schar[i] == '[') {
					op = "<=";
					ar_lo = 1;
				} else if (schar[i] == ']') {
					op = ">=";
					ar_lo = 1;
				} else if (schar[i] == '?') {
					op = "!=";
					ar_lo = 1;
				} else if (schar[i] == '@') {
					op = "==";
					ar_lo = 1;
				}
				String ra = sop.pop();// (,,*,)
				String la = sop.pop();// (,*,,)
				temp = temp.substring(0, 0);
				if (ar_lo == 0) {
					temp = temp + 't' + num;
					number++;
				} else {
					temp = temp + "bt" + num;
					number++;
				}
				sop.push(temp);

				String line = "";
				line = line + '(' + op + ',' + la + ',' + ra + ',' + temp + ')';
				vs = vs + line + '`'; // ` is devidetion
				num++;
			}
			if (schar[i] == '!') {
				String op = sop.pop();// (!,,,)
				String la = sop.pop();// (,*,,)
				temp = temp.substring(0, 0);
				temp = temp + "bt" + num;
				number++;
				String line = "";
				line = line + '(' + op + ',' + la + ", ," + temp + ')';
				vs = vs + line + '`';
				sop.push(temp);
				num++;
			}
			if (schar[i] == '=') {
				String op = sop.pop();// (=,,,)
				String la = sop.pop();// (,*,,)
				String re = sop.pop();// (,,,*)
				String line = "";
				line = line + '(' + op + ',' + la + ", ," + re + ')';
				vs = vs + line + '`';
			}
		}
		return vs;
	}

	int number_back() {
		return num;
	}

}
