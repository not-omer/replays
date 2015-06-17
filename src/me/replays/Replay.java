package me.replays;

import java.util.Date;

import me.replays.transforms.impl.HardRockTransform;
import me.replays.transforms.impl.ScoreTransform;

public class Replay {
  private Mode mode;
  private int fileVersion, mods;
  private long onlineScoreID;
  private int hit300, hit100, hit50, beat300, beat100, misses, maxCombo, points;
  private String md5, username, hash, diagram;
  private Date timestamp;
  private byte[] compressed;
  private boolean perfect;

  public int getFileVersion() {
    return fileVersion;
  }

  public int getMods() {
    return mods;
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public void setFileVersion(int fileVersion) {
    this.fileVersion = fileVersion;
  }

  public void setMods(int mods) {
    setMods(mods, true);
  }

  /**
   * @param transform
   *          If this is true, apply the score multipliers and hard rock
   *          transform according to the mods set. This should usually be true,
   *          unless setting the mods when parsing the replay file for the first
   *          time.
   */
  public void setMods(int mods, boolean transform) {
    this.mods = mods;

    if (transform)
      try {
        ScoreTransform st = new ScoreTransform();
        this.points = st.apply(this).getPoints();

        if (Mods.has(mods, Mods.HardRock)) {
          HardRockTransform hrt = new HardRockTransform();
          this.compressed = hrt.apply(this).getCompressedData();
        }
      } catch (Exception exception) {
        exception.printStackTrace();
      }
  }

  public void setOnlineScoreID(long l) {
    this.onlineScoreID = l;
  }

  public void setHit300(int hit300) {
    this.hit300 = hit300;
  }

  public void setHit100(int hit100) {
    this.hit100 = hit100;
  }

  public void setHit50(int hit50) {
    this.hit50 = hit50;
  }

  public void setBeat300(int beat300) {
    this.beat300 = beat300;
  }

  public void setBeat100(int beat100) {
    this.beat100 = beat100;
  }

  public void setMisses(int misses) {
    this.misses = misses;
  }

  public void setMaxCombo(int maxCombo) {
    this.maxCombo = maxCombo;
  }

  public void setBeatmapHash(String md5) {
    this.md5 = md5;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setReplayHash(String hash) {
    this.hash = hash;
  }

  public void setDiagram(String diagram) {
    this.diagram = diagram;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setCompressedData(byte[] compressed) {
    this.compressed = compressed;
  }

  public long getOnlineScoreID() {
    return onlineScoreID;
  }

  public int getHit300() {
    return hit300;
  }

  public int getHit100() {
    return hit100;
  }

  public int getHit50() {
    return hit50;
  }

  public int getBeat300() {
    return beat300;
  }

  public int getBeat100() {
    return beat100;
  }

  public int getMisses() {
    return misses;
  }

  public int getMaxCombo() {
    return maxCombo;
  }

  public String getBeatmapHash() {
    return md5;
  }

  public String getUsername() {
    return username;
  }

  public String getReplayHash() {
    return hash;
  }

  public String getDiagram() {
    return diagram;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public byte[] getCompressedData() {
    return compressed;
  }

  public Mode getMode() {
    return mode;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public boolean isPerfect() {
    return perfect;
  }

  public void setPerfect(boolean isPerfect) {
    this.perfect = isPerfect;
  }
}