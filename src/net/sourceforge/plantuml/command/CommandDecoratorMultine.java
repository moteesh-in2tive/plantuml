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
package net.sourceforge.plantuml.command;

import net.sourceforge.plantuml.core.Diagram;

public class CommandDecoratorMultine<D extends Diagram> implements Command<D> {

	private final SingleLineCommand2<D> cmd;
	private final boolean removeEmptyColumn;
	private final int nbMaxLines;
	
	public CommandDecoratorMultine(SingleLineCommand2<D> cmd, int nbMaxLines) {
		this(cmd, false, nbMaxLines);
	}

	public CommandDecoratorMultine(SingleLineCommand2<D> cmd, boolean removeEmptyColumn, int nbMaxLines) {
		this.cmd = cmd;
		this.removeEmptyColumn = removeEmptyColumn;
		this.nbMaxLines = nbMaxLines;
	}

	public CommandExecutionResult execute(D diagram, BlocLines lines) {
		if (removeEmptyColumn) {
			lines = lines.removeEmptyColumns();
		}
		lines = lines.toSingleLineWithHiddenNewLine();
		return cmd.execute(diagram, lines);
	}

	public CommandControl isValid(BlocLines lines) {
		if (cmd.isCommandForbidden()) {
			return CommandControl.NOT_OK;
		}
		lines = lines.toSingleLineWithHiddenNewLine();
		if (cmd.isForbidden(lines.getFirst().getString())) {
			return CommandControl.NOT_OK;
		}
		final CommandControl tmp = cmd.isValid(lines);
		if (tmp == CommandControl.OK_PARTIAL) {
			throw new IllegalStateException();
		}
		if (tmp == CommandControl.OK) {
			return tmp;
		}
		return CommandControl.OK_PARTIAL;
	}

	public String[] getDescription() {
		return cmd.getDescription();
	}

	public int getNbMaxLines() {
		return nbMaxLines;
	}

}
