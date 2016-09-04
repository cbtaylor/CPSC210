package ca.ubc.cs.cpsc210.utility;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Create a thumbnail (small) image from a full image. The thumbnail will be
 * written to a specified directory. A future modification could be to save the
 * thumbnail in memory.
 * 
 * Based on Thumbnail.java by Marco Schmidt
 * (http://schmidt.devlib.org/java/save-jpeg-thumbnail.html) There appear to be
 * no licensing restrictions on this code
 * 
 * @author CPSC 211 Instructor
 */

public class Thumbnail {

	/**
	 * invariants width != 0 && height != 0 path is the full name of a valid
	 * path that can be written to name is a valid file name (not ending in
	 * .jpg)
	 */

	// Size of the thumbnail
	protected int width;
	protected int height;
	
	// Default sizes of thumbnails
	private final int DEFAULT_WIDTH = 64;
	private final int DEFAULT_HEIGHT = 64;

	// Full path to directory in which thumbnails will be stored, including trailing separator
	String path;

	// Name of thumbnail
	String name;;
	
	private final String THUMBNAIL_FILE_TYPE = ".jpg";

	/**
	 * Create a new thumbnail from the given image. Default size is 16x16
	 * 
	 * @pre thumbnailDirectoryPath is writeable
	 * @@ thumbnailName is a valid name for a file && fullImage != null
	 * @post true
	 * @param thumbnailDirectoryPath
	 *            The full path name of a writeable directory to store
	 *            thumbnails, including the trailing separator
	 * @param thumbnailName
	 *            The name given to the thumbnail
	 * @param fullImage
	 *            The image from which to create a thumbnail
	 */
	public Thumbnail(String thumbnailDirectoryPath, String thumbnailName,
			Image fullImage) {
		// Set the width/height in constructor to allow for extension of
		// another constructor that takes width/height as parameters
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		path = thumbnailDirectoryPath;
		name = thumbnailName;
		computeThumbnail(fullImage);
	}

	/**
	 * Retrieve the image for the thumbnail from the thumbnails directory
	 * 
	 * @pre true
	 * @post true
	 * @return The image for the thumbnail
	 * @throws ThumbnailDoesNotExistException when the thumbnail file is no longer available
	 */
	public Image getThumbnailImage() {
		
		try {
			
			String thumbnailFilename = new String(path + name + THUMBNAIL_FILE_TYPE);
			BufferedImage thumbnailImage = ImageIO.read( new File( thumbnailFilename ) );
			return thumbnailImage;
		} catch( IOException ioe ) {
			// This will not happen with test data. Simplifying for base version.
			return null;
		}
	}

	/**
	 * Compute the thumbnail image and store it in thumbnails directory.
	 * Note this method does not complain if it can't store the thumbnail 
	 * image. The complaint will happen on retrieval.
	 * 
	 * @pre fullImage != null
	 * @post thumbnail is stored in the appropriate directory
	 * @param fullImage Image to compute thumbnail from
	 */
	private void computeThumbnail(Image fullImage) {
		
		BufferedImage thumbnailImage;

		int thumbWidthToUse = width;
		int thumbHeightToUse = height;

		// Calculate the desired ratio of the thumbnail
		double thumbRatio = (double) width / (double) height;

		// Calculate the ratio of the image
		double imageRatio = (double) fullImage.getWidth(null)
				/ (double) fullImage.getHeight(null);

		if (thumbRatio < imageRatio) {
			thumbHeightToUse = (int) (width / imageRatio);
		} else {
			thumbWidthToUse = (int) (height / imageRatio);
		}

		// Draw the full image to the thumbnail image, scaling it on the fly
		thumbnailImage = new BufferedImage(thumbWidthToUse, thumbHeightToUse,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbnailImage.createGraphics();
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(fullImage, 0, 0, thumbWidthToUse,
				thumbHeightToUse, null);
		try {
			String thumbnailFilename = new String( path + name  + THUMBNAIL_FILE_TYPE);
			ImageIO.write(thumbnailImage, "jpg", new File( thumbnailFilename) );
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}

}
