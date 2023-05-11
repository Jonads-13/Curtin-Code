public abstract class ModifyImage 
{
    protected ImageData oldImage;

    public ModifyImage(ImageData oldImage)
    {
        this.oldImage = oldImage;
    }

    public ImageData modify()
    {
        int newWidth = widthHook();
        int newHeight = heightHook();

        ImageData newImage = new ImageData(newWidth, newHeight);

        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, valueHook());
            }
        }
        return newImage;
    }

    /*
     * Hook Methods below
     */

    protected int widthHook()
    {
        return oldImage.getWidth();
    }

    protected int heightHook()
    {
        return oldImage.getHeight();
    }

    protected abstract int valueHook();

}
