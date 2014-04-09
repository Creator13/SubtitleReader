package com.cvanbattum.api.srt;

/**
 * 
 * @author Casper van Battum
 *
 */
public class SRTEntry {

	/**
	 * Subtitle line 1
	 */
	public static final int LINE_1 = 0b11000001;
	/**
	 * Subtitle line 2
	 */
	public static final int LINE_2 = 0b11000010;
//	/**
//	 * Subtitle line 3
//	 */
//	public static final int LINE_3 = 0b11000011;
//	/**
//	 * Subtitle line 4
//	 */
//	public static final int LINE_4 = 0b11000100;
	
	private long startTime;
	private long endTime;
	private String[] text;
	
	/**
	 * <p>Creates a new instance of <code>SRTEntry</code> with a start time, 
	 * end time and the text to show. The text has to be divided in lines, each
	 * entry of the array stands for one line. So, a subtitle consisting of two
	 * line would be an array with two String in it.</p>
	 * 
	 * <p>The start and end times in milliseconds are relative to the playing
	 * time. The subtitle will start when <code>x</code> milliseconds have 
	 * passed by since the starting of the subtitle</p>
	 * 
	 * @param startTime 
	 * 			The time to start the subtitle in milliseconds
	 * @param endTime
	 * 			The time the subtitle will end in milliseconds
	 * @param text
	 * 			The text of the subtitle
	 */
	public SRTEntry(long startTime, long endTime, String... text) {
		if (startTime >= 0 && startTime < endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
			
		}
		else if (startTime == endTime) {
			throw new IllegalArgumentException("Starting and ending times cannot be equal to eachother");
			
		}
		else if (startTime < 0) {
			throw new IllegalArgumentException("Starting time cannot be equal to zero");
			
		}
		else {
			throw new IllegalArgumentException("Starting time cannot be greater than ending time");
			
		}
		
		this.text = text;
		
	}
	
	/**
	 * Sets the start time of this subtitle. This time is relative to the 
	 * movie, so the subtitle will start at <code>x</code> milliseconds 
	 * from the beginning of this movie. 
	 * 
	 * @param startTime the start time of this subtitle.
	 * @throws IllegalArgumentException 
	 * 			When the starting time is greater than the current ending time
	 * 			or when it is smaller than zero.
	 */
	public void setStartTime(long startTime) {
		if (startTime < this.endTime && startTime >= 0) {
			this.startTime = startTime;
			
		}
		else {
			throw new IllegalArgumentException("Starting time cannot be greater than ending time or smaller than zero.");
			
		}
		
	}
	
	/**
	 * Sets the end time of this subtitle. This time is relative to the movie, 
	 * so the subtitle will end at <code>x</code> milliseconds from the 
	 * beginning of this movie. 
	 * 
	 * @param endTime the end time of this subtitle.
	 * @throws IllegalArgumentException 
	 * 			When the ending time is smaller than the current starting time.
	 */
	public void setEndTime(long endTime) {
		if (endTime > this.startTime) {
			this.endTime = endTime;
			
		}
		else {
			throw new IllegalArgumentException("Ending time cannot be smaller than starting time");
			
		}
		
	}
	
	/**
	 * Changes all the text in this subtitle to the new, given text. Differs 
	 * from {@link #setTextAtLine(int, String)} as the whole array of lines 
	 * is overwritten with a new one, not only one line. Also, if you replace 
	 * the array with one with less entries, the difference in line will also
	 * be overwritten.
	 * 
	 * @param text The new text of this subtitle.
	 */
	public void setText(String... text) {
		this.text = text;
		
	}
	
	/**
	 * Sets the text of the subtitle at a specific line. The line numbers are 
	 * the lines given in in {@link SRTEntry}, and can either be 1, 2, 3 or 4.
	 * However, most subtitles only have a maximum of two lines.
	 * 
	 * @param line 
	 * 			The line of the text that will be replaced
	 * @param text 
	 * 			The new text on this line
	 */
	public void setTextAtLine(int line, String text) {
		int i = getCorrespondingLineNumber(line);
		this.text[i] = text;
		
	}
	
	// Gives the corresponding index to the line number constant in this class
	private int getCorrespondingLineNumber(int line) {
		if (line != LINE_1 || line != LINE_2 /*|| line != LINE_3 || line != LINE_4*/) { //TODO WARNING: potential problem  //TODO WARNING: commented out
			throw new IllegalArgumentException("Line number invalid: choose from constants in SRTEntry class");
			
		}
		
		switch (line) {
		case LINE_1:
			return 0;
		case LINE_2:
			return 1;
//		case LINE_3:
//			return 2;
//		case LINE_4:
//			return 3;
		default:
			throw new IllegalArgumentException("Line number invalid: unknown");
		}
		
	}
	
	/**
	 * Returns the end time of this entry in milliseconds. The end time is 
	 * relative to the movie the subtitles belong to. For example, if the end
	 * time is <code>00:11:56.345</code>, this subtitle is set to end at this
	 * time since the start of the movie.
	 * 
	 * @return The end time of this entry in milliseconds
	 */
	public long getEndTime() {
		return this.endTime;
		
	}
	
	/**
	 * Returns the starting time of this entry in milliseconds. The starting 
	 * time is relative to the movie this subtitle belongs to. For example, if
	 * the starting time is <code>00:11:56.345</code>, this subtitle is set to
	 * show at that time form the moment the user hits 'play'.
	 * 
	 * @return the starting time of this entry in milliseconds
	 */
	public long getStartTime() {
		return this.startTime;
		
	}
	
	/**
	 * Returns the array of lines of text the subtitle has to display. Every 
	 * array-entry stands for one line.
	 * 
	 * @return The text of the subtitle
	 */
	public String[] getText() {
		return this.text;
		
	}
	
	/**
	 * Returns the duration of this entry in milliseconds. The duration is 
	 * defined by the end time of this entry minus he start time.
	 * 
	 * @return The duration of this subtitle entry in milliseconds.
	 */
	public long getDuration() {
		return this.endTime - this.startTime;
		
	}
	
}
