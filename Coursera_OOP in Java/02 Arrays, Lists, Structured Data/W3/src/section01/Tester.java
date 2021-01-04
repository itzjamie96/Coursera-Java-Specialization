package section01;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tester {

	public void testLogEntry() {
		LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
		System.out.println(le);

		LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
		System.out.println(le2);
	}

	public void testLogAnalyzer() {
		
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
//        la.printAll();
        
//        int uniqueIps = la.countUniqueIps();
//        System.out.println(uniqueIps+" unique IPs");

//        System.out.println();
//        la.printAllHigherThanNum(400);
        
//        System.out.println(la.uniqueIpVisitsOnDay("Sep 24").size());
        
//        System.out.println(la.countUniqueIPsInRange(400, 499));
        
//        System.out.println(la.countVisitsPerIP().toString());
        
//        HashMap<String, Integer> log = la.countVisitsPerIP();
//        System.out.println(la.iPsMostVisits(log));
//        System.out.println(la.mostNumVisitsByIP(log));
        
//        System.out.println(la.iPsForDays().toString());
        
//        System.out.println(la.dayWithMostIpVisits(la.iPsForDays()));
        
        HashMap<String, ArrayList<String>> log = la.iPsForDays();
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(log, "Sep 29");
        System.out.println(ips.toString());
      
        
        
        
    }

	public static void main(String[] args) {
		Tester test = new Tester();
//		test.testLogEntry();
		test.testLogAnalyzer();

	}

}
