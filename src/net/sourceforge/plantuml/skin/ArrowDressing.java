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
package net.sourceforge.plantuml.skin;

import java.util.Objects;

public class ArrowDressing {

	private final ArrowHead head;
	private final ArrowPart part;
	// private final ArrowDecoration decoration;

	public String name() {
		return toString();
	}

	@Override
	public String toString() {
		return head.name();
	}

	private ArrowDressing(ArrowHead head, ArrowPart part) {
		this.head = Objects.requireNonNull(head);
		this.part = Objects.requireNonNull(part);
	}

	public static ArrowDressing create() {
		return new ArrowDressing(ArrowHead.NONE, ArrowPart.FULL);
	}

	public ArrowDressing withHead(ArrowHead head) {
		return new ArrowDressing(head, part);
	}

	public ArrowDressing withPart(ArrowPart part) {
		return new ArrowDressing(head, part);
	}

	public ArrowHead getHead() {
		return head;
	}

	public ArrowPart getPart() {
		return part;
	}

}
