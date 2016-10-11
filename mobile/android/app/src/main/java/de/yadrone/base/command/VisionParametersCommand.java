package de.yadrone.base.command;

public class VisionParametersCommand
  extends ATCommand
{
  int coarse_scale;
  int nb_pair;
  int loss_per;
  int nb_tracker_width;
  int nb_tracker_height;
  int scale;
  int trans_max;
  int max_pair_dist;
  int noise;
  
  public VisionParametersCommand(int coarse_scale, int nb_pair, int loss_per, int nb_tracker_width, int nb_tracker_height, int scale, int trans_max, int max_pair_dist, int noise)
  {
    this.coarse_scale = coarse_scale;
    this.nb_pair = nb_pair;
    this.loss_per = loss_per;
    this.nb_tracker_width = nb_tracker_width;
    this.nb_tracker_height = nb_tracker_height;
    this.scale = scale;
    this.trans_max = trans_max;
    this.max_pair_dist = max_pair_dist;
    this.noise = noise;
  }
  
  protected String getID()
  {
    return "VISP";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.coarse_scale), Integer.valueOf(this.nb_pair), Integer.valueOf(this.loss_per), Integer.valueOf(this.nb_tracker_width), Integer.valueOf(this.nb_tracker_height), Integer.valueOf(this.scale), Integer.valueOf(this.trans_max), 
      Integer.valueOf(this.max_pair_dist), Integer.valueOf(this.noise) };
  }
}
