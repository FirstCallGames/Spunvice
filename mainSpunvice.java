import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class mainSpunvice {
	
	public static int AddY = 15;
	public static int AddX = 50;
	
	public static int LabelY = 20;
	public static int LabelX = 50;
	
	public static Path path;
	
	public static JButton Apps,Games,AppInstalled;
	
	public static String selected;
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args)  throws MalformedURLException, IOException, URISyntaxException 
	{
		//JOptionPane.showMessageDialog( null, "Warning!!! The App is currently in Development So It would have bugs. Version Experimental:- 0.1" );
		
		createFolder createFolder = new createFolder();
		
		Download.AppData();
		
		//Creating a frame and setting the visibility to true.
		JFrame frame = new JFrame("Spunvice");
		
		frame.setResizable(false);
		
		frame.getContentPane().setLayout(null);		
		
		Store store = new Store(frame);
		
		GUI(frame);
		
	}
	
	public static void GUI(JFrame frame) throws IOException
	{		
		//Frame Properties
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1250,700);
		frame.setFocusable(false);
		
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		//Getting Place
		Path pathtocreate = Paths.get("Applications").toAbsolutePath();
    	
		String File = pathtocreate + "/AppData/GUI Files/";
		
		//Menu Bar
		
		Icon Icon = new ImageIcon(File + "Menu.png");
		
		JLabel Menu = new JLabel(Icon);
		Menu.setBounds(0, 0, 1600, 60);
		
		Icon Icon1 = new ImageIcon(File + "UnderBar.png");
		
		// Yellow Line Label
		
		JLabel YellowLabel = new JLabel(Icon1);
		YellowLabel.setBounds(AddY,AddX,100,5);
		frame.add(YellowLabel);
		
		// Getting A Yellow Line Under Selection
		Apps(frame, YellowLabel);
		Games(frame, YellowLabel);
		AppInstalled(frame, YellowLabel);		
		
		// Setting Gamespanel to unvisible
		Application.Appspanel.setVisible(true);
		Game.Gamespanel.setVisible(false);
		
		frame.add(Menu);
		
		frame.setVisible(true);
	}
	
	public static void Apps(JFrame frame, JLabel YellowLabel)
	{
		//Application
		
		JButton Apps = new JButton("Apps");
		Apps.setFont(new Font(null, 0, 20));
		Apps.setForeground(Color.white);
		Apps.setBounds(20, 5, 100, 50);
		Apps.setOpaque(false);
		Apps.setContentAreaFilled(false);
		Apps.setBorderPainted(false);
		Apps.setFocusable(false);
		Apps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					LabelY = 20;
					YellowLabel.setBounds(LabelY,LabelX,100,5);
					
					Game.Gamespanel.setVisible(false);
					Application.Appspanel.setVisible(true);
					AppsInstalled.Installedpanel.setVisible(false);
					Store.Storepanel.setVisible(false);
					selected = "Apps";
					Store.pane.setVisible(true);
				}
			});
		frame.add(Apps);
		
	}
	
	public static void Games(JFrame frame, JLabel YellowLabel)
	{		
		//Games
		
		JButton Games = new JButton("Games");
		Games.setFont(new Font(null, 0, 20));
		Games.setForeground(Color.white);
		Games.setBounds(150, 2, 150, 60);
		Games.setOpaque(false);
		Games.setContentAreaFilled(false);
		Games.setBorderPainted(false);
		Games.setFocusable(false);
		Games.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					LabelY = 175;
					YellowLabel.setBounds(LabelY,LabelX,100,5);
					
					Game.Gamespanel.setVisible(true);
					Application.Appspanel.setVisible(false);
					AppsInstalled.Installedpanel.setVisible(false);
					Store.Storepanel.setVisible(false);
					selected = "Games";
					Store.pane.setVisible(true);
				}
			});
		frame.add(Games);
	}
	
	public static void AppInstalled (JFrame frame,JLabel YellowLabel)
	{
		//Apps On Device
		
		JButton AppInstalled = new JButton("Apps Installed");
		AppInstalled.setFont(new Font(null, 0, 20));
		AppInstalled.setForeground(Color.white);
		AppInstalled.setBounds(1050, 5, 200, 50);
		AppInstalled.setOpaque(false);
		AppInstalled.setContentAreaFilled(false);
		AppInstalled.setBorderPainted(false);
		AppInstalled.setFocusable(false);

		AppInstalled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(selected == "AppsInstalled") || selected == null)
				{
					LabelY = 1100;
				
			YellowLabel.setBounds(LabelY,LabelX,100,5);
			
			AppsInstalled.Installedpanel.removeAll();
			
			Game.Gamespanel.setVisible(false);
			Application.Appspanel.setVisible(false);
			AppsInstalled.Installedpanel.setVisible(true);
			Store.Storepanel.setVisible(false);
			Store.pane.setVisible(false);
			
			try {
				@SuppressWarnings("unused")
				AppsInstalled apps = new AppsInstalled(frame);
			} catch (IOException e1) {
			
				e1.printStackTrace();
			}
							
			selected = "AppsInstalled";
			
		}
				
		}
			
	});
		frame.add(AppInstalled);
}
}