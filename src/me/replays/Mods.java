package me.replays;

public enum Mods {
  None(0), NoFail(1), Easy(2), Hidden(8), HardRock(16), SuddenDeath(32), DoubleTime(64), Relax(128), HalfTime(256), Nightcore(
      512), Flashlight(1024), Autoplay(2048), SpunOut(4096), Relax2(8192), Perfect(16384), Key4(32768), Key5(65536), Key6(
      131072), Key7(262144), Key8(524288), keyMod(1015808), FadeIn(1048576), Random(2097152), LastMod(4194304), FreeModAllowed(
      2077883);

  private int value;

  Mods(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }

  public static boolean has(int a, Mods b) {
    return (a & b.value()) > None.value();
  }
}