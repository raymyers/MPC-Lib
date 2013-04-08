package com.cadrlife.mpc1000;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.input.SwappedDataInputStream;

import com.cadrlife.mpc1000.util.SwappedDataOutputHelper;

public class PadFilter extends BaseMpcData {
	private int type; // 0="Off", 1="Lowpass", 2="Bandpass", 3="Highpass"
	private int freq;
	private int res;
	private int velocityToFrequency;

	@Override
	public void read(InputStream in) throws IOException {
		SwappedDataInputStream swappedIn = convertInputStream(in);
		type = swappedIn.readUnsignedByte();
		freq = swappedIn.readUnsignedByte();
		res = swappedIn.readUnsignedByte();
		swappedIn.skipBytes(4);
		velocityToFrequency = swappedIn.readUnsignedByte();
	}

	@Override
	public void write(OutputStream out) throws IOException {
		out.write(type);
		out.write(freq);
		out.write(res);
		SwappedDataOutputHelper.writeZeroes(out, 4);
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
