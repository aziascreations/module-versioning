package com.azias.module.tests;

import static org.junit.Assert.*;
import org.junit.*;

import com.azias.module.version.IllegalVersionFormatException;
import com.azias.module.version.Version;

@SuppressWarnings("unused")
public class VersionTest {
	private static Version a;
	
	@BeforeClass
	public static void runOnceBeforeClass() {
		//System.out.println("[Intro message]");
	}
	
	@Test
	public void basicVersionTest() {
		try {
			a = new Version("1.0.0");
		} catch (IllegalVersionFormatException e) {
			fail("Detected a formatting error for: \"1.0.0\"");
		}
	}
	
	@Test
	public void completeVersionTest() {
		try {
			a = new Version("1.0.0-rc1+build252");
		} catch (IllegalVersionFormatException e) {
			fail("Detected a formatting error for: \"1.0.0-rc1+build252\"");
		}
	}
	
	//TODO: Improve this, I feel bad by just thinking I shat this thing into the world.
	@Test
	public void formattingErrorTest() {
		try {
			a = new Version("1.0.0-rc1-errsrc");
			fail("Didn't throw an error for: \"1.0.0-rc1-errsrc\"");
		} catch (IllegalVersionFormatException e) {
			
		}
		
		try {
			a = new Version("1.0.0+rc1+errsrc");
			fail("Didn't throw an error for: \"1.0.0+rc1+errsrc\"");
		} catch (IllegalVersionFormatException e) {
			
		}
		
		try {
			a = new Version("1.0.0-rc1+build252+errsrc");
			fail("Didn't throw an error for: \"1.0.0-rc1+build252+errsrc\"");
		} catch (IllegalVersionFormatException e) {
			
		}
		
		try {
			a = new Version("1.0.0-rc1+build252-errsrc");
			fail("Didn't throw an error for: \"1.0.0-rc1+build252-errsrc\"");
		} catch (IllegalVersionFormatException e) {
			
		}
		
		try {
			a = new Version("1.0.0+build#12");
			fail("Didn't throw an error for: \"1.0.0+build#12\"");
		} catch (IllegalVersionFormatException e) {
			
		}
	}
	
	//TODO: Comparing versions, other constructors.
}
