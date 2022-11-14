package edu.ncsu.csc316.compression.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * Tests the ReportManager class.
 * @author Jamel Clarke
 *
 */
public class ReportManagerTest {
	
	/**
	 * Tests ReportManager.compress() functionality
	 */
	@Test
	public void testReportManagerCompression() {
		try {
			ReportManager rm = new ReportManager("input/tcp.txt");
			assertNotNull(rm);
			String expected = "Compressed Output {\n"
					+ "   Line 1:The Collected Poems\n"
					+ "   Line 2:Sylvia Plath\n"
					+ "   Line 3:In ruck and quibble of courtfolk\n"
					+ "   Line 4:This giant hulked I tell you on her scene\n"
					+ "   Line 5:With hands like derricks\n"
					+ "   Line 6:Looks fierce 8 black as rooks\n"
					+ "   Line 7:Why, all the windows broke when he stalked in\n"
					+ "   Line 8:Her dainty acres 37 ramped through\n"
					+ "   Line 9:And used 19 gentle doves with manners rude\n"
					+ "   Line 10:15 do not know\n"
					+ "   Line 11:What fury urged him slay\n"
					+ "   Line 12:40 antelope who meant 61 naught but good\n"
					+ "   Line 13:She spoke most chiding 39 his ear\n"
					+ "   Line 14:Till 37 some pity took upon 19 crying\n"
					+ "   Line 15:Of rich attire\n"
					+ "   Line 16:He made 19 shoulders bare\n"
					+ "   Line 17:46 solaced 19 69 quit 19 at cocks crowing\n"
					+ "   Line 18:A hundred heralds she sent out\n"
					+ "   Line 19:To summon 39 19 slight 32 doughty men\n"
					+ "   Line 20:Whose force might fit\n"
					+ "   Line 21:Shape 10 19 sleep 19 thought\n"
					+ "   Line 22:None 10 that greenhorn lot matched 19 bright crown\n"
					+ "   Line 23:So 106 is come to this rare pass\n"
					+ "   Line 24:Whereby 106 treks 39 blood 45 sun 8 squall\n"
					+ "   Line 25:46 sings 17 thus\n"
					+ "   Line 26:How sad alas it 138\n"
					+ "   Line 27:109 see my people shrunk so small 167 168\n"
					+ "}";
			assertEquals(expected, rm.compress());
		} catch (FileNotFoundException e) {
			fail();
		}
	}
	
	/**
	 * Tests ReportManager.compress() functionality
	 */
	@Test
	public void testReportManagerDecompression() {
		try {
			ReportManager rm = new ReportManager("input/compressed_tcp_input-.txt");
			assertNotNull(rm);
			String expected = "Decompressed Output {\n"
					+ "   Line 1:The Collected Poems\n"
					+ "   Line 2:Sylvia Plath\n"
					+ "   Line 3:In ruck and quibble of courtfolk\n"
					+ "   Line 4:This giant hulked I tell you on her scene\n"
					+ "   Line 5:With hands like derricks\n"
					+ "   Line 6:Looks fierce and black as rooks\n"
					+ "   Line 7:Why, all the windows broke when he stalked in\n"
					+ "   Line 8:Her dainty acres he ramped through\n"
					+ "   Line 9:And used her gentle doves with manners rude\n"
					+ "   Line 10:I do not know\n"
					+ "   Line 11:What fury urged him slay\n"
					+ "   Line 12:Her antelope who meant him naught but good\n"
					+ "   Line 13:She spoke most chiding in his ear\n"
					+ "   Line 14:Till he some pity took upon her crying\n"
					+ "   Line 15:Of rich attire\n"
					+ "   Line 16:He made her shoulders bare\n"
					+ "   Line 17:And solaced her but quit her at cocks crowing\n"
					+ "   Line 18:A hundred heralds she sent out\n"
					+ "   Line 19:To summon in her slight all doughty men\n"
					+ "   Line 20:Whose force might fit\n"
					+ "   Line 21:Shape of her sleep her thought\n"
					+ "   Line 22:None of that greenhorn lot matched her bright crown\n"
					+ "   Line 23:So she is come to this rare pass\n"
					+ "   Line 24:Whereby she treks in blood through sun and squall\n"
					+ "   Line 25:And sings you thus\n"
					+ "   Line 26:How sad alas it is\n"
					+ "   Line 27:To see my people shrunk so small so small\n"
					+ "}";
			assertEquals(expected, rm.decompress());
		} catch (FileNotFoundException e) {
			fail();
		}
	}
}
