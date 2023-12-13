import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Application {
	
	public static int AddY = 100;
	public static int AddX = 10;

	public static JPanel Appspanel = new JPanel();
	
	public static Image image = null;
	
	public static void application(JFrame frame, int Invoked, String Name, String url, String StrPath, String imageExt, String CName, String DevStatus, String Description)
	{
		//Apps Panel
		Appspanel.setBounds(0, 60, 1545, 740);
		Appspanel.setBackground(Color.black);
		Appspanel.setLayout(null);
		
		frame.add(Appspanel);
		
		Path pathtocreate = Paths.get("Applications").toAbsolutePath();
    	
		String File = pathtocreate + "/AppData/App Icon/" + imageExt;
		
		File file = new File(File);
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		
		JButton App = new JButton((Icon) new ImageIcon(image));
		
		
		App.setBounds(AddY * Invoked, AddX, 200, 200);
		Appspanel.add(App);
		App.setFocusable(false);
		App.setBorderPainted(false);
		
		JLabel AppDetails = new JLabel(Name);
		AppDetails.setFont(new Font(null, 0, 20));
		AppDetails.setBounds(AddY * Invoked, AddX + 190, 200, 50);
		AppDetails.setForeground(Color.white);
		Appspanel.add(AppDetails);
		
		JLabel CreatorName = new JLabel(CName);
		CreatorName.setBounds(AddY * Invoked, AddX + 220, 200, 50);
		CreatorName.setForeground(Color.white);
		Appspanel.add(CreatorName);
		
		
		DownloadBar(Appspanel);
		
		if(Invoked >= 2)
		{
			App.setBounds(AddY * Invoked + 160, AddX, 200, 200);
			AppDetails.setBounds(AddY * Invoked + 160, AddX + 190, 200, 50);
			CreatorName.setBounds(AddY * Invoked + 160, AddX + 220, 200, 50);
		}
		
		App.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				
				Path path = Paths.get("Applications").toAbsolutePath();
				
				Runtime runtime = Runtime.getRuntime();
				
				String loc = path + "/" + StrPath;
				
				File file = new File(loc);
				
				Appspanel.setVisible(false);
				Store.StorePage(frame, imageExt, Name, CName, url, StrPath, DevStatus, Description);
				
			}
		});
	}
	
	public static void DownloadBar(JPanel panel)
	{
		//JProgressBar Bar = new JProgressBar(0, 100);
		//panel.add(Bar);
		//Bar.setBounds(AddY + 100,AddX + 20,380,20);
		//Bar.setValue(0);
	}
	
}
