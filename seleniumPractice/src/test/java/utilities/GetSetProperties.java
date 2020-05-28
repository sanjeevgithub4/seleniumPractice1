package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class GetSetProperties {
	Properties prop = null;
	public String getProperties(String key) {
		 
		  try {
			  prop = new Properties();
			  InputStream input = new FileInputStream("./src/test/resources/config/config.properties");
			  prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return prop.getProperty(key);
		  
	}

	public void setProperties(String key1, String value1) {
		try {
			  prop = new Properties();
			  OutputStream output = new FileOutputStream("./src/test/resources/config/config.properties",true);
			  prop.setProperty(key1, value1);
			  prop.store(output, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty(key1));

	}

}
