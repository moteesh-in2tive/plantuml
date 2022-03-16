/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml;

import java.io.File;
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

	public void errorLine(int lineError, File file) {
		Log.error("Error line " + (lineError + 1) + " in file: " + file.getPath());
	}

	private void out(final PrintStream output, final PSystemError err) {
		output.println("protocolVersion=1");
		if (empty(err)) {
			output.println("status=NO_DATA");
		} else {
			output.println("status=ERROR");
			output.println("lineNumber=" + (err.getLineLocation().getPosition() + 1));
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

	public void finalMessage(ErrorStatus error) {
		if (error.hasError()) {
			Log.error("Some diagram description contains errors");
		}
		if (error.isNoData()) {
			Log.error("No diagram found");
		}
	}

}
