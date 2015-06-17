package me.replays.transforms;

import me.replays.Replay;

public interface Transform {
  public Replay apply(Replay replay) throws Exception;
}