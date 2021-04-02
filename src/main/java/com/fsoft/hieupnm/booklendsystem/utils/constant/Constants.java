package com.fsoft.hieupnm.booklendsystem.utils.constant;

public class Constants {

	/*
	 * Store all file input/output path information
	 */
	public static final String CREATE_BOOK_PATH = "inputfile\\book\\createbook.txt";
	public static final String READ_BOOK_PATH = "inputfile\\book\\readbook.txt";
	public static final String UPDATE_BOOK_PATH = "inputfile\\book\\updatebook.txt";
	public static final String DELETE_BOOK_PATH = "inputfile\\book\\deletebook.txt";
	public static final String LEND_BOOK_PATH = "inputfile\\book\\lendbook.txt";
	public static final String RETURN_BOOK_PATH = "inputfile\\book\\returnbook.txt";

	public static final String CREATE_USER_PATH = "inputfile\\user\\createuser.txt";
	public static final String READ_USER_PATH = "inputfile\\user\\readuser.txt";
	public static final String UPDATE_USER_PATH = "inputfile\\user\\updateuser.txt";
	public static final String DELETE_USER_PATH = "inputfile\\user\\deleteuser.txt";

	public static final String CREATE_DATABASE_PATH = "inputfile\\database\\book_lend_system_HieuPNM.sql";

	public static final String EXCEL_TEMPLATE_USER_PATH = "template\\user_template.xls";
	public static final String EXCEL_TEMPLATE_BOOK_PATH = "template\\book_template.xls";

	public static final String PRINT_ALL_USER_PATH = "outputfile\\user\\";
	public static final String PRINT_ALL_BOOK_PATH = "outputfile\\book\\";

	/*
	 * Format view for app
	 */
	public static final int FRAME_WIDTH = 70;
	public static final String HORIZONTAL_LINE = "-".repeat(FRAME_WIDTH);
}
