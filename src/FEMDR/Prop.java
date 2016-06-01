package FEMDR;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Prop {
	
	static Prop instance;
	
	private Properties properties;
	
	private Prop() throws IOException {
		BufferedInputStream bis = new BufferedInputStream(
				Prop.class.getClassLoader().getResourceAsStream(
						"femdr.properties"
						));
		
		properties = new Properties();
		properties.load(bis);
	}
	
	static Prop getInstance() throws IOException {
		if (instance != null) {
			return instance;
		} else {
			instance = new Prop();
			return instance;
		}
	}
	
	static String getVersion() {
		String version = "";
		try {
			version = (String) getInstance().properties.get("FEMDR.version");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return version;
	}
	
	static String getRevision() {
		String revision = "";
		try {
			revision = (String) getInstance().properties.get("FEMDR.revision");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return revision;
	}

	static String getBeep() {
		String beep = "";
		try {
			beep = (String) getInstance().properties.get("FEMDR.audio.beep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beep;
	}
	
    public static void main(String[] args) {
		getVersion();
	}


}
