package com.srt.exception;

import ch.qos.logback.classic.Level;

/**
 * @author Ajay
 *
 */
public enum SeverityLevel {
	DEBUG(Level.DEBUG),
	ERROR(Level.ERROR),
	INFO(Level.INFO),
	WARN(Level.WARN),
	ALL(Level.ALL),
	TRACE(Level.TRACE);
	
	private Level level;
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	private SeverityLevel(Level level){
		this.level = level;
	}
}
