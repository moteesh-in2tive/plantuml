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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;

import net.sourceforge.plantuml.SignatureUtils;

public class LicenseInfo {

	private final static Preferences prefs = Preferences.userNodeForPackage(LicenseInfo.class);
	public final static LicenseInfo NONE = new LicenseInfo(LicenseType.NONE, 0, 0, null, null, null);

	private final LicenseType type;
	private final long generationDate;
	private final long expirationDate;
	private final String owner;
	private final String context;
	private final byte[] sha;

	public LicenseInfo(LicenseType type, long generationDate, long expirationDate, String owner, String context,
			byte[] sha) {
		this.type = type;
		this.generationDate = generationDate;
		this.expirationDate = expirationDate;
		this.owner = owner;
		this.context = context;
		this.sha = sha;
	}

	public static void persistMe(String key) throws BackingStoreException {
		prefs.sync();
		prefs.put("license", key);
	}

	private static LicenseInfo cache;

	public static BufferedImage retrieveDistributorImage(LicenseInfo licenseInfo) {
		if (licenseInfo.getLicenseType() != LicenseType.DISTRIBUTOR) {
			return null;
		}
		try {
			final byte[] s1 = PLSSignature.retrieveDistributorImageSignature();
			if (SignatureUtils.toHexString(s1).equals(SignatureUtils.toHexString(licenseInfo.sha)) == false) {
				return null;
			}
			final InputStream dis = PSystemVersion.class.getResourceAsStream("/distributor.png");
			if (dis == null) {
				return null;
			}
			try {
				final BufferedImage result = ImageIO.read(dis);
				return result;
			} finally {
				dis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Collection<File> fileCandidates() {
		final Set<File> result = new TreeSet<File>();
		final String classpath = System.getProperty("java.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);
		for (String s : classpathEntries) {
			File dir = new File(s);
			if (dir.isFile()) {
				dir = dir.getParentFile();
			}
			if (dir != null && dir.isDirectory()) {
				result.add(new File(dir, "license.txt"));
			}
		}
		return result;
	}

	public final Date getGenerationDate() {
		return new Date(generationDate);
	}

	public final Date getExpirationDate() {
		return new Date(expirationDate);
	}

	public final String getOwner() {
		return owner;
	}

	public boolean isNone() {
		return owner == null;
	}

	public boolean isValid() {
		return owner != null && System.currentTimeMillis() <= this.expirationDate;
	}

	public boolean hasExpired() {
		return owner != null && System.currentTimeMillis() > this.expirationDate;
	}

	public final LicenseType getLicenseType() {
		return type;
	}

	public final String getContext() {
		return context;
	}

}