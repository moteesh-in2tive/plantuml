/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2017, Arnaud Roques
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
package net.sourceforge.plantuml.project3;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexResult;

public class CommandGanttArrow extends SingleLineCommand2<GanttDiagram> {

	public CommandGanttArrow() {
		super(getRegexConcat());
	}

	static RegexConcat getRegexConcat() {
		return new RegexConcat(new RegexLeaf("^"), //
				new RegexLeaf("CODE1", "([\\p{L}0-9_.]+)"), //
				new RegexLeaf("[%s]*"), //
				new RegexLeaf("ARROW", "(-+)\\>"), //
				new RegexLeaf("[%s]*"), //
				new RegexLeaf("CODE2", "([\\p{L}0-9_.]+)"), //
				new RegexLeaf("[%s]*"), //
				new RegexLeaf("$"));
	}

	@Override
	protected CommandExecutionResult executeArg(GanttDiagram diagram, RegexResult arg) {

		final String code1 = arg.get("CODE1", 0);
		final String code2 = arg.get("CODE2", 0);
		final Task task1 = diagram.getExistingTask(code1);
		if (task1 == null) {
			return CommandExecutionResult.error("No such task " + code1);
		}
		final Task task2 = diagram.getExistingTask(code2);
		if (task2 == null) {
			return CommandExecutionResult.error("No such task " + code2);
		}

		final TaskInstant end1 = new TaskInstant(task1, TaskAttribute.END);

		task2.setStart(end1.getInstantPrecise());
		diagram.addContraint(new GanttConstraint(end1, new TaskInstant(task2, TaskAttribute.START)));

		return CommandExecutionResult.ok();
	}

}