package com.cvanbattum.subreader.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.cvanbattum.api.srt.SRTEntry;
import com.cvanbattum.subreader.gui.colorscheme.ColorScheme;
import com.cvanbattum.subreader.gui.colorscheme.Colorizable;
import com.cvanbattum.subreader.gui.colorscheme.SchemeManager;

/**
 * 
 * @author Casper van Battum
 *
 */
public class SubtitlePanel extends JPanel implements Colorizable {
	
	private static final long serialVersionUID = 1L;
	
	private final JFrame parent;
	
	private ColorScheme scheme;
	private String[] text;
	private Font f;
	private JLabel label;
	
	/**
	 * Creates a new instance of <code>SubtitlePanel</code> with no starting 
	 * text.
	 */
	public SubtitlePanel(JFrame parentFrame) {
		super();
		
		this.parent = parentFrame;
		this.text = new String[] {""};
		
		createPanel();
		
	}
	
	/**
	 * Creates a new instance of <code>SubtitlePanel</code>. An 
	 * <code>SRTEntry</code> is asked as parameter for use as the starting 
	 * text.
	 * 
	 * @param entry Starting entry.
	 */
	public SubtitlePanel(JFrame parentFrame, SRTEntry entry) {
		super();
		
		this.parent = parentFrame;
		this.text = entry.getText();
		
		createPanel();
		
	}
	
	/**
	 * Creates a new instance of SubtitlePanel using one or more Strings as 
	 * starting text.
	 * 
	 * @param str The text to display.
	 */
	public SubtitlePanel(JFrame parentFrame, String... str) {
		super();
		
		this.parent = parentFrame;
		this.text = str;
		
		createPanel();
		
	}
	
	/**
	 * Sets the text using a <code>String</code>. If the parameter is an array 
	 * with more than one element, each element will be displayed as a separate
	 * line. The onscreen text is immediately changed when this method gets 
	 * called.
	 * 
	 * @param str 
	 * 		A single <code>String</code> or an array of Strings as the new text
	 * 		of this </code>SubtitlePanel</code>.
	 */
	public void setText(String... str) {
		this.text = str;
		label.setText(createLabelText(str));
		
	}
	
	/**
	 * Sets the text using a <code>SRTEnry</code>. It uses the text from this 
	 * entry, but no other information is used. Each element of the entry's 
	 * text array is displayed as a separate line. The onscreen text is 
	 * immediately changed when this method is called.
	 * 
	 * @param entry 
	 * 		A <code>SRTEntry</code> of which the text will be used as the new 
	 * 		text of this <code>SubtitlePanel</code>.
	 */
	public void setTextFromEntry(SRTEntry entry) {
		this.text = entry.getText();
		label.setText(createLabelText(this.text));
		
	}
	
	/* Creates the panel. Used as a generic constructor, but can also be used
	 * to reset the panel
	 */
	private void createPanel() {
		if (this.text == null || this.text.length == 0) {
			throw new IllegalStateException("text array must be set to a non-null element or contain any elements");
			
		}
		
		//TODO: Make font setting
		f = new Font("Verdana", Font.BOLD, 26);
		scheme = SchemeManager.getPreferredScheme();
		
		setLayout(new BorderLayout());
		
		SubtitlePanelMouseListener spml = new SubtitlePanelMouseListener();
		
		addMouseMotionListener(spml);
		addMouseListener(spml);
		
		label = new JLabel();
		label.setFont(f);
		label.setForeground(scheme.getText());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText(createLabelText(text));
		add(label, BorderLayout.CENTER);
		
		setColors();
		
	}
	
	private void setColors() {
		setBackground(scheme.getBackground());
		label.setForeground(scheme.getText());
		
	}
	
	/*
	 * Creates the text of the label. The text has the following format:
	 * 
	 *		<html>
	 *			<div style="text-align:center">
	 *				[LINE 1]<br>
	 *				[LINE 2]<br>
	 *				...
	 *			</div>
	 *		</html>
	 * 
	 * The div style property is used to center the text as this is not easily
	 * done when using the Java Swing API
	 * 
	 */
	private String createLabelText(String... str) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><div style=\"text-align:center\">");
		for (String s : str) {
			sb.append(s + "<br>");
			
		}
		sb.delete(sb.length() - 4, sb.length());
		sb.append("</div></html>");
		
		return sb.toString();
		
	}
	
	/**
	 * Returns the text of this {@link SubtitlePanel} instance in the form of 
	 * an array. Each element of that array stands for a displayed line. If you
	 * want for example use the second line, you use this method and retrieve
	 * element [1]. 
	 * 
	 * @return 
	 * 		An array of <code>Strings</code> which contains all lines as 
	 * 		different elements
	 */
	public String[] getText() {
		return this.text;
		
	}

	@Override
	public void setColorScheme(ColorScheme scheme) {
		this.scheme = scheme;
		setColors();
		
	}

	@Override
	public ColorScheme getColorScheme() {
		return scheme;
		
	}
	
	private class SubtitlePanelMouseListener implements MouseMotionListener, MouseListener {
		
		private int px, py;
		
		@Override
		public void mouseDragged(MouseEvent e) {
			parent.setLocation(e.getXOnScreen() - px, e.getYOnScreen() - py);
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {
			px = e.getX();
			py = e.getY();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {
//			System.out.println("exiteds");
			
		}
		
	}
	
}
