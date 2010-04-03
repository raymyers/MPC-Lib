package com.cadrlife.mpc1000;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.input.SwappedDataInputStream;
import org.apache.commons.lang.BooleanUtils;

import com.cadrlife.mpc1000.util.SwappedDataOutputHelper;

public class Pad extends BaseMpcData {
	private PadSample[] padSamples = new PadSample[4];
	{
		for (int i=0; i<4; i++) {
			padSamples[i] = new PadSample();
		}
	}
	private boolean voiceOverlap;
	private int muteGroup;
	private int attack;
	private int decay;
	private int decayMode; // 0="End", 1="Start"
	private int velocityToLevel;
	private PadFilter filter1;
	private PadFilter filter2;
	private int mixerLevel;
	private int mixerPan;
	private int output; // 0="Stereo", 1="1-2", 2="3-4"
	private int fxSend; // 0="Off", 1="1", 2="2"
	private int fxSendLevel;
	private int filterAttenuation; // 0="0dB", 1="-6dB", 2="-12dB"
	
	public void read(InputStream in) throws IOException {
		SwappedDataInputStream swappedIn = convertInputStream(in);
		for (int i=0; i<4; i++) {
			padSamples[i].read(swappedIn);
		}
		swappedIn.skipBytes(2);
		// SwappedDataInputStream.readBoolean returns inverted results as of IO Commons 1.4.
		voiceOverlap = BooleanUtils.toBoolean(swappedIn.readByte());
		muteGroup = swappedIn.readUnsignedByte();
		swappedIn.skipBytes(1); // Padding
		swappedIn.skipBytes(1); // Unknown. 0x01
		attack = swappedIn.readUnsignedByte();
		decay = swappedIn.readUnsignedByte();
		decayMode = swappedIn.readUnsignedByte();
		swappedIn.skipBytes(2);
		velocityToLevel = swappedIn.readUnsignedByte();
		swappedIn.skipBytes(5);
		filter1 = new PadFilter();
		filter1.read(swappedIn);
		filter2 = new PadFilter();
		filter2.read(swappedIn);
		swappedIn.skipBytes(14);
		mixerLevel = swappedIn.readUnsignedByte();
		mixerPan = swappedIn.readUnsignedByte();
		output = swappedIn.readUnsignedByte();
		fxSend = swappedIn.readUnsignedByte();
		fxSendLevel = swappedIn.readUnsignedByte();
		filterAttenuation = swappedIn.readUnsignedByte();
		swappedIn.skipBytes(15);
	}

	@Override
	public void write(OutputStream out) throws IOException {
		for (int i=0; i<4; i++) {
			padSamples[i].write(out);
		}
		SwappedDataOutputHelper.writeZeroes(out, 2);
		out.write(BooleanUtils.toInteger(voiceOverlap));
		out.write(muteGroup);
		SwappedDataOutputHelper.writeZeroes(out, 1); // Padding
		out.write(1); // Unknown. 0x01
		out.write(attack);
		out.write(decay);
		out.write(decayMode);
		SwappedDataOutputHelper.writeZeroes(out, 2); // Padding
		out.write(velocityToLevel);
		SwappedDataOutputHelper.writeZeroes(out, 5); // Padding
		filter1.write(out);
		filter2.write(out);
		SwappedDataOutputHelper.writeZeroes(out, 14); // Padding
		out.write(mixerLevel);
		out.write(mixerPan);
		out.write(output);
		out.write(fxSend);
		out.write(fxSendLevel);
		out.write(filterAttenuation);
		SwappedDataOutputHelper.writeZeroes(out, 15); // Padding
	}

	public PadSample[] getPadSamples() {
		return padSamples;
	}

	public void setPadSamples(PadSample[] padSamples) {
		this.padSamples = padSamples;
	}

	public boolean isVoiceOverlap() {
		return voiceOverlap;
	}

	public void setVoiceOverlap(boolean voiceOverlap) {
		this.voiceOverlap = voiceOverlap;
	}

	public int getMuteGroup() {
		return muteGroup;
	}

	public void setMuteGroup(int muteGroup) {
		this.muteGroup = muteGroup;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDecay() {
		return decay;
	}

	public void setDecay(int decay) {
		this.decay = decay;
	}

	public int getDecayMode() {
		return decayMode;
	}

	public void setDecayMode(int decayMode) {
		this.decayMode = decayMode;
	}

	public int getVelocityToLevel() {
		return velocityToLevel;
	}

	public void setVelocityToLevel(int velocityToLevel) {
		this.velocityToLevel = velocityToLevel;
	}

	public PadFilter getFilter1() {
		return filter1;
	}

	public void setFilter1(PadFilter filter1) {
		this.filter1 = filter1;
	}

	public PadFilter getFilter2() {
		return filter2;
	}

	public void setFilter2(PadFilter filter2) {
		this.filter2 = filter2;
	}

	public int getMixerLevel() {
		return mixerLevel;
	}

	public void setMixerLevel(int mixerLevel) {
		this.mixerLevel = mixerLevel;
	}

	public int getMixerPan() {
		return mixerPan;
	}

	public void setMixerPan(int mixerPan) {
		this.mixerPan = mixerPan;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public int getFxSend() {
		return fxSend;
	}

	public void setFxSend(int fxSend) {
		this.fxSend = fxSend;
	}

	public int getFxSendLevel() {
		return fxSendLevel;
	}

	public void setFxSendLevel(int fxSendLevel) {
		this.fxSendLevel = fxSendLevel;
	}

	public int getFilterAttenuation() {
		return filterAttenuation;
	}

	public void setFilterAttenuation(int filterAttenuation) {
		this.filterAttenuation = filterAttenuation;
	}

}
