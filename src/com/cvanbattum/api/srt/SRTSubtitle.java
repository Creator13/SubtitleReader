package com.cvanbattum.api.srt;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Casper van Battum
 *
 */
public class SRTSubtitle extends ArrayList<SRTEntry> {
	
	private static final long serialVersionUID = -2522062504183812033L;
	
	private long[][] timetable = new long[Integer.MAX_VALUE - 8][2];
	
	/**
	 * Creates a new, empty instance of <code>SRTSubtitle</code>.
	 */
	public SRTSubtitle() {
		super(0);
		
	}
	
	/**
	 * Creates a new instance of <code>SRTSubtitle</code>, with values in it.
	 * 
	 * @param entries The entries of which this subtitle consists.
	 */
	public SRTSubtitle(SRTEntry... entries) {
		super(entries.length);
		
		//Add all elements from entries to the arraylist
		for (SRTEntry entry : entries) {
			add(entry);
			
		}
		
	}
	
	/**
	 * Insert one or more SRT entries at a given index. The entries of the 
	 * list above will be increased. The entries will be inserted at exactly
	 * the given index. The entry currently at that index will be pushed 
	 * upwards in the list.
	 * <br>
	 * <br>
	 * <b>Warning:</b> inserting in large arrays can take very long.
	 * 
	 * @param i
	 * 		The index o insert the entries.
	 * @param entries
	 * 		The entries to insert.
	 */
	public void insertAt(int index, SRTEntry... entries) {
		if (index < 0 || index > size()) {
			throw new IllegalArgumentException("index may not be larger than the array's size or smaller than zero");
			
		}
		//Given index may be at the precise end. Use more simple method instead and quit directly
		else if (index == size()) {
			for (SRTEntry e : entries) {
				add(e);
				
			}
			return;
			
		}
		
		//Normal method
		ArrayList<SRTEntry> al = new ArrayList<>();
		
		//Loop over entries until i < index (entries up to (not including) the
		//index of the new item) and add them to the new list
		int i = 0;
		while (i < index) {
			al.add(get(i));
			i++;
			
		}
		
		//Add the new entries
		for (SRTEntry e : entries) {
			al.add(e);
			
		}
		
		//Now add the remaining entries of the original list.
		i = 0;
		while (i < size() - index) {
			//Use the index variable as offset.
			al.add(get(i + index));
			i++;
			
		}
		
		//Clear the current list to add the new list to it
		clear();
		for (SRTEntry e : al) {
			add(e);
			
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (SRTEntry entry : this) {
			sb.append((indexOf(entry) + 1) + "\n");
			sb.append(String.format("%s --> %s\n", formatTime(entry.getStartTime()), formatTime(entry.getEndTime())));
			for (String s : entry.getText()) {
				sb.append(s + "\n");
				
			}
			sb.append("\n");
			
		}
		
		return sb.toString();
	}
	
	private String formatTime(long timeMillis) {
		final long hr = TimeUnit.MILLISECONDS.toHours(timeMillis);
        final long min = TimeUnit.MILLISECONDS.toMinutes(timeMillis - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(timeMillis - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(timeMillis - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        return String.format("%02d:%02d:%02d,%03d", hr, min, sec, ms);
		
	}
	
}
