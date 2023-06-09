package dozer.util.misc;

public class UtilTimer {

    private long lastMS = 0L;

    public boolean hasReached(long ms) {
        return System.currentTimeMillis() - this.lastMS >= ms;
    }

    public void reset() {
        this.lastMS = System.currentTimeMillis();
    }

}
