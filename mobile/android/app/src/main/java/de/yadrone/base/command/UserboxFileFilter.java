package de.yadrone.base.command;

import org.apache.commons.net.ftp.FTPFile;

final class UserboxFileFilter implements org.apache.commons.net.ftp.FTPFileFilter
{
  public boolean accept(FTPFile f)
  {
    int t = f.getType();
    String nm = f.getName();
    return ((t == 1) && (nm.startsWith("flight_"))) || (nm.startsWith("tmp_flight_"));
  }
}
