package me.replays;

import java.util.Date;

public class Replay {
  private Mode mode;
  private int fileVersion, mods;
  private long onlineScoreID;
  private int hit300, hit100, hit50, beat300, beat100, misses, maxCombo,
      points;
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
    this.mods = mods;
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

  public void setMd5(String md5) {
    this.md5 = md5;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public void setDiagram(String diagram) {
    this.diagram = diagram;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public void setCompressed(byte[] compressed) {
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

  public String getMd5() {
    return md5;
  }

  public String getUsername() {
    return username;
  }

  public String getHash() {
    return hash;
  }

  public String getDiagram() {
    return diagram;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public byte[] getCompressed() {
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