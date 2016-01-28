package f;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class W_City {

	private String city;
	private String[] time;
	private String[] wheather;
	private double[] snow;
	private double[] wind;
	

	public W_City( String city )
	{
		this.city = city;
		read(city);
	}
	

	public void read( String city ) 
	{
		String filename = city;	//読み込むファイルの名前
		File file = new File(filename);
		try 
		{
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line;
			ArrayList txtData = new ArrayList();
			while ( ( line = br.readLine()) != null ) 
			{
				String[] cols = line.split(" ");
				txtData.add(cols);
			}


			time = new String[txtData.size()];
			wheather = new String[txtData.size()];
			snow = new double[txtData.size()];
			wind = new double[txtData.size()];

			for(int i = 0; i < txtData.size(); i++)
			{
				String[] row = (String[]) txtData.get(i);

				time[i] = row[0];
				wheather[i] = row[1];
				snow[i] = Double.parseDouble(row[2]);
				wind[i] = Double.parseDouble(row[3]);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String[] getTime() {
		return time;
	}


	public void setTime(String[] time) {
		this.time = time;
	}


	public String[] getWheather() {
		return wheather;
	}


	public void setWheather(String[] wheather) {
		this.wheather = wheather;
	}


	public double[] getSnow() {
		return snow;
	}


	public void setSnow(double[] snow) {
		this.snow = snow;
	}


	public double[] getWind() {
		return wind;
	}


	public void setWind(double[] wind) {
		this.wind = wind;
	}
}


