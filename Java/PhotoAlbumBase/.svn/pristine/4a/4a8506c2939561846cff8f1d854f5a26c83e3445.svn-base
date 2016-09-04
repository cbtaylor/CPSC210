package ca.ubc.cs.cpsc210.photo;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class that manages a set of tags and enforces that
 * only a single tag with a given name exists in the system.
 */
public class TagManager {

	//Map from tag names to tags
	private Map<String, Tag> tags = new HashMap<String, Tag>();

	/**
	 * Get the tag with the given name, or null if no such tag exists.
	 */
	public Tag findTag(String name){
		return tags.get(name);
	}
	
	/**
	 * Create and return a new tag with the given name.
	 * @pre name != null
	 * @throws NameAlreadyUsedException if there is already a tag with the provided name.
	 */
	public Tag createTag(String name)  {
		if(tags.containsKey(name))
			return tags.get(name);
		
		Tag tag = new Tag(name);
		tags.put(name, tag);
		return tag;
	}
	
	/**
	 * Attempt to rename a tag. newName must not be null.
	 * Returns true/false
	 * @throws NameAlreadyUsedException if there is a different tag with the provided name.
	 */
	public boolean renameTag(String oldName, String newName) {
		/*
		 * Warning: this relies on the fact that Tag's
		 * .equals, .hashCode, etc. do NOT use the tag name;
		 * if they did, changing the name would mess up
		 * existing collections of tags.
		 */
		
		if(oldName.equals(newName)) return false;
		
		Tag tag = tags.get(oldName);
		if(tag == null) return false;
		
		if(tags.containsKey(newName))
			return false;
		
		tags.remove(oldName);
		tag.setName(newName);
		tags.put(newName, tag);
		return true;
	}
	
	/**
	 * @return The set of tags 
	 */
	public Set<Tag> getTags(){
		Set<Tag> tagSet = new HashSet<Tag>();
		for(Tag tag : tags.values())
			tagSet.add(tag);
		return Collections.unmodifiableSet(tagSet);
	}

	/**
	 * Remove a tag from the system.
	 * Does nothing if there is no tag with the provided name.
	 */
	public boolean removeTag(String name){
		Tag tag = tags.get(name);
		if(tag == null) return false;
		
		Set<Photo> photos = tag.getPhotos();
		for (Photo photo : photos)
			photo.removeTag(tag);

		tags.remove(name);
		return true;
	}
	
}
