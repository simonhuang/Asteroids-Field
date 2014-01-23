package com.badlogic.androidgames.asteroids;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.androidgames.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;
	public static int[] highscores = new int[] {0, 0, 0, 0, 0};
	
	public static void load(FileIO files){
		BufferedReader in = null;
		try{
			in = new BufferedReader (new InputStreamReader(files.readFile("astfield.txt")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for (int i=0;i<5;i++){
				highscores[i] = Integer.parseInt(in.readLine());
			}
		} catch (IOException e)	{
			// its okay we have defaults
		} catch (NumberFormatException e){
			// its okay we have defaults
		} finally{
			try{
				if (in != null)
					in.close();
			} catch (IOException e){
				
			}
		}
	}
	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter (new OutputStreamWriter(
					files.writeFile("astfield.txt")));
			out.write(Boolean.toString(soundEnabled));
			out.newLine();
			for (int i=0;i<5;i++){
				out.write(Integer.toString(highscores[i]));
				out.newLine();
			}
		} catch (IOException e){
			
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				
			}
		}
	}
	
	public static void addScore (int score){
		for (int i=0;i<4;i++){
			if (highscores[i]<score){
				for (int j=4;j>i;j--){
					highscores[j]=highscores[j-1];
				}
				highscores[i]=score;
				break;
			}
		}
	}
}
