package com.example.newproject;

import com.example.newproject.client.NewprojectTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class NewprojectSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for Newproject");
		suite.addTestSuite(NewprojectTest.class);
		return suite;
	}
}
