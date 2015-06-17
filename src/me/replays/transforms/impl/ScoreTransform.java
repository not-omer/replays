package me.replays.transforms.impl;

import me.replays.Mods;
import me.replays.Replay;
import me.replays.transforms.Transform;

public class ScoreTransform implements Transform {
  @Override
  public Replay apply(Replay replay) throws Exception {
    int mods = replay.getMods();
    int points = replay.getPoints();

    if (Mods.has(mods, Mods.Easy))
      replay.setPoints((int) (points * 0.5));

    if (Mods.has(mods, Mods.NoFail))
      replay.setPoints((int) (points * 0.5));

    if (Mods.has(mods, Mods.HalfTime))
      replay.setPoints((int) (points * 0.3));

    if (Mods.has(mods, Mods.HardRock))
      switch (replay.getMode()) {
      case CatchTheBeat:
        replay.setPoints((int) (points * 1.12));
        break;
      case Osu:
      case Taiko:
        replay.setPoints((int) (points * 1.06));
        break;
      default:
        break;
      }

    if (Mods.has(mods, Mods.DoubleTime) || Mods.has(mods, Mods.Nightcore))
      switch (replay.getMode()) {
      case CatchTheBeat:
        replay.setPoints((int) (points * 1.06));
        break;
      case Osu:
      case Taiko:
        replay.setPoints((int) (points * 1.12));
        break;
      default:
        break;
      }

    if (Mods.has(mods, Mods.Hidden))
      switch (replay.getMode()) {
      case OsuMania:
        replay.setPoints((int) (points * 1.06));
        break;
      default:
        break;
      }

    return replay;
  }
}