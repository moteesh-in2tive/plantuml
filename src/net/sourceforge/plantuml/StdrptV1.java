/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml;

import java.io.PrintStream;

import net.sourceforge.plantuml.command.PSystemAbstractFactory;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.error.PSystemError;

public class StdrptV1 implements Stdrpt {

	public void printInfo(final PrintStream output, Diagram sys) {
		if (sys == null || sys instanceof PSystemError) {
			out(output, (PSystemError) sys);
		}
	}

	private void out(final PrintStream output, final PSystemError err) {
		output.println("protocolVersion=1");
		if (empty(err)) {
			output.println("status=NO_DATA");
		} else {
			output.println("status=ERROR");
			output.println("lineNumber=" + err.getLineLocation().getPosition());
			for (ErrorUml er : err.getErrorsUml()) {
				output.println("label=" + er.getError());
			}
		}
		output.flush();
	}

	private boolean empty(final PSystemError err) {
		if (err == null) {
			return true;
		}
		for (ErrorUml er : err.getErrorsUml()) {
			if (PSystemAbstractFactory.EMPTY_DESCRIPTION.equals(er.getError()))
				return true;
		}
		return false;
	}

}
