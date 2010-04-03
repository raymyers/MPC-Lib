package com.cadrlife.mpc1000;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.input.SwappedDataInputStream;

public class MidiData extends BaseMpcData {
	private int[] padMidiNoteValues = new int[64];
	private int[] midiNotePadValues = new int[128];
	private int midiProgramChange = 0;
	
	@Override
	public void read(InputStream in) throws IOException {
		SwappedDataInputStream swappedIn = convertInputStream(in);
		for (int i = 0; i<64; i++) {
			padMidiNoteValues[i] = swappedIn.readUnsignedByte();
		}
		for (int i = 0; i<128; i++) {
			midiNotePadValues[i] = swappedIn.readUnsignedByte();
		}
		midiProgramChange = swappedIn.readUnsignedByte();
	}
	
	@Override
	
	public void write(OutputStream out) throws IOException {
		for (int i = 0; i<64; i++) {
			out.write(padMidiNoteValues[i]);
//			out.writeByte(padMidiNoteValues[i]);
		}
		for (int i = 0; i<128; i++) {
			out.write(midiNotePadValues[i]);
		}
		out.write(midiProgramChange);
	}

	public int[] getPadMidiNoteValues() {
		return padMidiNoteValues;
	}

	public void setPadMidiNoteValues(int[] padMidiNoteValues) {
		this.padMidiNoteValues = padMidiNoteValues;
	}

	public int[] getMidiNotePadValues() {
		return midiNotePadValues;
	}

	public void setMidiNotePadValues(int[] midiNotePadValues) {
		this.midiNotePadValues = midiNotePadValues;
	}

	public int getMidiProgramChange() {
		return midiProgramChange;
	}

	public void setMidiProgramChange(int midiProgramChange) {
		this.midiProgramChange = midiProgramChange;
	}

}
