package me.replays.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import me.replays.Mode;
import me.replays.Replay;
import me.replays.util.Utilities;

public class ReplayReader {
  private File replay;

  public ReplayReader(File replay) {
    this.replay = replay;
  }

  public Replay parse() throws IOException {
    if (!Utilities.getExtension(replay).equals("osr")) // TODO or .osg?
      throw new IOException("not a valid .osr file!");

    OsuBinaryReader in = new OsuBinaryReader(new FileInputStream(replay));
    Replay nReplay = new Replay();

    nReplay.setMode(Mode.values()[in.getByte()]);
    nReplay.setFileVersion(in.getInt());
    nReplay.setMd5(in.getLine());
    nReplay.setUsername(in.getLine());
    nReplay.setHash(in.getLine());
    nReplay.setHit300(in.getUInt16());
    nReplay.setHit100(in.getUInt16());
    nReplay.setHit50(in.getUInt16());
    nReplay.setBeat300(in.getUInt16());
    nReplay.setBeat100(in.getUInt16());
    nReplay.setMisses(in.getUInt16());
    nReplay.setMaxCombo(in.getUInt16());
    in.readBoolean();
    nReplay.setMods(in.getInt());
    nReplay.setDiagram(in.getLine());
    nReplay.setTimestamp(in.getDate());
    nReplay.setCompressed(in.getBytes());
    nReplay.setOnlineScoreID(in.getInt());

    in.close();
    return nReplay;
  }
}