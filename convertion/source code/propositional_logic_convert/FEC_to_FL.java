package propositional_logic_convert;

import java.util.Enumeration;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class FEC_to_FL {
	Vector<String> hs;

	FEC_to_FL(Vector<String> hs) {
		this.hs = hs;
	}

	Vector<String> turnto() {
		String line;
		Vector<String> nhs = new Vector<String>();// new hs

		Enumeration<String> elements = hs.elements();
		while (elements.hasMoreElements()) {
			// replace all FEC term into LF term
			line = elements.nextElement();
			line = line.replaceAll("F_2_2,C_2_0", "=>");
			line = line.replaceAll("F_2_2,C_2_1", "∧");
			line = line.replaceAll("F_2_1,C_2_2", "∨");
			line = line.replaceAll("F_2_1,C_2_3", "┐");
			line = line.replaceAll("V_2_0", "A");
			line = line.replaceAll("V_2_1", "B");
			line = line.replaceAll("V_2_2", "C");
			line = line.replaceAll("V_2_3", "D");
			line = line.replaceAll("V_2_4", "E");
			line = line.replaceAll("V_2_5", "F");
			line = line.replaceAll("V_2_6", "G");
			line = line.replaceAll("V_2_7", "H");

			line = line.replaceAll("Q_0,V_0_0", "∀x");
			line = line.replaceAll("Q_0,V_0_1", "∀y");
			line = line.replaceAll("Q_0,V_0_2", "∀z");
			line = line.replaceAll("Q_0,V_0_3", "∀u");
			line = line.replaceAll(",EOQ", "");
			line = line.replaceAll(",", " ");

			if (line.indexOf("AllPool") != -1 || line.indexOf("NewPool") != -1) {// sign of result of FEC machine
				nhs.add(line);
				continue;
			}

			StringTokenizer st = new StringTokenizer(line);
			Stack<String> s = new Stack<String>();
			Stack<String> stack = new Stack<String>();

			boolean flagid = false;// flag of line id, number of line is no need joining converting
			String follownum = " ";// write down id

			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				integer_detection id = new integer_detection(token);
				flagid = id.isInteger();
				if (flagid == false) {
					s.push(token);
				} else
					follownum = follownum + token + " ";

			}

			while (!s.isEmpty()) {
				if (s.peek().equals("=>")) {
					stack.push("(" + stack.pop() + s.pop() + stack.pop() + ")");
				} else if (s.peek().equals("∧") || s.peek().equals("∨")) {
					stack.push("(" + stack.pop() + s.pop() + stack.pop() + ")");
				} else if (s.peek().equals("┐")) {
					stack.push("(" + s.pop() + stack.pop() + ")");
				} else if (s.peek().equals("∀x") || s.peek().equals("∀y") || s.peek().equals("∀z")
						|| s.peek().equals("∀u")) {
					stack.push("(" + s.pop() + stack.pop() + ")");
				} else
					stack.push(s.pop());
			}
			line = stack.toString();
			line = line + follownum;
			line = line.replaceAll("]", "");
			line = line.replaceAll("\\[", "");

			nhs.add(line);
		}

		return nhs;
	}
}
