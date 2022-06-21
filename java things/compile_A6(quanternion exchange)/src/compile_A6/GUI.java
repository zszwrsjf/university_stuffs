package compile_A6;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI implements ActionListener {
	String title;
	JFrame frame;
	JButton buttonopen, buttonsave, buttonchange, buttonquit;
	JTextArea textarea;
	JScrollPane scrollpane;
	ImageIcon imageIcon;
	JFileChooser fc;
	String showtext = "";

	Vector<String> showfin = new Vector<String>();

	GUI(String title) {
		this.title = title;
	}

	void start() throws IOException {

		frame = new JFrame(title);
		frame.setLayout(null);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);// set center

		imageIcon = new ImageIcon("1.jpg");
		JLabel labelimage = new JLabel(imageIcon);
		labelimage.setBounds(600, 285, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		frame.add(labelimage);

		buttonopen = new JButton("open");
		buttonopen.setBounds(650, 20, 90, 40);
		buttonopen.setHorizontalAlignment(JLabel.CENTER);
		buttonopen.setFont(new Font("Times New Roman", 0, 18));
		frame.add(buttonopen);

		buttonchange = new JButton("convert");
		buttonchange.setBounds(650, 90, 90, 40);
		buttonchange.setHorizontalAlignment(JLabel.CENTER);
		buttonchange.setFont(new Font("Times New Roman", 0, 18));
		frame.add(buttonchange);
		
		buttonsave = new JButton("save");
		buttonsave.setBounds(650, 160, 90, 40);
		buttonsave.setHorizontalAlignment(JLabel.CENTER);
		buttonsave.setFont(new Font("Times New Roman", 0, 18));
		buttonsave.setEnabled(false);
		frame.add(buttonsave);

		buttonquit = new JButton("quit");
		buttonquit.setBounds(650, 230, 90, 40);
		buttonquit.setHorizontalAlignment(JLabel.CENTER);
		buttonquit.setFont(new Font("Times New Roman", 0, 18));
		frame.add(buttonquit);

		textarea = new JTextArea("");
		scrollpane = new JScrollPane(textarea);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		textarea.setCaretPosition(textarea.getText().length());
		scrollpane.setBounds(0, 0, 600, 564);
		frame.add(scrollpane);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		buttonopen.addActionListener(this);
		buttonsave.addActionListener(this);
		buttonchange.addActionListener(this);
		buttonquit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonopen) {
			fc = new JFileChooser();
			fc.showOpenDialog(frame);
			File file = fc.getSelectedFile();
			String filename = null;
			try {// find path of file
				filename = file.getAbsolutePath();
			}
			catch(NullPointerException e1) {
				textarea.setText("please choose a file");
				return ;
			}
			
			if(filename.substring(filename.length()-3, filename.length()).indexOf("txt") == -1) {
				textarea.setText("not txt file");
				return ;
			};
			
			InputStreamReader read = null;
			try {
				read = new InputStreamReader(new FileInputStream(filename));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				textarea.setText("wrong file");
			}
			BufferedReader in = new BufferedReader(read);

			String line = null;
			try {
				line = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				textarea.setText("no text");
			}
			if(line == null) {
				textarea.setText("no text");
				return ;
			}
			String text = "";
			while (line != null) {
				text = text + line;
				try {
					line = in.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				in.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			code_arrange ca = new code_arrange();

			try {
				showtext = ca.code(text, 2);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			textarea.setText(showtext);
		}

		if (e.getSource() == buttonsave) {
			String showtext = "";
			showtext = textarea.getText();

			fc = new JFileChooser();
			fc.showSaveDialog(frame);
			File fl = fc.getSelectedFile();
			String filename2 = fl.getAbsolutePath();
			filename2 = filename2 + ".txt";
			
			
			
			try {
				// write into file
				BufferedWriter out = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(filename2, true), "UTF-8"));
				showtext = showtext.replaceAll("\n", "\r\n");
				out.write(showtext);
				out.close();
			} catch (IOException e1) {
				e1.getStackTrace();
				return;
			}
		}

		if (e.getSource() == buttonchange) {
			code_arrange ca = new code_arrange();
			showtext = textarea.getText();
			try {
				showtext = ca.code(showtext, 0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			textarea.setText(showtext);

			buttonsave.setEnabled(true);
		}
		if (e.getSource() == buttonquit) {
			System.exit(0);
		}

	}
}
