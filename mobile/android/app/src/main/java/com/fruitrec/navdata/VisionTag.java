package com.fruitrec.navdata;

import com.fruitrec.command.DetectionType;
import java.util.Arrays;

public class VisionTag
{
  private int type;
  private int x;
  private int y;
  private int width;
  private int height;
  private int distance;
  float orientationAngle;
  float[][] rotation;
  float[] translation;
  DetectionType source;
  
  public VisionTag(int type, int x, int y, int width, int height, int distance, float orientation_angle, float[][] rotation, float[] translation, DetectionType source)
  {
    this.type = type;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.distance = distance;
    this.orientationAngle = orientation_angle;
    this.rotation = rotation;
    this.translation = translation;
    this.source = source;
  }
  
  public int getType() {
    return this.type;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getWidth() {
    return this.width;
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public int getDistance() {
    return this.distance;
  }
  


  public float getOrientationAngle()
  {
    return this.orientationAngle;
  }
  


  public float[][] getRotation()
  {
    return this.rotation;
  }
  


  public float[] getTranslation()
  {
    return this.translation;
  }
  


  public DetectionType getSource()
  {
    return this.source;
  }
  



  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("VisionTag [type=");
    builder.append(this.type);
    builder.append(", x=");
    builder.append(this.x);
    builder.append(", y=");
    builder.append(this.y);
    builder.append(", width=");
    builder.append(this.width);
    builder.append(", height=");
    builder.append(this.height);
    builder.append(", distance=");
    builder.append(this.distance);
    builder.append(", orientationAngle=");
    builder.append(this.orientationAngle);
    builder.append(", rotation=");
    builder.append(Arrays.toString(this.rotation));
    builder.append(", translation=");
    builder.append(Arrays.toString(this.translation));
    builder.append(", cameraSource=");
    builder.append(this.source);
    builder.append("]");
    return builder.toString();
  }
}
