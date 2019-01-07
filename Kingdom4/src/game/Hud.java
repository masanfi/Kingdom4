package game;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Hud {

	private String[] pickedUpItems = new String[3];
	private String[] collectedTrophys = new String[5];
	
	private ImageView hudItemOneImageView;
	private ImageView hudItemTwoImageView;
	private ImageView hudItemThreeImageView;

	private ImageView hudTrophyOneImageView;
	private ImageView hudTrophyTwoImageView;
	private ImageView hudTrophyThreeImageView;
	private ImageView hudTrophyFourImageView;
	private ImageView hudTrophyFiveImageView;
	
	public void setHuditemOneImageView(ImageView ImageView) {
		this.hudItemOneImageView = ImageView;
	}

	public void setHuditemTwoImageView(ImageView ImageView) {
		this.hudItemTwoImageView = ImageView;
	}

	public void setHuditemThreeImageView(ImageView ImageView) {
		this.hudItemThreeImageView = ImageView;
	}
	
	public void setHudTrophyOneImageView(ImageView ImageView) {
		this.hudTrophyOneImageView = ImageView;
	}
	
	public void setHudTrophyTwoImageView(ImageView ImageView) {
		this.hudTrophyTwoImageView = ImageView;
	}
	
	public void setHudTrophyThreeImageView(ImageView ImageView) {
		this.hudTrophyThreeImageView = ImageView;
	}
	
	public void setHudTrophyFourImageView(ImageView ImageView) {
		this.hudTrophyFourImageView = ImageView;
	}
	
	public void setHudTrophyFiveImageView(ImageView ImageView) {
		this.hudTrophyFiveImageView = ImageView;
	}

	public void setCollectedTrophy(Image image,String item) {
		for (int i = 0; i < 5; i++) {
			if (collectedTrophys[i] == null) {
				positionCollectedTrophy(image, i);
				collectedTrophys[i] = item;
				break;
			}
		}
	}
	
	private void positionCollectedTrophy(Image image, int pos) {
		switch (pos) {
		case 0:
			Platform.runLater(() -> {
				hudTrophyOneImageView.setImage(image);
			});
			break;
		case 1:
			Platform.runLater(() -> {
				hudTrophyTwoImageView.setImage(image);
			});
			break;
		case 2:
			Platform.runLater(() -> {
				hudTrophyThreeImageView.setImage(image);
			});
			break;
		case 3:
			Platform.runLater(() -> {
				hudTrophyFourImageView.setImage(image);
			});
			break;
		case 4:
			Platform.runLater(() -> {
				hudTrophyFiveImageView.setImage(image);
			});
			break;
		}
	}
		
	public void setPickedUpItem(Image image,String item) {
		for (int i = 0; i < 3; i++) {
			if (pickedUpItems[i] == null) {
				positionPickedUpItem(image, i);
				pickedUpItems[i] = item;
				break;
			}
		}
	}
	
	public String[] getPickedUpItems() {
		return pickedUpItems;
	}
	

	private void positionPickedUpItem(Image image, int pos) {
		switch (pos) {
		case 0:
			Platform.runLater(() -> {
				hudItemOneImageView.setImage(image);
			});
			break;
		case 1:
			Platform.runLater(() -> {
				hudItemTwoImageView.setImage(image);
			});
			break;
		case 2:
			Platform.runLater(() -> {
				hudItemThreeImageView.setImage(image);
			});
			break;
		}
	}

	private void removePickedUpItem(int pos) {
		switch (pos) {
		case 0:
			Platform.runLater(() -> {
				hudItemOneImageView.setImage(null);
				;
			});
			break;
		case 1:
			Platform.runLater(() -> {
				hudItemTwoImageView.setImage(null);
			});
			break;
		case 2:
			Platform.runLater(() -> {
				hudItemThreeImageView.setImage(null);
			});
			break;
		}
	}

	public void findRemovePickedUpItem(String name) {
		for (int i = 0; i < 3; i++) {
			if (pickedUpItems[i] == name) {
				removePickedUpItem(i);
				pickedUpItems[i] = null;
			}
		}
	}
}
