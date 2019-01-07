package game;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * This enables conversations in the Kingdom of Faboma
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Conversations {

    private GameEngine gameEngine;
    private List<String> wisemanText = new ArrayList<String>();
    private List<String> heroText = new ArrayList<String>();
    private boolean statusSpeechBubble = false;
    private Random random;

    public Conversations(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.random = new Random();

        gameEngine.getCharacter().put("lady", 0);
        gameEngine.getCharacter().put("fisherman", 0);
        gameEngine.getCharacter().put("blacksmith", 0);
        gameEngine.getCharacter().put("knight", 0);
        gameEngine.getCharacter().put("wiseman", 0);

        this.fillWisemanWithWisdom();
        this.fillHeroWithNonsense();
    }

    private void fillWisemanWithWisdom() {
        wisemanText.add("Mein Sohn!\nDie Schule des Lebens\nhat niemals Ferien.");
        wisemanText.add("Im Haus des Glücks\nist der Warteraum das\ngrößte Zimmer.");
        wisemanText.add("Schmutziges Geschirr\nschimmelt nicht, wenn\nman es in der Gefrier-\ntruhe aufbewahrt.");
        wisemanText.add("Alte Matrosen-Weisheit:\nLieber Rum trinken,\nals rumsitzen!");
        wisemanText.add("Zwiebeln statt Kiwis\nkaufen! Zwiebeln sind\nbilliger und länger\nhaltbar.");
        wisemanText.add("Mögen die Höhepunkte\nder Vergangenheit die\nTiefpunkte der Zukunft\nsein.");
        wisemanText.add("Auch im Luftschlösserbau\ngibt es Konjunkturen und\nKrisen.");
        wisemanText.add("Das Wort Windows stammt\naus einem alten Dialekt\nder Apachen und bedeuted:\n'Weißer Mann starren durch\nGlasscheibe auf Sanduhr'.");
        wisemanText.add("Das unsympathische an\nComputern ist, dass sie\nnur ja oder nein sagen\nkönnen, aber nicht\nvielleicht.");
        wisemanText.add("Gestern standen wir\nnoch vor einem Abgrund,\nheute sind wir schon einen\ngroßen Schritt weiter.");
        wisemanText.add("Das Schwierige am\nDiskutieren ist nicht, den\neigenen Standpunkt zu\nverteidigen, sondern ihn\nzu kennen.");
        wisemanText.add("Was sind die letzten\nWorte eines Informatikers?\n'Ich bleibe hier bis\ndas Problem gelöst ist.'");
        wisemanText.add("Irren ist menschlich.\nAber für das richtige\nChaos, braucht man\neinen Computer.");
        wisemanText.add("Hardware nennt man die\nTeile eines Computers, die\nman treten kann.");
    }
    private void fillHeroWithNonsense() {
    	heroText.add("Das schreibe ich mir\nsofort auf.");
    	heroText.add("Interessant.\nDarüber möchte ich\nspäter mehr wissen.");
    	heroText.add("Wahnsinn, hätte ich\nnicht gedacht.");
    	heroText.add("Wenn ich alt bin,\nwill ich auch mal\nso klug werden.");
    	heroText.add("So kluge Sachen höre\nich nie vom Schmied.");
    	heroText.add("Je länger ich spiele,\ndesto mehr scheine ich\nzu lernen.");
    	heroText.add("Ich habe das Gefühl,\ndiese Sprüche erzählst du\nnach dem Zufallsprinzip.");
    	heroText.add("Hold on.\nIch komme gleich wieder!");
    }

    public void startConversation(Trigger trigger) {
        Timeline timeline = new Timeline();
        double triggerX = trigger.getCoordinates().getX();
        double triggerY = trigger.getCoordinates().getY();
        double playerX = gameEngine.getPlayer()[0].getX() + gameEngine.getTileSize();
        double playerY = gameEngine.getPlayer()[0].getY() + gameEngine.getTileSize();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> gameEngine.setMovement(false)));
        timeline.setOnFinished(t->{
    		gameEngine.setMovement(true);
    	});
        if (trigger.getName().contentEquals("lady")) {
            if(!this.getStatusSpeechBubble()) {
                if (gameEngine.getCharacter().get("lady") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag! Ich bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Willkommen zurück im\nKönigreich Faboma,\nOreh!", 2, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich weiß, wer du\nbist, du mächtiger Held.\nWo warst du?", 3, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7300), ae -> this.showSpeechBubble(playerX, playerY, "Ich war auf\nGeschäftsreise!\nWas ist denn passiert?", 2.5, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(9900), ae -> this.showSpeechBubble(triggerX, triggerY, "Unsere Prinzessin Adlez\nwurde aus der Burg\nentführt!\nDu musst sie finden!", 3.5, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(13500), ae -> this.showSpeechBubble(playerX, playerY, "Schreck lass nach!\nWo könnte sie denn sein?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(15600), ae -> this.showSpeechBubble(triggerX, triggerY, "Man munkelt, dass du sie\nauf der anderen Seite\nder Brücke finden kannst.", 3, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(18700), ae -> this.showSpeechBubble(playerX, playerY, "Das sehe ich mir sofort\nan!", 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    gameEngine.getCharacter().put("lady", 1);
                }
                else if (gameEngine.getCharacter().get("lady") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Wie heißt du eigentlich?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Oreh, wir haben im\nNachbarkönigreich\nzusammen Informatik\nstudiert.", 4, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6200), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich bin Derni Ydal!\nHast du die Prinzessin\nschon finden können?", 3.5, Color.RED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(9800), ae -> this.showSpeechBubble(playerX, playerY, "Ich bin kurz vor dem\ngroßen Durchbruch!\nMuss los, see ya!", 3, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    gameEngine.getCharacter().put("lady", 2);
                }
                else {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Hallo Derni Ydal!\nIch bin Oreh,\nein mächtiger Held!", 3, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3100), ae -> this.showSpeechBubble(triggerX, triggerY, "Du bist\nvielleicht eine Nervensäge!\nSuch die Prinzessin!", 3.5, Color.RED, Color.WHITE)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("wiseman")) {
        	int randomNum = random.nextInt(wisemanText.size());
            int randomNumHero = random.nextInt(heroText.size());
            if(!this.getStatusSpeechBubble()) {
                if (gameEngine.getCharacter().get("wiseman") == 0) {
                    if (gameEngine.getCharacter().get("lady") >= 1) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, weiser Man!\nIch suche die Prinzessin.\nWeißt du, wo sie sein\nkönnte?", 2, Color.WHITE, Color.BLACK)));
                    }
                    else {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, weiser Man!\nWas gibt's Neues?", 2, Color.WHITE, Color.BLACK)));
                    }
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, wisemanText.get(randomNum), 3.5, Color.web("#744D34"), Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5700), ae -> this.showSpeechBubble(playerX, playerY, heroText.get(randomNumHero), 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    gameEngine.getCharacter().put("wiseman", 1);
                }
                else if (gameEngine.getCharacter().get("wiseman") == 1) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Was ich noch fragen\nwollte...", 1, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1100), ae -> this.showSpeechBubble(triggerX, triggerY, wisemanText.get(randomNum), 3.5, Color.web("#744D34"), Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4700), ae -> this.showSpeechBubble(playerX, playerY, heroText.get(randomNumHero), 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("blacksmith")) {
            if(!this.getStatusSpeechBubble()) {
                if (gameEngine.getCharacter().get("blacksmith") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Jeder kennt dich, Oreh.\nHast du das Geld\nfür das Schwert\ndabei?", 3, Color.DARKRED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), ae -> this.showSpeechBubble(playerX, playerY, "Ja, sicher. Ich hab\ndas, äh, hier.\nGeb ich dir gleich.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7300), ae -> this.showSpeechBubble(playerX, playerY, "Wie war ihr Name,\nedler Mann?\nIch war so lange weg.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(9400), ae -> this.showSpeechBubble(triggerX, triggerY, "Du warst zwei Tage weg.\nAuf \"Geschäftsreise\".\nIch bin Fabian Schmied!", 3, Color.DARKRED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(12500), ae -> this.showSpeechBubble(playerX, playerY, "Stimmt!\nLeider habe ich immer\nso derbe viel zu tun!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(14600), ae -> this.showSpeechBubble(triggerX, triggerY, "Ja, ja.\nBring mir einen Hasen,\nwenn du schon kein\nGeld hast.", 3, Color.DARKRED, Color.WHITE)));
                    if(gameEngine.findItemInInventory("R")) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(17700), ae -> this.showSpeechBubble(playerX, playerY, "Den habe ich gleich\nhier.", 2, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(17700), ae -> gameEngine.findRemovePickedUpItem("R")));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(19800), ae -> this.showSpeechBubble(triggerX, triggerY, "Einen prächtigen Hasen\nhast du da. Den\nkonnte man schon von\nweitem sehen.", 3, Color.DARKRED, Color.WHITE)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(22900), ae -> this.showSpeechBubble(playerX, playerY, "Für dich nur das Beste!", 2, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(25000), ae -> this.showSpeechBubble(triggerX, triggerY, "Dafür bekommst du dein\nSchwert. Das nächste mal,\nkommst du aber nicht so\ngünstig weg.", 3.5, Color.DARKRED, Color.WHITE)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(28600), ae -> this.showSpeechBubble(playerX, playerY, "Hab Dank. Ich empfehle\ndich gerne weiter.", 2, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(28600), ae -> gameEngine.pickUpSword()));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(28600), ae -> gameEngine.getCharacter().put("blacksmith", 4)));
                    }
                    else {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(17700), ae -> this.showSpeechBubble(playerX, playerY, "Hase! Geht klar!\nIch hopple gleich los.", 2, Color.WHITE, Color.BLACK)));
                        gameEngine.getCharacter().put("blacksmith", 1);
                    }
                    timeline.play();
                }
                else if (gameEngine.getCharacter().get("blacksmith") == 1 && !gameEngine.findItemInInventory("R")){
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Hallo!\nIch bin Oreh,\nein mächtiger Held!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Hat der mächtige Held\neinen Hasen gefunden?", 2, Color.DARKRED, Color.WHITE)));
                    timeline.play();
                }
                else if (gameEngine.getCharacter().get("blacksmith") == 1 && gameEngine.findItemInInventory("R")){
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(triggerX, triggerY, "Einen prächtigen Hasen\nhast du da. Den\nkonnte man schon von\nweitem sehen.", 3, Color.DARKRED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3100), ae -> this.showSpeechBubble(playerX, playerY, "Für dich nur das Beste!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(3100), ae -> gameEngine.findRemovePickedUpItem("R")));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), ae -> this.showSpeechBubble(triggerX, triggerY, "Dafür bekommst du dein\nSchwert. Das nächste mal,\nkommst du aber nicht so\ngünstig weg.", 3.5, Color.DARKRED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7200), ae -> gameEngine.pickUpSword()));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8800), ae -> this.showSpeechBubble(playerX, playerY, "Hab Dank. Ich empfehle\ndich gerne weiter.", 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    gameEngine.getCharacter().put("blacksmith", 3);
                }
                else if (gameEngine.getCharacter().get("blacksmith") == 3){
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(triggerX, triggerY, "Es sieht nicht so aus,\nals hättest du Geld dabei.", 2.5, Color.DARKRED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2600), ae -> this.showSpeechBubble(playerX, playerY, "Der Ritter lässt mich\nnicht über die Brücke,\nwas kann ich tun?", 3, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5700), ae -> this.showSpeechBubble(triggerX, triggerY, "Red mit dem Fischer,\nvielleicht kann der\nwas für Bettler wie\ndich tun.", 3.5, Color.DARKRED, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(9400), ae -> this.showSpeechBubble(playerX, playerY, "Ich empfehle dir 'Alt'\nund 'F4' zu drücken,\nwenn du mal wieder\nProbleme hast.", 3.5, Color.WHITE, Color.BLACK)));
                    timeline.play();
                    gameEngine.getCharacter().put("blacksmith", 3);
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("fisherman")) {
            if(!this.getStatusSpeechBubble()) {
                if (gameEngine.getCharacter().get("fisherman") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, edler Fischer!\nIch bin Oreh, ein...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Wenn du jetzt sagst,\ndass du ein mächtiger\nHeld bist, gibt's was\nauf die Löffel!", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), ae -> this.showSpeechBubble(playerX, playerY, "Wollte ich nicht sagen!\nähm... Wie laufen die\nGeschäfte?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7300), ae -> this.showSpeechBubble(triggerX, triggerY, "Im Angelbusiness bin ich\nein großer Fisch.\nWenn du weißt, wie\nich meine.", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10400), ae -> this.showSpeechBubble(triggerX, triggerY, "Aber, schau mal, die\nTruhe hier. Wo ist\ndenn der Schlüssel dafür?", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(13500), ae -> this.showSpeechBubble(playerX, playerY, "Also, ich hab den nicht\nim Wald verloren!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(15600), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich habe nichts von\neinem Wald gesagt.\nAber es wäre gut, wenn\nsich der Schlüssel schnell\nwieder anfindet!!", 3.5, Color.YELLOW, Color.BLACK)));
                    if (gameEngine.findItemInInventory("K")) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(19100), ae -> this.showSpeechBubble(playerX, playerY, "Weißt du was ich\nzufällig gefunden habe?", 2, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(21200), ae -> this.showSpeechBubble(triggerX, triggerY, "Die Bedeutung von 42?", 2, Color.YELLOW, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(23300), ae -> this.showSpeechBubble(playerX, playerY, "So ähnlich.\nEinen Schlüssel der\nzu deiner Truhe\npassen könnte.", 3, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(25300), ae -> gameEngine.findRemovePickedUpItem("K")));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(26400), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich sag mal, TOP EBAYER.\nNimm diesen Fisch\nals Dank.", 3, Color.YELLOW, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(29400), ae -> gameEngine.pickUpFish()));
                        gameEngine.getCharacter().put("fisherman", 2);
                    }
                    timeline.play();
                    gameEngine.getCharacter().put("fisherman", 1);
                }
                else if (gameEngine.getCharacter().get("fisherman") == 1 && !gameEngine.findItemInInventory("K")) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Nochmal wegen...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2200), ae -> this.showSpeechBubble(triggerX, triggerY, "Hast du den Schlüssel?", 2, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4300), ae -> this.showSpeechBubble(playerX, playerY, "Ich war quasi gerade\nauf dem Weg... ähm ...\nbis gleich.", 2, Color.WHITE, Color.BLACK)));
                    timeline.play();
                }
                else if (gameEngine.getCharacter().get("fisherman") == 1 && gameEngine.findItemInInventory("K")) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Weißt du was ich\nzufällig gefunden habe?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2200), ae -> this.showSpeechBubble(triggerX, triggerY, "Die Bedeutung von 42?", 2, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4300), ae -> this.showSpeechBubble(playerX, playerY, "So ähnlich.\nEinen Schlüssel der\nzu deiner Truhe\npassen könnte.", 3, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7300), ae -> gameEngine.findRemovePickedUpItem("K")));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(7400), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich sag mal, TOP EBAYER.\nNimm diesen Fisch\nals Dank.", 3, Color.YELLOW, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10400), ae -> gameEngine.pickUpFish()));
                    timeline.play();
                    gameEngine.getCharacter().put("fisherman", 2);
                }
                else if (gameEngine.getCharacter().get("fisherman") == 2) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Ich wollte noch mal...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2200), ae -> this.showSpeechBubble(triggerX, triggerY, "So lange du nicht weißt\nwas die Bedeutung von\n42 ist, brauchst du\nnicht wiederkommen.", 3.5, Color.YELLOW, Color.BLACK)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
        else if (trigger.getName().contentEquals("knight")) {
            if(!this.getStatusSpeechBubble()) {
                if (gameEngine.getCharacter().get("knight") == 0) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Guten Tag, edler Ritter!\nIch...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Was willst du?", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(playerX, playerY, "Ich bin ein...", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6300), ae -> this.showSpeechBubble(triggerX, triggerY, "...Typ mit einem Problem,\nwenn du nicht sofort\nverschwindest!", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8400), ae -> this.showSpeechBubble(playerX, playerY, "Sieh mal, ich muss nur\nauf die andere Seite.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10500), ae -> this.showSpeechBubble(triggerX, triggerY, "Solange ich und mein\nSchwert hier stehen,\nkannst du dir das Ufer\nvon dieser Seite\nanschauen.", 3.5, Color.NAVY, Color.WHITE)));
                    timeline.play();
                    gameEngine.getCharacter().put("knight", 1);
                }
                else if (gameEngine.getCharacter().get("knight") == 1 && !gameEngine.findItemInInventory("S")) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Ich muss über die Brücke!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "Habe ich mich unklar\nausgedrückt?", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(triggerX, triggerY, "So unbewaffnet wie du\nbist, brauchst du hier\nnicht nochmal an-\nrücken.", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
                }
                else if (gameEngine.getCharacter().get("knight") == 1 && gameEngine.findItemInInventory("S")) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Jetzt gehts aber los.\nPlatz, ich muss auf die\nandere Seite!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2200), ae -> this.showSpeechBubble(triggerX, triggerY, "Ist das ein Schwert\noder ein Zahnstocher?", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4300), ae -> this.showSpeechBubble(triggerX, triggerY, "Also ernsthaft, ich hoffe\ndu hast dafür nichts\nbezahlt. Jedenfalls werde\nich damit nicht gegen\ndich kämpfen!", 4, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8400), ae -> this.showSpeechBubble(playerX, playerY, "Ähm ... schau mal\nein Ufo!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10500), ae -> this.showSpeechBubble(triggerX, triggerY, "Bis ich nicht einen Fisch\ngefangen habe, schau ich\nnirgendwo hin.", 3, Color.NAVY, Color.WHITE)));
                    if (gameEngine.findItemInInventory("F")) {
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(13600), ae -> this.showSpeechBubble(playerX, playerY, "Ich habe zufällig einen\nFisch bei mir.", 2, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(15700), ae -> this.showSpeechBubble(triggerX, triggerY, "Heute scheint dein\nGlücktag zu sein!", 2, Color.NAVY, Color.WHITE)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(17800), ae -> this.showSpeechBubble(triggerX, triggerY, "Wenn du mir den Fisch\nfür sagen wir ... nichts\nverkaufst, dann lass ich\ndich rüber.", 3.5, Color.NAVY, Color.WHITE)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(21400), ae -> this.showSpeechBubble(playerX, playerY, "Dann nimm dieses\nGeschenk von Herzen.", 2, Color.WHITE, Color.BLACK)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(21400), ae -> gameEngine.findRemovePickedUpItem("F")));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(23500), ae -> this.showSpeechBubble(triggerX, triggerY, "Lauf, bevor ich es mir\nanders überlege!", 2.5, Color.NAVY, Color.WHITE)));
                        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(26100), ae -> gameEngine.changeKnight()));
                        gameEngine.getCharacter().put("knight", 3);
                    }
                    timeline.play();
                    gameEngine.getCharacter().put("knight", 2);
                }
                else if (gameEngine.getCharacter().get("knight") == 2 && !gameEngine.findItemInInventory("F")) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Kann ich ganz bitte,\nbitte nur mal kurz über\ndie Brücke?", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2100), ae -> this.showSpeechBubble(playerX, playerY, "Ich will nach ganz oben\nin den Highscore!", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4200), ae -> this.showSpeechBubble(triggerX, triggerY, "Klar!", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6300), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich reiß die Brücke gleich\nein, dann ist vorbei mit\nHighscore. Hast du ein\nGlück, dass ich Hunger\nhabe.", 3.5, Color.NAVY, Color.WHITE)));
                    timeline.play();
                }
                else if (gameEngine.getCharacter().get("knight") == 2 && gameEngine.findItemInInventory("F")) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Ich habe zufällig einen\nFisch bei mir.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2200), ae -> this.showSpeechBubble(triggerX, triggerY, "Heute scheint dein\nGlücktag zu sein!", 2, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4300), ae -> this.showSpeechBubble(triggerX, triggerY, "Wenn du mir den Fisch\nfür sagen wir ... nichts\nverkaufst, dann lass ich\ndich rüber.", 3.5, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8000), ae -> this.showSpeechBubble(playerX, playerY, "Dann nimm dieses\nGeschenk von Herzen.", 2, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(8000), ae -> gameEngine.findRemovePickedUpItem("F")));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10100), ae -> this.showSpeechBubble(triggerX, triggerY, "Lauf, bevor ich es mir\nanders überlege!", 2.5, Color.NAVY, Color.WHITE)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(12700), ae -> gameEngine.changeKnight()));
                    timeline.play();
                    gameEngine.getCharacter().put("knight", 3);
                }
                else if (gameEngine.getCharacter().get("knight") == 3) {
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), ae -> this.showSpeechBubble(playerX, playerY, "Ähm...", 1.5, Color.WHITE, Color.BLACK)));
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1700), ae -> this.showSpeechBubble(triggerX, triggerY, "Ich esse. Verschwinde!", 2, Color.NAVY, Color.WHITE)));
                    timeline.play();
                }
                this.setStatusSpeechBubble(true);
            }
        }
    }

    /**
     * Shows a speech bubble of a conversation.
     * @param x
     * @param y
     * @param textString
     * @param duration
     * @param backgroundColor
     * @param textColor
     */
    public void showSpeechBubble(double x, double y, String textString, double duration, Color backgroundColor, Color textColor) {
        Polygon littlePointer = new Polygon();
        littlePointer.getPoints().addAll(new Double[]{ x + 20, y - 20, x + 40, y, x + 64, y - 20 });
        littlePointer.setFill(backgroundColor);
        Rectangle bubble = new Rectangle(x - 10, y - 2 * gameEngine.getTileSize() + 10, 210, 110);
        bubble.setFill(backgroundColor);

        Text text = new Text(x, y - 90, textString);
        text.setFont(Font.font ("Verdana", 14));
        text.setFill(textColor);

        gameEngine.getTextOver().getChildren().addAll(littlePointer, bubble, text);
        gameEngine.getTextOver().setVisible(true);

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(duration),
                ae -> {
                    gameEngine.getTextOver().setVisible(false);
                    gameEngine.getTextOver().getChildren().clear();
                }));
        timeline.play();
    }

    public boolean getStatusSpeechBubble() {
        return statusSpeechBubble;
    }

    public void setStatusSpeechBubble(boolean statusSpeechBubble) {
        this.statusSpeechBubble = statusSpeechBubble;
    }
}
