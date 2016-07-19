package dreamersweekend.salience.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import dreamersweekend.salience.commons.Commons;
import dreamersweekend.salience.commons.ImageType;
import dreamersweekend.salience.commons.OSType;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.io.IO.Paths;
import dreamersweekend.salience.stepDefinitions.SalienceStepDefinitions;

/**
 * @author 416474
 *
 */
public class ImageWriter{

	private String mBaseImageVersion, mCurrentImageVersion, mIncompatibleImageVersion;
	private File mImageFile;
	private byte[] mImageArr;
	private OSType mOsType = new OSType();
	private SalienceProperties mSalienceProp = new SalienceProperties();
	private String mImageFormat = mSalienceProp.getImageFormat();
	private static final String sIncompatibleImageFormat = ".jpg";

	public ImageWriter(File mImageFile, byte[] mImageArr){
		this.mImageFile = mImageFile;
		this.mImageArr = mImageArr;
	}

	public ImageWriter(File mImageFile){
		this.mImageFile = mImageFile;
	}

	public void writeImage(ImageType imageType, String scenario, File file){
		mBaseImageVersion = mSalienceProp.getBaseVersion();
		mCurrentImageVersion = mSalienceProp.getCurrentVersion();
		mIncompatibleImageVersion = mSalienceProp.getIncompatibleVersion();
		switch(imageType){
		case BASE_IMAGE:
			if(mOsType.isOSLinuxType() || mOsType.isOSMacType()){
				if(SalienceStepDefinitions.getBrowserName().toLowerCase().contains("fire")){
					File op = new File(Paths.BASE_IMAGE_ABS_DIR_PATH+mBaseImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
					try {
						FileUtils.copyFile(file, op);
					} catch (IOException e) {
						
					}
				}else{
					try{
						ByteArrayInputStream in = new ByteArrayInputStream(mImageArr);
						BufferedImage im = ImageIO.read(in);
						File op = new File(Paths.BASE_IMAGE_ABS_DIR_PATH+mBaseImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
						ImageIO.write(im, mImageFormat, op);
					}catch (IOException e) {
					
					}
				}
			}else if(mOsType.isOSWinType()){
				if(SalienceStepDefinitions.getBrowserName().toLowerCase().contains("fire")){
					File op = new File(Paths.BASE_IMAGE_ABS_DIR_PATH+mBaseImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
					try {
						FileUtils.copyFile(file, op);
					} catch (IOException e) {
					
					}
				}else{
					try{
						ByteArrayInputStream in = new ByteArrayInputStream(mImageArr);
						BufferedImage im = ImageIO.read(in);
						File op = new File(Paths.BASE_IMAGE_ABS_DIR_PATH+mBaseImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
						ImageIO.write(im, mImageFormat, op);
					}catch (IOException e) {
						
					}
				}
			}
			break;
		case CURRENT_IMAGE:
			if(mOsType.isOSLinuxType() || mOsType.isOSMacType()){
				if(SalienceStepDefinitions.getBrowserName().toLowerCase().contains("fire")){
					File op = new File(Paths.CURRENT_IMAGE_ABS_DIR_PATH+mCurrentImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
					try {
						FileUtils.copyFile(file, op);
					} catch (IOException e) {
					
					}
				}else{
					try{
						ByteArrayInputStream in = new ByteArrayInputStream(mImageArr);
						BufferedImage im = ImageIO.read(in);
						File op = new File(Paths.CURRENT_IMAGE_ABS_DIR_PATH+mCurrentImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
						ImageIO.write(im, mImageFormat, op);
					}catch (IOException e) {
						
					}
				}
			}else if(mOsType.isOSWinType()){
				if(SalienceStepDefinitions.getBrowserName().toLowerCase().contains("fire")){
					File op = new File(Paths.CURRENT_IMAGE_ABS_DIR_PATH+mCurrentImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
					try {
						FileUtils.copyFile(file, op);
					} catch (IOException e) {
						
					}
				}else{
					try{
						ByteArrayInputStream in = new ByteArrayInputStream(mImageArr);
						BufferedImage im = ImageIO.read(in);
						File op = new File(Paths.CURRENT_IMAGE_ABS_DIR_PATH+mCurrentImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
						ImageIO.write(im, mImageFormat, op);
					}catch (IOException e) {
						
					}
				}
			}
			break;
		case INCOMPATIBLE_IMAGE:
			if(mOsType.isOSLinuxType() || mOsType.isOSMacType()){
				try{
					ByteArrayInputStream in = new ByteArrayInputStream(mImageArr);
					BufferedImage im = ImageIO.read(in);
					File op = new File(Paths.INCOMPATIBLE_IMAGE_ABS_DIR_PATH+mIncompatibleImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat);
					ImageIO.write(im, mImageFormat, op);
					Commons.addToIncompatibleUrls(mImageFile.getName(), op.getAbsolutePath());
				}catch (IOException e) {
					
				}
			}else if(mOsType.isOSWinType()){
				try{
					ByteArrayInputStream in = new ByteArrayInputStream(mImageArr);
					BufferedImage im = ImageIO.read(in);
					File op = new File(Paths.INCOMPATIBLE_IMAGE_ABS_DIR_PATH+mIncompatibleImageVersion+File.separator+scenario+File.separator+mImageFile+sIncompatibleImageFormat);
					ImageIO.write(im, mImageFormat, op);
					Commons.addToIncompatibleUrls(mImageFile.getName(), op.getAbsolutePath());
				}catch (IOException e) {
					
				}
			}
			break;
		}

	}
}



