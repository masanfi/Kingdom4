package game;

public class Item {

    private int id;
    private String name;
    private String type;
    private String walkable;
    private String portable;
    private int xBegin;
    private int yBegin;
    private int xEnd;
    private int yEnd;
    private String icon;
    private int fixX;
    private int fixY;
    private String info;
    private int minAmount;
    private int maxAmount;
	private double width;
	private double height;

    public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWalkable() {
        return walkable;
    }

    public void setWalkable(String walkable) {
        this.walkable = walkable;
    }

    public String getPortable() {
        return portable;
    }

    public void setPortable(String portable) {
        this.portable = portable;
    }

    public int getXBegin() {
        return xBegin;
    }

    public void setXBegin(int xBegin) {
        this.xBegin = xBegin;
    }

    public int getYBegin() {
        return yBegin;
    }

    public void setYBegin(int yBegin) {
        this.yBegin = yBegin;
    }

    public int getXEnd() {
        return xEnd;
    }

    public void setXEnd(int xEnd) {
        this.xEnd = xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }

    public void setYEnd(int yEnd) {
        this.yEnd = yEnd;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getFixX() {
        return fixX;
    }

    public void setFixX(int fixX) {
        this.fixX = fixX;
    }

    public int getFixY() {
        return fixY;
    }

    public void setFixY(int fixY) {
        this.fixY = fixY;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", type=" + type + ", walkable=" + walkable + ", portable="
				+ portable + ", xBegin=" + xBegin + ", yBegin=" + yBegin + ", xEnd=" + xEnd + ", yEnd=" + yEnd
				+ ", icon=" + icon + ", fixX=" + fixX + ", fixY=" + fixY + ", info=" + info + ", minAmount=" + minAmount
				+ ", maxAmount=" + maxAmount + ", width=" + width + ", height=" + height + "]";
	}


}