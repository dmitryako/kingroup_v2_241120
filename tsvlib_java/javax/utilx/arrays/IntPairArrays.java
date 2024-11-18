package javax.utilx.arrays;
import javax.utilx.pair.Int2;

/**
 * Copyright KinGroup Team.
 * User: jc138691, Date: 15/11/2005, Time: 12:47:55
 */
public class IntPairArrays
{
  public static Int2[] make(int size)
  {
    Int2[] res = new Int2[size];
    for (int i = 0; i < res.length; i++) {
      res[i] = new Int2(0, 0);
    }
    return res;
  }
}
