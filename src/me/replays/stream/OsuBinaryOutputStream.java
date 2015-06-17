package me.replays.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class OsuBinaryOutputStream extends DataOutputStream {
  public OsuBinaryOutputStream(OutputStream out) {
    super(out);
  }

  public void writeUInt16(int value) throws IOException {
    byte[] buffer = new byte[2];
    buffer[0] = (byte) value;
    buffer[1] = (byte) (value >> 8);
    write(buffer);
  }

  public void writeInt32(int value) throws IOException {
    byte[] buffer = new byte[4];
    buffer[0] = (byte) value;
    buffer[1] = (byte) (value >> 8);
    buffer[2] = (byte) (value >> 16);
    buffer[3] = (byte) (value >> 24);
    write(buffer);
  }

  public void writeUInt32(int value) throws IOException {
    byte[] buffer = new byte[4];
    buffer[0] = (byte) value;
    buffer[1] = (byte) (value >> 8);
    buffer[2] = (byte) (value >> 16);
    buffer[3] = (byte) (value >> 24);
    write(buffer);
  }

  public void writeInt64(long value) throws IOException {
    byte[] buffer = new byte[8];
    buffer[0] = (byte) value;
    buffer[1] = (byte) (value >> 8);
    buffer[2] = (byte) (value >> 16);
    buffer[3] = (byte) (value >> 24);
    buffer[4] = (byte) (value >> 32);
    buffer[5] = (byte) (value >> 40);
    buffer[6] = (byte) (value >> 48);
    buffer[7] = (byte) (value >> 56);
    write(buffer);
  }

  public void writeLine(String value) throws IOException {
    if (value.length() == 0) {
      writeByte(0x00);
      return;
    }
    byte[] v = value.getBytes("UTF-8");
    writeByte(0x0B);
    write7BitEncodedInt(v.length);
    write(v);
  }

  private void write7BitEncodedInt(int value) throws IOException {
    long v = value & 0x00000000ffffffffL;
    while (v >= 0x80) {
      writeByte((byte) (v | 0x80));
      v >>= 7;
    }
    writeByte((byte) v);
  }

  public void writeDate(Date timestamp) throws IOException {
    writeLong(timestamp.getTime());
  }
}