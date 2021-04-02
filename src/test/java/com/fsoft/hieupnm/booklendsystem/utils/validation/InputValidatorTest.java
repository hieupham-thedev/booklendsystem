package com.fsoft.hieupnm.booklendsystem.utils.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InputValidatorTest {
	@Test
	public void isValidId_pass() {
		assertTrue(InputValidator.isValidId("123"));
	}
	
	@Test
	public void isValidId_fail() {
		assertFalse(InputValidator.isValidId("abc"));
	}
	
	@Test
	public void isValidLength_pass() {
		assertTrue(InputValidator.isValidLength("JOHN"));
	}
	
	@Test
	public void isValidLength_fail() {
		assertFalse(InputValidator.isValidLength("123456789123456789123456789"));
	}
	
	@Test
	public void isValidDate_pass() {
		assertTrue(InputValidator.isValidDate("2020-05-20"));
	}
	
	@Test
	public void isValidDate_fail() {
		assertFalse(InputValidator.isValidDate("2020-13-20"));
		assertFalse(InputValidator.isValidDate("20208-131-200"));
	}
}
