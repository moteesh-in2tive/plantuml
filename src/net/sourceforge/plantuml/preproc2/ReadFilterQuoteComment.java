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
package net.sourceforge.plantuml.preproc2;

import java.io.IOException;

import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.preproc.ReadLine;

public class ReadFilterQuoteComment implements ReadFilter {

	public ReadLine applyFilter(final ReadLine source) {
		return new ReadLine() {
			public void close() throws IOException {
				source.close();
			}

			public StringLocated readLine() throws IOException {
				boolean longComment = false;
				while (true) {
					final StringLocated result = source.readLine();
					if (result == null) {
						return null;
					}
					final String trim = result.getString().replace('\t', ' ').trim();
					if (longComment && trim.endsWith("'/")) {
						longComment = false;
						continue;
					}
					if (longComment) {
						continue;
					}
					if (trim.startsWith("'")) {
						continue;
					}
					if (trim.startsWith("/'") && trim.endsWith("'/")) {
						continue;
					}
					if (trim.startsWith("/'") && trim.contains("'/") == false) {
						longComment = true;
						continue;
					}
					return ((StringLocated) result).removeInnerComment();
				}
			}
		};
	}

}
