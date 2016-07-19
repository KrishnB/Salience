package dreamersweekend.salience.statuscode;

public final class StatusCodeValue {
	
	public static final String BASE_IMAGE_FOUND = "Base Image is available";
	public static final String CURRENT_IMAGE_FOUND = "Current Image available for Base Image Comparison";
	public static final String BASE_IMAGE_NOT_FOUND = "Base Image not available";
	public static final String CURRENT_IMAGE_NOT_FOUND = "Current Image not available looks like the page is removed from current webpage";
	
	/*
	 * These status code values might be redundant but holding them till a
	 * round of execution.
	 */
	public static final String BASE_URL_FOUND = "Base URL found";
	public static final String BASE_URL_NOT_FOUND = "Base URL not found";
	public static final String CURRENT_URL_FOUND = "Current URL found";
	public static final String CURRENT_URL_NOT_FOUND = "Current URL not found";
	
	public static final String BASE_CURRENT_IMAGES_NOT_FOUND = "base image is not found,"
			+ "looks like the url is newly added to current webpage";
	
	public static final String EXTREMELY_INCOMPATIBLE_IMAGES = "Either base or current url has thrown error or have differences in dimensions. Refer base image/url and current image/url";
	
	public static final String MATCHED_IMAGES = "Current Image matches with its base image";
	
	public static final String MISMATCHED_IMAGES = "Current Image does not match with its base image";
	
	public static final String ERROR = "Unknown Error";
}
