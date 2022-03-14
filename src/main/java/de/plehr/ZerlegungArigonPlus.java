package de.plehr;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.alamos.fe2.external.interfaces.IAlarmExtractor;

/**
 * 
 * @author Pascal Lehr (PLehr.de)
 *
 */
public class ZerlegungArigonPlus implements IAlarmExtractor {
	/**
	 * 
	 * @param data    String with whole data
	 * @param pattern RegEX-Pattern
	 * @param matchID which ID of regex-Match
	 * @return
	 */
	private static String cutter(String data, String pattern, int matchID) {
		Matcher matcher = Pattern.compile(pattern).matcher(data);
		matcher.find();
		return matcher.group(matchID);
	}

	/**
	 * This method should clean your Strings from double spaces
	 * 
	 * @param input string to clean
	 * @return cleaned string
	 */
	private static String cleaner(String str) {
		while (str.contains("  ") || str.contains(System.lineSeparator()))
			str = str.replace("  ", " ").replace(System.lineSeparator(), "");
		return str;
	}

	/**
	 * *
	 * 
	 * @param input
	 * @return
	 */
	@Override
	public Map<String, String> extract(String input) {
		Map<String, String> data = new HashMap<>();
		String[] inData = input.replace("\r\n", System.lineSeparator())
				.split("============================================================");

		// operationID
		try {
			data.put("operationID", cutter(inData[0], "[\\W]{6}([0-9]{10})", 1));
		} catch (IllegalStateException ex) {
		}

		// operator
		try {
			data.put("operator", cutter(inData[0], "\\t([A-Za-z].*)", 1));
		} catch (IllegalStateException ex) {
		}

		// keyword
		try {
			data.put("keyword", cutter(inData[4], "[A-Z][0-9].*", 0));
		} catch (IllegalStateException ex) {
		}

		// startDate
		try {
			data.put("startDate", cutter(inData[0], "[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}", 0));
		} catch (IllegalStateException ex) {
		}
		// startTime
		try {
			data.put("startTime", cutter(inData[0], "[0-9]{2}:[0-9]{2}:[0-9]{2}", 0));
		} catch (IllegalStateException ex) {
		}
		// city
		try {
			data.put("city", cutter(inData[2], "Stadt\\/Ort:\\W{10}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// city_abbr
		try {
			data.put("city_abbr", cutter(inData[2], "Ortsteil:\\W{4}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// street
		try {
			data.put("street", cutter(inData[2], "Straße:\\W{13}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// house
		try {
			data.put("house", cutter(inData[2], "\\W{5}([0-9].*)", 1));
		} catch (IllegalStateException ex) {
		}
		// object
		try {
			data.put("object", cutter(inData[2], "Objekt:\\W{13}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// priority
		try {
			data.put("priority", cutter(inData[10], "\\:\\W*(\\d.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// sondersignal
		try {
			data.put("sondersignal", cutter(inData[10], "signal:\\s{7}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// hinweise
		try {
			data.put("hinweise", inData[inData.length - 1]);
		} catch (IllegalStateException ex) {
		}
		// caller
		try {
			data.put("caller", cutter(inData[8], "\\W{16}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// caller_str
		try {
			data.put("caller_str", cutter(inData[8], "Hnr.:\\W{7}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// caller_city
		try {
			data.put("caller_city", cutter(inData[8], "dt:\\W{9}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// caller_signaltype
		try {
			data.put("caller_signaltype", cutter(inData[8], "eg:\\W{11}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// caller_number
		try {
			data.put("caller_number", cutter(inData[8], "Nummer:\\W{13}(.*)", 1));
		} catch (IllegalStateException ex) {
		}
		// caller_contact
		try {
			data.put("caller_contact", cutter(inData[8], "nummer:\\W{9}(.*)", 1));
		} catch (IllegalStateException ex) {
		}

		// vehicle
		try {
			if (inData[13].contains("Fahrzeuge"))
				data.put("vehicle", cleaner(inData[14].replace("verfügbar", ";;").replace(";;", System.lineSeparator())));
		} catch (IllegalStateException ex) {
		}
		

		return data;
	}
}
