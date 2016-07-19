package dreamersweekend.salience.commons;


/**
 * Compatibility States between Base Image and Current Image.
 *  
 * @author 416474
 *
 */
public enum Bool {
	
	/** When Current Image matches with Base Image.*/
	YES,

	/** When Current Image does not match with Base Image.*/
	NO,
	
	/** When either of Current Image or Base Image is not found.*/
	EXTREMELY_INCOMPATIBLE
	
}
