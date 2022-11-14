package edu.ncsu.csc316.compression.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * Tests the CompressionManager class.
 * @author Jamel Clarke
 *
 */
public class CompressionManagerTest {

	/**
	 * Tests getCompressed() exception checking.
	 */
	@Test
	public void testGetCompressedException() {
		try {
			CompressionManager cm = new CompressionManager("input/empty.txt");
			Exception e1 = assertThrows(IllegalArgumentException.class, () -> cm.getCompressed());
			assertEquals("The provided input file has no text to compress.", e1.getMessage());
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * Tests getCompressed() exception checking.
	 */
	@Test
	public void testGetDecompressedException() {
		try {
			CompressionManager cm = new CompressionManager("input/empty.txt");
			Exception e1 = assertThrows(IllegalArgumentException.class, () -> cm.getDecompressed());
			assertEquals("The provided input file has no text to decompress.", e1.getMessage());
		} catch (FileNotFoundException e) {
			fail();
		}
	}
}
