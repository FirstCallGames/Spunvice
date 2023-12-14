import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AppsInstalled {
	
	static int AddY = 100;
	static int AddX = 10;
	
	public static JPanel Installedpanel = new JPanel();
	
	String fileName;
	
	String line = "";  
	String splitBy = ",";
	
	String[] app;
	
	public static Image image = null;
	public static Image image2 = null;
	
	public static boolean yo = false;
	
	static int pressed = 0;
	static boolean menuactive = false;
	
	AppsInstalled(JFrame frame) throws IOException
	{
		int Invoked = 0;
		
		JLabel InstallApp = new JLabel("No App Installed");
		InstallApp.setBounds(500, 300,6500, 100);
		InstallApp.setFont(new Font(null,0,50));
		InstallApp.setForeground(Color.white);
		Installedpanel.setBounds(0, 60, 1545, 740);
		Installedpanel.setLayout(null);
		Installedpanel.add(InstallApp);
		Installedpanel.setBackground(Color.black);
		frame.add(Installedpanel);
		
		Path path = Paths.get("Applications").toAbsolutePath();
		String loc = path.toString();

		File[] files = new File(loc).listFiles();

		for (File file : files) {
		    if (file.isFile()) {
		        
		        if(file.getName().endsWith(".jar"))
		        {
		        	@SuppressWarnings("resource")
		    		BufferedReader br = new BufferedReader(new FileReader("Applications/AppData/Spunvice.csv"));  
		    		while ((line = br.readLine()) != null)   //returns a Boolean value  
		    		{  
		    			String[] app = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		    		 
		    		if(app[2].startsWith(file.getName()))
		    		{
		    			Invoked += 1;
						String descript = app[7].replace('"', ' ').trim();
				    	GUI(frame, app[4], file.getName(), Invoked, app[0], app[5], app[1], app[8],descript);
				    	InstallApp.setVisible(false);
		    		}		    		 
		    		 
		    		}
		        	
		        }
		    }
		}
		
		
		
	}
	
	private static void GUI(JFrame frame, String imageExt, String StrPath, final int Invoked, String Name, String CName, String url, String DevStatus, String Description)
	{
		//Applications Panel
		
		JLabel AppDetails = new JLabel(Name);
		AppDetails.setFont(new Font(null, 0, 17));
		AppDetails.setBounds(AddY * Invoked, AddX + 190, 200, 50);
		AppDetails.setForeground(Color.white);
		Installedpanel.add(AppDetails);
		
		JLabel CreatorName = new JLabel(CName);
		CreatorName.setBounds(AddY * Invoked, AddX + 220, 200, 50);
		CreatorName.setForeground(Color.white);
		Installedpanel.add(CreatorName);
		
		
		
		
		Path pathtocreate = Paths.get("Applications").toAbsolutePath();
    	
		String File = pathtocreate + "/AppData/App Icon/" + imageExt;
		
		File file = new File(File);
		
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		// Triple Dot Menu
		String File2 = pathtocreate + "/AppData/GUI Files/Dot.png";
		
		File file2 = new File(File2);
		
		try {
			image2 = ImageIO.read(file2);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		

		JPanel menupanel = new JPanel();
		menupanel.setOpaque(true);
		menupanel.setBackground(Color.gray);
		menupanel.setBounds(300,220,250,250);
		menupanel.setVisible(false);
		Installedpanel.add(menupanel);
		
		JButton StorePage = new JButton("Open Store Page");
		StorePage.setFont(new Font(null, 0, 20));
		StorePage.setForeground(Color.black);
		StorePage.setContentAreaFilled(false);
		StorePage.setBorderPainted(false);
		StorePage.setFocusable(false);
		StorePage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Installedpanel.setVisible(false);
				try {
					Store.StorePage(frame, imageExt, Name, CName, url, StrPath, DevStatus, Description);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Store.pane.setVisible(true);
			}
		});
		StorePage.addMouseListener(new MouseAdapter() {
	         public void mouseEntered(MouseEvent evt) {
	            Color c = StorePage.getBackground(); // When the mouse moves over a label, the background color changed.
	            StorePage.setBackground(StorePage.getForeground());
	            StorePage.setForeground(c);
	         }
	         public void mouseExited(MouseEvent evt) {
	            Color c = StorePage.getBackground();
	            StorePage.setBackground(StorePage.getForeground());
	            StorePage.setForeground(c);
	         }
	      });
		menupanel.add(StorePage);
		
		JButton Uninstall = new JButton("Uninstall");
		Uninstall.setFont(new Font(null, 0, 20));
		Uninstall.setForeground(Color.black);
		Uninstall.setContentAreaFilled(false);
		Uninstall.setBorderPainted(false);
		Uninstall.setFocusable(false);
		Uninstall.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Path path = Paths.get("Applications").toAbsolutePath();
				File file = new File(path + "/" + StrPath);
				file.delete();
				Installedpanel.removeAll();
				Installedpanel.repaint();
				menupanel.setVisible(false);
				try {
					@SuppressWarnings("unused")
					AppsInstalled apps = new AppsInstalled(frame);
				} catch (IOException e1) {
				
					e1.printStackTrace();
				}
				Installedpanel.repaint();
			}
		});
		Uninstall.addMouseListener(new MouseAdapter() {
	         public void mouseEntered(MouseEvent evt) {
	            Color c = Uninstall.getBackground(); // When the mouse moves over a label, the background color changed.
	            Uninstall.setBackground(Uninstall.getForeground());
	            Uninstall.setForeground(c);
	         }
	         public void mouseExited(MouseEvent evt) {
	            Color c = Uninstall.getBackground();
	            Uninstall.setBackground(Uninstall.getForeground());
	            Uninstall.setForeground(c);
	         }
	      });
		menupanel.add(Uninstall);
		
		JButton Dot = new JButton((Icon) new ImageIcon(image2));
		Dot.setBounds(275, 215, 30, 60);
		Dot.setBackground(null);
		Dot.setOpaque(false);
		Dot.setContentAreaFilled(false);
		Dot.setBorderPainted(false);
		Dot.setFocusable(false);
		Dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressed += 1;
				menupanel.setVisible(false);
				SideMenu(pressed, menupanel);
			}
			});
		
		

		Installedpanel.add(Dot);
		Installedpanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				menupanel.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		frame.add(Installedpanel);
		
			
		JButton App = new JButton((Icon) new ImageIcon(image));
		App.setBounds(AddY * Invoked, AddX, 200, 200);
		Installedpanel.add(App);
				App.setFocusable(false);
				App.setBorderPainted(false);
					
		if(Invoked >= 2)
		{
			App.setBounds(AddY * Invoked + 160, AddX, 200, 200);
			AppDetails.setBounds(AddY * Invoked + 160, AddX + 190, 200, 50);
			CreatorName.setBounds(AddY * Invoked + 160, AddX + 220, 200, 50);
			Dot.setBounds(AddY * Invoked + 335, AddX + 205, 30, 60);
			menupanel.setBounds(Dot.getBounds().x + 25, Dot.getBounds().y + 5,250,250);
		}
					
		App.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
							
				Path path = Paths.get("Applications").toAbsolutePath();
							
				Runtime runtime = Runtime.getRuntime();
							
				String loc = path + "/" + StrPath;
							
				File file = new File(loc);
							
							
				if(file.exists())
					{
						String location = "java -jar " + path + "/" + StrPath;		
					}
				}
			});

		}
	
	private static void SideMenu(int Pressed, JPanel menupanel)
	{		
		if(Pressed == 1)
		{
			menupanel.setVisible(false);
			
		}else
		{
			menupanel.setVisible(false);
			
		}
		menuactive = false;
		menupanel.setVisible(true);
		
		if(menuactive = false)
		{
			menupanel.setVisible(true);
		}else if(menuactive = true)
		{
		} 
	}
	}
