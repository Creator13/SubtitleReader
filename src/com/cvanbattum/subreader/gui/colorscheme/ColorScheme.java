package com.cvanbattum.subreader.gui.colorscheme;

import java.awt.Color;

/**
 * ColorScheme interface containing methods to obtain preset Colors
 * 
 * @author Casper van Battum
 *
 */
public interface ColorScheme {
	
	/**
	 * Background color
	 */
	public Color getBackground();
	
	/**
	 * Foreground color
	 */
	public Color getForeground();
	
	/**
	 * Background color when hovered
	 */
	public Color getBackgroundHover();
	
	/**
	 * Background color when hovered
	 */
	public Color getForegroundHover();
	
	/**
	 * Background color when clicked
	 */
	public Color getBackgroundClick();
	
	/**
	 * Foreground color when clicked
	 */
	public Color getForegroundClick();
	
	/**
	 * Background color when disabled
	 */
	public Color getBackgroundDisabled();
	
	/**
	 * Foreground color when disabled
	 */
	public Color getForegroundDisabled();
	
	/**
	 * Text color. <br>
	 * <br>
	 * Text color might be the same as the foreground color.
	 */
	//TODO: is this really necessary?
	public Color getText();
	
}
