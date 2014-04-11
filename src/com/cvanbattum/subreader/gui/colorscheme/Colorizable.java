package com.cvanbattum.subreader.gui.colorscheme;

public interface Colorizable {

	/**
	 * Sets this class' color scheme.
	 * 
	 * @param scheme The new {@link ColorScheme} for this class
	 */
	public void setColorScheme(ColorScheme scheme);
	
	/** 
	 * @return The {@link ColorScheme} this class is using.
	 */
	public ColorScheme getColorScheme();
	
}
