package me;

import java.io.File;
import java.io.IOException;

import me.replays.Replay;
import me.replays.stream.ReplayReader;

public class Main {
  public static void main(String[] args) throws IOException {
    ReplayReader reader = new ReplayReader(new File("replay.osr"));
    Replay replay = reader.parse();

    System.out.println(replay.getMode());
    System.out.println(replay.getUsername());
    System.out.println(replay.getHit300());
    System.out.println(replay.getHit100());
    System.out.println(replay.getHit50());
    System.out.println(replay.getBeat300());
    System.out.println(replay.getBeat100());
    System.out.println(replay.getMisses());
  }
}