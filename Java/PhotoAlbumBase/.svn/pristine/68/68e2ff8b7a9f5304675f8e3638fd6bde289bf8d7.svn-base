package ca.ubc.cs.cpsc210.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.cpsc210.photo.Album;
import ca.ubc.cs.cpsc210.photo.Photo;

/**
 * Tests Album, except visual support (like display, we'll get to that in Project Part 4)
 */

public class AlbumTest {

	Album album1;
	Album album2;
	Photo photo1;
	Photo photo2;

	@Before
	public void setUp() throws Exception {
		album1 = new Album("1");
		album2 = new Album("2");
		photo1 = new Photo("1");
		photo2 = new Photo("2");
	}

	@Test
	public void testChangeAlbumName() {
		assertEquals("1", album1.getName());	
		album1.setName("One");
		assertEquals("One", album1.getName());
	}

	@Test
	public void testAddPhoto()  {
		album1.addPhoto(photo1);	
		Set<Photo> photosInAlbum = album1.getPhotos();
		assertEquals(1, photosInAlbum.size());
		assertTrue(photosInAlbum.contains(photo1));	
		assertEquals(album1, photo1.getAlbum());
	}
	
	public void testAddMoreThanOnePhoto()  {
		album1.addPhoto(photo1);
		album1.addPhoto(photo2);
		Set<Photo> photosInAlbum = album1.getPhotos();
		assertEquals(2, photosInAlbum.size());
		assertTrue(photosInAlbum.contains(photo1));
		assertTrue(photosInAlbum.contains(photo2));
		
		assertEquals(album1, photo1.getAlbum());
		assertEquals(album1, photo2.getAlbum());
	}
	
	// Remove for base version
//	@Test(expected=PhotoAlreadyInAlbumException.class)
//	public void testAddPhotoTwice() throws PhotoAlreadyInAlbumException {
//		album1.addPhoto(photo1);
//		album1.addPhoto(photo1);
//	}
//	
//	@Test(expected=PhotoAlreadyInAlbumException.class)
//	public void testAddPhotoToDifferentAlbums() throws PhotoAlreadyInAlbumException {
//		album1.addPhoto(photo1);
//		album2.addPhoto(photo1);
//	}
	
	@Test
	public void testMovePhotoBetweenAlbums()  {
		album1.addPhoto(photo1);
		album1.removePhoto(photo1);
		album2.addPhoto(photo2);
		assertEquals(0, album1.getPhotos().size());
		assertEquals(1, album2.getPhotos().size());
		assertTrue(album2.getPhotos().contains(photo2));
		assertEquals(album2, photo2.getAlbum());
	}
	
	@Test
	public void testRemovePhoto() {
		album1.addPhoto(photo1);
		album1.removePhoto(photo1);
	}
	
	// Remove for base version
//	@Test(expected=PhotoDoesNotExistException.class)
//	public void testRemovePhotoTwice() throws PhotoDoesNotExistException {
//		album1.removePhoto(photo1);
//		album1.removePhoto(photo1);
//	}
	
}
