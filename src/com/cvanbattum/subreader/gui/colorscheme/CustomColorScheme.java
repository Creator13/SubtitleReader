package com.cvanbattum.subreader.gui.colorscheme;

import java.awt.Color;
import java.util.HashMap;

/**
 * 
 * @author Casper van Battum
 *
 */
public abstract class CustomColorScheme implements ColorScheme {

	private HashMap<Object, Color> customColorMap = new HashMap<>();
	
	/**
	 * Adds a new custom color with a specified key. This functionality can be
	 * used to have custom colors for specified components in a program. <br> <br>
	 * 
	 * <b>Warning</b>: If a class wants to use this function, it must know what colors are bound to what keys, and how to interpret these colors/keys. This class only provides function to store colors that are not specified in the default ColorScheme
	 * 
	 * @param key
	 * 		The key for this color
	 * @param color
	 * 		The color
	 */
	public void addCustomColor(Object key, Color color) {
		customColorMap.put(key, color);
		
	}
	
	public boolean hasKey(Object key) {
		return customColorMap.containsKey(key);
		
	}
	
	public Color get(Object key) {
		return customColorMap.get(key);
		
	}
	
}
