package propositional_logic_convert;

import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

/*LF to FEC*/

public class FL_to_FEC {
	Vector<String> hs;

	FL_to_FEC(Vector<String> hs) {
		this.hs = hs;
	}

	int prior(String stackmark) {
		if (stackmark.indexOf("∀") != -1)
			return 4;
		if (stackmark.indexOf("┐") != -1)
			return 3;
		else if (stackmark.indexOf("∧") != -1 || stackmark.indexOf("∨") != -1)
			return 2;
		else if (stackmark.indexOf("=>") != -1)
			return 1;
		else if (stackmark.indexOf(")") != -1)
			return 0;
		else
			return -1;
	}

	Vector<String> turnto() {
		
		Enumeration <String> element1s = hs.elements();
		while(element1s.hasMoreElements()) {
			System.out.println(element1s.nextElement());
		}

		Stack<String> logmark = new Stack<String>();
		Stack<String> stack = new Stack<String>();
		Vector<String> nhs = new Vector<String>();

		Enumeration<String> element = hs.elements();
		while (element.hasMoreElements()) {
			String temstr = element.nextElement();
			if (temstr.indexOf("ormula") != -1)
				continue;
			if (temstr.length() == 0)
				continue;// deletes space

			// add EOQ
			String newstr = "";
			int tx = 0;
			int ty = 0;
			int tz = 0;
			int tu = 0;
			int flagx = 0;
			int flagy = 0;
			int flagz = 0;
			int flagu = 0;
			for (int i = 0; i <= temstr.length() - 1; i++) {
				newstr = newstr + temstr.charAt(i);
				if (temstr.charAt(i) == '∀') {
					i++;
					if (temstr.charAt(i) == 'x')
						flagx = 1;
					else if (temstr.charAt(i) == 'y')
						flagy = 1;
					else if (temstr.charAt(i) == 'z')
						flagz = 1;
					else if (temstr.charAt(i) == 'u')
						flagu = 1;
					newstr = newstr + temstr.charAt(i);
					continue;
				}
				if (temstr.charAt(i) == '(' && flagx == 1)
					tx++;
				if (temstr.charAt(i) == '(' && flagy == 1)
					ty++;
				if (temstr.charAt(i) == '(' && flagz == 1)
					tz++;
				if (temstr.charAt(i) == '(' && flagu == 1)
					tu++;

				if (temstr.charAt(i) == ')' && flagx == 1)
					tx--;
				if (temstr.charAt(i) == ')' && flagy == 1)
					ty--;
				if (temstr.charAt(i) == ')' && flagz == 1)
					tz--;
				if (temstr.charAt(i) == ')' && flagu == 1)
					tu--;

				if (tx == 0 && flagx == 1) {
					newstr = newstr + "EOQ";
					flagx = 0;
				}
				if (ty == 0 && flagy == 1) {
					newstr = newstr + "EOQ";
					flagy = 0;
				}
				if (tz == 0 && flagz == 1) {
					newstr = newstr + "EOQ";
					flagz = 0;
				}
				if (tu == 0 && flagu == 1) {
					newstr = newstr + "EOQ";
					flagu = 0;
				}
			}
			temstr = newstr;
			for (int i = temstr.length() - 1; i >= 0; i--) {
				char nm = temstr.charAt(i);// now mark
				if (nm != '=' && nm != '>' && nm != '(' && nm != ')' && nm != '┐' && nm != '∧' && nm != '∨'
						&& !(nm >= 'A' && nm <= 'G') && nm != 'Q' && nm != 'O' && nm != '∀' && nm != 'x' && nm != 'y'
						&& nm != 'z' && nm != 'u')
					continue;
				else if (nm == '>') {
					i--;
					if (logmark.isEmpty())
						logmark.push("=>");
					else {
						if (prior("=>") > prior(logmark.peek())) {
							logmark.push("=>");
						}

						else {
							stack.push(logmark.pop());
							logmark.push("=>");
						}
					}
				} else if (nm == 'Q') {
					i -= 2;
					stack.push("EOQ");
				} else if (nm == 'x' || nm == 'y' || nm == 'z' || nm == 'u') {
					i--;
					logmark.push("∀" + nm);
				} else if (nm >= 'A' && nm <= 'H')
					stack.push(String.valueOf(nm));
				else if (nm == ')')
					logmark.push(String.valueOf(nm));
				else if (!logmark.isEmpty() && nm == '(') {
					if (logmark.peek().indexOf(")") != -1) {
						logmark.pop();
					} else
						stack.push(logmark.pop());
					Stack<String> tems = new Stack<String>();

					if (!logmark.empty()) {
						while (!(logmark.peek().indexOf(")") != -1)) {
							tems.push(logmark.pop());
							if (logmark.empty())
								break;
						}
					}
					if (!logmark.empty())
						logmark.pop();
					while (!tems.isEmpty())
						logmark.push(tems.pop());
				} else if (nm == '∨' || nm == '∧' || nm == '┐') {
					if (logmark.isEmpty())
						stack.push(String.valueOf(nm));
					else {
						if (prior(logmark.peek()) > prior(String.valueOf(nm))) {
							stack.push(logmark.pop());
							logmark.push(String.valueOf(nm));
						} else if (prior(logmark.peek()) <= prior(String.valueOf(nm)))
							logmark.push(String.valueOf(nm));
					}
				}
			}
			while (!logmark.isEmpty())
				stack.push(logmark.pop());
			String logfor = "";// logical formula
			while (!stack.isEmpty())
				logfor = logfor + stack.pop();
			if (logfor.length() > 0) {
				logfor = logfor.replaceAll("A", "V_2_0,");
				logfor = logfor.replaceAll("B", "V_2_1,");
				logfor = logfor.replaceAll("C", "V_2_2,");
				logfor = logfor.replaceAll("D", "V_2_3,");
				logfor = logfor.replace("EOQ", "protect");
				logfor = logfor.replaceAll("E", "V_2_4,");
				logfor = logfor.replace("protect", "EOQ,");
				logfor = logfor.replaceAll("F", "V_2_5,");
				logfor = logfor.replaceAll("G", "V_2_6,");
				logfor = logfor.replaceAll("H", "V_2_7,");
				logfor = logfor.replaceAll("=>", "F_2_2,C_2_0,");
				logfor = logfor.replaceAll("∧", "F_2_2,C_2_1,");
				logfor = logfor.replaceAll("∨", "F_2_1,C_2_2,");
				logfor = logfor.replaceAll("┐", "F_2_1,C_2_3,");
				logfor = logfor.replaceAll("∀x", "Q_0,V_0_0,");
				logfor = logfor.replaceAll("∀y", "Q_0,V_0_1,");
				logfor = logfor.replaceAll("∀z", "Q_0,V_0_2,");
				logfor = logfor.replaceAll("∀u", "Q_0,V_0_3,");
				logfor = logfor.substring(0, logfor.length() - 1);
				nhs.add(logfor);
			}
		}

		Vector<String> fhs = new Vector<String>();
		fhs.add("InferenceRule 1");
		fhs.add("2 1 F_2_2,C_2_0,V_2_0,V_2_1 V_2_0 V_2_1");
		String temstr = "LogicalPremise " + nhs.size();
		fhs.add(temstr);
		Enumeration<String> elements = nhs.elements();
		while (elements.hasMoreElements()) {
			fhs.add(elements.nextElement());
		}
		fhs.add("Degree 1");
		fhs.add("C_2_0 3");
		
		element1s = fhs.elements();
		while(element1s.hasMoreElements()) {
			System.out.println(element1s.nextElement());
		}
		
		return fhs;
	}
}
