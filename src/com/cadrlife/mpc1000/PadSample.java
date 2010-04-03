package com.cadrlife.mpc1000;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.input.SwappedDataInputStream;

import com.cadrlife.mpc1000.util.SwappedDataOutputHelper;

public class PadSample extends BaseMpcData {
	public static int MAX_RANGE = 127;
	public static int MIN_RANGE = 0;
	public static int MAX_LEVEL = 100;
	public static int MIN_LEVEL = 0;
	public static int MAX_TUNING = 3600;
	public static int MIN_TUNING = -3600;
	public static int ONE_SHOT_PLAYMODE = 0;
	public static int NOTE_ON_PLAYMODE = 1;
	private String sampleName = "";
	private int level = 0;
	private int rangeLower = 0;
	private int rangeUpper = 0;
	private short tuning = 0;
	private int playMode = 0; // 0="One Shot", 1="Note On"

	public void read(InputStream in) throws IOException {
		SwappedDataInputStream swappedIn = convertInputStream(in);
		byte[] sampleNameBytes = new byte[16];
		swappedIn.readFully(sampleNameBytes);
		setSampleName(new String(sampleNameBytes, Program.CHARSET).replace("\0", ""));
		swappedIn.skipBytes(1);
		setLevel(swappedIn.readUnsignedByte());
		setRangeLower(swappedIn.readUnsignedByte());
		setRangeUpper(swappedIn.readUnsignedByte());
		setTuning(swappedIn.readShort());
		setPlayMode(swappedIn.readUnsignedByte());
		swappedIn.skipBytes(1);
	}

	@Override
	public void write(OutputStream out) throws IOException {
		SwappedDataOutputHelper.writeAsciiString(out, getSampleName(), 16);
		SwappedDataOutputHelper.writeZeroes(out,1);
		out.write(getLevel());
		out.write(getRangeLower());
		out.write(getRangeUpper());
		SwappedDataOutputHelper.writeSignedShort(out, getTuning());
		out.write(getPlayMode());
		SwappedDataOutputHelper.writeZeroes(out,1);
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		checkLength(sampleName, 16);
		this.sampleName = sampleName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		checkRange(level,MIN_LEVEL,MAX_LEVEL);
		this.level = level;
	}

	public int getRangeLower() {
		return rangeLower;
	}

	public void setRangeLower(int rangeLower) {
		checkRange(rangeLower,MIN_RANGE,MAX_RANGE);
		this.rangeLower = rangeLower;
	}

	public int getRangeUpper() {
		return rangeUpper;
	}

	public void setRangeUpper(int rangeUpper) {
		checkRange(rangeUpper,MIN_RANGE,MAX_RANGE);
		this.rangeUpper = rangeUpper;
	}

	public short getTuning() {
		return tuning;
	}

	public void setTuning(short tuning) {
		checkRange(tuning,MIN_TUNING,MAX_TUNING);
		this.tuning = tuning;
	}

	public int getPlayMode() {
		return playMode;
	}

	public void setPlayMode(int playMode) {
		checkRange(playMode,ONE_SHOT_PLAYMODE,NOTE_ON_PLAYMODE);
		this.playMode = playMode;
	}


}
