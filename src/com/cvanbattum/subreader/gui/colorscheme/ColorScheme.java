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
	 * Text color
	 */
	public Color getText();
	
}
