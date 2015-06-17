package me;

import java.io.File;
import java.io.IOException;

import me.replays.Mods;
import me.replays.Replay;
import me.replays.parse.ReplayReader;
import me.replays.parse.ReplayWriter;

import org.apache.commons.compress.compressors.CompressorException;

public class Main {
  public static void main(String[] args) throws IOException, CompressorException {
    ReplayReader reader = new ReplayReader(new File("replay.osr"));
    Replay replay = reader.parse();

    replay.setUsername("AppleJuice");
    replay.setMods(Mods.DoubleTime.value() | Mods.Easy.value());

    ReplayWriter writer = new ReplayWriter(replay);
    writer.write(new File("replay2.osr"));
  }
}