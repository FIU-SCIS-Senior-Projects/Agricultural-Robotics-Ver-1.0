package de.yadrone.base.command;

public class GainsCommand
  extends ATCommand
{
  int pq_kp;
  int r_kp;
  int r_ki;
  int ea_kp;
  int ea_ki;
  int alt_kp;
  int alt_ki;
  int vz_kp;
  int vz_ki;
  int hovering_kp;
  int hovering_ki;
  int hovering_b_kp;
  int hovering_b_ki;
  
  public GainsCommand(int pq_kp, int r_kp, int r_ki, int ea_kp, int ea_ki, int alt_kp, int alt_ki, int vz_kp, int vz_ki, int hovering_kp, int hovering_ki, int hovering_b_kp, int hovering_b_ki)
  {
    this.pq_kp = pq_kp;
    this.r_kp = r_kp;
    this.r_ki = r_ki;
    this.ea_kp = ea_kp;
    this.ea_ki = ea_ki;
    this.alt_kp = alt_kp;
    this.alt_ki = alt_ki;
    this.vz_kp = vz_kp;
    this.vz_ki = vz_ki;
    this.hovering_kp = hovering_kp;
    this.hovering_ki = hovering_ki;
    this.hovering_b_kp = hovering_b_kp;
    this.hovering_b_ki = hovering_b_ki;
  }
  
  protected String getID()
  {
    return "GAIN";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { Integer.valueOf(this.pq_kp), Integer.valueOf(this.r_kp), Integer.valueOf(this.r_ki), Integer.valueOf(this.ea_kp), Integer.valueOf(this.ea_ki), Integer.valueOf(this.alt_kp), Integer.valueOf(this.alt_ki), Integer.valueOf(this.vz_kp), Integer.valueOf(this.vz_ki), Integer.valueOf(this.hovering_kp), Integer.valueOf(this.hovering_ki), 
      Integer.valueOf(this.hovering_b_kp), Integer.valueOf(this.hovering_b_ki) };
  }
}
