package game;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


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
	private HBox hboxItemOneImageView;
	private HBox hboxItemTwoImageView;
	private HBox hboxItemThreeImageView;
	
	public void setHuditemOneImageView(ImageView ImageView,HBox box) {
		this.hudItemOneImageView = ImageView;
		this.hboxItemOneImageView = box;
	}

	public void setHuditemTwoImageView(ImageView ImageView,HBox box) {
		this.hudItemTwoImageView = ImageView;
		this.hboxItemTwoImageView = box;
	}

	public void setHuditemThreeImageView(ImageView ImageView,HBox box) {
		this.hudItemThreeImageView = ImageView;
		this.hboxItemThreeImageView = box;
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
		String border ="-fx-border-color: black;"
                + "-fx-border-width: 1;";
		switch (pos) {
		case 0:
			Platform.runLater(() -> {
				hudItemOneImageView.setImage(image);
				hboxItemOneImageView.setStyle(border);
			});
			break;
		case 1:
			Platform.runLater(() -> {
				hudItemTwoImageView.setImage(image);
				hboxItemTwoImageView.setStyle(border);
			});
			break;
		case 2:
			Platform.runLater(() -> {
				hudItemThreeImageView.setImage(image);
				hboxItemThreeImageView.setStyle(border);
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
