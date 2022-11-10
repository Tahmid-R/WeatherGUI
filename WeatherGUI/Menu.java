import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;

import org.json.JSONObject;
public class Menu implements ActionListener{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton reset = new JButton("Reset");
	JButton submit = new JButton("Submit");
	JLabel messageLabel = new JLabel("");
	JLabel city = new JLabel("City");
	JTextField cityText = new JTextField(20);
	JLabel location = new JLabel("Location: ");
	JLabel weather = new JLabel("Weather:");
	JLabel temperature = new JLabel("Temperature:");
	JLabel high = new JLabel("High:");
	JLabel low = new JLabel("Low:");
	JLabel humidity = new JLabel("Humidity:");
	JLabel dateTime = new JLabel("Date/Time:");
	
	
	
	Menu(){
		
		city.setBounds(70, 20, 80, 25 );
		cityText.setBounds(100, 20, 165, 25);
		
		submit.setBounds(100, 50, 80, 25);
		submit.addActionListener(this);
		
		reset.setBounds(181, 50, 83,25);
		reset.addActionListener(this);
		
		messageLabel.setBounds(100,80,200,25);
		
		
		location.setBounds(30,100,200,25);
		weather.setBounds(30,130,200,25);
		temperature.setBounds(30,160,200,25);
		high.setBounds(50,190,200,25);
		low.setBounds(50,220,200,25);
		humidity.setBounds(30,250,200,25);
		dateTime.setBounds(30,280,200,25);
		
		
		
		frame.add(city);
		frame.add(cityText);
		frame.add(submit);
		frame.add(reset);
		frame.add(messageLabel);
		frame.add(location);
		frame.add(weather);
		frame.add(temperature);
		frame.add(high);
		frame.add(low);
		frame.add(humidity);
		frame.add(dateTime);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Menu");
		frame.setSize(420,420);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String userCity = cityText.getText();
		
		if(e.getSource()==reset) {
			cityText.setText("");
			messageLabel.setText("");
			location.setText("Location: ");
			weather.setText("Weather: ");
			temperature.setText("Temperature: ");
			high.setText("High: ");
			low.setText("Low: ");
			humidity.setText("Humidity: ");
			dateTime.setText("Date/Time: ");
		}
		
		if(e.getSource() == submit) {
			try {
				
				
				HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
				httpURLConnection.setRequestMethod("GET");
				
				String line = "";
				InputStreamReader inputStreamreader = new InputStreamReader(httpURLConnection.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(inputStreamreader);
				
				StringBuilder response = new StringBuilder();
				
				 while((line=bufferedReader.readLine())!=null) {
				    	response.append(line);
				    }
				 bufferedReader.close();
				 
				 JSONObject jsonObject = new JSONObject(response.toString());
				 
				
				 location.setText("Location: " + jsonObject.getString("name") + ", " + jsonObject.getJSONObject("sys").getString("country"));
				 weather.setText("Weather: " + jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
				 temperature.setText("Temperature: " + Weather.KtoC(jsonObject.getJSONObject("main").getDouble("temp")) + "°C");
				 high.setText("High: " + Weather.KtoC(jsonObject.getJSONObject("main").getDouble("temp_max")) + "°C");
				 low.setText("Low: " + Weather.KtoC(jsonObject.getJSONObject("main").getDouble("temp_min")) + "°C");
				 humidity.setText("Humidity: " + jsonObject.getJSONObject("main").getInt("humidity"));
				 dateTime.setText("Date/Time: "+ Weather.DateTime(System.currentTimeMillis()+(jsonObject.getInt("timezone")*1000), "dd/MM/yyyy HH:mm"));
				 
				 messageLabel.setForeground(Color.green);
				 messageLabel.setFont(new Font(null,Font.ITALIC,10));
				
				 messageLabel.setText("Success!");
				
			}
			catch(Exception e1) {
				messageLabel.setForeground(Color.red);
				messageLabel.setFont(new Font(null,Font.ITALIC,15));
				
				messageLabel.setText("The city entered does not exist");
				
			
			
			}
			

		
			
			
			
		}
		
		
	}

}
