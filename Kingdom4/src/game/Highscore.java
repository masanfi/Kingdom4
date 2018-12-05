package game;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * This allows to create a high score object that is added to the high score list in the final scene.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Highscore {


	private final SimpleStringProperty userName;
    private final SimpleIntegerProperty counter;
    private final SimpleLongProperty duration;
    private final SimpleLongProperty highScoreTime;
	
	public Highscore(String userName, int counter, long duration, long highScoreTime){
        this.userName = new SimpleStringProperty(userName);
        this.counter = new SimpleIntegerProperty(counter);
        this.duration = new SimpleLongProperty(duration);
        this.highScoreTime = new SimpleLongProperty(highScoreTime);
    }

	public String getUserName() {
		return userName.get();
	}

	public int getCounter() {
		return counter.get();
	}

	public long getDuration() {
		return duration.get();
	}

	public long getHighScoreTime() {
		return highScoreTime.get();
	}

}
