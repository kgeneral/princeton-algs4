import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

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
        if (isBorder(x, y))
            return 1000;

        Color target = source.get(x, y);

        Color left = source.get(x - 1, y);
        Color right = source.get(x + 1, y);
        Color top = source.get(x, y - 1);
        Color bottom = source.get(x, y + 1);

        int xGradientSquare = (right.getRed() - left.getRed()) * (right.getRed() - left.getRed()) +
                (right.getGreen() - left.getGreen()) * (right.getGreen() - left.getGreen()) +
                (right.getBlue() - left.getBlue()) * (right.getBlue() - left.getBlue());

        int yGradientSquare = (top.getRed() - bottom.getRed()) * (top.getRed() - bottom.getRed()) +
                (top.getGreen() - bottom.getGreen()) * (top.getGreen() - bottom.getGreen()) +
                (top.getBlue() - bottom.getBlue()) * (top.getBlue() - bottom.getBlue());

        return Math.sqrt(xGradientSquare + yGradientSquare);
    }

    private boolean isBorder(int x, int y) {
        return x - 1 == width() || y - 1 == height() || x == 0 || y == 0;
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
