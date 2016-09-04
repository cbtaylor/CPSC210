package ca.ubc.cs.cpsc210.photo;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that manages a collection of albums, and thereby also a collection of photos.
 * To add/remove photos in this collection, simply add/remove them in the albums.
 */
public class PhotoManager {
	
	private Set<Album> albums = new HashSet<Album>();
	
	/**
	 * @return The set of albums currently in the system.
	 */
	public Set<Album> getAlbums(){
		return Collections.unmodifiableSet(albums);
	}

	/**
	 * Add an album.
	 */
	public void addAlbum(Album album){
		albums.add(album);
	}

	/**
	 * Remove an album.
	 */
	public void removeAlbum(Album album) {
		albums.remove(album);
	}
	
	/**
	 * Return a particular album
	 */
	public Album findAlbum(String name) {
		for (Album anAlbum: albums)
			if (anAlbum.getName().equals(name))
				return anAlbum;
		return null;
	}

	
	/**
	 * @return The set of photos currently in the system.
	 */
	public Set<Photo> getPhotos(){
		Set<Photo> photos = new HashSet<Photo>();
		for(Album album : albums) 
			photos.addAll(album.getPhotos());

		return Collections.unmodifiableSet(photos);
	}
	
	
	/**
	 * @return The photos in the system that were added in the provided date range (inclusive).
	 */
	public Set<Photo> findPhotosInDateRange(Date start, Date end){
		Set<Photo> photos = new HashSet<Photo>();

		for(Photo photo: getPhotos()) {
			if(!photo.getDateAdded().before(start) && !photo.getDateAdded().after(end))
				photos.add(photo);
		}

		return Collections.unmodifiableSet(photos);
	}
}
