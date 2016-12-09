package com.fruitrec.command;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class CommandQueue extends PriorityBlockingQueue<ATCommand>
{
  private static final AtomicLong seq = new AtomicLong(0L);

  public boolean add(ATCommand e)
  {
    e.setQorder(seq.getAndIncrement());
    return super.add(e);
  }
  
  public CommandQueue(int capacity) {
    super(capacity, new Comparator<ATCommand>()
    {
      public int compare(ATCommand l, ATCommand r)
      {
        Priority lp = l.getPriority();
        Priority rp = r.getPriority();
        if (lp != rp) {
          return lp.compareTo(rp);
        }
        
        long lo = l.getQorder();
        long ro = r.getQorder();
        
        if (lo < ro) {
          return -1;
        }
        if (lo > ro) {
          return 1;
        }
        return 0;
      }
    });
  }
}
