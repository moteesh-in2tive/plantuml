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

import net.sourceforge.plantuml.security.SFile;

public class BaseFile {

	private final String basename;
	private final SFile basedir;

	public BaseFile() {
		this.basedir = null;
		this.basename = null;
	}

	public BaseFile(SFile file) {
		this.basedir = file.getParentFile();
		this.basename = extractBasename(file.getName());
	}

	private static String extractBasename(String name) {
		final int idx = name.lastIndexOf('.');
		if (idx == -1) {
			return name;
		}
		return name.substring(0, idx);
	}

	@Override
	public String toString() {
		if (basedir == null || basename == null) {
			return "(DEFAULT)";
		}
		return basedir + " " + basename;
	}

	public String getBasename() {
		return basename;
	}

	public SFile getBasedir() {
		return basedir;
	}

	public SFile getTraceFile(String tail) {
		if (basedir == null || basename == null) {
			return new SFile(tail);
		}
		return basedir.file(basename + "_" + tail);
	}

}
