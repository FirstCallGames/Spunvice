import java.io.*;
import java.net.*;
import java.nio.file.*;

public class Download {
	
    public static void download(String urlStr, String fileStr) throws MalformedURLException, IOException, URISyntaxException {
    	
    	Path pathtocreate = Paths.get("Applications").toAbsolutePath();
    	
		String File = pathtocreate + fileStr;
		downloadpercentage(urlStr);
    	
    	try (BufferedInputStream in1 = new BufferedInputStream(new URI(urlStr).toURL().openStream());	    			
    			
    			FileOutputStream fileOutputStream = new FileOutputStream(File)) {
    			    byte dataBuffer[] = new byte[1024];
    			    int bytesRead;
    			    while ((bytesRead = in1.read(dataBuffer, 0, 1024)) != -1) {
    			        fileOutputStream.write(dataBuffer, 0, bytesRead);
    			    }
    			} catch (IOException e) {
    			    // handle exception
    			}
    	
    	InputStream in1 = new URI(urlStr).toURL().openStream();
    	Files.copy(in1, Paths.get(File), StandardCopyOption.REPLACE_EXISTING);

    }
    
    public static void AppData()  throws MalformedURLException, IOException, URISyntaxException
    {
		String urlStr = "https://drive.google.com/uc?id=1By2TLSGA2rKZswIQeClzXLpiRMf_5I6d&export=download";
		
		Path pathtocreate = Paths.get("Applications").toAbsolutePath();
    	
		String File = pathtocreate + "/AppData.zip";
		
		File file = new File(pathtocreate.toString() + "/AppData");
		
		
    	if(file.exists())
    	{
    		
    	}else
    	{
    		@SuppressWarnings("unused")
    		BufferedInputStream in = new BufferedInputStream(new URI(urlStr).toURL().openStream());
        	
        	try (BufferedInputStream in1 = new BufferedInputStream(new URI(urlStr).toURL().openStream());
        			  FileOutputStream fileOutputStream = new FileOutputStream(File)) {
        			    byte dataBuffer[] = new byte[1024];
        			    int bytesRead;
        			    while ((bytesRead = in1.read(dataBuffer, 0, 1024)) != -1) {
        			        fileOutputStream.write(dataBuffer, 0, bytesRead);
        			    }
        			} catch (IOException e) {
        			    // handle exception
        			}
   
        	if(System.getProperty("os.name").equals("Windows 11")){
				System.out.println("extacted windows");
				String zipPath = pathtocreate+"/AppData.zip";
				Extractor.unzip(zipPath,pathtocreate.toString());
			}else{
				System.out.println("extacted mac");
				Unzip.UnzipFile();
			}
    	}
    	
    	
    }

    public static void downloadpercentage(String urlStr) throws URISyntaxException
    {
    	BufferedInputStream in = null;
    	
    	try {
    		//URI is uniform resource identifier
	        URI uri = new URI(urlStr);
	        URLConnection conn = uri.toURL().openConnection();
	        int size = conn.getContentLength();
	        System.out.println("File size: " + size);
	        
	        in = new BufferedInputStream(uri.toURL().openStream());
	        byte data[] = new byte[1024];
	        int count;
	        float sumCount = 0f;
	    	
	    	while ((count = in.read(data, 0, 1024)) != -1) {

	            sumCount += count;
	            if (size > 0) {
	                int pnt = (int) (sumCount / size * 100);
	                System.out.println(pnt);
	                
	            }
	    	}
    	}catch (MalformedURLException e1) {
	        e1.printStackTrace();
	            } catch (IOException e2) {
	        e2.printStackTrace();
	            }
    	
    	
	    	
}
}
