package compile_A6;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/*
 * To fix the code in a clear form and do the lexical analysis.
 */

public class code_arrange {
	String code(String code, int flag) throws IOException {
		// TODO Auto-generated method stub

		Vector<String> s4 = new Vector<String>();
		Vector<String> ns4 = new Vector<String>();
		s4.addElement(code);

		Vector<Character> input = new Vector<Character>();
		Enumeration<String> elements = s4.elements();
		while (elements.hasMoreElements()) {

			// delete space and enter
			String str = elements.nextElement();
//        	System.out.println(str);
			char[] schar = str.toCharArray();
			int length = str.length();
			for (int i = 0; i < length; i++) {
				while (schar[i] == ' ' || schar[i] == '\n') {
					if (i == length - 1) {
						length--;
						break;
					}
					for (int it = i; it < length - 1; it++) {
						schar[it] = schar[it + 1];
					}
					length--;
				}
//    			System.out.print(schar[i]);
			}
//    		System.out.println("");
			// devide ; and {}
			if (length != 0) {
				str = "";
				int length_temp = 0;
				for (int i = 0; i < length; i++) {
					if (schar[i] == ';') {
						ns4.addElement(str);
						ns4.addElement(";");
						length_temp = 0;
						str = "";
					} else {
						if (schar[i] == '{' || schar[i] == '}') {
							if (length_temp != 0)
								ns4.addElement(str);
							ns4.addElement(String.valueOf(schar[i]));
							length_temp = 0;
							str = "";
						} else {
							str = str + schar[i];
							length_temp++;
						}
					}
					if (length_temp != 0 && i == length - 1 && schar[i] != ';') {
						ns4.addElement(str);
					}
				}
			}
		}

// if while case divide
		s4.clear();
		Enumeration<String> elements1 = ns4.elements();
		while (elements1.hasMoreElements()) {
			String s = elements1.nextElement();
			if (!(s.indexOf("if") != -1 || s.indexOf("else") != -1 || s.indexOf("while") != -1)) {
				s4.addElement(s);
				continue;
			}

			char[] schar = s.toCharArray();
			int length = s.length();
			String str = "";
			for (int i = 0; i < length; i++) {

				if (i + 2 < length) {// if case
					if (schar[i] == 'i' && schar[i + 1] == 'f' && schar[i + 2] == '(') {
						if (str.length() != 0) {
							s4.addElement(str);
						}
						str = "if(";
						i = i + 3;
						int t = 1;
						while (t != 0) {
							if (schar[i] == '(')
								t++;
							if (schar[i] == ')')
								t--;
							str = str + schar[i];
							i++;
						}
						s4.addElement(str);
						str = "";
						i--;
						continue;
					}
				}

				if (i + 3 < length) {// else case
					if (schar[i] == 'e' && schar[i + 1] == 'l' && schar[i + 2] == 's' && schar[i + 3] == 'e') {
						if (str.length() != 0) {
							s4.addElement(str);
						}
						str = "else";
						i = i + 4;
						s4.addElement(str);
						str = "";
						i--;
						continue;
					}
				}

				if (i + 5 < length) {// while case
					if (schar[i] == 'w' && schar[i + 1] == 'h' && schar[i + 2] == 'i' && schar[i + 3] == 'l'
							&& schar[i + 4] == 'e' && schar[i + 5] == '(') {
						if (str.length() != 0) {
							s4.addElement(str);
						}
						str = "while(";
						i = i + 6;
						int t = 1;
						while (t != 0) {
							if (schar[i] == '(')
								t++;
							if (schar[i] == ')')
								t--;
							str = str + schar[i];
							i++;
						}
						s4.addElement(str);
						str = "";
						i--;
						continue;
					}
				}

				str = str + schar[i];

				if (i == length - 1 && str.length() != 0) {
					s4.addElement(str);
					str = "";
				}
			}
		}

		String showtext = "";
		Enumeration<String> elements123 = s4.elements();
		while (elements123.hasMoreElements()) {
			showtext = showtext + elements123.nextElement();
			showtext = showtext + '\n';
		}
		showtext = showtext.substring(0, showtext.length() - 1);
		showtext = showtext.replaceAll("\n;", ";");

		if (flag == 2) {
			return showtext;
		}

		ns4.clear();

		input.addElement('#');
		ns4.addElement("");
		Enumeration<String> elements12 = s4.elements();
		while (elements12.hasMoreElements()) {
			String str = elements12.nextElement();

			if (str.indexOf("if") != -1) {
				input.addElement('i');
				ns4.addElement(str.substring(3, str.length() - 1));
			} else if (str.indexOf("else") != -1) {
				input.addElement('e');
				ns4.addElement("");
			} else if (str.indexOf("while") != -1) {
				input.addElement('w');
				ns4.addElement(str.substring(6, str.length() - 1));
			} else if (str.indexOf(";") != -1) {
				input.addElement(';');
				ns4.addElement("");
			} else if (str.indexOf("{") != -1) {
				input.addElement('{');
				ns4.addElement("");
			} else if (str.indexOf("}") != -1) {
				input.addElement('}');
				ns4.addElement("");
			} else {
				input.addElement('a');
				ns4.addElement(str);
			}
		}
		input.addElement('#');
		ns4.addElement("");

		reverse_poland rp = new reverse_poland();
		int number = 0;
		Four_change_calculate fc = new Four_change_calculate();
		s4.clear();
		Enumeration<String> element1 = ns4.elements();

		while (element1.hasMoreElements()) {
			String snow = element1.nextElement();
			snow = rp.get_reverse(snow);
			snow = fc.get_change(snow, number);
			number = fc.number_back();
			s4.addElement(snow);
//				System.out.println(snow);
//				System.out.println(elementinput.nextElement());
		}
		lrtest lr = new lrtest();
		Vector<String> showfin = new Vector<String>();
		showfin = lr.lrtest(input, s4);
		showtext = "";
		Enumeration<String> element = showfin.elements();
		while (element.hasMoreElements()) {
			showtext = showtext + element.nextElement() + '\n';
		}
		return showtext;
	}
}
