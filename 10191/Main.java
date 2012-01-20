import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		
		List<Meeting> meetings = new ArrayList<Meeting>();
		int nApps;
		int day = 1;
		while ( sc.hasNext() ) {
		
			nApps = sc.nextInt(); 
			sc.nextLine();
			System.out.print("Day #" + day + ": ");
			
			for (int i=0;i<nApps;i++) {	
				meetings.add(parseMeeting( sc.nextLine() ));
			}

			meetings.add(meeting(18, 00, 18, 00));			
			Collections.sort(meetings, new MeetingComp());
			Meeting prev = meeting(10, 00, 10, 00);
			
			int longestNap = 0, nap;
			Time st = time(10,0);
			
			for (Meeting m : meetings) {
				nap = napTime(prev, m);
				if ( nap > longestNap ) {
					longestNap = nap;
					st = prev.end;
				}
				prev = m;
			}
			
			System.out.print("the longest nap starts at " + st + " and will last for ");
			System.out.println( printNap(longestNap) + ".");
			meetings.clear();
			day++;
		}
		
		sc.close();
	}
	
	static int napTime(Meeting m1, Meeting m2) {
		return Time.diffMins(m1.end , m2.start);
	}
	
	static String printNap(int longestNap) {
		int hours = Time.getHours(longestNap);
		int mins  = Time.getMins(longestNap);
		String s = mins + " minutes";
		if (hours > 0)
			s = hours + " hours and " + s;
	 	return s;
	}
	
	static Time time(int min, int hour) {
		return new Time(min, hour);
	}
	
	static Meeting parseMeeting ( String line ) {
		String [] times = line.split(" ");
		String [] start = times[0].split(":");
		String [] end   = times[1].split(":");
		int startH = Integer.parseInt(start[0]);
		int startM = Integer.parseInt(start[1]);
		int endH   = Integer.parseInt(end[0]);
		int endM   = Integer.parseInt(end[1]);
		return meeting(startH, startM, endH, endM);
	}
	
	static Meeting meeting (int startHour, int startMins, int endHour, int endMins ) {
		return new Meeting (time(startHour, startMins), time(endHour, endMins));
	}
	
}

class Meeting implements Comparable<Meeting> {
	Time start;
	Time end;
	Meeting(Time start, Time end) {
		this.start = start;
		this.end   = end;
	}
	@Override
	public String toString() {
		return start.toString() + " to " + end.toString();
	}
	@Override
	public int compareTo(Meeting m) {
		return start.compareTo(m.start);
	}
}

class MeetingComp implements Comparator<Meeting> {
	@Override
	public int compare(Meeting m1, Meeting m2) {
		return m1.compareTo(m2);
	}
}

class Time implements Comparable<Time>{
	int hour;
	int mins;
	Time (int hour, int mins) {
		this.hour = hour;
		this.mins = mins;
	}
	@Override
	public String toString() {
		String ms = ( mins < 10 ? "0" + mins: "" + mins);
		return hour + ":" + ms;
	}
	@Override
	public int compareTo(Time t) {
		if ( hour < t.hour || ( hour == t.hour && mins < t.mins) ) {
			return -1;
		} else if (hour > t.hour || (hour == t.hour && mins > t.mins) ) {
			return 1;
		}
		return 0;
	}
	
	public static int diffMins(Time t1, Time t2) {
		return (t2.hour - 10) * 60 + t2.mins - ((t1.hour - 10) * 60 + t1.mins);
	}
	
	
	static int getHours (int mins) {
		return mins / 60;
	}
	
	static int getMins (int mins) {
		return mins % 60;
	}
}
