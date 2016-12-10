package com.fruitrec.manager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public abstract class AbstractTCPManager
  implements Runnable
{
  protected InetAddress inetaddr = null;
  protected Socket socket = null;
  protected boolean connected = false;
  protected Thread thread;
  
  public AbstractTCPManager(InetAddress inetaddr) {
    this.inetaddr = inetaddr;
  }
  
  public boolean connect(int port) {
    try {
      this.socket = new Socket(this.inetaddr, port);
      this.socket.setSoTimeout(3000);
    } catch (IOException e) {
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
    try {
      this.connected = false;
      if (this.socket != null) {
        this.socket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  protected void ticklePort(int port) {
    byte[] buf = { 1 };
    try {
      if (this.socket != null) {
        OutputStream os = this.socket.getOutputStream();
        os.write(buf);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  protected InputStream getInputStream() {
    try {
      if (this.socket != null) {
        return this.socket.getInputStream();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public void start() {
    if ((this.thread == null) || (this.thread.getState() == Thread.State.TERMINATED)) {
      String name = getClass().getSimpleName();
      this.thread = new Thread(this, name);
      this.thread.start();
    }
  }
}
