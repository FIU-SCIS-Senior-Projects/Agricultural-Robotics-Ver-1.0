package de.yadrone.base.command;

import org.apache.commons.net.ftp.FTPFile;

public class NavDataFileFilter extends FileFilter
{
  public boolean accept(FTPFile f)
  {
    String nm = f.getName();
    return (super.accept(f)) && (nm.startsWith("userbox_"));
  }
}
