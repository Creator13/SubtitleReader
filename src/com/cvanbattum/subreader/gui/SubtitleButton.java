package com.cvanbattum.subreader.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.cvanbattum.subreader.functional.SubtitleReaderProgram;
import com.cvanbattum.subreader.gui.colorscheme.ColorScheme;
import com.cvanbattum.subreader.gui.colorscheme.Colorizable;
import com.cvanbattum.subreader.gui.colorscheme.SchemeManager;

/**
 * <code>SubtitleButton</code> is an implementation of JButton, fitting the 
 * needs of the {@link SubtitleReaderProgram} look and feel. 
 * <code>SubtitleButton</code> implements the {@link Colorizable} interface to 
 * make it compatible with color schemes.
 * 
 * @version 1.0
 * @author Casper van Battum
 *
 */
public class SubtitleButton extends JButton implements Colorizable {
	
	private static final long serialVersionUID = 1L;
	
	private final boolean isVisualButton;
	private final boolean hasPrefColorSet;
	
	private ColorScheme scheme;
	private ArrayList<String> customColors;
	private Color prefColor;
	private Color prefColorClick;
	private Color prefColorHover;
	private Color prefColorDisabled;
	private Color prefColorText;
	private Font f;
	private Path2D path;
	
	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		
		Path2D p = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		p.moveTo(0, 0);
		p.lineTo(20, 0);
		p.lineTo(20, 20);
		p.lineTo(0, 20);
		p.lineTo(5, 10);
		p.closePath();
		
		SubtitleButton sb = createMinimizeButton(new Dimension(150, 150));
		
		f.add(sb, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
		
	}
	
	/**
	 * Creates a new, empty {@link SubtitleButton}.
	 */
	public SubtitleButton() {
		super();
		
		scheme = SchemeManager.getPreferredScheme();
		isVisualButton = false;
		hasPrefColorSet = false;
		f = new Font("Tahoma", Font.PLAIN, 9);
		
	}
	
	/**
	 * Creates a new {@link SubtitleButton} displaying text, and therefore 
	 * cannot display a shape (as this is not needed anywhere in the program)
	 * 
	 * @param s The text to display on the button
	 */
	public SubtitleButton(String s) {
		super(s);
		
		scheme = SchemeManager.getPreferredScheme();
		isVisualButton = false;
		hasPrefColorSet = false;
		f = new Font("Tahoma", Font.PLAIN, 9);
		
	}
	
	/**
	 * Creates a new {@link SubtitleButton} displaying a custom shape. This 
	 * shape has to be an instance of {@link Path2D}, which is very similar to
	 * {@link GeneralPath}. The size argument of this constructor defines the
	 * size of the button. <br>
	 * <br>
	 * A button displaying a shape cannot display text!
	 * 
	 * @param path
	 * 			The path to draw on this button
	 * @param size
	 * 			The size of the button
	 */
	public SubtitleButton(Path2D path, Dimension size) {
		super();
		
		scheme = SchemeManager.getPreferredScheme();
		
		setPreferredSize(size);
		
		isVisualButton = true;
		hasPrefColorSet = false;
		f = new Font("Tahoma", Font.PLAIN, 9);
		
		this.path = path;
		
	}
	
	/**
	 * Creates a new {@link SubtitleButton} displaying a shape. The colors of 
	 * this button are fully customizable. Setting a color to <code>null</code>
	 * will give the current default color specified by the current 
	 * {@link ColorScheme}. The size argument is defines the size of this 
	 * button. <br>
	 * <br>
	 * A button displaying a shape cannot display text!
	 *  
	 * @param path
	 * 			The shape to draw on this button
	 * @param size
	 * 			The size of the button
	 * @param normal
	 * 			The color of this button in its normal state
	 * @param hover
	 * 			The color of this button when hovered
	 * @param click
	 * 			The color of this button when clicked
	 * @param disabled
	 * 			The color of this button when disabled
	 * @param text
	 * 			The color of the text on this button
	 */
	public SubtitleButton(Path2D path, Dimension size, Color normal, 
			Color hover, Color click, Color disabled, Color text) {
		super();
		
		scheme = SchemeManager.getPreferredScheme();
		
		setPreferredSize(size);
		
		isVisualButton = true;
		hasPrefColorSet = true;
		f = new Font("Tahoma", Font.PLAIN, 9);
		
		this.prefColor = (normal == null) ? scheme.getForeground() : normal;
		this.prefColorClick = (click == null) ? scheme.getForegroundClick() : click;
		this.prefColorDisabled = (disabled == null) ? scheme.getForegroundDisabled() : disabled;
		this.prefColorHover = (hover == null) ? scheme.getForegroundHover() : hover;
		this.prefColorText = (text == null) ? scheme.getText() : text;
		this.path = path;
		
	}
	
