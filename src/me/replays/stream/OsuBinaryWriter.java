package me.replays.stream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * I only implemented the necessary methods, there are still a ton more
 * 
 * @author Omer
 */
public class OsuBinaryWriter extends DataOutputStream {
  private byte[] largeByteBuffer;
  private int maxChars;
  private final int largeByteBufferSize = 256;

  public OsuBinaryWriter(OutputStream out) {
    super(out);
  }

  public void writeByte(byte value) throws IOException {
    writeByte(value & 0xFF);
  }

  public void writeChars(char[] chars) throws IOException {
    if (chars == null)
      throw new NullPointerException("chars");
    writeChars(new String(chars));
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

  /*
   * public void writeLine(String value) { if (value == null) throw new
   * NullPointerException("writeLine value");
   * 
   * int len = value.getBytes().length; write7BitEncodedInt(len);
   * 
   * if (largeByteBuffer == null) { largeByteBuffer = new
   * byte[largeByteBufferSize]; maxChars = largeByteBufferSize /
   * _encoding.GetMaxByteCount(1); }
   * 
   * if (len <= largeByteBufferSize) { largeByteBuffer = value.getBytes();
   * write(largeByteBuffer, 0, len); } else { int charStart = 0; int numLeft =
   * value.length(); int totalBytes = 0; while (numLeft > 0) { int charCount =
   * (numLeft > maxChars) ? maxChars : numLeft; int byteLen =
   * getBytes(value.toCharArray() + charStart, charCount, largeByteBuffer,
   * largeByteBufferSize, charCount == numLeft); totalBytes += byteLen;
   * write(largeByteBuffer, 0, byteLen); charStart += charCount; numLeft -=
   * charCount; } } }
   */

  private void write7BitEncodedInt(int value) throws IOException {
    long v = value & 0x00000000ffffffffL;
    while (v >= 0x80) {
      write((byte) (v | 0x80));
      v >>= 7;
    }
    write((byte) v);
  }
}