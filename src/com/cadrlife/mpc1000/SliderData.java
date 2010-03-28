package com.cadrlife.mpc1000;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.cadrlife.mpc1000.util.DataOutputHelper;

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
	public void read(DataInput in) throws IOException {
		pad = in.readUnsignedByte();
		in.skipBytes(1); // Unknown 0x01;
		parameter = in.readUnsignedByte();
		tuneLow = in.readByte();
		tuneHigh = in.readByte();
		filterLow = in.readByte();
		filterHigh = in.readByte();
		layerLow = in.readByte();
		layerHigh = in.readByte();
		attackLow = in.readByte();
		attackHigh = in.readByte();
		decayLow = in.readByte();
		decayHigh = in.readByte();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.write(pad);
		out.write(1); // Unknown 0x01;
		out.write(parameter);
		DataOutputHelper.writeSignedByte(out, tuneLow);
		DataOutputHelper.writeSignedByte(out, tuneHigh);
		DataOutputHelper.writeSignedByte(out, filterLow);
		DataOutputHelper.writeSignedByte(out, filterHigh);
		DataOutputHelper.writeSignedByte(out, layerLow);
		DataOutputHelper.writeSignedByte(out, layerHigh);
		DataOutputHelper.writeSignedByte(out, attackLow);
		DataOutputHelper.writeSignedByte(out, attackHigh);
		DataOutputHelper.writeSignedByte(out, decayLow);
		DataOutputHelper.writeSignedByte(out, decayHigh);
	}

}
