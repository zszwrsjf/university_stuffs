package propositional_logic_convert;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*UI screen is made by swing*/

public class UI implements ActionListener {
	JFrame frame;
	JButton buttoninput, buttonturn;// input for choose file, turn for convert
	JLabel notice, notice2;// notice for head, notice2 for bottom
	JFileChooser fc;

	Vector<String> hs = new Vector<String>();// use vector to storage String to make it infinity
	int flag = 0;// flag to tell file writen by FreeCal Language or logical formula
	int flagppt = 0;

	public UI(String title) {
		// set init
		frame = new JFrame(title);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(0, 200, 125));
		frame.setSize(450, 180);
		frame.setLocationRelativeTo(null);// set center

		notice2 = new JLabel(" ");
		notice2.setBounds(0, 90, 390, 50);
		notice2.setHorizontalAlignment(JLabel.CENTER);
		notice2.setFont(new Font("Times New Roman", 0, 16));
		frame.add(notice2);

		notice = new JLabel("Please click file button to choose a file.");
		notice.setBounds(0, 0, 390, 50);
		notice.setHorizontalAlignment(JLabel.CENTER);
		notice.setFont(new Font("Times New Roman", 0, 20));
		frame.add(notice);

		buttoninput = new JButton("file");
		buttoninput.setBounds(80, 50, 80, 30);
		frame.add(buttoninput);

		buttonturn = new JButton("convert");
		buttonturn.setBounds(280, 50, 80, 30);
		buttonturn.setEnabled(false);// without input file no convert
		frame.add(buttonturn);

		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttoninput.addActionListener(this);
		buttonturn.addActionListener(this);
	}

	// use actionperform to listen clicks
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttoninput) {
			notice2.setFont(new Font("Times New Roman", 0, 16));

			// use filechooser to get filename
			fc = new JFileChooser();
			fc.showOpenDialog(frame);
			File file = fc.getSelectedFile();
			String filename = file.getAbsolutePath();
			cut_file cf = new cut_file(filename);

			try {
				flag = cf.gettype();// 1 for FL 2 for FECL
				flagppt = cf.propotell();// 1 for propositional logic 2 for predicate logic
				hs = cf.getvector();// get original file

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				return;
			}

			if (flag == 1) {
				if (flagppt == 1) {
					notice.setText("This is a file of propositional logical formula.");
					notice2.setText("Please click convert to convert to FreeEnCal language.");
					buttonturn.setEnabled(true);
				} else if (flagppt == 2) {
					notice.setText("This is a file of predicate logic formula.");
					notice2.setText("Please click convert to convert to FreeEnCal language.");
					buttonturn.setEnabled(true);
				}
			}

			if (flag == 2) {
				if (flagppt == 1) {
					notice.setText("This is a FEC language file of propositional logic.");
					notice2.setText("Please click convert to convert to logical formula.");
					buttonturn.setEnabled(true);
				} else if (flagppt == 2) {
					notice.setText("This is a FEC language file of predicate logic.");
					notice2.setText("Please click convert to convert to logical formula.");
					buttonturn.setEnabled(true);
				}
			}

			// not in right way, users should find error by them selves
			if (flag == 0) {
				notice.setText("Please make file into right way.");
				notice2.setFont(new Font("Times New Roman", 0, 13));
				notice2.setText("Maybe forget LogicalFormula or isn't logic fragment or something.");
				return;
			}
		}

		if (e.getSource() == buttonturn) {

			if (flag == 1) {
				FL_to_FEC fotofe = new FL_to_FEC(hs);
				hs = fotofe.turnto();
			}

			if (flag == 2) {
				FEC_to_FL fetofo = new FEC_to_FL(hs);
				hs = fetofo.turnto();
			}
			// use file chooser to save file
			fc = new JFileChooser();
			fc.showSaveDialog(frame);
			File fl = fc.getSelectedFile();
			String filename2 = fl.getAbsolutePath();
			filename2 = filename2 + ".txt";
			
			Enumeration <String> element1s = hs.elements();
			while(element1s.hasMoreElements()) {
				System.out.println(element1s.nextElement());
			}
			
			try {
				// write into file
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(filename2, true), "UTF-8"));
				Enumeration<String> elements1 = hs.elements();
				while (elements1.hasMoreElements()) {
					out.write(elements1.nextElement());
					out.newLine();
				}
				out.close();
			} catch (IOException e1) {
				e1.getStackTrace();
				return;
			}
			notice.setText("Successed.");
			notice2.setText("Click file to choose an another file.");
			buttonturn.setEnabled(false);
		}
	}
}