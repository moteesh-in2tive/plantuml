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
package net.sourceforge.plantuml.bpm;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.api.ThemeStyle;
import net.sourceforge.plantuml.command.Command;
import net.sourceforge.plantuml.command.PSystemCommandFactory;
import net.sourceforge.plantuml.core.DiagramType;
import net.sourceforge.plantuml.core.UmlSource;

public class BpmDiagramFactory extends PSystemCommandFactory {

	public BpmDiagramFactory(DiagramType type) {
		super(DiagramType.BPM);
	}

	@Override
	protected List<Command> createCommands() {
		final List<Command> result = new ArrayList<>();
		result.add(new CommandDockedEvent());
		result.add(new CommandMerge());
		result.add(new CommandResume());
		result.add(new CommandGoto());
		result.add(new CommandNewBranch());
		result.add(new CommandElseBranch());
		result.add(new CommandEndBranch());
		return result;
	}

	@Override
	public AbstractPSystem createEmptyDiagram(ThemeStyle style, UmlSource source, ISkinSimple skinParam) {
		return new BpmDiagram(style, source);
	}

}
