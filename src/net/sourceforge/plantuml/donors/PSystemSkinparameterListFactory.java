/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.donors;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.command.PSystemSingleLineFactory;

public class PSystemSkinparameterListFactory extends PSystemSingleLineFactory {

	@Override
	protected AbstractPSystem executeLine(String line) {
		if (line.matches("(?i)^(skinparameters)\\s*$")) {
			return PSystemSkinparameterList.create();
		}
		return null;
	}

}
