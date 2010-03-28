package com.cadrlife.mpc1000.util;

import java.io.DataOutput;
import java.io.IOException;

import com.cadrlife.mpc1000.Program;

public class DataOutputHelper {

	public static void writeUnsignedShort(DataOutput out, int anUnsignedShort) throws IOException {
		out.write((anUnsignedShort & 0xFF00) >> 8);
		out.write(anUnsignedShort & 0x00FF);
	}
	public static void writeAsciiString(DataOutput out, String string, int size) throws IOException {
		if (string.length() > size) {
			throw new IOException("String too big");
		}
		out.write(string.getBytes(Program.CHARSET));
		writeZeroes(out, size - string.length());
	}
	public static void writeZeroes(DataOutput out, int size) throws IOException {
		byte[] buf = new byte[size];
		out.write(buf);
	}
	
	public static void writeSignedByte(DataOutput out, byte signedByte) throws IOException {
		out.write(new byte[] {signedByte});
	}

}
