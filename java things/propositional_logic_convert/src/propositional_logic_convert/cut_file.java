package propositional_logic_convert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
/*trim original file in a appropriate order*/

class cut_file {
	String filename;
	Vector<String> fec_lang;
	int flagppt = 1;

	cut_file(String filename) {
		this.filename = filename;
	}

	@SuppressWarnings("resource")
	int gettype() throws IOException {
		InputStreamReader read = new InputStreamReader(new FileInputStream(filename));
		BufferedReader in = new BufferedReader(read);

		String line = in.readLine();
		int flaga = 0;// flag of cut file
		int flagb = 0;// flag of differentiate type of file
		fec_lang = new Vector<String>();
		while (line != null) {
			// for logical formula(LF)
			if (line.indexOf("ormula") != -1 || line.indexOf("=>") != -1 || line.indexOf("┐") != -1) {
				if (flagb == 2) {
					flaga = 0;
					flagb = 0;
					return 0;
				} else {
					flaga = 1;
					flagb = 1;
				}
			}

			// for FreeEnCal Language(FECL)
			if (line.indexOf("AllPool") != -1) {
				flaga = 1; // delete all before 'Allpool'
				flagb = 2;
			}
			if (line.indexOf("InferenceRule") != -1) {
				flaga = 0; // delete all behind interferece rules
				in.close();
				return 2;// can be returned
			}

			if (line.indexOf("x") != -1 || line.indexOf("V_0") != -1 || line.indexOf("Q_0") != -1) // character of
																									// predicate logic
				flagppt = 2;
			if (line.indexOf("V_1") != -1 || line.indexOf("C_1") != -1 || line.indexOf("C_0") != -1) {// character of
																										// empirical
				flagb = 0;
				in.close();
				return 0;
			}

			if (flaga == 1)
				fec_lang.add(line);
			line = in.readLine();
		}
		in.close();
		return flagb;
	}

	// return vector prepared in gettype
	Vector<String> getvector() {
		return fec_lang;
	}

	// return flagppt prepared in gettype
	int propotell() {// differeciate propositional logic and predicate logic (propositional tell)
		return flagppt;
	}
}