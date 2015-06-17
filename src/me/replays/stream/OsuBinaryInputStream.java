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
    return ByteBuffer.wrap(readBytes(8)).order(ByteOrder.LITTLE_ENDIAN)
        .getLong();
  }

  public int getInt32() throws IOException {
    return ByteBuffer.wrap(readBytes(4)).order(ByteOrder.LITTLE_ENDIAN)
        .getInt();
  }

  public int getInt16() throws IOException {
    return ByteBuffer.wrap(readBytes(2)).order(ByteOrder.LITTLE_ENDIAN)
        .getShort();
  }

  public int getUInt16() throws IOException {
    return getInt16() & 0xFFFF;
  }

  /*
   * public <T> List<T> getAll() { int capacity = in.readInt(); if (capacity <
   * 0) return null; List<T> list = new ArrayList<T>(capacity); for (int i = 0;
   * i < capacity; i++) { T item = new T(); item.ReadFromStream(this);
   * list.Add(item); } return list; }
   */

  /*
   * public Object get() throws IOException { switch (getByte()) { case 1:
   * return readBoolean(); case 2: return getByte(); case 3: return
   * readUnsignedShort(); case 4: return readInt() & 0xffffffffl; case 5: return
   * readLong() & 0xffffffffl; // TODO ?? case 6: return readByte(); // sbyte
   * case 7: return readShort(); case 8: return readInt(); case 9: return
   * readLong(); case 10: return readChar(); case 11: return readUTF(); case 12:
   * return readFloat(); case 13: return readDouble(); case 14: return
   * readShort(); // TODO reads a 16 bit decimal, no idea how to // implement
   * this case 15: return getDate(); case 16: return getBytes(); case 17: return
   * getChars(); case 18: return null; // TODO implement this //
   * BinaryObjectSerializer.ReadObject(BaseStream); default: return null; } }
   */

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