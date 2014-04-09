package com.cvanbattum.subreader.gui.colorscheme;

import java.awt.Color;

/**
 * Default color scheme <br>
 * <br>
 * <b>Colors:</b>
 * <ul>
 *     <li>Background: Dark gray (#080808 (r: 8, g: 8, b: 8))</li>
 *     <li>Foreground: Green (#6DC22D (r: 109, g: 194, b: 45))</li>
 *     <li>Hover (background): Lighter gray (#4A4A4A (r: 74, g: 74, b: 74))</li>
 *     <li>Hover (foreground): Green(#7BDB33 (r: 109, g: 194, b: 45))</li>
 *     <li>Click (background): Lighter gray (#4A4A4A (r: 74, g: 74, b: 74))</li>
 *     <li>Click (Foreground): Lighter green (#7BDB33 (r: 123, g: 219, b: 51))</li>
 *     <li>Disabled (background): Dark gray (#2E2E2E (r: 46, g: 46, b: 46)</li>
 *     <li>Disabled (foreground): Light green (#B2EF84 (r: 178, g: 239, b: 132)</li>
 *     <li>Text: Very light gray (#EDEDED (r: 237, g: 237, b: 237))</li>
 * </ul>
 * 
 * @author Casper van Battum
 *
 */
public class DarkColorScheme implements ColorScheme {

	private static final Color DARK_GRAY = new Color(8, 8, 8);
	private static final Color GREEN = new Color(109, 194, 45);
	private static final Color LIGHTER_GRAY = new Color(74, 74, 74);
	private static final Color LIGHTER_GREEN = new Color(123, 219, 51);
	private static final Color VERY_LIGHT_GRAY = new Color(237, 237, 237);
	private static final Color DISABLED_GRAY = new Color(46, 46, 46);
	private static final Color DISABLED_GREEN = new Color(178, 239, 132);
	
	/**
	 * Dark gray (#080808 (r: 8, g: 8, b: 8))
	 */
	@Override
	public Color getBackground() {
		return DARK_GRAY;
		
	}

	/**
	 * Green (#6DC22D (r: 109, g: 194, b: 45))
	 */
	@Override
	public Color getForeground() {
		return GREEN;
		
	}
	
	/**
	 * Lighter gray (#4A4A4A (r: 74, g: 74, b: 74))
	 */
	@Override
	public Color getBackgroundHover() {
		return LIGHTER_GRAY;
		
	}
	
	/**
	 * Green(#7BDB33 (r: 123, g: 219, b: 51))
	 */
	@Override
	public Color getForegroundHover() {
		return GREEN;
		
	}

	/**
	 * Lighter gray (#4A4A4A (r: 74, g: 74, b: 74))
	 */
	@Override
	public Color getBackgroundClick() {
		return LIGHTER_GRAY;
		
	}

	/**
	 * Lighter green (#4A4A4A (r: 74, g: 74, b: 74))
	 */
	@Override
	public Color getForegroundClick() {
		return LIGHTER_GREEN;
		
	}
	
	/**
	 * Dark gray (#2E2E2E (r: 46, g: 46, b: 46)
	 */
	@Override
	public Color getBackgroundDisabled() {
		return DISABLED_GRAY;
		
	}
	
	/**
	 * Disabled (foreground): Light green (#B2EF84 (r: 178, g: 239, b: 132)
	 */
	@Override
	public Color getForegroundDisabled() {
		return DISABLED_GREEN;
		
	}
	
	/**
	 * Very light gray (#EDEDED (r: 237, g: 237, b: 237))
	 */
	@Override
	public Color getText() {
		return VERY_LIGHT_GRAY;
		
	}

}
