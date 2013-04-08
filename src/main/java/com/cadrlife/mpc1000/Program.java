package com.cadrlife.mpc1000;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.input.SwappedDataInputStream;

import com.cadrlife.mpc1000.util.SwappedDataOutputHelper;
import com.google.common.base.Throwables;

public class Program extends BaseMpcData {
	public static final Charset CHARSET = Charset.forName("US-ASCII");
	private int fileSizeInBytes = 1066;
	private String filetype = "MPC1000 PGM 1.00";
	private Pad[] pads = new Pad[64];
	{
		for (int i = 0; i<64; i++) {
    		pads[i] = new Pad();
    	}
	}
	
	private MidiData midiData = new MidiData();
	private SliderData slider1 = new SliderData();
	private SliderData slider2 = new SliderData();

	public static Program createDefault() {
		Program program = new Program();
		try {
			program.read(new SwappedDataInputStream(program.getClass().getResourceAsStream("default.pgm")));
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		return program;
	}
	
	public static Program createChromatic() {
		
		Program program = new Program();
		try {
			program.read(new SwappedDataInputStream(program.getClass().getResourceAsStream("chromatic.pgm")));
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		return program;
	}
	
	public void readFromFile(String filename) throws IOException {
		SwappedDataInputStream in = new SwappedDataInputStream(new FileInputStream(filename));
		this.read(in);
		in.close();
	}
	public void writeToFile(String filename) throws IOException {
		FileOutputStream file = new FileOutputStream(filename);
		this.write(file);
		file.close();
	}
	
	@Override
	public void read(InputStream in) throws IOException {
		SwappedDataInputStream swappedIn = convertInputStream(in);
    	readHeader(swappedIn);
    	for (int i = 0; i<64; i++) {
    		getPads()[i].read(swappedIn);
    	}
    	getMidiData().read(swappedIn);
    	getSlider1().read(swappedIn);
    	getSlider2().read(swappedIn);
    	swappedIn.skipBytes(17);
    }
    
    private void readHeader(SwappedDataInputStream in) throws IOException {
    	setFileSizeInBytes(in.readUnsignedShort());
    	in.skipBytes(2); // Padding
    	byte[] filetypeBytes = new byte[16];
    	in.readFully(filetypeBytes); 
    	setFiletype(new String(filetypeBytes, CHARSET).replace("\0",""));
    	in.skipBytes(4); // Padding
    }
    
    @Override
	public void write(OutputStream out) throws IOException {
    	writeHeader(out);
    	for (int i = 0; i<64; i++) {
			getPads()[i].write(out);
    	}
    	getMidiData().write(out);
    	getSlider1().write(out);
    	getSlider2().write(out);
    	SwappedDataOutputHelper.writeZeroes(out, 17);
	}
    
    private void writeHeader(OutputStream out) throws IOException {
    	SwappedDataOutputHelper.writeUnsignedShort(out, getFileSizeInBytes());
    	SwappedDataOutputHelper.writeZeroes(out, 2); // Padding
		SwappedDataOutputHelper.writeAsciiString(out, getFiletype(), 16);
		SwappedDataOutputHelper.writeZeroes(out, 4); // Padding
	}

	public void setFileSizeInBytes(int fileSizeInBytes) {
		this.fileSizeInBytes = fileSizeInBytes;
	}

	public int getFileSizeInBytes() {
		return fileSizeInBytes;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setPads(Pad[] pads) {
		this.pads = pads;
	}

	public Pad[] getPads() {
		return pads;
	}

	public void setMidiData(MidiData midiData) {
		this.midiData = midiData;
	}

	public MidiData getMidiData() {
		return midiData;
	}

	public void setSlider1(SliderData slider1) {
		this.slider1 = slider1;
	}

	public SliderData getSlider1() {
		return slider1;
	}

	public void setSlider2(SliderData slider2) {
		this.slider2 = slider2;
	}

	public SliderData getSlider2() {
		return slider2;
	}
	
}
