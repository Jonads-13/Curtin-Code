public class Invert extends ModifyImage
{
    public Invert(ImageData oldImage)
    {
        super(oldImage);
    }

    @Override
    protected int valueHook()
    {
        return ~oldImage.getPixel(x, y);
    }
}
