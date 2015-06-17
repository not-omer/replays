package me.replays.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import me.replays.Replay;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class HardRockHelper {
  public static Replay convert(Replay replay) throws IOException,
      CompressorException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    CompressorOutputStream lzma = new CompressorStreamFactory()
        .createCompressorOutputStream(CompressorStreamFactory.LZMA, out);
    LZMACompressorInputStream in = new LZMACompressorInputStream(
        new ByteArrayInputStream(replay.getCompressedData()));
    StringBuilder buffer = new StringBuilder();

    String text = new String(IOUtils.toByteArray(in));
    String[] split = text.split(",");

    for (String action : split) {
      String[] actions = action.split("\\|");
      buffer.append(actions[0] + "|");
      buffer.append(actions[1] + "|");
      buffer.append("-" + actions[2].replace("-", "") + "|");
      buffer.append(actions[3]);
      buffer.append(",");
    }

    lzma.write(buffer.toString().getBytes(StandardCharsets.US_ASCII));
    replay.setCompressedData(out.toByteArray());

    lzma.close();
    in.close();
    out.close();
    return replay;
  }
}