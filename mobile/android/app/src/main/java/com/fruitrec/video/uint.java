package com.fruitrec.video;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.nio.ByteBuffer;

public class uint
{
  private int base2;
  
  public String toString()
  {
    return Integer.toString(this.base2, 2);
  }
  
  public uint(int base) {
    this.base2 = base;
  }
  
  public uint(uint that)
  {
    this.base2 = that.base2;
  }
  
  public uint(byte[] bp, int start) {
    try {
      byte[] b = new byte[4];
      b[0] = bp[(start + 3)];
      b[1] = bp[(start + 2)];
      b[2] = bp[(start + 1)];
      b[3] = bp[(start + 0)];
      
      ByteArrayInputStream bas = new ByteArrayInputStream(b);
      DataInputStream din = new DataInputStream(bas);
      
      this.base2 = din.readInt();
    } catch (Exception e) {
      throw new RuntimeException("error creating uint", e);
    }
  }
  
  public uint(ByteBuffer bp, int start)
  {
    try {
      ByteBuffer bb = ByteBuffer.allocate(4);
      bb.put(bp.array()[(start + 3)]);
      bb.put(bp.array()[(start + 2)]);
      bb.put(bp.array()[(start + 1)]);
      bb.put(bp.array()[(start + 0)]);
      bb.flip();
      this.base2 = bb.getInt();
    } catch (Exception e) {
      throw new RuntimeException("error creating uint", e);
    }
  }

  public short times(short i)
  {
    return (short)(intValue() * i);
  }

  public uint shiftRight(int i)
  {
    int base = this.base2;
    

    base >>>= i;
    


    return new uint(base);
  }
  
  public uint shiftLeft(int i) {
    int base = this.base2;
    base <<= i;
    
    return new uint(base);
  }
  
  public int flipBits()
  {
    int base = this.base2 ^ 0xFFFFFFFF;
    
    return base;
  }
  
  public int intValue() {
    return this.base2;
  }
  
  public uint and(int andval)
  {
    int retval = this.base2 & andval;
    return new uint(retval);
  }
  
  public void shiftLeftEquals(int i) {
    int base = this.base2;
    
    base <<= i;
    
    this.base2 = base;
  }
  
  public void shiftRightEquals(int i) {
    int base = this.base2;
    
    base >>>= i;
    
    this.base2 = base;
  }
  
  public uint or(uint orval) {
    int retval = this.base2 | orval.base2;
    return new uint(retval);
  }
}
