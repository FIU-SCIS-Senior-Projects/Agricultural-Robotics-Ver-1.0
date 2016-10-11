package de.yadrone.base.manager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

















public abstract class AbstractManager
  implements Runnable
{
  protected InetAddress inetaddr = null;
  protected DatagramSocket socket = null;
  protected boolean doStop = false;
  protected boolean connected = false;
  protected Thread thread = null;
  
  public AbstractManager(InetAddress inetaddr) {
    this.inetaddr = inetaddr;
  }
  
  public boolean connect(int port) {
    try {
      this.socket = new DatagramSocket(port);
      this.socket.setSoTimeout(3000);
    } catch (SocketException e) {
      e.printStackTrace();
      this.connected = false;
      return false;
    }
    this.connected = true;
    return true;
  }
  
  public boolean isConnected() {
    return this.connected;
  }
  
  public void close() {
    if (this.socket != null) {
      this.socket.close();
    }
    this.connected = false;
  }
  
  public void stop() {
    System.out.println("AbstractManager: Stopping " + getClass().getSimpleName());
    if (this.thread != null) {
      this.thread.interrupt();
      this.doStop = true;
      this.thread = null;
    }
  }
  
  protected void ticklePort(int port) {
    byte[] buf = { 1 };
    DatagramPacket packet = new DatagramPacket(buf, buf.length, this.inetaddr, port);
    try {
      if (this.socket != null) {
        this.socket.send(packet);
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void start() {
    System.out.println("AbstractManager: Starting " + getClass().getSimpleName());
    if (this.thread == null) {
      this.doStop = false;
      String name = getClass().getSimpleName();
      this.thread = new Thread(this, name);
      this.thread.start();
    } else {
      System.out.println("Already started before " + getClass().getSimpleName());
    }
  }
}
