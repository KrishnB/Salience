package dreamersweekend.salience.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dreamersweekend.salience.commons.Commons;
import dreamersweekend.salience.commons.ImageType;
import dreamersweekend.salience.commons.OSType;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.io.IO.Paths;

/**
 * @author 416474
 *
 */
public class ImageReader {
	private String mBaseImageVersion, mCurrentImageVersion;
	private static BufferedImage mBaseImage,mCurrentImage;
	private File mImageFile;
	private OSType mOsType = new OSType();
	private SalienceProperties mNayanProp = new SalienceProperties();
	private String mImageFormat = mNayanProp.getImageFormat();

	public ImageReader(File mImageFile){
		this.mImageFile = mImageFile;
	}

	public BufferedImage readImage(ImageType imageType, String scenario){
		mBaseImageVersion = mNayanProp.getBaseVersion();
		mCurrentImageVersion = mNayanProp.getCurrentVersion();
		switch(imageType){
		case BASE_IMAGE:
			if(mOsType.isOSLinuxType() || mOsType.isOSMacType()){
				try{
					String path = Paths.BASE_IMAGE_DIR_PATH+mBaseImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat;
					mBaseImage = ImageIO.read(getClass().getResourceAsStream(path));
					Commons.addToRemarks(mImageFile.getName(), "Base Page available");
				}catch (IOException e) {
					Commons.addToRemarks(mImageFile.getName(), "Base Page is not available");
					System.out.println("Base Image: "+mImageFile+" is not available in base image url");
				}
				return mBaseImage;
			}else if(mOsType.isOSWinType()){
				try{
					String path = Paths.BASE_IMAGE_DIR_PATH+mBaseImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat;
					mBaseImage = ImageIO.read(getClass().getResource(path));
					Commons.addToRemarks(mImageFile.getName(), "Base Page available");
				}catch (IOException e) {
					Commons.addToRemarks(mImageFile.getName(), "Base Page is not available");
					System.out.println("Base Image: "+mImageFile+" is not available in base image url");
				}
				return mBaseImage;
			}
			break;
			
		case CURRENT_IMAGE:
			if(mOsType.isOSLinuxType() || mOsType.isOSMacType()){
				try{
					String path = Paths.CURRENT_IMAGE_DIR_PATH+mCurrentImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat;
					mCurrentImage = ImageIO.read(getClass().getResourceAsStream(path));
					Commons.addToRemarks(mImageFile.getName(), "Current Page available");
				}catch (IOException e) {
					Commons.addToRemarks(mImageFile.getName(), "Current Page is not available");
					System.out.println("Current Image: "+mImageFile+" is not available in current image url");
				}
				return mCurrentImage;
			}else if(mOsType.isOSWinType()){
				try{
					String path = Paths.CURRENT_IMAGE_DIR_PATH+mCurrentImageVersion+File.separator+scenario+File.separator+mImageFile+"."+mImageFormat;
					mCurrentImage = ImageIO.read(getClass().getResource(path));
					Commons.addToRemarks(mImageFile.getName(), "Current Page available");
				}catch (IOException e) {
					Commons.addToRemarks(mImageFile.getName(), "Current Page is not available");
					System.out.println("Current Image: "+mImageFile+" is not available in current image url");
				}
				return mCurrentImage;
			}
			break;
		default:
			break;
		}
		return null;
		
	}


}
