public class Scale extends ModifyImage
{
    public Scale(ImageData oldImage)
    {
        super(oldImage);
    }

    @Override
    protected int widthHook()
    {
        return oldImage.getWidth() / 2;
    }

    @Override
    protected int heightHook()
    {
        return oldImage.getHeight() / 2;
    }

    @Override
    protected int valueHook()
    {
        return oldImage.getPixel(x * 2, y * 2);
    }
        
}
