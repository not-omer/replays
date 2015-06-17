package me.replays;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class ReplayData {
  private Replay replay;
  private ArrayList<Action> actions = new ArrayList<Action>();

  public ReplayData(Replay replay) {
    this.replay = replay;
  }

  public void parse() throws IOException, CompressorException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    LZMACompressorInputStream in = new LZMACompressorInputStream(new ByteArrayInputStream(replay.getCompressedData()));
    String text = new String(IOUtils.toByteArray(in));
    String[] split = text.split(",");

    for (String piece : split) {
      String[] pieces = piece.split("\\|");
      Action action = new Action();
      action.setW(Long.parseLong(pieces[0]));
      action.setX(Float.parseFloat(pieces[1]));
      action.setY(Float.parseFloat(pieces[2]));
      action.setZ(Integer.parseInt(pieces[3]));
      actions.add(action);
    }

    in.close();
    out.close();
  }

  public ArrayList<Action> getActions() {
    return actions;
  }

  public void setActions(ArrayList<Action> actions) {
    this.actions = actions;
  }

  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    for (Action action : actions)
      buffer.append(action.toString());
    return buffer.toString();
  }

  public class Action {
    private long w;
    private float x, y;
    private int z;

    /**
     * @return Time in milliseconds since the previous action
     */
    public long getW() {
      return w;
    }

    public void setW(long w) {
      this.w = w;
    }

    /**
     * @return x-coordinate of the cursor from 0 - 512
     */
    public float getX() {
      return x;
    }

    public void setX(float x) {
      this.x = x;
    }

    /**
     * @return y-coordinate of the cursor from 0 - 384
     */
    public float getY() {
      return y;
    }

    public void setY(float y) {
      this.y = y;
    }

    /**
     * @return Bitwise combination of keys/mouse buttons pressed (M1 = 1, M2 =
     *         2, K1 = 5, K2 = 10)
     */
    public int getZ() {
      return z;
    }

    public void setZ(int z) {
      this.z = z;
    }

    @Override
    public String toString() {
      return w + "|" + x + "|" + y + "|" + z + ",";
    }
  }
}