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
package net.sourceforge.plantuml.hcl;

import java.util.Objects;

public class HclTerm {

	private final SymbolType type;
	private final String data;

	public HclTerm(SymbolType type) {
		this.type = type;
		this.data = null;
	}

	public HclTerm(SymbolType type, String data) {
		this.type = type;
		this.data = Objects.requireNonNull(data);
	}

	@Override
	public String toString() {
		if (data == null)
			return type.toString();

		return type + "(" + data + ")";
	}

	public SymbolType getType() {
		return type;
	}

	public String getData() {
		return data;
	}

	public boolean is(SymbolType type) {
		return this.type == type;
	}

	public boolean is(SymbolType type1, SymbolType type2) {
		return this.type == type1 || this.type == type2;
	}

}
