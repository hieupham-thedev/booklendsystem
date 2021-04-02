package com.fsoft.hieupnm.booklendsystem.utils.format;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringFormatTest {
	@Test
	public void displayTitle_pass() {
		String actual = StringFormat.displayTitle("123456");
		String expect = "-".repeat(32) + "123456" + "-".repeat(32);
		boolean result = expect.equals(actual);
		assertTrue(result);
	}
}
