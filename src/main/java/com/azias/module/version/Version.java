package com.azias.module.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Version extends Object {
	//Use this as the standard: http://semver.org/spec/v2.0.0.html
	//NOTE: It might be a good idea to change compareToVersion() to versionObject.isNewerThan() or something like that.
	
	protected static final Pattern prPattern = Pattern.compile("-[0-9a-zA-Z\\.]{2,"+Integer.MAX_VALUE+"}");
	protected static final Pattern bmPattern = Pattern.compile("\\+[0-9a-zA-Z\\.]{2,"+Integer.MAX_VALUE+"}");
	
	public static final int NEWER_VERSION = 1;
	public static final int SAME_VERSION = 0;
	public static final int OLDER_VERSION = -1;
	
	protected String originalVersion, friendlyName;
	protected int majorVersion = 0, minorVersion = 0, patchVersion = 0;
	protected String preRelease, buildMetadata;
	
	/**
	 * This constructor should only be used to create a Version object manually and step-by-step.<br>
	 * Default version: 0.0.0
	 */
	public Version() {}
	
	public Version(int majorVersion, int minorVersion, int patchVersion) throws IllegalVersionFormatException {
		this(majorVersion, minorVersion, patchVersion, null, null);
	}
	
	public Version(int majorVersion, int minorVersion, int patchVersion, String preRelease, String buildMetadata) throws IllegalVersionFormatException {
		this((majorVersion+"."+minorVersion+"."+patchVersion+preRelease+buildMetadata).replaceAll("null", ""));
	}
	
	public Version(String version) throws IllegalVersionFormatException {
		this.originalVersion = version;
		Version.isFormatCorrect(this.originalVersion);
		this.parse();
	}
	
	/**
	 * Checks if the given version if formatted using the "Semantic Versioning 2.0.0" standard.
	 * @param version - ?
	 * @return <b>true</b> - Returned if the version is correctly formatted.
	 * @throws IllegalVersionFormatException - Thrown if the version isn't correctly formatted.
	 */
	public static boolean isFormatCorrect(String version) throws IllegalVersionFormatException {
		if(!version.matches("^(([0-9]|[1-9][0-9]+)\\.){2}([0-9]|[1-9][0-9]+)((-|\\+)[0-9a-zA-Z\\.]+){0,2}$"))
			throw new IllegalVersionFormatException("Formatting error somewhere in the version.");
		
		Matcher prMatcher = Version.prPattern.matcher(version);
		int prCount = 0;
		while (prMatcher.find())
			prCount++;
		if(prCount > 1)
			throw new IllegalVersionFormatException("There is a formatting error in the pre-release part.");
		
		Matcher bmMatcher = Version.bmPattern.matcher(version);
		int bmCount = 0;
		while (bmMatcher.find())
			bmCount++;
		if(bmCount > 1)
			throw new IllegalVersionFormatException("There is a formatting error in the build metadata part.");
		
		return true;
	}
	
	/**
	 * Converts the version into a string and calls the other function with the same name.
	 * @see Version.isFormatCorrect(String version)
	 */
	public static boolean isFormatCorrect(Version version) throws IllegalVersionFormatException {
		return Version.isFormatCorrect(version.toString());
	}
	
	private void parse() {
		String[] a = this.originalVersion.replaceAll("(-[0-9a-zA-Z\\.]+)?","").replaceAll("(\\+[0-9a-zA-Z\\.]+)?","").split("\\.");
		this.majorVersion = Integer.parseInt(a[0]);
		this.minorVersion = Integer.parseInt(a[1]);
		this.patchVersion = Integer.parseInt(a[2]);
		
		this.preRelease = this.originalVersion.replaceAll("^([0-9]+\\.){2}[0-9]+", "").replaceAll("(\\+[0-9a-zA-Z\\.]+)?", "");
		this.buildMetadata = this.originalVersion.replaceAll("^([0-9]+\\.){2}[0-9]+", "").replaceAll("(-[0-9a-zA-Z\\.]+)?", "");
		
		if(this.preRelease.length() == 0)
			this.preRelease = null;
		
		if(this.buildMetadata.length() == 0)
			this.buildMetadata = null;
	}
	
	//Used to test retro-compatibility (see "semver" specs for more info).
	public boolean isCompatibleWith(Version version) {
		return !(this.majorVersion != version.getMajorVersion());
	}
	
	public static boolean areVersionsCompatible(Version ver1, Version ver2) {
		return !(ver1.getMajorVersion()!= ver2.getMajorVersion());
	}
	
	//The name is confusing return the state of the version in par1. - See note on top.
	@Deprecated
	public int compareToVersion(Version version) {
		if(version.majorVersion > this.majorVersion)
			return Version.NEWER_VERSION;
		if(version.majorVersion < this.majorVersion)
			return Version.OLDER_VERSION;
		
		return Version.SAME_VERSION;
	}
	
	//Getters
	public int getMajorVersion() {
		return this.majorVersion;
	}
	
	public int getMinorVersion() {
		return this.minorVersion;
	}
	
	public int getPatchVersion() {
		return this.patchVersion;
	}
	
	public String getPreRelease() {
		return this.preRelease;
	}
	
	public String getBuildMetadata() {
		return this.buildMetadata;
	}
	
	public String getOriginalVersion() {
		return this.originalVersion;
	}
	
	public String getFriendlyName() {
		return this.friendlyName;
	}
	
	//Setters
	public boolean setMajorVersion(int newMajorVersion) {
		if(newMajorVersion < 0)
			return false;
		this.majorVersion = newMajorVersion;
		return true;
	}
	
	public boolean setMinorVersion(int newMinorVersion) {
		if(newMinorVersion < 0)
			return false;
		this.minorVersion = newMinorVersion;
		return true;
	}
	
	public boolean setPatchVersion(int newPatchVersion) {
		if(newPatchVersion < 0)
			return false;
		this.patchVersion = newPatchVersion;
		return true;
	}
	
	/** Unfinished */
	public void setFriendlyName(String a) {
		this.friendlyName = a;
	}
	
	//Override(s)
	@Override
	public String toString() {
		return (this.majorVersion+"."+this.minorVersion+"."+this.patchVersion+this.preRelease+this.buildMetadata).replaceAll("null", "");
	}
}
