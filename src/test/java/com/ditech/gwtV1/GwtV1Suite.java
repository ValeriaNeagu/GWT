package com.ditech.gwtV1;

import com.ditech.gwtV1.client.GwtV1Test;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GwtV1Suite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for GwtV1");
		suite.addTestSuite(GwtV1Test.class);
		return suite;
	}
}
