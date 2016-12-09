package com.fruitrec.command;


public class ConfigureIdsCommand
  extends ATCommand
{
  protected String sessionId;
  
  protected String userId;
  protected String applicationId;
  
  private ConfigureIdsCommand(String sessionId, String userId, String applicationId)
  {
    this.sessionId = sessionId;
    this.userId = userId;
    this.applicationId = applicationId;
  }
  
  protected String getID()
  {
    return "CONFIG_IDS";
  }
  
  protected Object[] getParameters()
  {
    return new Object[] { this.sessionId, this.userId, this.applicationId };
  }
}
