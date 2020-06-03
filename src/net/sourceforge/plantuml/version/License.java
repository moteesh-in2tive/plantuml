/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
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
 */
package net.sourceforge.plantuml.version;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sourceforge.plantuml.OptionFlags;

public enum License {

	GPL, GPLV2, LGPL, APACHE, EPL, MIT, BSD;

	public static License getCurrent() {
		return MIT;
	}

	private void addMit(final LicenseInfo licenseInfo, final List<String> text) {
		text.add("PlantUML is free software; you can redistribute it and/or modify it");
		text.add("under the terms of the MIT License.");
		text.add(" ");
		text.add("See http://opensource.org/licenses/MIT");
		text.add(" ");
		text.add("Permission is hereby granted, free of charge, to any person obtaining");
		text.add("a copy of this software and associated documentation files (the \"Software\"),");
		text.add("to deal in the Software without restriction, including without limitation");
		text.add("the rights to use, copy, modify, merge, publish, distribute, sublicense,");
		text.add("and/or sell copies of the Software, and to permit persons to whom the");
		text.add("Software is furnished to do so, subject to the following conditions:");
		text.add(" ");
		text.add("The above copyright notice and this permission notice shall be included");
		text.add("in all copies or substantial portions of the Software.");
		text.add(" ");
		text.add("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS");
		text.add("OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,");
		text.add("FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE");
		text.add("AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,");
		text.add("WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR");
		text.add("IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
		text.add(" ");
		text.add("the MIT License.");
		text.add(" ");
		text.add("The generated images can then be used without any reference to the MIT License.");
		text.add("It is not even necessary to stipulate that they have been generated with PlantUML,");
		text.add("also this will be appreciate by PlantUML team.");
		text.add(" ");
		text.add("There is an exception : if the textual description in PlantUML language is also covered");
		text.add("by a license (like the MIT), then the generated images are logically covered");
		text.add("by the very same license.");
	}

	private void addBsd(final LicenseInfo licenseInfo, final List<String> text) {
		text.add("PlantUML is free software; you can redistribute it and/or modify it");
		text.add("under the terms of the Revised BSD License.");
		text.add(" ");
		text.add("All rights reserved.");
		text.add("Redistribution and use in source and binary forms, with or without");
		text.add("modification, are permitted provided that the following conditions are met:");
		text.add(" ");
		text.add("* Redistributions of source code must retain the above copyright");
		text.add("  notice, this list of conditions and the following disclaimer.");
		text.add("* Redistributions in binary form must reproduce the above copyright");
		text.add("  notice, this list of conditions and the following disclaimer in the");
		text.add("  documentation and/or other materials provided with the distribution.");
		text.add("* Neither the name of the University of California, Berkeley nor the");
		text.add("  names of its contributors may be used to endorse or promote products");
		text.add("  derived from this software without specific prior written permission.");
		text.add(" ");
		text.add("THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY");
		text.add("EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED");
		text.add("WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE");
		text.add("DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY");
		text.add("DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES");
		text.add("(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;");
		text.add("LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND");
		text.add("ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT");
		text.add("(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS");
		text.add("SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.");
		text.add(" ");
		text.add("the Eclipse Public License.");
		text.add(" ");
		text.add("The generated images can then be used without any reference to the Eclipse Public License.");
		text.add("It is not even necessary to stipulate that they have been generated with PlantUML,");
		text.add("also this will be appreciate by PlantUML team.");
		text.add(" ");
		text.add("There is an exception : if the textual description in PlantUML language is also covered");
		text.add("by a license (like the BSD), then the generated images are logically covered");
		text.add("by the very same license.");
	}

	private void addApache(final LicenseInfo licenseInfo, final List<String> text) {
		text.add("PlantUML is free software; you can redistribute it and/or modify it");
		text.add("under the terms of the Apache Software License.");
		text.add(" ");
		text.add("Licensed under the Apache License, Version 2.0 (the \"License\");");
		text.add("you may not use this file except in compliance with the License.");
		text.add("You may obtain a copy of the License at");
		text.add(" ");
		text.add("http://www.apache.org/licenses/LICENSE-2.0");
		text.add(" ");
		text.add("Unless required by applicable law or agreed to in writing, software");
		text.add("distributed under the License is distributed on an \"AS IS\" BASIS,");
		text.add("WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.");
		text.add("See the License for the specific language governing permissions and");
		text.add("limitations under the License.");
		text.add(" ");
		text.add("the Apache license.");
		text.add(" ");
		text.add("The generated images can then be used without any reference to the Apache license.");
		text.add("It is not even necessary to stipulate that they have been generated with PlantUML,");
		text.add("also this will be appreciate by PlantUML team.");
		text.add(" ");
		text.add("There is an exception : if the textual description in PlantUML language is also covered");
		text.add("by a license (like the Apache), then the generated images are logically covered");
		text.add("by the very same license.");
	}

	private void header1(final List<String> text, LicenseInfo licenseInfo) {
		text.add("+=======================================================================");
		text.add("| ");
		text.add("|      PlantUML : a free UML diagram generator");
		text.add("| ");
		text.add("+=======================================================================");
	}

	public List<String> getJavaHeader() {
		final List<String> h = new ArrayList<String>();
		h.add("/* ========================================================================");
		h.add(" * PlantUML : a free UML diagram generator");
		h.add(" * ========================================================================");

		return Collections.unmodifiableList(h);
	}

	public List<String> getTextFull() {
		final LicenseInfo licenseInfo = LicenseInfo.NONE;
		final List<String> text = new ArrayList<String>();
		header1(text, licenseInfo);
		end3(text, licenseInfo);
		return text;
	}

	public List<String> getText1(LicenseInfo licenseInfo) {
		final List<String> text = new ArrayList<String>();
		header1(text, licenseInfo);
		return text;
	}

	public List<String> getText2(LicenseInfo licenseInfo) {
		final List<String> text = new ArrayList<String>();
		header1(text, licenseInfo);
		end3(text, licenseInfo);
		return text;
	}

	private void end3(final List<String> text, final LicenseInfo licenseInfo) {
		if (this == License.MIT) {
			addMit(licenseInfo, text);
		} else if (this == License.BSD) {
			addBsd(licenseInfo, text);
		} else if (this == License.APACHE) {
			addApache(licenseInfo, text);
		} else {
			throw new IllegalStateException();
		}
		if (OptionFlags.getInstance().isEnableStats()) {
			text.add(" ");
			text.add("This version of PlantUML records general local statistics about usage.");
			text.add("(more info on https://plantuml.com/statistics-report)");
		}
		text.add(" ");
		text.add("Icons provided by OpenIconic :  https://useiconic.com/open");
		text.add("Archimate sprites provided by Archi :  http://www.archimatetool.com");
		text.add("Stdlib AWS provided by https://github.com/milo-minderbinder/AWS-PlantUML");
		text.add("Stdlib Icons provided https://github.com/tupadr3/plantuml-icon-font-sprites");
		text.add("ASCIIMathML (c) Peter Jipsen http://www.chapman.edu/~jipsen");
		text.add("ASCIIMathML (c) David Lippman http://www.pierce.ctc.edu/dlippman");
		text.add("CafeUndZopfli ported by Eugene Klyuchnikov https://github.com/eustas/CafeUndZopfli");
		text.add("Brotli (c) by the Brotli Authors https://github.com/google/brotli");
		text.add(" ");
	}
}
