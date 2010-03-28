package com.cadrlife.mpc1000;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

import com.cadrlife.mpc1000.util.DataOutputHelper;

public class Program extends BaseMpcData {
	public static final Charset CHARSET = Charset.forName("US-ASCII");
	private int fileSizeInBytes = 1066;
	private String filetype = "MPC1000 PGM 1.00"; // File Type "MPC1000 PGM 1.00"
	private Pad[] pads = new Pad[64];
	{
		for (int i = 0; i<64; i++) {
    		pads[i] = new Pad();
    	}
	}
	private MidiData midiData = new MidiData();
	private SliderData slider1 = new SliderData();
	private SliderData slider2 = new SliderData();

	public void readFromFile(String filename) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filename, "r");
		this.read(file);
		file.close();
	}
	public void writeToFile(String filename) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filename, "rw");
		this.write(file);
		file.close();
	}
	
	public void read(DataInput in) throws IOException {
    	readHeader(in);
    	for (int i = 0; i<64; i++) {
    		pads[i].read(in);
    	}
    	midiData = new MidiData();
    	midiData.read(in);
    	slider1.read(in);
    	slider2.read(in);
    	in.skipBytes(17);
    }
    
    public void readHeader(DataInput in) throws IOException {
    	fileSizeInBytes  = in.readUnsignedShort();
    	in.skipBytes(2); // Padding
    	byte[] filetypeBytes = new byte[16];
    	in.readFully(filetypeBytes); 
    	filetype = new String(filetypeBytes, CHARSET);
    	in.skipBytes(4); // Padding
    }
    
    public void write(DataOutput out) throws IOException {
    	writeHeader(out);
    	for (int i = 0; i<64; i++) {
			pads[i].write(out);
    	}
    	midiData.write(out);
    	slider1 = new SliderData();
    	slider1.write(out);
    	slider2.write(out);
    	out.write(new byte[17], 0, 17);
	}
    
    private void writeHeader(DataOutput out) throws IOException {
    	DataOutputHelper.writeUnsignedShort(out, fileSizeInBytes);
    	DataOutputHelper.writeZeroes(out, 2); // Padding
		DataOutputHelper.writeAsciiString(out, filetype, 16);
		DataOutputHelper.writeZeroes(out, 4); // Padding
	}

    public int getFileSizeInBytes() {
		return fileSizeInBytes;
	}
	public void setFileSizeInBytes(int fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public Pad[] getPads() {
		return pads;
	}
	public void setPads(Pad[] pads) {
		this.pads = pads;
	}
	public MidiData getMidiData() {
		return midiData;
	}
	public void setMidiData(MidiData midiData) {
		this.midiData = midiData;
	}
	public SliderData getSlider1() {
		return slider1;
	}
	public void setSlider1(SliderData slider1) {
		this.slider1 = slider1;
	}
	public SliderData getSlider2() {
		return slider2;
	}
	public void setSlider2(SliderData slider2) {
		this.slider2 = slider2;
	}
	
}
