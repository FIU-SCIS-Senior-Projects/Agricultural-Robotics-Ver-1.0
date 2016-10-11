package de.yadrone.base.command;

public enum VideoCodec {
  NULL(0),  UVLC(32),  P264(64),  MP4_360P(128),  H264_360P(129),  MP4_360P_H264_720P(130),  H264_720P(131),  MP4_360P_SLRS(
    132),  H264_360P_SLRS(133),  H264_720P_SLRS(134),  H264_AUTO_RESIZE(135),  MP4_360P_H264_360P(136);
  
  private int value;
  
  private VideoCodec(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return this.value;
  }
}
