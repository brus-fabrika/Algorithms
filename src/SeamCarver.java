
public class SeamCarver {
    private static final double BORDER_ENERGY = 195075.0;
    private Picture im;
    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        im = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return im;
    }

    // width of current picture
    public int width() {
        return im.width();
    }

    // height of current picture
    public int height() {
        return im.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x > im.width() - 1 || y < 0 || y > im.height() - 1)
            throw new java.lang.IndexOutOfBoundsException();

        if (x == 0 || y == 0 || x == im.width() - 1 || y == im.height() - 1)
            return BORDER_ENERGY;

        int rx = (im.get(x + 1, y).getRed() - im.get(x - 1, y).getRed());
        int gx = (im.get(x + 1, y).getGreen() - im.get(x - 1, y).getGreen());
        int bx = (im.get(x + 1, y).getBlue() - im.get(x - 1, y).getBlue());

        int ry = (im.get(x, y + 1).getRed() - im.get(x, y - 1).getRed());
        int gy = (im.get(x, y + 1).getGreen() - im.get(x, y - 1).getGreen());
        int by = (im.get(x, y + 1).getBlue() - im.get(x, y - 1).getBlue());

        return rx*rx + gx*gx + bx*bx + ry*ry + gy*gy + by*by;
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return null;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return null;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.NullPointerException();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null)
            throw new java.lang.NullPointerException();

        //TODO: check seam for validity and throw IllegalArgumentException
        //TODO: check width and height for validity and throw IllegalArgumentException


    }
}






















