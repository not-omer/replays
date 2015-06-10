package me.replays.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import me.replays.Replay;

public class ReplayWriter {
  private Replay replay;

  public ReplayWriter(Replay replay) {
    this.replay = replay;
  }

  public void write(File output) throws IOException {
    /*
     * TODO OsuBinaryWriter out = new OsuBinaryWriter(new
     * FileOutputStream(output)); out.writeByte((byte)
     * replay.getMode().ordinal()); out.writeInt32(20131129);
     * out.write(BeatmapMd5); out.write(UserName); out.write(calcHash());
     * out.write(Hit300); out.write(Hit100); out.write(Hit50);
     * out.write(Hit300Beat); out.write(Hit100Beat); out.write(Hit0);
     * out.write(Points); out.write((ushort)maxCombo); out.write(bool_4);
     * out.write((int)_property<Mods>.Get(Mods)); out.write(method_10());
     * out.write(TimeStamp); out.writeByteArray(CompressedReplayData);
     * out.write(onlineScoreId); out.flush(); out.close();
     */
  }
}