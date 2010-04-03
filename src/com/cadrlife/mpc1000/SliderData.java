package com.cadrlife.mpc1000;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.input.SwappedDataInputStream;

public class SliderData extends BaseMpcData {

	private int pad;
	private int parameter; // 0="Tune", 1="Filter", 2="Layer", 3="Attack", 4="Decay"
	private byte tuneLow;
	private byte tuneHigh;
	private byte filterLow;
	private byte filterHigh;
	private byte layerLow;
	private byte layerHigh;
	private byte attackLow;
	private byte attackHigh;
	private byte decayLow;
	private byte decayHigh;

	@Override
	public void read(InputStream in) throws IOException {
		SwappedDataInputStream swappedInput = convertInputStream(in);
		pad = swappedInput.readUnsignedByte();
		swappedInput.skipBytes(1); // Unknown 0x01;
		parameter = swappedInput.readUnsignedByte();
		tuneLow = swappedInput.readByte();
		tuneHigh = swappedInput.readByte();
		filterLow = swappedInput.readByte();
		filterHigh = swappedInput.readByte();
		layerLow = swappedInput.readByte();
		layerHigh = swappedInput.readByte();
		attackLow = swappedInput.readByte();
		attackHigh = swappedInput.readByte();
		decayLow = swappedInput.readByte();
		decayHigh = swappedInput.readByte();
	}

	@Override
	public void write(OutputStream out) throws IOException {
		out.write(pad);
		out.write(1); // Unknown 0x01;
		out.write(parameter);
		out.write(tuneLow);
		out.write(tuneHigh);
		out.write(filterLow);
		out.write(filterHigh);
		out.write(layerLow);
		out.write(layerHigh);
		out.write(attackLow);
		out.write(attackHigh);
		out.write(decayLow);
		out.write(decayHigh);
	}

}
