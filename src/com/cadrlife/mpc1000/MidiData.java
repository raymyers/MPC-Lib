package com.cadrlife.mpc1000;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MidiData extends BaseMpcData {
	private int[] padMidiNoteValues = new int[64];
	private int[] midiNotePadValues = new int[64];
	private int midiProgramChange = 0;
	
	@Override
	public void read(DataInput in) throws IOException {
		for (int i = 0; i<64; i++) {
			padMidiNoteValues[i] = in.readUnsignedByte();
		}
		for (int i = 0; i<64; i++) {
			midiNotePadValues[i] = in.readUnsignedByte();
		}
		midiProgramChange = in.readUnsignedByte();
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		for (int i = 0; i<64; i++) {
			out.write(padMidiNoteValues[i]);
//			out.writeByte(padMidiNoteValues[i]);
		}
		for (int i = 0; i<64; i++) {
			out.writeByte(midiNotePadValues[i]);
		}
		out.writeByte(midiProgramChange);
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
