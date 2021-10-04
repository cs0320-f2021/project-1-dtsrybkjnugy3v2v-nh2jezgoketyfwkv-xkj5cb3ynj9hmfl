package edu.brown.cs.student.stars;

import edu.brown.cs.student.kdtree.IKDInsertable;

import java.util.ArrayList;

public class Star implements IKDInsertable {

  private int _starID;
  private String _name;
  private double _x;
  private double _y;
  private double _z;
  private double _distance;

  public Star(String starID, String name, String x, String y, String z) {
    _starID = Integer.parseInt(starID);
    _name = name;
    _x = Double.parseDouble(x);
    _y = Double.parseDouble(y);
    _z = Double.parseDouble(z);
  }

  public int getID() {
    return _starID;
  }

  public String getName() {
    return _name;
  }

  public double getX() {
    return _x;
  }

  public double getY() {
    return _y;
  }

  public double getZ() {
    return _z;
  }

  public int getDistance() {
    return (int) _distance;
  }

  public void setDistance(double x2, double y2, double z2) {
    _distance = Math.sqrt(Math.pow((x2 - _x), 2)
        + Math.pow((y2 - _y), 2) + Math.pow((z2 - _z), 2));
  }

  /**
   * method that returns all the parameters of the object that are numerical in
   * an array list
   */
  @Override
  public ArrayList returnNumParams() {
    ArrayList returnList = new ArrayList<>();
    returnList.add(this._x);
    returnList.add(this._y);
    returnList.add(this._z);
    return returnList;
  }

  @Override
  public int returnID() {
    return _starID;
  }
}