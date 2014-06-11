package weChat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import weChat.pojo.MovieInfo;

public class MyFileReader {
	private Map<String, MovieInfo> myData = new HashMap<String, MovieInfo>();
	private Map<String, String> myCinemaData = new HashMap<String, String>();
	public Map<String, MovieInfo> getMyData() {
		return myData;
	}

	public void setMyData(Map<String, MovieInfo> myData) {
		this.myData = myData;
	}

	public Map<String, String> getMyCinemaData() {
		return myCinemaData;
	}

	public void setMyCinemaData(Map<String, String> myCinemaData) {
		this.myCinemaData = myCinemaData;
	}

	public MyFileReader() throws IOException {

		File f = new File(this.getClass().getClassLoader().getResource("/")
				.getPath().replace("%20", " ")
				+ "siff171.csv");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(f), "utf-8"));
		br.readLine();
		String focus = br.readLine();
		while (focus != null) {
			String[] data = focus.split(",");
			if (data.length != 8) {
				focus = br.readLine();
				continue;
			}
			data[0]=data[0].toUpperCase();
			MovieInfo movie = new MovieInfo();
			movie.setId(data[0]);
			movie.setMovieName(data[1]);
			movie.setNationality(data[2]);
			movie.setDirector(data[3]);
			movie.setActor(data[4]);
			Timestamp ts;
			ts = Timestamp.valueOf(data[5]);
	        Date date = new Date();   
	        date = ts; 
	        DateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
	        String dateStr= sdf.format(date);
			movie.setShowTime(dateStr);
			movie.setCinemaName(data[6]);
			movie.setHallName(data[7]);
			myData.put(data[0], movie);
			String temp = "";
			int i = 0;
			char[] num = data[0].toCharArray();
			while (Character.isLetter(num[i])) {
				temp = temp + num[i++];
			}
			temp = temp + num[i];
			if (!myCinemaData.containsKey(temp))
				myCinemaData.put(temp, data[6] + data[7]);
			focus = br.readLine();
		}
		br.close();

	}

	public Map<String, MovieInfo> myIdQuery(String comp) {
		/*
		 * if (!(comp.length() == 3 || comp.length() == 5 || comp.length() ==
		 * 7)) { return null; }
		 */
		Set<String> set = myData.keySet();
		Set<String> resultSet = new HashSet<String>();
		Iterator<String> it = set.iterator();
		String temp = null;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.substring(0, comp.length()).equalsIgnoreCase(comp)) {
				resultSet.add(temp);
			}
		}
		Map<String, MovieInfo> result = new HashMap<String, MovieInfo>();
		it = resultSet.iterator();
		while (it.hasNext()) {
			temp = it.next();
			result.put(temp, myData.get(temp));
		}
		return result;
	}
	public Map<String, MovieInfo> myFilmQuery(String comp){
		Set<String> set = myData.keySet();
		Map<String, MovieInfo> result = new HashMap<String, MovieInfo>();
		Iterator<String> it = set.iterator();
		String temp = null;
		while (it.hasNext()) {
			temp = it.next();
			if (myData.get(temp).getMovieName().contains(comp)) {
				result.put(temp,myData.get(temp));
			}
		}
		return result;
	}
}
