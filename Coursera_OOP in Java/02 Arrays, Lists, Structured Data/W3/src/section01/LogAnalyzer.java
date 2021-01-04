package section01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import edu.duke.*;

public class LogAnalyzer {

	private ArrayList<LogEntry> records;

	public LogAnalyzer() {
		records = new ArrayList<LogEntry>();
	}

	public void readFile(String filename) {
		FileResource file = new FileResource(
				"C:\\Git\\Coursera-Java-Specialization\\02 Arrays, Lists, Structured Data\\data\\3\\webLog\\"
						+ filename);

		for (String s : file.lines()) {
			records.add(WebLogParser.parseEntry(s));
		}
	}

	public void printAll() {
		for (LogEntry le : records) {
			System.out.println(le);
		}
	}

	public int countUniqueIps() {
		ArrayList<String> uniqueIps = new ArrayList<String>();

		for (LogEntry le : records) {
			String ip = le.getIpAddress();

			if (!uniqueIps.contains(ip))
				uniqueIps.add(ip);
		}
		return uniqueIps.size();
	}

	public void printAllHigherThanNum(int num) {
		for (LogEntry le : records) {
			if (num < le.getStatusCode())
				System.out.println(le);
		}
	}

	public ArrayList<String> uniqueIpVisitsOnDay(String someday) {
		ArrayList<String> uniqueIps = new ArrayList<String>();

		for (LogEntry le : records) {

			// Wed Sep 30 20:47:11 KST 2015
			String access = le.getAccessTime().toString();
			String[] arr = access.split(" ");

			String date = arr[1] + " " + arr[2];

			if (date.contains(someday) && !uniqueIps.contains(le.getIpAddress())) {
				uniqueIps.add(le.getIpAddress());
			}
		}
		return uniqueIps;
	}

	public int countUniqueIPsInRange(int low, int high) {

		HashMap<String, Integer> logs = new HashMap<String, Integer>();

		for (LogEntry le : records) {
			if (low <= le.getStatusCode() && le.getStatusCode() <= high) {
				logs.put(le.getIpAddress(), le.getStatusCode());
			}
		}
		return logs.size();
	}

	public HashMap<String, Integer> countVisitsPerIP() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();

		for (LogEntry le : records) {
			if (!counts.containsKey(le.getIpAddress()))
				counts.put(le.getIpAddress(), 1);
			else
				counts.put(le.getIpAddress(), counts.get(le.getIpAddress()) + 1);
		}
		return counts;
	}

	public int mostNumVisitsByIP(HashMap<String, Integer> log) {
		int max = 0;

		for (Integer v : log.values()) {
			if (v > max)
				max = v;
		}
		return max;
	}

	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> log) {
		ArrayList<String> ips = new ArrayList<String>();

		int max = mostNumVisitsByIP(log);

		for (String ip : log.keySet()) {
			if (log.get(ip) == max)
				ips.add(ip);
		}
		return ips;
	}

	public HashMap<String, ArrayList<String>> iPsForDays() {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		for (LogEntry le : records) {
			// Wed Sep 30 20:47:11 KST 2015
			String access = le.getAccessTime().toString();
			String[] arr = access.split(" ");

			String day = arr[1] + " " + arr[2];
//			System.out.println(day+" "+le.getIpAddress());

			// map에 날짜가 없으면 날짜랑 ip 추가
			if (!map.containsKey(day)) {
				ArrayList<String> ips = new ArrayList<String>();
				ips.add(le.getIpAddress());
				map.put(day, ips);
			}
			// 날짜가 있으면?
			// ip리스트에 특정 ip가 있는지 확인하고 없으면 그 ip 리스트에 추가하고 다시 맵에 넣기
			else {
				ArrayList<String> ips = map.get(day);
				ips.add(le.getIpAddress());
				map.put(day, ips);
			}
		}

		return map;
	}

	public String dayWithMostIpVisits(HashMap<String, ArrayList<String>> log) {
		String day = "";
		int max = 0;
		
		for(String days : log.keySet()) {
			if(log.get(days).size()>max)
				max = log.get(days).size();
		}
		for(String days : log.keySet()) {
			if(log.get(days).size() == max)
				day = days;
		}

		return day;
	}
	
	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> log, String day){
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		
		HashMap<String, ArrayList<String>> ips = iPsForDays();
				
		for(String d : ips.get(day)) {
			if(!count.containsKey(d))
				count.put(d, 1);
			else
				count.put(d, count.get(d)+1);
		}
		ArrayList<String> ip = iPsMostVisits(count);
		
		
		return ip;
	}

}
