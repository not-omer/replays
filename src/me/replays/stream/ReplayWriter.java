package me.replays.stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

import me.replays.Replay;
import me.replays.util.Utilities;

public class ReplayWriter {
  private Replay replay;

  public ReplayWriter(Replay replay) {
    this.replay = replay;
  }

  public void write(File output) throws IOException {
    OsuBinaryWriter out = new OsuBinaryWriter(new FileOutputStream(output));
    out.writeByte((byte) replay.getMode().ordinal());
    out.writeInt32(20150414);
    out.writeLine(replay.getMd5());
    out.writeLine(replay.getUsername());
    out.writeLine("c651f625dd9d8f875266b7b883b5ca20"); // calcHash(replay)
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
    out.writeLine("6171|1,8265|1,10538|1,12631|1,14732|1,16807|1,18909|1,21002|1,23096|1,25182|1,27279|1,29379|1,31642|1,33749|1,35813|1,38095|1,40365|1,42488|0.98,44538|1,46817|1,48890|1,51175|1,53269|1,55536|1,58154|1,60237|1,62351|1,64410|0.95,66517|1,68635|1,70697|1,72979|1,75076|1,77178|1,79260|1,81345|1,83444|1,85539|1,87632|1,89707|1,91797|1,93906|1,95987|1,98270|1,");
    out.writeDate(replay.getTimestamp());
    out.write(replay.getCompressed());
    out.writeInt64(replay.getOnlineScoreID());
    out.close();
  }

  private String calcHash(Replay replay) {
    return Utilities.md5(MessageFormat.format(
        "{0}p{1}o{2}o{3}t{4}a{5}r{6}e{7}y{8}o{9}u{10}{11}{12}",
        replay.getHit100() + replay.getHit300(), replay.getHit50(),
        replay.getBeat300(), replay.getBeat100(), replay.getMisses(),
        replay.getMd5(), replay.getMaxCombo(), true, replay.getUsername(),
        replay.getPoints(), "SS" /* implement ranking */, replay.getMods(),
        true));
  }

  /*
   * private String getDiagram(Replay replay) { StringBuilder stringBuilder =
   * new StringBuilder(); float num = 0.0f; for (int index = 0; index <
   * this.list_0.Count; ++index) { Vector2 vector2 = this.list_0[index]; if
   * ((double) vector2.X - (double) num > 2000.0 || index == this.list_0.Count -
   * 1 || index == 0) { num = vector2.X; stringBuilder.AppendFormat( "{0}|{1},",
   * (object) Math.Round((double) vector2.X, 2).ToString( (IFormatProvider)
   * Class112.numberFormatInfo_0), (object) Math.Round((double) vector2.Y,
   * 2).ToString( (IFormatProvider) Class112.numberFormatInfo_0)); } } return
   * stringBuilder.toString(); }
   */
}