package com.fruitrec.command;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

class FileFilter implements FTPFileFilter
{
  public boolean accept(FTPFile f)
  {
    return f.getType() == 0;
  }
}
