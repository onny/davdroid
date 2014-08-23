/*******************************************************************************
 * Copyright (c) 2014 Ricki Hirner (bitfire web engineering).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Richard Hirner (bitfire web engineering) - initial API and implementation
 ******************************************************************************/
package at.bitfire.davdroid.mirakel.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a resource that can be contained in a LocalCollection or RemoteCollection
 * for synchronization by WebDAV.
 */
@ToString
public abstract class Resource {
	@Getter @Setter protected String name, ETag;
	@Getter @Setter protected String uid;
	@Getter protected long localID;

	
	public Resource(String name, String ETag) {
		this.name = name==null?null:name.replace(".ics","");
		this.ETag = ETag;
	}
	
	public Resource(long localID, String name, String ETag) {
		this(name, ETag);
		this.localID = localID;
	}
	
	// sets UID and resource name (= remote file name)
	public abstract void initialize();
	
	public abstract void parseEntity(InputStream entity) throws IOException, InvalidResourceException;
	public abstract ByteArrayOutputStream toEntity() throws IOException;
}
