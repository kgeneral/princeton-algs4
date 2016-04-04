import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture source;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        source = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return source;
    }

    // width of current picture
    public int width() {
        return source.width();
    }

    // height of current picture
    public int height() {
        return source.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        return 0;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return new int[0];
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
    }
}
