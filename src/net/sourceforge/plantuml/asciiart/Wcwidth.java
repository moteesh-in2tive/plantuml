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
 * Source code provided by Yasuhiro Matsumoto
 *
 */
/**
 * Source code provided by Yasuhiro Matsumoto.
 * See:
 * https://github.com/plantuml/plantuml/issues/74
 * 
 * Many thanks for his help!
 */
/**
 * <p>See <a href="http://www.cl.cam.ac.uk/~mgk25/ucs/wcwidth.c">wcwidth.c</a></p>
 *
 * <p>This is an implementation of wcwidth() and wcswidth() (defined in
 * IEEE Std 1002.1-2001) for Unicode.</p>
 *
 * http://www.opengroup.org/onlinepubs/007904975/functions/wcwidth.html
 * http://www.opengroup.org/onlinepubs/007904975/functions/wcswidth.html
 *
 * <p>In fixed-width output devices, Latin characters all occupy a single
 * "cell" position of equal width, whereas ideographic CJK characters
 * occupy two such cells. Interoperability between terminal-line
 * applications and (teletype-style) character terminals using the
 * UTF-8 encoding requires agreement on which character should advance
 * the cursor by how many cell positions. No established formal
 * standards exist at present on which Unicode character shall occupy
 * how many cell positions on character terminals. These routines are
 * a first attempt of defining such behavior based on simple rules
 * applied to data provided by the Unicode Consortium.</p>
 *
 * <p>For some graphical characters, the Unicode standard explicitly
 * defines a character-cell width via the definition of the East Asian
 * FullWidth (F), Wide (W), Half-width (H), and Narrow (Na) classes.
 * In all these cases, there is no ambiguity about which width a
 * terminal shall use. For characters in the East Asian Ambiguous (A)
 * class, the width choice depends purely on a preference of backward
 * compatibility with either historic CJK or Western practice.
 * Choosing single-width for these characters is easy to justify as
 * the appropriate long-term solution, as the CJK practice of
 * displaying these characters as double-width comes from historic
 * implementation simplicity (8-bit encoded characters were displayed
 * single-width and 16-bit ones double-width, even for Greek,
 * Cyrillic, etc.) and not any typographic considerations.</p>
 *
 * <p>Much less clear is the choice of width for the Not East Asian
 * (Neutral) class. Existing practice does not dictate a width for any
 * of these characters. It would nevertheless make sense
 * typographically to allocate two character cells to characters such
 * as for instance EM SPACE or VOLUME INTEGRAL, which cannot be
 * represented adequately with a single-width glyph. The following
 * routines at present merely assign a single-cell width to all
 * neutral characters, in the interest of simplicity. This is not
 * entirely satisfactory and should be reconsidered before
 * establishing a formal standard in this area. At the moment, the
 * decision which Not East Asian (Neutral) characters should be
 * represented by double-width glyphs cannot yet be answered by
 * applying a simple rule from the Unicode database content. Setting
 * up a proper standard for the behavior of UTF-8 character terminals
 * will require a careful analysis not only of each Unicode character,
 * but also of each presentation form, something the author of these
 * routines has avoided to do so far.</p>
 *
 * <p>http://www.unicode.org/unicode/reports/tr11/</p>
 */
package net.sourceforge.plantuml.asciiart;

public class Wcwidth {