	//------------\\
	//PAINT METHOD\\
	
	@Override
	public void paint(Graphics g) {
		//Change Graphics object into Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		
		ButtonModel model = getModel();
		
		Font f = getFont();
		
		//Set colors and offset
		final int y_offset;
		final int x_offset;
		
		final Color bg;
		final Color fg;
		final Color clr_text;
		if (hasPrefColorSet) {
			clr_text = prefColorText;
			
			if (model.isPressed()) {		//Colors if the button is pressed
				bg = scheme.getBackgroundHover();
				fg = prefColorClick;
				
			}
			else if (! model.isEnabled()) {	//Colors if the button is disabled
				bg = scheme.getBackgroundDisabled();
				fg = prefColorDisabled;
				
			}
			else if (model.isRollover()) {	//Colors if the button is hovered over
				bg = scheme.getBackgroundClick();
				fg = prefColorHover;
				
			}
			else {							//Colors when the button is normal
				bg = scheme.getBackground();
				fg = prefColor;
				
			}
		
		}
		else {
			clr_text = scheme.getText();
			if (model.isPressed()) {		//Colors if the button is pressed
				bg = scheme.getBackgroundHover();
				fg = scheme.getForegroundHover();
				
			}
			else if (! model.isEnabled()) {	//Colors if the button is disabled
				bg = scheme.getBackgroundDisabled();
				fg = scheme.getForegroundDisabled();
				
			}
			else if (model.isRollover()) {	//Colors if the button is hovered over
				bg = scheme.getBackgroundClick();
				fg = scheme.getForegroundClick();
				
			}
			else {							//Colors when the button is normal
				bg = scheme.getBackground();
				fg = scheme.getForeground();
				
			}
			
		}
		
		//Offset
		if (model.isPressed()) {		//Offset when the button is clicked
			y_offset = 1;
			x_offset = 1;
			
		}
		else {							//Offset when the button is normal
			y_offset = 0;
			x_offset = 0;
			
		}
		
		g2.setColor(bg);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		if (! isVisualButton) {
			//Draw the text (if any)
			String text = getText();
			if (text != null && text != "") {
				FontMetrics fm = g2.getFontMetrics(f);
				final int hgt = (int) getTextHeight(g2, text);
				final int wdt = fm.stringWidth(text);
				
				float x = getWidth() / 2 - wdt / 2 + x_offset;
				float y = getHeight() / 2 + hgt / 2 + y_offset;
				
				g2.setColor(clr_text);
				g2.drawString(text, x, y);
				
			}
			
		}
		//If this button is a visual button (aka has to draw a shape) then draw
		//the shape of this visual button. A visual button cannot have a String
		//on it.
		else {
			g2.setColor(fg);
			if (path == null) {
				throw new IllegalStateException("Shape can not be null when SubtitleButton.isVisualButton is set true");
				
			}
			else {
				Rectangle r = path.getBounds();
				
				float x = getWidth() / 2 - r.width / 2 + x_offset;
				float y = getHeight() / 2 - r.height / 2 + y_offset;
				
				AffineTransform at = AffineTransform.getTranslateInstance(x, y);
				
				Path2D p = (Path2D) at.createTransformedShape(path);
				
				g2.fill(p);
				
			}
			
		}
		
	}
	
	//-------------\\
	//OTHER METHODS\\
	
	//Defines the height (better than FontMetrics.getHeight()).
	private float getTextHeight(Graphics2D g2, String str) {
	    FontRenderContext frc = g2.getFontRenderContext();
	    return f.getLineMetrics(str, frc).getHeight();
	    
	}
	
	@Override
	public void setFont(Font font) {
		this.f = font;
		super.setFont(font);
		
	}
	
	/**
	 * <b>Warning:</b> This method has no functionality in this class.
	 */
	@Override
	@Deprecated
	public void setIcon(Icon defaultIcon) {
		//Do nothing
		
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		if (! isVisualButton) {
			super.setPreferredSize(preferredSize);
			
		}
		//else: do nothing
		
	}
	
	@Override
	public void setColorScheme(ColorScheme scheme) {
		this.scheme = scheme;
		
	}
	
	@Override
	public ColorScheme getColorScheme() {
		return this.scheme;
		
	}
	
	 //=========================\\
	//= Static 'create' methods =\\
	//---------------------------\\
	
