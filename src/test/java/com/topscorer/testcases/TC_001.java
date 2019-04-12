package com.topscorer.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001 {
	
	@Test
	public void firstTest() {
		Assert.assertEquals('a','a');
	}
	
}
