package com.fsoft.hieupnm.booklendsystem.utils.format;

import com.fsoft.hieupnm.booklendsystem.utils.constant.Constants;

public class StringFormat {

	/*
	 * Display the title with horizontal line
	 */
	public static String displayTitle(String title) {
		int titleLength = title.length();
		int frontSpace = Integer.valueOf((Constants.FRAME_WIDTH - titleLength) / 2);
		int backSpace = Constants.FRAME_WIDTH - titleLength - frontSpace;
		title = "-".repeat(frontSpace) + title + "-".repeat(backSpace);
		return title;
	}
}
