import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.EventQueue;
import java.io.*;
import java.util.Formatter;


public class GUI extends TextEditor{
	//environ vars
	JFrame frame;
	JPanel mainPanel;
	int width;
	int height;
	File selected;
	
	//setting up the gui
	public GUI(int w, int h){
		frame = new JFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		width = w;
		height = h;
	}
	public void setUp(){
		JTextArea tA = new JTextArea();
		JScrollPane spane = new JScrollPane(tA);
		
		frame.setSize(width, height);
		frame.setTitle("Text Editai");
		
		JPanel bPannel = new JPanel();
		bPannel.setLayout(new BorderLayout());
		mainPanel.add(bPannel, BorderLayout.NORTH);
		
		JPanel tPannel = new JPanel();
		tPannel.setLayout(new BorderLayout());
		mainPanel.add(tPannel, BorderLayout.CENTER);
		
		//menus and menubar
		JMenuBar menu = new JMenuBar();
		JMenu menu1 = new JMenu("File"), menu2 = new JMenu("Font"), menu3 = new JMenu("Size");
		menu.add(menu1);
		menu.add(menu2);
		menu.add(menu3);

		JMenuItem fileItem1 = new JMenuItem("Open File"), fileItem2 = new JMenuItem("Save");
		menu1.add(fileItem1);
		menu1.add(fileItem2);

		JMenuItem fontItem1 = new JMenuItem("Times New Roman"), fontItem2 = new JMenuItem("Arial"), fontItem3 = new JMenuItem("monospaced");
		menu2.add(fontItem1);
		menu2.add(fontItem2);
		menu2.add(fontItem3);

		JMenuItem sizeItem0 = new JMenuItem("14"), sizeItem1 = new JMenuItem("12"), sizeItem2 = new JMenuItem("24"), sizeItem3 = new JMenuItem("36");
		menu3.add(sizeItem0);
		menu3.add(sizeItem1);
		menu3.add(sizeItem2);
		menu3.add(sizeItem3);
		
		bPannel.add(menu, BorderLayout.CENTER);
		tPannel.add(spane);
		frame.add(mainPanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//adding actions to the gui
		ActionListener fileActs = new ActionListener(){
			//open a file
			@Override
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == fileItem1){
					try
					{
						JFileChooser chooser = new JFileChooser();
						chooser.setDialogTitle("select file to open");
						chooser.showOpenDialog(null);
						
						File openFile = chooser.getSelectedFile();
						if(!openFile.exists()){
							//error message
							selected = openFile;
							System.out.println("does not extist");
						}
						Scanner read = new Scanner(openFile);
						String content = "";
						while(read.hasNextLine()){
							content += read.nextLine()+"\n";
						}
						read.close();
						tA.setText(content);
						
						selected = openFile;
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}else if(e.getSource() == fileItem2){
					//save a file
					try{
						JFileChooser chooser = new JFileChooser();
						chooser.setDialogTitle("select where to save");
						chooser.showOpenDialog(null);
						
						File openFile = chooser.getSelectedFile();
						String content = tA.getText();
						
						Formatter form = new Formatter(openFile);
						form.format("%s", content);
						form.close();
						
						selected = openFile;
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		};
		//actions for fonts
		ActionListener fontActs = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == fontItem1){
					Font font = new Font("Times New Roman", Font.PLAIN, 14);
					tA.setFont(font);
				}else if(e.getSource() == fontItem2){
					Font font = new Font("Arial", Font.PLAIN, 14);
					tA.setFont(font);					
				}else if(e.getSource() == fontItem3){
					Font font = new Font("monospaced", Font.PLAIN, 14);
					tA.setFont(font);
				}
			}
		};
		//actions for sizes
		ActionListener sizeActs = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == sizeItem0){
					Font font = new Font("", Font.PLAIN, 14);
					tA.setFont(font);
				}else if(e.getSource() == sizeItem1){
					Font font = new Font("", Font.PLAIN, 12);
					tA.setFont(font);					
				}else if(e.getSource() == sizeItem2){
					Font font = new Font("", Font.PLAIN, 24);
					tA.setFont(font);
				}else if(e.getSource() == sizeItem3){
					Font font = new Font("", Font.PLAIN, 36);
					tA.setFont(font);					
				}				
			}
		};
		fontItem1.addActionListener(fontActs);
		fontItem2.addActionListener(fontActs);
		fontItem3.addActionListener(fontActs);
		
		sizeItem0.addActionListener(sizeActs);
		sizeItem1.addActionListener(sizeActs);
		sizeItem2.addActionListener(sizeActs);
		sizeItem3.addActionListener(sizeActs);
		
		fileItem1.addActionListener(fileActs);
		fileItem2.addActionListener(fileActs);
	}
}