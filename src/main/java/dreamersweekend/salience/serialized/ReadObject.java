package dreamersweekend.salience.serialized;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import dreamersweekend.salience.commons.RunType;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.exceptions.SalienceException;
import dreamersweekend.salience.io.IO.Paths;

/**
 * @author 416474
 *
 * @param <T>
 */
public class ReadObject<T> {
	T obj;
	private String mType;
	private static SalienceProperties mVersion = new SalienceProperties();
	
	public ReadObject(RunType paramType) {
		System.out.println(paramType.toString());
		this.mType = paramType.toString();
	}
	
	@SuppressWarnings("unchecked")
	public T readObjectState(String filename) throws SalienceException{
		String brandname = mVersion.getBrandName();
		FileInputStream fIn = null;
		ObjectInputStream in = null;
		try{
			fIn = new FileInputStream(Paths.OBJECT_STATE_FOLDER+File.separator+brandname+File.separator+mType+File.separator+filename+".ser");
			in = new ObjectInputStream(fIn);
			obj = (T)in.readObject();
			in.close();
		}catch(Exception e){
			throw new SalienceException();
		}
		return obj;
	}
}
