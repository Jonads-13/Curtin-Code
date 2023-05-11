public class Rotate extends ModifyImage
{
    public Rotate(ImageData oldImage)
    {
        super(oldImage);
    } 

    @Override
    protected int valueHook()
    {
        return oldImage.getPixel(newHeight - y, x);
    }
}
