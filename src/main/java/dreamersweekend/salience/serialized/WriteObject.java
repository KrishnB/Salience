package dreamersweekend.salience.serialized;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import dreamersweekend.salience.commons.RunType;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.io.IO.Paths;

/**
 * @author 416474
 *
 * @param <T>
 */
public class WriteObject<T> {
	
	T obj;
	static SalienceProperties mVersion = new SalienceProperties();
	private String mType;
	public WriteObject(T obj, RunType paramType) {
		this.obj = obj;
		this.mType = paramType.toString();
		
	}
	
	
	public void writeObjectState(String filename){
		String brandname = mVersion.getBrandName();
		FileOutputStream mFout = null;
		ObjectOutputStream mOut = null;	
		try{
			checkDirectoryState(brandname, filename);
			mFout = new FileOutputStream(new File(Paths.OBJECT_STATE_FOLDER+File.separator+brandname+File.separator+mType+File.separator+filename+".ser"));
			mOut = new ObjectOutputStream(mFout);
			mOut.writeObject(obj);
			mOut.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void checkDirectoryState(String brandname, String filename) {
		File file = new File(Paths.OBJECT_STATE_FOLDER+File.separator+brandname+File.separator+mType);
		if(!file.isDirectory()){
			file.mkdirs();
		}
	}
	
}
