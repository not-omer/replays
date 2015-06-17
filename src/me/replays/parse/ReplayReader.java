package me.replays.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import me.replays.Mode;
import me.replays.Replay;
import me.replays.stream.OsuBinaryInputStream;
import me.replays.util.Utilities;

public class ReplayReader {
  private File replay;

  public ReplayReader(File replay) {
    this.replay = replay;
  }

  public Replay parse() throws IOException {
    if (!Utilities.getExtension(replay).equals("osr"))
      throw new IOException("not a valid .osr file!");

    OsuBinaryInputStream in = new OsuBinaryInputStream(new FileInputStream(replay));
    Replay nReplay = new Replay();

    nReplay.setMode(Mode.values()[in.readByte()]);
    nReplay.setFileVersion(in.getInt32());
    nReplay.setBeatmapHash(in.getLine());
    nReplay.setUsername(in.getLine());
    nReplay.setReplayHash(in.getLine());
    nReplay.setHit300(in.getUInt16());
    nReplay.setHit100(in.getUInt16());
    nReplay.setHit50(in.getUInt16());
    nReplay.setBeat300(in.getUInt16());
    nReplay.setBeat100(in.getUInt16());
    nReplay.setMisses(in.getUInt16());
    nReplay.setPoints(in.getInt32());
    nReplay.setMaxCombo(in.getUInt16());
    nReplay.setPerfect(in.readBoolean());
    nReplay.setMods(in.getInt32());
    nReplay.setDiagram(in.getLine());
    nReplay.setTimestamp(in.getDate());
    nReplay.setCompressedData(in.getBytes());
    nReplay.setOnlineScoreID(in.getInt64());

    in.close();
    return nReplay;
  }
}