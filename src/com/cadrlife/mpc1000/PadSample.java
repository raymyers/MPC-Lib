package com.cadrlife.mpc1000;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.cadrlife.mpc1000.util.DataOutputHelper;

public class PadSample extends BaseMpcData {
	public String sampleName;
	private int level = 0;
	private int rangeLower = 0;
	private int rangeUpper = 0;
	private short tuning = 0;
	private int playMode = 0; // 0="One Shot", 1="Note On"

	public void read(DataInput in) throws IOException {
		byte[] sampleNameBytes = new byte[16];
		in.readFully(sampleNameBytes);
		sampleName = new String(sampleNameBytes, Program.CHARSET);
		in.skipBytes(1);
		level  = in.readUnsignedByte();
		rangeLower = in.readUnsignedByte();
		rangeUpper = in.readUnsignedByte();
		tuning = in.readShort();
		playMode = in.readUnsignedByte();
		in.skipBytes(1);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		DataOutputHelper.writeAsciiString(out, sampleName, 16);
		DataOutputHelper.writeZeroes(out,1);
		out.write(level);
		out.write(rangeLower);
		out.write(rangeUpper);
		out.writeShort(tuning);
		out.write(playMode);
		DataOutputHelper.writeZeroes(out,1);
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRangeLower() {
		return rangeLower;
	}

	public void setRangeLower(int rangeLower) {
		this.rangeLower = rangeLower;
	}

	public int getRangeUpper() {
		return rangeUpper;
	}

	public void setRangeUpper(int rangeUpper) {
		this.rangeUpper = rangeUpper;
	}

	public short getTuning() {
		return tuning;
	}

	public void setTuning(short tuning) {
		this.tuning = tuning;
	}

	public int getPlayMode() {
		return playMode;
	}

	public void setPlayMode(int playMode) {
		this.playMode = playMode;
	}
}
