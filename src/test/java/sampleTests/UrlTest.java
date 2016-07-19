package sampleTests;


import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class UrlTest {

	@Test
	public void test() {
		getDomainCount("http://hasbro.com/cs-cz/brands/playdoh");
	}
	
	private int getDomainCount(String baseUrl) {
		try {
			URL url = new URL(baseUrl);
			String host = url.getHost();
			//if(host.contains("www")) {
				String[] hosts = host.split("\\.");
				String topLevelDomain = "."+hosts[hosts.length-1];
				System.out.println(topLevelDomain);
				int topLevelDomainSize = topLevelDomain.length();
				System.out.println(topLevelDomainSize);
			//}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
