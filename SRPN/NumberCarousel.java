

public class NumberCarousel {
    
    private int[] carousel = {
        44245652,
        903121759,
        235758559,
        501379754,
        982102748,
        915653778,
        734522384,
        55873735,
        303150701,
        325696894
    };

    private int currentIndex = 0;

    public int getNextInt() {
        int val = currentIndex % carousel.length;
        currentIndex++;
        return this.carousel[val];
    }
}
