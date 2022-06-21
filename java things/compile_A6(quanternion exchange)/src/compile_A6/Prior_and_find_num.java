package compile_A6;

public class Prior_and_find_num {
	int prior_order(char c) {// operator priority encoding
		if (c == '/' || c == '*')
			return 7;
		else if (c == '+' || c == '-')
			return 6;
		else if (c == '>' || c == '<' || c == '[' || c == ']' || c == '?' || c == '@')//  the mark from left to right is <= >= != ==
			return 5;
		else if (c == '!')
			return 4;
		else if (c == '&')
			return 3;
		else if (c == '|')
			return 2;
		else if (c == '(')
			return 1;
		else if (c == '=')
			return 0;
		return 0;
	}

	int cnownum(char c) {// get lrform colunm offset of the characters
		if (c == 'a')
			return 0;
		else if (c == ';')
			return 1;
		else if (c == 'i')
			return 2;
		else if (c == 'e')
			return 3;
		else if (c == 'w')
			return 4;
		else if (c == '{')
			return 5;
		else if (c == '}')
			return 6;
		else if (c == '#')
			return 7;
		return 0;
	}
}
