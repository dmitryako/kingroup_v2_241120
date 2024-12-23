package kingroup.model;
/* Copyright (C) 2004  Dr. Dmitry Konovalov.
* This code is licensed under the GPL license (see www.gnu.org) for academic,
* not-for-profit use or for use within other Open Source software (see www.opensource.org).
* See www.kingroup.org for more details.
*/

import junit.framework.*;

/**
 * Testing all classes in <code>kingroup.model.*</code>.
 *
 */
public class KAllModelTests  {
   public static void main(String[] args) {
      junit.textui.TestRunner.run(suite());
   }
   public static Test suite() {
      TestSuite suite = new TestSuite("All JUnit geometryx for kingroup.model.*");
      suite.addTest(KDescentIdentityBeanTest.suite());
      suite.addTest(ProjectTest.suite());
      //suite.addTest(KJKinshipProjectBeanTest.suite());
      suite.addTest(KKinshipFileFormatBeanTest.suite());
      return suite;
   }
}



