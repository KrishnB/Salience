package dreamersweekend.salience.core;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dreamersweekend.salience.commons.Commons;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.serialized.ImagesState;

public class URLExtractor {
	
	private static HashMap<String, String> sUrls = new HashMap<>();
	private SalienceProperties mSalienceProp = new SalienceProperties();
	private Document mDoc;
	private Elements mTags;
	private String mBaseUrl;
	private ArrayList<String> mNamesList = new ArrayList<>();
	private ArrayList<String> mUrlsList = new ArrayList<>();
	private final int mLineNameLength = 15;
	
	public URLExtractor(String mBaseUrl) {
		this.mBaseUrl = mBaseUrl;
	}
	
	public void extractURLs(ImagesState state) {
		setUp();
		//int anchorElements = getAnchorElements();
		int anchorElements = 0;
		if(anchorElements != 0) {
			getUrls();
		} else {
			sUrls.put(mBaseUrl, "HomePage");
		}
		state.setUrlsMap(sUrls);
	}

	private void setUp(){
		try{
			System.setProperty("javax.net.ssl.trustStore", "/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/jre/lib/security/cacerts");
			String username = mSalienceProp.getProxyUser();
			String password = mSalienceProp.getProxyPassword();
			String login = username + ":" + password;
			String base64login = new String(Base64.encodeBase64(login.getBytes()));
			mDoc = Jsoup
					.connect(mBaseUrl)
					.header("Authorization", "Basic " + base64login)
					.timeout(10*1000)
					.get();
		}catch(Exception e){
			String proxyHost = mSalienceProp.getProxyHost();
			String proxyPort = mSalienceProp.getProxyPort();
			System.setProperty("http.proxyHost", proxyHost);
			System.setProperty("http.proxyPort", proxyPort);
			String username = mSalienceProp.getProxyUser();
			String password = mSalienceProp.getProxyPassword();
			String login = username + ":" + password;
			String base64login = new String(Base64.encodeBase64(login.getBytes()));
			try {
				mDoc = Jsoup
						.connect(mBaseUrl)
						.header("Authorization", "Basic " + base64login)
						.timeout(10*1000)
						.get();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	private int getAnchorElements(){
		mTags = mDoc.getElementsByTag("a");
		return mTags.size();
	}

	private void getUrls(){
		for(Element url : mTags) {
			if(url.hasAttr("href")) {
				String href = url.attr("href");
				if(isHrefValid(href)){
					String validUrl = "";
					if (!(href.startsWith("http")||href.startsWith("https"))) {
						String topLevelDomain = getTopLevelDomain(mBaseUrl);					
						String trimUrl = mBaseUrl.substring(0, mBaseUrl.indexOf(topLevelDomain)+topLevelDomain.length());
						if (href.startsWith("/")) {
							validUrl = trimUrl+href;
						} else {
							validUrl = trimUrl+"/"+href;
						}
						
					}	else {
						validUrl = href;
					}

						
					
					String eleName = getText(url);
					//System.out.println("***"+eleName+"="+validUrl+"***");
					if(!mUrlsList.contains(validUrl))
					sUrls.put(validUrl, eleName);
					mUrlsList.add(validUrl);
				}
			}
		}
		
		Set<String> keyUrls = sUrls.keySet();
		Iterator<String> keyIterator = keyUrls.iterator();
		System.out.println(sUrls.size());
		while (keyIterator.hasNext()) {
			String keyUrl = keyIterator.next();
			String keyText = sUrls.get(keyUrl);
			System.out.println(keyText+"="+keyUrl);
			
		}
		
	}

	private boolean isHrefValid(String href) {
		boolean validHref = false;
		boolean allLinksIncluded = Commons.isAllLinksIncluded();
		String brandname = mSalienceProp.getBrandName();
		String brandname1 = mSalienceProp.getBrandName1();
		String locale = mSalienceProp.getLocale();
		String locale1 = mSalienceProp.getLocale1();
		if(!(href.equals("")) && !(href.equals("#") || href.equals("##")) && !(href.equals("/"))|| (href.startsWith("http")) && !(href.startsWith("javascript")) && !(href.endsWith(".pdf"))){
			if(!allLinksIncluded){
				if(href.contains(brandname.toLowerCase()) || href.contains(brandname1.toLowerCase()) || href.startsWith("/"+locale) || href.startsWith("/"+locale1)){
					validHref = true;
				}else if(href.contains("terms") || href.contains("faq")){
					validHref = true;
				}else{
					validHref = true;
				}
			}else if(href.contains("terms") || href.contains("faq")){
				validHref = true;
			}else{
				validHref = true;
			}

		}else{
			validHref = false;
		}

		return validHref;
	}

	private String getText(Element ele){
		String urlText = null;
		String eleText = ele.text();
		String uniqueText = getUniqueText(eleText,ele);
		try{
		if(uniqueText.equals(ele.attr("href"))){
			String eleHref = ele.attr("href");
			urlText = eleHref;
			String formatText = "";
			if(urlText.contains("<") || urlText.contains(">") || urlText.contains(":") || urlText.contains("/") 
					|| urlText.contains("\\") || urlText.contains("|") || urlText.contains("?") || urlText.contains("*")|| urlText.contains(".") || urlText.contains(",")){
				formatText = urlText.replace("<", "").replace(">", "").replace(":", "").replace("/", "")
						.replace("\\", "").replace("|", "").replace("?", "").replace("*", "").replace(".", "").replace(",", "");
			}else{
				formatText = urlText;
			}
			if(formatText.equals("")) {
				formatText = "Home";
			}
			String finalName = null;
			if (formatText.length()>mLineNameLength) {
				finalName = formatText.substring(0, mLineNameLength);
			}else {
				finalName = formatText;
			}
			return finalName;
		}else{
			String formatText1 = null;
			if(uniqueText.contains("<") || uniqueText.contains(">") || uniqueText.contains(":") || uniqueText.contains("/") 
					|| uniqueText.contains("\\") || uniqueText.contains("|") || uniqueText.contains("?") || uniqueText.contains("*")|| uniqueText.contains(".") || uniqueText.contains(",")){
				formatText1 = uniqueText.replace("<", "").replace(">", "").replace(":", "").replace("/", "")
						.replace("\\", "").replace("|", "").replace("?", "").replace("*", "").replace(".", "").replace(",", "");
			
			
		} else {
			formatText1 = uniqueText;
		}
			String finalName1 = null;
			if (formatText1.length()>mLineNameLength) {
				finalName1 = formatText1.substring(0, mLineNameLength);
			}else {
				finalName1 = formatText1;
			}
			return finalName1;
		}
		}catch(Exception e) {
			return uniqueText;
		}
	}

	@Deprecated
	private String getUniqueText(String paramEleText) {
		String tempText = null;
		if(!paramEleText.equals("")) {
			if(mNamesList.contains(paramEleText)) {
				int incNameCount = getIncVal(paramEleText);
				if(incNameCount==1) {
					tempText = paramEleText;
					mNamesList.add(tempText+incNameCount);
				}
				return tempText;
				
			} else {
				mNamesList.add(paramEleText);
				return paramEleText;
			}
		} else {
			return "NoName";
		}
	}
	
	private String getUniqueText(String paramEleText, Element ele) {
		String tempText = null;
		if(!paramEleText.equals("")) {
			if(mNamesList.contains(paramEleText)) {
				int incNameCount = getIncVal(paramEleText);
				if(incNameCount==1) {
					tempText = paramEleText;
					mNamesList.add(tempText+incNameCount);
				}
				return tempText;
				
			} else {
				mNamesList.add(paramEleText);
				return paramEleText;
			}
		} else {
			return ele.attr("href");
		}
	}

	private String getTopLevelDomain(String baseUrl) {
		String topLevelDomain = null;
		try {
			URL url = new URL(baseUrl);
			String host = url.getHost();
			String[] hosts = host.split("\\.");
			topLevelDomain = "."+hosts[hosts.length-1];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return topLevelDomain;
	}
	
	private int getIncVal(String name) {
		int count = 0;
		Iterator<String> namesIterator = mNamesList.iterator();
		while(namesIterator.hasNext()) {
			String tempName = namesIterator.next();
			if(tempName.contains(name)) {
				count++;
			}
		}	
		return count;
	}

}
