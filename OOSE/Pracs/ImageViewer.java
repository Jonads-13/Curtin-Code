public class ImageViewer 
{
    private ImageData scale(ImageData oldImage)
    {
        int newWidth = oldImage.getWidth() / 2;
        int newHeight = oldImage.getHeight() / 2;
        ImageData newImage = new ImageData(newWidth, newHeight);

        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, oldImage.getPixel(x * 2, y * 2));
            }
        }
        return newImage;
    }  
    
    private ImageData rotate(ImageData oldImage)
    {
        int newWidth = oldImage.getHeight();
        int newHeight = oldImage.getWidth();
        ImageData newImage = new ImageData(newWidth, newHeight);

        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, oldImage.getPixel(newHeight - y, x));
            }
        }
        return newImage;
    }

    private ImageData invert(ImageData oldImage)
    {
        int newWidth = oldImage.getWidth();
        int newHeight = oldImage.getHeight();
        ImageData newImage = new ImageData(newWidth, newHeight);
        
        for(int y = 0; y < newHeight; y++)
        {
            for(int x = 0; x < newWidth; x++)
            {
                newImage.setPixel(x, y, ~oldImage.getPixel(x, y));
            }
        }
        return newImage;
    }
}