  /**
   * sorted list of non-overlapping intervals of non-spacing characters
   * generated by "uniset +cat=Me +cat=Mn +cat=Cf -00AD +1160-11FF +200B c"
   */
  private static final int[][] COMBINING = {
      {0x0300, 0x036F}, {0x0483, 0x0486}, {0x0488, 0x0489},
      {0x0591, 0x05BD}, {0x05BF, 0x05BF}, {0x05C1, 0x05C2},
      {0x05C4, 0x05C5}, {0x05C7, 0x05C7}, {0x0600, 0x0603},
      {0x0610, 0x0615}, {0x064B, 0x065E}, {0x0670, 0x0670},
      {0x06D6, 0x06E4}, {0x06E7, 0x06E8}, {0x06EA, 0x06ED},
      {0x070F, 0x070F}, {0x0711, 0x0711}, {0x0730, 0x074A},
      {0x07A6, 0x07B0}, {0x07EB, 0x07F3}, {0x0901, 0x0902},
      {0x093C, 0x093C}, {0x0941, 0x0948}, {0x094D, 0x094D},
      {0x0951, 0x0954}, {0x0962, 0x0963}, {0x0981, 0x0981},
      {0x09BC, 0x09BC}, {0x09C1, 0x09C4}, {0x09CD, 0x09CD},
      {0x09E2, 0x09E3}, {0x0A01, 0x0A02}, {0x0A3C, 0x0A3C},
      {0x0A41, 0x0A42}, {0x0A47, 0x0A48}, {0x0A4B, 0x0A4D},
      {0x0A70, 0x0A71}, {0x0A81, 0x0A82}, {0x0ABC, 0x0ABC},
      {0x0AC1, 0x0AC5}, {0x0AC7, 0x0AC8}, {0x0ACD, 0x0ACD},
      {0x0AE2, 0x0AE3}, {0x0B01, 0x0B01}, {0x0B3C, 0x0B3C},
      {0x0B3F, 0x0B3F}, {0x0B41, 0x0B43}, {0x0B4D, 0x0B4D},
      {0x0B56, 0x0B56}, {0x0B82, 0x0B82}, {0x0BC0, 0x0BC0},
      {0x0BCD, 0x0BCD}, {0x0C3E, 0x0C40}, {0x0C46, 0x0C48},
      {0x0C4A, 0x0C4D}, {0x0C55, 0x0C56}, {0x0CBC, 0x0CBC},
      {0x0CBF, 0x0CBF}, {0x0CC6, 0x0CC6}, {0x0CCC, 0x0CCD},
      {0x0CE2, 0x0CE3}, {0x0D41, 0x0D43}, {0x0D4D, 0x0D4D},
      {0x0DCA, 0x0DCA}, {0x0DD2, 0x0DD4}, {0x0DD6, 0x0DD6},
      {0x0E31, 0x0E31}, {0x0E34, 0x0E3A}, {0x0E47, 0x0E4E},
      {0x0EB1, 0x0EB1}, {0x0EB4, 0x0EB9}, {0x0EBB, 0x0EBC},
      {0x0EC8, 0x0ECD}, {0x0F18, 0x0F19}, {0x0F35, 0x0F35},
      {0x0F37, 0x0F37}, {0x0F39, 0x0F39}, {0x0F71, 0x0F7E},
      {0x0F80, 0x0F84}, {0x0F86, 0x0F87}, {0x0F90, 0x0F97},
      {0x0F99, 0x0FBC}, {0x0FC6, 0x0FC6}, {0x102D, 0x1030},
      {0x1032, 0x1032}, {0x1036, 0x1037}, {0x1039, 0x1039},
      {0x1058, 0x1059}, {0x1160, 0x11FF}, {0x135F, 0x135F},
      {0x1712, 0x1714}, {0x1732, 0x1734}, {0x1752, 0x1753},
      {0x1772, 0x1773}, {0x17B4, 0x17B5}, {0x17B7, 0x17BD},
      {0x17C6, 0x17C6}, {0x17C9, 0x17D3}, {0x17DD, 0x17DD},
      {0x180B, 0x180D}, {0x18A9, 0x18A9}, {0x1920, 0x1922},
      {0x1927, 0x1928}, {0x1932, 0x1932}, {0x1939, 0x193B},
      {0x1A17, 0x1A18}, {0x1B00, 0x1B03}, {0x1B34, 0x1B34},
      {0x1B36, 0x1B3A}, {0x1B3C, 0x1B3C}, {0x1B42, 0x1B42},
      {0x1B6B, 0x1B73}, {0x1DC0, 0x1DCA}, {0x1DFE, 0x1DFF},
      {0x200B, 0x200F}, {0x202A, 0x202E}, {0x2060, 0x2063},
      {0x206A, 0x206F}, {0x20D0, 0x20EF}, {0x302A, 0x302F},
      {0x3099, 0x309A}, {0xA806, 0xA806}, {0xA80B, 0xA80B},
      {0xA825, 0xA826}, {0xFB1E, 0xFB1E}, {0xFE00, 0xFE0F},
      {0xFE20, 0xFE23}, {0xFEFF, 0xFEFF}, {0xFFF9, 0xFFFB},
      {0x10A01, 0x10A03}, {0x10A05, 0x10A06}, {0x10A0C, 0x10A0F},
      {0x10A38, 0x10A3A}, {0x10A3F, 0x10A3F}, {0x1D167, 0x1D169},
      {0x1D173, 0x1D182}, {0x1D185, 0x1D18B}, {0x1D1AA, 0x1D1AD},
      {0x1D242, 0x1D244}, {0xE0001, 0xE0001}, {0xE0020, 0xE007F},
      {0xE0100, 0xE01EF}
  };

