package de.yadrone.base.navdata;

public class HDVideoStreamData
  extends NavData
{
  private HDVideoState hdvideo_state;
  private int storage_fifo_nb_packets;
  private int storage_fifo_size;
  private int usbkey_size;
  private int usbkey_freespace;
  private int frame_number;
  private int usbkey_remaining_time;
  
  public HDVideoStreamData(HDVideoState hdvideo_state, int storage_fifo_nb_packets, int storage_fifo_size, int usbkey_size, int usbkey_freespace, int frame_number, int usbkey_remaining_time)
  {
    this.hdvideo_state = hdvideo_state;
    this.storage_fifo_nb_packets = storage_fifo_nb_packets;
    this.storage_fifo_size = storage_fifo_size;
    this.usbkey_size = usbkey_size;
    this.usbkey_freespace = usbkey_freespace;
    this.frame_number = frame_number;
    this.usbkey_remaining_time = usbkey_remaining_time;
  }
  


  public HDVideoState getHdvideo_state()
  {
    return this.hdvideo_state;
  }
  


  public int getStorageFifoNbPackets()
  {
    return this.storage_fifo_nb_packets;
  }
  


  public int getStorageFifoSize()
  {
    return this.storage_fifo_size;
  }
  


  public int getUsbkeySize()
  {
    return this.usbkey_size;
  }
  


  public int getUsbkeyFreespace()
  {
    return this.usbkey_freespace;
  }
  


  public int getFrameNumber()
  {
    return this.frame_number;
  }
  


  public int getUsbkeyRemainingTime()
  {
    return this.usbkey_remaining_time;
  }
  





  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("HDVideoStreamData [hdvideo_state=");
    builder.append(this.hdvideo_state);
    builder.append(", storage_fifo_nb_packets=");
    builder.append(this.storage_fifo_nb_packets);
    builder.append(", storage_fifo_size=");
    builder.append(this.storage_fifo_size);
    builder.append(", usbkey_size=");
    builder.append(this.usbkey_size);
    builder.append(", usbkey_freespace=");
    builder.append(this.usbkey_freespace);
    builder.append(", frame_number=");
    builder.append(this.frame_number);
    builder.append(", usbkey_remaining_time=");
    builder.append(this.usbkey_remaining_time);
    builder.append("]");
    return builder.toString();
  }
}
