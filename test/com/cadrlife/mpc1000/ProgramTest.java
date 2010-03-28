package com.cadrlife.mpc1000;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class ProgramTest {
	@Test
	public void readWrite() throws IOException {
		Program program = new Program();
    	program.readFromFile("c:\\0.pgm");
    	program.writeToFile("c:\\1.pgm");
    	Program program2 = new Program();
    	program2.readFromFile("c:\\1.pgm");
    	assertEquals(program, program2);
	}
}
