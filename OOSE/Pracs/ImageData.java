public class ImageData      // Don't modify this particular code.
{                           // (It's not the interesting bit!)
    private int[][] image;
    private int height;
    private int width;

    public ImageData(int width, int height)
    {
        image = new int[height][width];
        this.height = height;
        this.width = width;
    }

    public void setPixel(int x, int y, int value)
    {
        image[y][x] = value;
    }

    public int getPixel(int x, int y)
    {
        return image[y][x];
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getWidth()
    {
        return this.width;
    }
}
