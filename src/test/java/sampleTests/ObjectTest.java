package sampleTests;

import java.io.File;
import org.junit.Test;

import dreamersweekend.salience.commons.RunType;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.exceptions.SalienceException;
import dreamersweekend.salience.serialized.ImagesState;
import dreamersweekend.salience.serialized.ReadObject;
import dreamersweekend.salience.serialized.WriteObject;

public class ObjectTest {

	@Test
	public void test() {
		ImagesState state = new ImagesState();
		state.addtoImageFiles(new File("Hello"));
		SalienceProperties prop = new SalienceProperties();
		prop.setFilename("Play");
		String fileName = prop.getFileName("Play");
		WriteObject<ImagesState> obj = new WriteObject<>(state, RunType.BASE);
		obj.writeObjectState(fileName);
		ReadObject<ImagesState> obj1 = new ReadObject<>(RunType.BASE);
		ImagesState obj2 = null;
		try {
			obj2 = obj1.readObjectState(fileName);
		} catch (SalienceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(obj2.getImageFiles());
	
	}

}
