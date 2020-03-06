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
 */
package net.sourceforge.plantuml.error;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.AbstractPSystem;
import net.sourceforge.plantuml.BackSlash;
import net.sourceforge.plantuml.ErrorUml;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.FileImageData;
import net.sourceforge.plantuml.LineLocation;
import net.sourceforge.plantuml.StringLocated;
import net.sourceforge.plantuml.api.ImageDataAbstract;
import net.sourceforge.plantuml.api.ImageDataSimple;
import net.sourceforge.plantuml.asciiart.UmlCharArea;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.GraphicStrings;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.HtmlColorUtils;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.graphic.TextBlockRaw;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.svek.TextBlockBackcolored;
import net.sourceforge.plantuml.ugraphic.ColorMapperIdentity;
import net.sourceforge.plantuml.ugraphic.ImageBuilder;
import net.sourceforge.plantuml.ugraphic.txt.UGraphicTxt;

public abstract class PSystemError extends AbstractPSystem {

	protected List<StringLocated> trace;
	protected ErrorUml singleError;

	final protected StringLocated getLastLine() {
		return trace.get(trace.size() - 1);
	}

	final public LineLocation getLineLocation() {
		return getLastLine().getLocation();
	}

	final public Collection<ErrorUml> getErrorsUml() {
		return Collections.singleton(singleError);
	}

	final public String getWarningOrError() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getDescription());
		sb.append(BackSlash.CHAR_NEWLINE);
		for (CharSequence t : getTitle().getDisplay()) {
			sb.append(t);
			sb.append(BackSlash.CHAR_NEWLINE);
		}
		sb.append(BackSlash.CHAR_NEWLINE);
		return sb.toString();
	}

	private TextBlockBackcolored getGraphicalFormatted() {
		final FontConfiguration fc0 = GraphicStrings.sansSerif14(HtmlColorUtils.BLACK).bold();
		final FontConfiguration fc1 = GraphicStrings.sansSerif14(HtmlColorUtils.MY_GREEN).bold();
		final FontConfiguration fc2 = GraphicStrings.sansSerif14(HtmlColorUtils.RED).bold();

		final List<String> fullBody = getTextFullBody();
		final TextBlock result0 = TextBlockUtils.addBackcolor(
				TextBlockUtils.withMargin(new TextBlockRaw(getTextFromStack(), fc0), 1, 1, 1, 4),
				HtmlColorUtils.MY_GREEN);
		final TextBlock result1 = new TextBlockRaw(allButLast(fullBody), fc1);
		final TextBlock result2 = new TextBlockRaw(onlyLast(fullBody), fc1.wave(HtmlColorUtils.RED));
		final TextBlock result3 = new TextBlockRaw(getTextError(), fc2);
		TextBlock result = result0;
		result = TextBlockUtils.mergeTB(result, result1, HorizontalAlignment.LEFT);
		result = TextBlockUtils.mergeTB(result, result2, HorizontalAlignment.LEFT);
		result = TextBlockUtils.mergeTB(result, result3, HorizontalAlignment.LEFT);
		result = TextBlockUtils.withMargin(result, 5, 5);
		return TextBlockUtils.addBackcolor(result, HtmlColorUtils.BLACK);
	}

	private List<String> getPureAsciiFormatted() {
		final List<String> result = getTextFromStack();
		result.addAll(getTextFullBody());
		result.add("^^^^^");
		result.addAll(getTextError());
		return result;
	}

	private List<String> getTextFromStack() {
		LineLocation lineLocation = getLineLocation();
		final List<String> result = new ArrayList<String>();
		if (lineLocation != null) {
			append(result, lineLocation);
			while (lineLocation.getParent() != null) {
				lineLocation = lineLocation.getParent();
				append(result, lineLocation);
			}
		}
		return result;
	}

	private List<String> getTextFullBody() {
		final List<String> result = new ArrayList<String>();
		result.add(" ");
		final int traceSize = trace.size();
		if (traceSize > 40) {
			for (StringLocated s : trace.subList(0, 5)) {
				addToResult(result, s);
			}
			result.add("...");
			final int skipped = traceSize - 5 - 20;
			result.add("... ( skipping " + skipped + " lines )");
			result.add("...");
			for (StringLocated s : trace.subList(traceSize - 20, traceSize)) {
				addToResult(result, s);
			}
		} else {
			for (StringLocated s : trace) {
				addToResult(result, s);
			}
		}
		return result;
	}

	private void addToResult(final List<String> result, StringLocated s) {
		String tmp = s.getString();
		if (tmp.length() > 120) {
			tmp = tmp.substring(0, 120) + " ...";
		}
		result.add(tmp);
	}

	private List<String> getTextError() {
		return Arrays.asList(" " + singleError.getError());
	}

	@Override
	final protected ImageData exportDiagramNow(OutputStream os, int num, FileFormatOption fileFormat, long seed)
			throws IOException {
		if (fileFormat.getFileFormat() == FileFormat.ATXT || fileFormat.getFileFormat() == FileFormat.UTXT) {
			final UGraphicTxt ugt = new UGraphicTxt();
			final UmlCharArea area = ugt.getCharArea();
			area.drawStringsLR(getPureAsciiFormatted(), 0, 0);
			area.print(new PrintStream(os));
			return new ImageDataSimple(1, 1);

		}
		final TextBlockBackcolored result = getGraphicalFormatted();

		TextBlock udrawable;
		final ImageBuilder imageBuilder = new ImageBuilder(new ColorMapperIdentity(), 1.0, result.getBackcolor(),
				getMetadata(), null, 0, 0, null, false);
		udrawable = result;
		imageBuilder.setUDrawable(udrawable);
		final ImageData imageData = imageBuilder.writeImageTOBEMOVED(fileFormat, seed(), os);
		((ImageDataAbstract) imageData).setStatus(FileImageData.ERROR);
		return imageData;
	}

	private void append(List<String> result, LineLocation lineLocation) {
		if (lineLocation.getDescription() != null) {
			result.add("[From " + lineLocation.getDescription() + " (line " + (lineLocation.getPosition() + 1) + ") ]");
		}
	}

	final public DiagramDescription getDescription() {
		return new DiagramDescription("(Error)");
	}

	private List<String> allButLast(List<String> full) {
		return full.subList(0, full.size() - 1);
	}

	private List<String> onlyLast(List<String> full) {
		return full.subList(full.size() - 1, full.size());
	}

	public int size() {
		return trace.size();
	}

}