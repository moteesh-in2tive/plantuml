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
package net.sourceforge.plantuml.openiconic;

public class StringDecipher {

	public static String decipher(String path) {
		path = path.trim();
		path = path.replace(',', ' ');
		path = path.replaceAll("\\s+", " ");
		path = path.replaceAll("([^e\\s])-", "$1 -");
		path = path.replaceAll("([a-df-zA-Z])(\\S)", "$1 $2");
		path = path.replaceAll("(\\S)([a-df-zA-Z])", "$1 $2");
		path = path.replaceAll("([a-df-zA-Z])(\\S)", "$1 $2");
		while (path.matches(".*\\.\\d+\\..*")) {
			path = path.replaceAll("(\\.\\d+)\\.", "$1 .");
		}
		return path;
	}

}
