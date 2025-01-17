/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml;

import java.io.IOException;

import net.sourceforge.plantuml.security.SFile;

public class AParentFolderRegular implements AParentFolder {

	private final SFile dir;

	public AParentFolderRegular(SFile dir) {
		this.dir = dir;
		// Log.info("Creating AParentFolderRegular " + dir);
	}

	@Override
	public String toString() {
		return "AParentFolderRegular::" + (dir == null ? "NULL" : dir.getPrintablePath());
	}

	public AFile getAFile(String nameOrPath) throws IOException {
		final SFile filecurrent;
		// Log.info("AParentFolderRegular::looking for " + nameOrPath);
		// Log.info("AParentFolderRegular::dir = " + dir);
		if (dir == null) {
			filecurrent = new SFile(nameOrPath);
		} else {
			filecurrent = dir.getAbsoluteFile().file(nameOrPath);
		}
		// Log.info("AParentFolderRegular::Filecurrent " + filecurrent);
		if (filecurrent.exists()) {
			return new AFileRegular(filecurrent.getCanonicalFile());
		}
		return null;
	}

}
