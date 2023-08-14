using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;

namespace DatabaseLib
{
    internal class DatabaseGenerator
    {
        private Random rand = new Random();
        private string[] FirstNames = { "Jake", "Nick", "Justin", "Tyler", "Christian", "Jarrid", "Caroline", "Stanley", "Tri", "Jordan", "Joleen", "Bec", "Clarissa", "Lauren", "Sam", "Oliver", "Wari" };
        private string[] LastNames = { "Jonas", "Wright", "Pan", "Milbourne", "Dao", "Twomney", "Lee", "Smith", "Tan", "Johnson", "Jones", "Lambie", "Cooper", "Herman", "Pang", "Gregorovich", "Dumbledore"};
        private string[] pics = { "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png", "10.png" };

        public DatabaseGenerator() { }
        public void GetNextAccount(out string firstName, out string lastName, out uint pin, out uint accno, out int balance, out byte[] pp)
        {
            firstName = GetFirstName();
            lastName = GetLastName();
            pin = GetPin();
            accno = GetAccNo(); 
            balance = GetBalance();
            pp = GetProfilePicture();

        }
        private string GetFirstName()
        {
            int index = rand.Next(FirstNames.Length);
            return FirstNames[index];
        }

        private string GetLastName() 
        {
            int index = rand.Next(LastNames.Length);
            return LastNames[index];
        }

        private uint GetPin()
        {
            return (uint)rand.Next(999,10000);
        }

        private uint GetAccNo()
        {
            return (uint)rand.Next(999999,10000000);
        }

        private int GetBalance()
        {
            return rand.Next(-100000, 100000);
        }

        private byte[] GetProfilePicture()
        {
            string path = @"C:\Users\Jake\Pictures\Saved Pictures\";

            int index = rand.Next(pics.Length);

            string pic = pics[index];

            path += pic;

            BitmapImage image = new BitmapImage(new Uri(path, UriKind.Absolute));

            using (MemoryStream memoryStream = new MemoryStream())
            {
                PngBitmapEncoder encoder = new PngBitmapEncoder();
                encoder.Frames.Add(BitmapFrame.Create(image));
                encoder.Save(memoryStream);

                byte[] imageBytes = memoryStream.ToArray();

                return imageBytes;
            }
        }
    }
}
