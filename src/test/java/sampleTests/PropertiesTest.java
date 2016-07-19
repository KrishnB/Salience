package sampleTests;


import org.junit.Test;

import dreamersweekend.salience.commons.SalienceProperties;

public class PropertiesTest {

	@Test
	public void test() {
		SalienceProperties version = new SalienceProperties();
		version.setScenarioName("Hey", 1);
		System.out.println(version.getBaseVersion());
	}

}
