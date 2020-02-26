package com.jqmart.transaction;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Transaction ID generator.  Copied from a web example
 * Used instead of a database for the purpose of this exercise.
 * 
 * @author Marco Vaca
 * @author Artem Moskalev
 */
public class IdGenerator {

	private AtomicInteger count = new AtomicInteger(1);
	
	private static IdGenerator generator = new IdGenerator();

	private IdGenerator() {
	}

	public static IdGenerator getInstance() {
		return generator;
	}

	public int generate() {
		return count.getAndIncrement();
	}

}