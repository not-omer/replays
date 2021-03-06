package me.replays.parse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;

import me.replays.Mods;
import me.replays.Replay;
import me.replays.ReplayData;
import me.replays.ReplayData.Action;
import me.replays.stream.OsuBinaryOutputStream;
import me.replays.util.Utilities;

import org.apache.commons.compress.compressors.CompressorException;

public class ReplayWriter {
  private Replay replay;

  public ReplayWriter(Replay replay) {
    this.replay = replay;
  }

  public void write(File output) throws IOException {
    System.out.println(getDiagram(replay));

    OsuBinaryOutputStream out = new OsuBinaryOutputStream(new FileOutputStream(output));
    out.writeByte((byte) replay.getMode().ordinal());
    out.writeInt32(20150414);
    out.writeLine(replay.getBeatmapHash());
    out.writeLine(replay.getUsername());
    out.writeLine(calcHash(replay));
    out.writeUInt16(replay.getHit300());
    out.writeUInt16(replay.getHit100());
    out.writeUInt16(replay.getHit50());
    out.writeUInt16(replay.getBeat300());
    out.writeUInt16(replay.getBeat100());
    out.writeUInt16(replay.getMisses());
    out.writeInt32(replay.getPoints());
    out.writeUInt16(replay.getMaxCombo());
    out.writeBoolean(replay.isPerfect());
    out.writeInt32(replay.getMods());
    out.writeLine("15033|1,7085|1,9207|1,11394|1,13850|1,15852|1,17853|1,19993|1,22007|1,24162|1,26313|1,28466|1,30505|1,32624|1,34777|1,36922|1,39085|1,41393|1,43545|1,45691|1,47696|0.99,49698|1,52005|1,54168|1,56170|1,58320|1,60387|1,62445|1,64468|1,66773|1,68928|1,71095|0.9,73395|1,75399|1,77856|1,80316|1,82466|1,84620|0.85,86777|0.98,88932|1,90933|1,91995|1,");
    out.writeLong(0); // timestamp but fuck that lmao
    out.writeInt32(replay.getCompressedData().length);
    out.write(replay.getCompressedData());
    out.writeInt64(replay.getOnlineScoreID());
    out.close();
  }

  private String calcHash(Replay replay) throws UnsupportedEncodingException {
    return Utilities.md5(MessageFormat.format("{0}p{1}o{2}o{3}t{4}a{5}r{6}e{7}y{8}o{9}u{10}{11}{12}",
        replay.getHit100() + replay.getHit300(), replay.getHit50(), replay.getBeat300(), replay.getBeat100(),
        replay.getMisses(), replay.getBeatmapHash(), replay.getMaxCombo(), Utilities.upper(replay.isPerfect()),
        replay.getUsername(), Integer.toString(replay.getPoints()), getRanking(replay), replay.getMods(), "True"));
  }

  private String getRanking(Replay replay) {
    int hitObjectCount = replay.getHit300() + replay.getHit100() + replay.getHit50() + replay.getMisses();

    float num = replay.getHit300() / (float) hitObjectCount;
    float num2 = replay.getHit50() / (float) hitObjectCount;
    if (num == 1f) {
      if (!Mods.has(replay.getMods(), Mods.Hidden) && !Mods.has(replay.getMods(), Mods.Flashlight))
        return "X";
      return "XH";
    }
    if (num > 0.9 && num2 <= 0.01 && replay.getMisses() == 0) {
      if (!Mods.has(replay.getMods(), Mods.Hidden) && !Mods.has(replay.getMods(), Mods.Flashlight))
        return "S";
      return "SH";
    }
    if ((num > 0.8 && replay.getMisses() == 0) || num > 0.9)
      return "A";
    if ((num > 0.7 && replay.getMisses() == 0) || num > 0.8)
      return "B";
    if (num > 0.6)
      return "C";
    return "D";
  }

  private String getDiagram(Replay replay) {
    DecimalFormat df = new DecimalFormat("0.#");
    ReplayData data = new ReplayData(replay);
    try {
      data.parse();
    } catch (IOException | CompressorException exception) {
      exception.printStackTrace();
    }

    StringBuilder buffer = new StringBuilder();
    ArrayList<Action> actions = data.getActions();

    float num = 0.0f;
    for (int index = 0; index < actions.size(); ++index) {
      Action action = actions.get(index);
      if (action.getX() - num > 2000f || index == actions.size() - 1 || index == 0) {
        num = action.getX();
        String a = df.format(Utilities.roundToSignificantFigures((double) action.getX(), 2));
        String b = df.format(Utilities.roundToSignificantFigures((double) action.getY(), 2));
        buffer.append(MessageFormat.format("{0}|{1},", a, b));
      }
    }

    return buffer.toString();
  }
}