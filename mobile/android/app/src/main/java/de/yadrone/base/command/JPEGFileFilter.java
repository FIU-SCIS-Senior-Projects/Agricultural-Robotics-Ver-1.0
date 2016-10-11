package de.yadrone.base.command;

import org.apache.commons.net.ftp.FTPFile;

final class JPEGFileFilter extends FileFilter
{
  public static boolean endsWithIgnoreCase(String s, String suffix)
  {
    int l1 = s.length();
    int l2 = suffix.length();
    String ext = s.substring(l1 - l2);
    return suffix.equalsIgnoreCase(ext);
  }
  
  public boolean accept(FTPFile f)
  {
    String nm = f.getName();
    return (super.accept(f)) && (endsWithIgnoreCase(nm, ".jpg"));
  }
}