	/**
	 * This method creates a new button with a cross sign on it. This button 
	 * can, for example, be used as a custom 'close' button. The 
	 * {@link SubtitleButton} class, in combination with the 
	 * {@link ColorScheme} class, contains the possibility to use a custom 
	 * color for a close button. <br> 
	 * The padding for the button is by default 1/6th of the width <br>
	 * <br>
	 * This button supports custom colors. In the default ColorScheme, these 
	 * are shades of red depending on the state the button is in. These custom
	 * colors can be disabled by setting <code>hasCustomColor</code> to 
	 * <code>false</code>
	 * 
	 * @param size
	 * 			The size of the button.
	 * @param hasCustomColor
	 * 			Set to <code>true</code> if you want this button to use custom 
	 * 			colors.
	 * @return
	 * 			A new button with a cross shape on it.
	 */
	public static SubtitleButton createCloseButton(Dimension size, boolean hasCustomColor) {
		int width = size.width;
		int height = size.height;
		
		float padding_x;
		float padding_y;
		float innerSide;
		//If square
		if (width == height) {
			padding_x = 1f/6f * width;
			padding_y = padding_x;
			innerSide = height - (2 * padding_y);
			
		}
		//if rectangle (lying)
		else if (width > height) {
			padding_y = 1f/6f * width;
			innerSide = height - (2 * padding_y);
			padding_x = 1f/2f * (width - innerSide);
			
		}
		//if rectangle (standing)
		else if (height > width) {
			padding_x = 1f/6f * width;
			innerSide = width - (2 * padding_x);
			padding_y = 1f/2f * (height - innerSide);
			
		}
		//Unreachable, but 'necessary' for no errors
		else {
			try {
				throw new Exception("Something very weird happened");
				
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
				
			}
			
		}
		
		float a = 1f/10f * innerSide;
		
		//This path was mathematically defined using a sheet of raster paper..
		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		//					   x       y
		/*0*/	path.moveTo(a, 		0     );
		/*1*/	path.lineTo(5 * a, 	4 * a );
		/*2*/	path.lineTo(9 * a,	0     );
		/*3*/	path.lineTo(10 * a, a     );
		/*4*/	path.lineTo(6 * a, 	5 * a );
		/*5*/	path.lineTo(10 * a, 9 * a );
		/*6*/	path.lineTo(9 * a, 	10 * a);
		/*7*/	path.lineTo(5 * a, 	6 * a );
		/*8*/	path.lineTo(a, 		10 * a);
		/*9*/	path.lineTo(0, 		9 * a );
		/*10*/	path.lineTo(4 * a, 	5 * a );
		/*11*/	path.lineTo(0, 		a     );
		path.closePath();
		
		if (hasCustomColor) {
			// Color for this button is #C22D2D (red)
			Color clr1 = new Color(194, 45, 45);
			//Hover and click color #DB3333
			Color clr2 = new Color(219, 51, 51);
			//Disabled color #EF8484
			Color clr3 = new Color(239, 132, 132);
			
			return new SubtitleButton(path, size, clr1, clr2, clr2, clr3, null);
			
		}
		else return new SubtitleButton(path, size);
		
	}
	
	/**
	 * This method creates a new {@link SubtitleButton} with a minimize sign on
	 * it, meant as a button for minimizing a window. This factory method does 
	 * not include support for custom colors. The size argument defines the 
	 * <i>preferred</i> size for the button. However, depending on the layout, 
	 * this might differ in reality
	 * 
	 * @param size The preferred size for this button
	 * @return A new <code>SubtitleButton</code>
	 */
	public static SubtitleButton createMinimizeButton(Dimension size) {
		int width = size.width;
		int height = size.height;
		
		float padding_x;
		float padding_y;
		float innerSide;
		//If square
		if (width == height) {
			padding_x = 1f/6f * width;
			padding_y = padding_x;
			innerSide = height - (2 * padding_y);
			
		}
		//if rectangle (lying)
		else if (width > height) {
			padding_y = 1f/6f * width;
			innerSide = height - (2 * padding_y);
			padding_x = 1f/2f * (width - innerSide);
			
		}
		//if rectangle (standing)
		else if (height > width) {
			padding_x = 1f/6f * width;
			innerSide = width - (2 * padding_x);
			padding_y = 1f/2f * (height - innerSide);
			
		}
		//Unreachable, but 'necessary' for no errors
		else {
			try {
				throw new Exception("Something very weird happened");
				
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
				
			}
			
		}
		
		float a = 1f/10f * innerSide;
		
		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		path.moveTo(0.5f * a, 6 * a);
		path.lineTo(9.5f * a, 6 * a);
		path.lineTo(9.5f * a, 7.5f * a);
		path.lineTo(0.5f * a, 7.5f * a);
		path.closePath();
		
		path.moveTo(10 * a, 10 * a);
		
		return new SubtitleButton(path, size);
		
	}
	
}
