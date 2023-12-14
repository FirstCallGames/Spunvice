import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

import javax.imageio.*;
import javax.swing.*;

public class Store {

public static Image image, VerifyImage = null;

public static JPanel Storepanel = new JPanel();

public static JScrollPane pane = new JScrollPane(Storepanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

public static JButton DownloadA = new JButton(); 

public static JButton Uninstall;

public static JTextArea DescriptionLabel = new JTextArea();

public static JProgressBar DownloadBar = new JProgressBar(0,100);
	
Store(JFrame frame)
	{
		String line = "";
		
		int InvokedGame = 0;
		int InvokedApp = 0;
		
		try   
		{ 
		//parsing a CSV file into BufferedReader class constructor  
		Path path = Paths.get("Applications").toAbsolutePath();
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(path + "/AppData/Spunvice.csv"));  
		while ((line = br.readLine()) != null)
		{  
		String[] app = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		/*App Name = app[0]  
		 *App Download URL = app[1] 
		 *App Install Name = app[2]
		 *App Type = app[3]
		 *App Image = app[4] 
		 *Creator Name = app[5]
		 *Verification = app[6]
		 *Description = app[7]
		 *Development Status = app[8]
		 */
		
		String descript = app[7].replace('"', ' ').trim();
		if(app[3].startsWith("Game"))
		{
			InvokedGame += 1;
			
			Game.game(frame,InvokedGame, app[0],  app[1], app[2], app[4], app[5], app[8], descript);
		}else if(app[3].startsWith("Application"))
		{
			InvokedApp += 1;
			
			Application.application(frame, InvokedApp, app[0], app[1], app[2], app[4], app[5], app[8], descript);
		}
		
		}
		}
		catch (IOException e)   
		{  
		e.printStackTrace(); 
		
	}
		
		
}

public static void StorePage(JFrame frame, String imageExt, String App, String CreatorName, String url, String StrPath,String DevStatus, String Description) throws IOException {
	
	pane.getAutoscrolls();
	
    Storepanel.setPreferredSize(new Dimension(1545,800));
	Storepanel.setBackground(Color.black);
	Storepanel.setLayout(null);
	Storepanel.removeAll();
	Storepanel.repaint();
	Storepanel.setVisible(true);
	
	//App Image
	Path pathtocreate = Paths.get("Applications").toAbsolutePath();
	
	File file = new File(pathtocreate + "/AppData/App Icon/" + imageExt);
	
	try {
		image = ImageIO.read(file);
	} catch (IOException e3) {
		e3.printStackTrace();
	}
    
	JLabel Appimage = new JLabel(new ImageIcon(image));
    Appimage.setBounds(500, 10, 400, 200);
    
    Storepanel.add(Appimage);
    JLabel AppName = new JLabel(App ,JLabel.CENTER);
	
	AppName.setFont(new Font(null, 0, 40));
	AppName.setForeground(Color.white);
	AppName.setBounds(480 , 210, 450, 50);
	Storepanel.add(AppName);
	
	String Dev = "Not Yet Started";
	
	if(Dev.compareTo(DevStatus) == 0)
	{
		JLabel label = new JLabel("Sorry, this app is currently under Development please check later.");
		label.setBounds(10, 150, 1000, 400);
		label.setForeground(Color.red);
		label.setFont(new Font(null,0,30));
		Storepanel.add(label);
	}else
	{
		//Creator Name
		String CreatorS = "Developer: " + CreatorName;
		JLabel Creator = new JLabel(CreatorS);
		Creator.setFont(new Font(null, 0, 20));
		Creator.setForeground(Color.white);
		Creator.setBounds(10, 300, 400, 50);
		Storepanel.add(Creator);
		
		//Description
		DescriptionLabel.setText(String.format(Description));
		
		DescriptionLabel.setWrapStyleWord(true);
		DescriptionLabel.setLineWrap(true);
		DescriptionLabel.setEditable(false);
		DescriptionLabel.setBounds(10, 450, 900, 400);
		DescriptionLabel.setFont(new Font(null,0,20));
		DescriptionLabel.setForeground(Color.white);
		DescriptionLabel.setBackground(Color.black);
		Storepanel.add(DescriptionLabel);
		
		DownloadProgram(url,StrPath, frame);
	}
	
    pane.repaint();
    pane.setBorder(null);
	pane.setBounds(0, 60, 1535, 740);	
	pane.getVerticalScrollBar().setValue(0);
	frame.add(pane);
    
}

public static void UninstallP(String FileStr, JButton DownloadA)
{
	JButton Uninstall = new JButton("Uninstall");
	Uninstall.setBounds(500, 360, 400, 50);
	Uninstall.setFocusable(false);
	Uninstall.setBackground(Color.black);
	Uninstall.setForeground(Color.black);
	Uninstall.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			File file = new File(FileStr);
			file.delete();
			DownloadA.setText("Download");
			Uninstall.setVisible(false);
			Storepanel.repaint();
		}
		});
	Storepanel.add(Uninstall);
}

public static void DownloadProgram(String url, String StrPath, JFrame frame) throws IOException
{
	Path path = Paths.get("Applications").toAbsolutePath();
	
	String loc = path + "/" + StrPath;
	
	File file = new File(loc);
	
	//Download Or Open
	JButton DownloadA = new JButton("Download");
	
	if(file.exists())
	{
		DownloadA.setText("Open");
		Storepanel.repaint();
		UninstallP(path + "/" + StrPath, DownloadA);
	}
	
	DownloadA.addActionListener(new ActionListener() {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e){
			if(DownloadA.getText() == "Download")
			{
				try {
					DownloadBar.setVisible(true);
					DescriptionLabel.setBounds(10, 450, 900, 400);
					Storepanel.repaint();
					try {
						Download.download(url, ("/" + StrPath));
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				} catch (MalformedURLException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				DownloadA.setText("Open");
				UninstallP(path + "/" + StrPath, DownloadA);
				DownloadBar.setVisible(true);
				Storepanel.repaint();
				
				
			}else if (DownloadA.getText() == "Open")
			{
				String location = "java -jar " + path + "/" + StrPath;
				
				try {
					Process process = Runtime.getRuntime().exec(location);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				DescriptionLabel.setBounds(10, 450, 900, 400);
				Storepanel.repaint();
				
				UninstallP(path + "\\" + StrPath, DownloadA);
			}
		}
		});
	
	DownloadA.setForeground(Color.black);
	DownloadA.setBackground(Color.black);
	DownloadA.setFocusable(false);
	DownloadA.setBounds(500, 300, 400, 50);
	Storepanel.add(DownloadA);
	
	//DownloadBar.setBounds(500, 270, 400, 20);
	//DownloadBar.setValue(0);
	//DownloadBar.repaint();
	//DownloadBar.setStringPainted(true);
	//Storepanel.add(DownloadBar);
	//DownloadBar.setVisible(false);
}


}
