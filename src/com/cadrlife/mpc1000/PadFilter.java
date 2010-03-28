package com.cadrlife.mpc1000;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.cadrlife.mpc1000.util.DataOutputHelper;

public class PadFilter extends BaseMpcData {
	private int type; // 0="Off", 1="Lowpass", 2="Bandpass", 3="Highpass"
	private int freq;
	private int res;
	private int velocityToFrequency;

	@Override
	public void read(DataInput in) throws IOException {
		type = in.readUnsignedByte();
		freq = in.readUnsignedByte();
		res = in.readUnsignedByte();
		in.skipBytes(4);
		velocityToFrequency = in.readUnsignedByte();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.write(type);
		out.write(freq);
		out.write(res);
		DataOutputHelper.writeZeroes(out, 4);
		out.write(velocityToFrequency);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public int getVelocityToFrequency() {
		return velocityToFrequency;
	}

	public void setVelocityToFrequency(int velocityToFrequency) {
		this.velocityToFrequency = velocityToFrequency;
	}

}
