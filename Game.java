import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game {
	
	public static int AddY = 100;
	public static int AddX = 10;
	
	public static JPanel Gamespanel = new JPanel();
	
	static Image image;
	
	public static void game(JFrame frame, int Invoked, String Name, String url, String StrPath, String imageExt, String CName, String DevStatus, String Description)
	{
		//Games Panel
		Gamespanel.setBounds(0, 60, 1545, 740);
		Gamespanel.setBackground(Color.black);
		Gamespanel.setLayout(null);
		
		frame.add(Gamespanel);
		
		Path pathtocreate = Paths.get("Applications").toAbsolutePath();
    	
		String File = pathtocreate + "/AppData/App Icon/" + imageExt;
		
		File file = new File(File);
		
		try {
			image = ImageIO.read(file);
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		
		JButton Game = new JButton((Icon) new ImageIcon(image));
		
		
		Game.setBounds(AddY * Invoked, AddX, 200, 200);
		Gamespanel.add(Game);
		Game.setFocusable(false);
		Game.setBorderPainted(false);
		
		JLabel AppDetails = new JLabel(Name);
		AppDetails.setFont(new Font(null, 0, 20));
		AppDetails.setBounds(AddY * Invoked, AddX + 190, 240, 50);
		AppDetails.setForeground(Color.white);
		Gamespanel.add(AppDetails);
		
		JLabel CreatorName = new JLabel(CName);
		CreatorName.setBounds(AddY * Invoked, AddX + 220, 200, 50);
		CreatorName.setForeground(Color.white);
		Gamespanel.add(CreatorName);
		
		if(Invoked >= 2)
		{
			Game.setBounds(AddY * Invoked + 160, AddX, 200, 200);
			AppDetails.setBounds(AddY * Invoked + 160, AddX + 190, 200, 50);
			CreatorName.setBounds(AddY * Invoked + 160, AddX + 220, 200, 50);
		}
		
		Game.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				
				Path path = Paths.get("Applications").toAbsolutePath();
				
				Runtime runtime = Runtime.getRuntime();
				
				String loc = path + "/" + StrPath;
				
				File file = new File(loc);
				
				try {
					Store.StorePage(frame, imageExt, Name, CName, url, StrPath,DevStatus, Description);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Gamespanel.setVisible(false);
	}
	
});
}
}
