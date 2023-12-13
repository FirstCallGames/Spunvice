import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class createFolder {

	@SuppressWarnings("unused")
	createFolder(){
		Path pathtocreate = Paths.get("Spunivce.jar").toAbsolutePath();
    	
		String path = pathtocreate.toString();
		path = "Applications";
      
		File f1 = new File(path); 
        
		boolean bool = f1.mkdir();
		
		String path1 = pathtocreate.toString() + "Applications/AppData";
      
		File f2 = new File(path1); 
        
		boolean bool1 = f2.mkdir();
		}  
   }  

