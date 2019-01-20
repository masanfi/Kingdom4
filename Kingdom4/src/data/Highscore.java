package data;

import java.util.Date;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * This allows to create a high score object that is added to the high score list in the final scene.
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.2
 *
 */

public class Highscore {

	private final SimpleStringProperty userName;
    private final SimpleStringProperty trophy;
    private final SimpleLongProperty duration;
    private final SimpleLongProperty highScoreTime;
	
	public Highscore(String userName, String trophy, long duration, long highScoreTime){
        this.userName = new SimpleStringProperty(userName);
        this.trophy = new SimpleStringProperty(trophy);
        this.duration = new SimpleLongProperty(duration);
        this.highScoreTime = new SimpleLongProperty(highScoreTime);
    }

	public String getUserName() {
		return userName.get();
	}

	public String getTrophy() {
		return trophy.get();
	}

	public long getDuration() {
	    
	    	
	    
		return duration.get();
	}

	public String getHighScoreTime() {
		
		
		String date = new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(highScoreTime.get()));
		
		
		return date;
		//return highScoreTime.get();
	}

}
