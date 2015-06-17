package me.replays.stream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;

public class OsuBinaryInputStream extends DataInputStream {
  public OsuBinaryInputStream(InputStream in) {
    super(in);
  }

  public String getLine() throws IOException {
    if (readByte() == 0)
      return null;
    return new String(readBytes(getStringLength()));
  }

  public byte[] getBytes() throws IOException {
    int i = getInt32();
    if (i > 0) {
      byte[] bytes = new byte[i];
      in.read(bytes);
      return bytes;
    }
    if (i < 0)
      return null;
    return new byte[0];
  }

  public char[] getChars() throws IOException {
    return new String(getBytes()).toCharArray();
  }

  public Date getDate() throws IOException {
    long d = readLong();
    // if (d < 0L)
    // throw new IOException("abandoned mutex");
    return new Date(d);
  }

  public long getInt64() throws IOException {
    return ByteBuffer.wrap(readBytes(8)).order(ByteOrder.LITTLE_ENDIAN).getLong();
  }

  public int getInt32() throws IOException {
    return ByteBuffer.wrap(readBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt();
  }

  public int getInt16() throws IOException {
    return ByteBuffer.wrap(readBytes(2)).order(ByteOrder.LITTLE_ENDIAN).getShort();
  }

  public int getUInt16() throws IOException {
    return getInt16() & 0xFFFF;
  }

  private int getStringLength() throws IOException {
    int count = 0;
    int shift = 0;
    boolean more = true;
    while (more) {
      byte b = (byte) read();
      count |= (b & 0x7F) << shift;
      shift += 7;
      if ((b & 0x80) == 0)
        more = false;
    }
    return count;
  }

  private byte[] readBytes(int length) throws IOException {
    byte[] bytes = new byte[length];
    read(bytes);
    return bytes;
  }
}