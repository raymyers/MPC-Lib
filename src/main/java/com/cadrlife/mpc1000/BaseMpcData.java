package com.cadrlife.mpc1000;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.input.SwappedDataInputStream;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class BaseMpcData {
	public abstract void read(InputStream in) throws IOException;
	public abstract void write(OutputStream out) throws IOException;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	protected void checkRange(int value, int lowerBound, int upperBound) {
		if (value < lowerBound || value > upperBound) {
			throw new AssertionError();
		}
	}
	protected void checkLength(String string, int maxLength) {
		if (string.length() > maxLength) {
			throw new AssertionError();
		}
	}
	protected SwappedDataInputStream convertInputStream(InputStream in) {
		if (in instanceof SwappedDataInputStream) {
			return (SwappedDataInputStream) in;
		}
		return new SwappedDataInputStream(in);
	}
}
