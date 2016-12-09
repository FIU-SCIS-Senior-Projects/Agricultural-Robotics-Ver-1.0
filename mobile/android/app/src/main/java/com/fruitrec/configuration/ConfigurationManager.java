package com.fruitrec.configuration;

import com.fruitrec.command.CommandManager;
import com.fruitrec.command.ControlCommand;
import com.fruitrec.command.ControlMode;
import com.fruitrec.manager.AbstractTCPManager;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ConfigurationManager
  extends AbstractTCPManager
{
  private CommandManager manager = null;
  
  public ConfigurationManager(InetAddress inetaddr, CommandManager manager) {
    super(inetaddr);
    this.manager = manager;
  }
  
  public void run()
  {
    connect(5559);
  }

  private String getControlCommandResult(ControlMode p1, int p2, final ConfigurationListener listener)
  {
    this.manager.setCommand(new ControlCommand(p1, p2));
    
    Thread t = new Thread()
    {
      public void run()
      {
        try {
          InputStream inputStream = ConfigurationManager.this.getInputStream();
          
          if (inputStream != null) {
            byte[] buf = new byte['Ѐ'];
            int n = 0;
            StringBuilder builder = new StringBuilder();
            try {
              while ((n = inputStream.read(buf)) != -1)
              {
                builder.append(new String(buf, 0, n, "ASCII"));
              }
            }
            catch (SocketTimeoutException localSocketTimeoutException) {}
            
            String s = builder.toString();
            if (listener != null) {
              listener.result(s);
            }
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };
    t.start();
    return "";
  }
  
  public String getCustomCofigurationIds(ConfigurationListener listener)
  {
    String s = getControlCommandResult(ControlMode.CUSTOM_CFG_GET, 0, listener);
    return s;
  }
  
  public String getPreviousRunLogs(ConfigurationListener listener) {
    String s = getControlCommandResult(ControlMode.LOGS_GET, 0, listener);
    return s;
  }
  
  public String getConfiguration(ConfigurationListener listener) {
    String s = getControlCommandResult(ControlMode.CFG_GET, 0, listener);
    return s;
  }
}
