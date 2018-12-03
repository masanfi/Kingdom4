package highscore;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

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
