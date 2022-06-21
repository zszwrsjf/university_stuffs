package compile_A6;

import java.util.Stack;
import java.util.Vector;

/*
 * Suffix code can sort the priority of operator.
 * The Qutarnion is also ranged by the priority of operator.
 */

public class reverse_poland {

	String get_reverse(String s) {
		char[] schar = s.toCharArray();
		char c;
		Stack sa = new Stack();// letter stack
		Stack sop = new Stack();// operator stack
		String ns = new String();// new string
		ns = "";
		Prior_and_find_num p = new Prior_and_find_num();// get prior
		int flag = 0; // first operator
		for (int i = 0; i < schar.length; i++) {
			if (schar[i] != '(' && schar[i] != ')' && schar[i] != '>' && schar[i] != '<' && schar[i] != '!'
					&& schar[i] != '|' && schar[i] != '&' && schar[i] != '+' && schar[i] != '-' && schar[i] != '*'
					&& schar[i] != '/' && schar[i] != '=') {
				sa.push(schar[i]);// letter push stack
				ns = ns + schar[i];
			} else {//operator function
				if (schar[i] == '(' || schar[i] == ')') {
					if (schar[i] == '(')
						sop.push('(');
					else {
						while (!((char) sop.peek() == '(')) {
							ns = ns + sop.pop();
						}
						sop.pop();
					}
				} else {
					if (schar[i] == '&' || schar[i] == '|') {
						i++;
					}
					if (i + 1 < schar.length
							&& (schar[i] == '>' || schar[i] == '<' || schar[i] == '!' || schar[i] == '=')
							&& schar[i + 1] == '=') {
						if (schar[i] == '<')
							schar[i + 1] = '[';
						else if (schar[i] == '>')
							schar[i + 1] = ']';
						else if (schar[i] == '=')
							schar[i + 1] = '@';
						else
							schar[i + 1] = '?';
						i++;
					}
					if (sop.empty()) {
						sop.push(schar[i]);
					} else {
						if (p.prior_order(schar[i]) > p.prior_order((char) sop.peek())) {
							sop.push(schar[i]);
						} else {
							while (!sop.empty() && (p.prior_order(schar[i]) <= p.prior_order((char) sop.peek()))) {
								ns = ns + sop.pop();
							}
							sop.push(schar[i]);
						}
					}
				}
			}
//			System.out.println(sa.toString());
//			System.out.println(sop.toString());
		}
		while (!sop.empty()) {// clear the stack
			ns = ns + sop.pop();
		}

		return ns;// get suffix code
	}
}