  static boolean bisearch(int ucs) {
    int min = 0;
    int mid;
    int max = COMBINING.length - 1;

    if (ucs < COMBINING[0][0] || ucs > COMBINING[max][1]) {
      return false;
    }
    while (max >= min) {
      mid = (min + max) / 2;
      if (ucs > COMBINING[mid][1]) {
        min = mid + 1;
      } else if (ucs < COMBINING[mid][0]) {
        max = mid - 1;
      } else {
        return true;
      }
    }

    return false;
  }

  /**
   * See : http://www.cl.cam.ac.uk/%7Emgk25/ucs/wcwidth.c
   *
   * The following two functions define the column width of an ISO 10646
   * character as follows:
   *
   *    - The null character (U+0000) has a column width of 0.
   *
   *    - Other C0/C1 control characters and DEL will lead to a return
   *      value of -1.
   *
   *    - Non-spacing and enclosing combining characters (general
   *      category code Mn or Me in the Unicode database) have a
   *      column width of 0.
   *
   *    - SOFT HYPHEN (U+00AD) has a column width of 1.
   *
   *    - Other format characters (general category code Cf in the Unicode
   *      database) and ZERO WIDTH SPACE (U+200B) have a column width of 0.
   *
   *    - Hangul Jamo medial vowels and final consonants (U+1160-U+11FF)
   *      have a column width of 0.
   *
   *    - Spacing characters in the East Asian Wide (W) or East Asian
   *      Full-width (F) category as defined in Unicode Technical
   *      Report #11 have a column width of 2.
   *
   *    - All remaining characters (including all printable
   *      ISO 8859-1 and WGL4 characters, Unicode control characters,
   *      etc.) have a column width of 1.
   *
   * This implementation assumes that wchar_t characters are encoded
   * in ISO 10646.
   */
  public static int of(int codePoint) {
    // test for 8-bit control characters
    if (codePoint == 0) {
      return 0;
    }
    if (codePoint < 32 || (codePoint >= 0x7f && codePoint < 0xa0)) {
      return -1;
    }
    // Added by Arnaud : Usual 8 bit char 
    if (codePoint < 127) {
      return 1;
    }
    // binary search in table of non-spacing characters
    if (bisearch(codePoint)) {
      return 0;
    }

    // if we arrive here, ucs is not a combining or C0/C1 control character
    return 1 +
        ((codePoint >= 0x1100 &&
            (codePoint <= 0x115f ||                    // Hangul Jamo init. consonants
                codePoint == 0x2329 || codePoint == 0x232a ||
                (codePoint >= 0x2e80 && codePoint <= 0xa4cf &&
                    codePoint != 0x303f) ||                  // CJK ... Yi
                (codePoint >= 0xac00 && codePoint <= 0xd7a3) || // Hangul Syllables
                (codePoint >= 0xf900 && codePoint <= 0xfaff) || // CJK Compatibility Ideographs
                (codePoint >= 0xfe10 && codePoint <= 0xfe19) || // Vertical forms
                (codePoint >= 0xfe30 && codePoint <= 0xfe6f) || // CJK Compatibility Forms
                (codePoint >= 0xff00 && codePoint <= 0xff60) || // Fullwidth Forms
                (codePoint >= 0xffe0 && codePoint <= 0xffe6) ||
                (codePoint >= 0x20000 && codePoint <= 0x2fffd) ||
                (codePoint >= 0x30000 && codePoint <= 0x3fffd))) ? 1 : 0);
  }

	public static int length(CharSequence s) {
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			result += of(s.charAt(i));
		}
		return result;
	}
}