package semesterafslutning;

public class Link {
    private int from;
    private String direction;
    private int to;

    public Link(int from, String direction, int to) {
        this.from = from;
        this.direction = direction;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
