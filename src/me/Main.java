package me;

import java.io.File;
import java.io.IOException;

import me.replays.Mods;
import me.replays.Replay;
import me.replays.stream.ReplayReader;
import me.replays.stream.ReplayWriter;

public class Main {
  public static void main(String[] args) throws IOException {
    ReplayReader reader = new ReplayReader(new File("replay.osr"));
    Replay replay = reader.parse();

    // replay.setUsername("AppleJuice");
    // replay.setMods(Mods.combine(Mods.DoubleTime.value(),
    // Mods.Flashlight.value()));

    ReplayWriter writer = new ReplayWriter(replay);
    writer.write(new File("replay2.osr"));
  }
}