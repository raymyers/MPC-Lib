package com.cadrlife.mpc1000;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class ProgramTest {
	@Test
	public void readWrite() throws IOException {
		Program program = new Program();
// Reading from the classpath gets bad data for whatever reason.
//		program.read(new DataInputStream(getClass().getResourceAsStream("test.pgm")));
		program.readFromFile("test/com/cadrlife/mpc1000/test.pgm");
		File temp = File.createTempFile("test",".pgm");
		temp.deleteOnExit();
		System.out.println(temp.getAbsolutePath());
		program.writeToFile(temp.getAbsolutePath());
    	Program program2 = new Program();
    	program2.readFromFile(temp.getAbsolutePath());
    	assertProgramsEqual(program, program2);
	}

	private void assertProgramsEqual(Program program, Program program2) {
		for (int i=0; i<64; i++) {
			Pad program1pad = program.getPads()[i];
			Pad program2Pad = program2.getPads()[i];
			assertEquals(program1pad.getPadSamples()[0], program2Pad.getPadSamples()[0]);
			assertEquals(program1pad.getPadSamples()[1], program2Pad.getPadSamples()[1]);
			assertEquals(program1pad.getPadSamples()[2], program2Pad.getPadSamples()[2]);
			assertEquals(program1pad.getPadSamples()[3], program2Pad.getPadSamples()[3]);
	    	assertEquals(program1pad.getAttack(), program2Pad.getAttack());
	    	assertEquals(program1pad.getDecay(), program2Pad.getDecay());
	    	assertEquals(program1pad.getDecayMode(), program2Pad.getDecayMode());
	    	assertEquals(program1pad.getFilter1(), program2Pad.getFilter1());
	    	assertEquals(program1pad.getFilter2(), program2Pad.getFilter2());
	    	assertEquals(program1pad.getFilterAttenuation(), program2Pad.getFilterAttenuation());
	    	assertEquals(program1pad.getFxSend(), program2Pad.getFxSend());
	    	assertEquals(program1pad.getFxSendLevel(), program2Pad.getFxSendLevel());
	    	assertEquals(program1pad.getMixerLevel(), program2Pad.getMixerLevel());
	    	assertEquals(program1pad.getMixerPan(), program2Pad.getMixerPan());
	    	assertEquals(program1pad.getMuteGroup(), program2Pad.getMuteGroup());
	    	assertEquals(program1pad.getOutput(), program2Pad.getOutput());
	    	assertEquals(program1pad.isVoiceOverlap(), program2Pad.isVoiceOverlap());
	    	assertEquals(program1pad.getVelocityToLevel(), program2Pad.getVelocityToLevel());
	    	assertEquals(program1pad, program2Pad);
		}
		String[] program1Lines = program.toString().split("\n");
		String[] program2Lines = program2.toString().split("\n");
		for (int i=0;i<program1Lines.length;i++) {
			if (!program1Lines[i].contains("@")) {
				assertEquals(program1Lines[i],program2Lines[i]);
			}
		}
    	assertEquals(program.getFileSizeInBytes(), program2.getFileSizeInBytes());
    	assertEquals(program.getFiletype(), program2.getFiletype());
    	assertEquals(program.getMidiData(), program2.getMidiData());
    	assertEquals(program.getSlider1(), program2.getSlider1());
    	assertEquals(program.getSlider2(), program2.getSlider2());
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

