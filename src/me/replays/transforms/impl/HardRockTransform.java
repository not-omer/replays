package me.replays.transforms.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import me.replays.Replay;
import me.replays.ReplayData;
import me.replays.ReplayData.Action;
import me.replays.transforms.Transform;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public class HardRockTransform implements Transform {
  @Override
  public Replay apply(Replay replay) throws IOException, CompressorException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    CompressorOutputStream lzma = new CompressorStreamFactory().createCompressorOutputStream(
        CompressorStreamFactory.LZMA, out);

    ReplayData data = new ReplayData(replay);
    data.parse();
    ArrayList<Action> actions = data.getActions();
    for (Action action : actions)
      action.setY(-action.getY());
    data.setActions(actions);

    lzma.write(data.toString().getBytes(StandardCharsets.US_ASCII));
    replay.setCompressedData(out.toByteArray());

    out.close();
    lzma.close();
    return replay;
  }
}