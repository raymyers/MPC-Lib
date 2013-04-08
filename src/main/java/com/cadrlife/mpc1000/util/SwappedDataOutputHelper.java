package com.cadrlife.mpc1000.util;

import java.io.IOException;
import java.io.OutputStream;

import com.cadrlife.mpc1000.Program;

public class SwappedDataOutputHelper {
	public static void writeUnsignedShort(OutputStream out, int anUnsignedShort) throws IOException {
		out.write(anUnsignedShort & 0x00FF);
		out.write((anUnsignedShort & 0xFF00) >> 8);
	}
	public static void writeAsciiString(OutputStream out, String string, int size) throws IOException {
		if (string.length() > size) {
			throw new IOException("String too big");
		}
		out.write(string.getBytes(Program.CHARSET));
		writeZeroes(out, size - string.length());
	}
	public static void writeZeroes(OutputStream out, int size) throws IOException {
		out.write(new byte[size]);
	}
	public static void writeSignedShort(OutputStream out, short aShort) throws IOException {
		out.write(aShort & 0x00FF);
		out.write((aShort & 0xFF00) >> 8);
	}

}
