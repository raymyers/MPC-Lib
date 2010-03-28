package com.cadrlife.mpc1000;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ProgramTest {
	@Test
	public void readWrite() throws IOException {
		Program program = new Program();
		program.read(new DataInputStream(getClass().getResourceAsStream("test.pgm")));
		File temp = File.createTempFile("test",".pgm");
		temp.deleteOnExit();

		program.writeToFile(temp.getAbsolutePath());
    	Program program2 = new Program();
    	program2.readFromFile(temp.getAbsolutePath());
    	assertEquals(program, program2);
	}
	
	@Test
	public void createDefault() {
		Program.createDefault();
	}
	
	@Test
	public void createChromatic() {
		Program.createChromatic();
	}
}
