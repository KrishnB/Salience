package dreamersweekend.salience.serialized;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;

public class ImagesState implements Serializable{

	/**
	 * Saving object state
	 */
	private static final long serialVersionUID = 8107266869536282747L;
	
	List<File> mImgFiles = new ArrayList<>();
	HashMap<String, String> mUrlsMap = new LinkedHashMap<>();
	
	public void addtoImageFiles(File fileName){
		mImgFiles.add(fileName);
	}
	
	public List<File> getImageFiles(){
		return mImgFiles;
	}
	
	public void addtoUrlsMap(String validUrl, String eleName) {
		mUrlsMap.put(validUrl, eleName);
	}
	
	public HashMap<String, String> getUrlsMap() {
		return mUrlsMap;
	}
	
	public void setUrlsMap(HashMap<String, String> sUrls) {
		this.mUrlsMap = sUrls;
	}

	public void clearUrlsMap() {
		mUrlsMap.clear();
	}

}
