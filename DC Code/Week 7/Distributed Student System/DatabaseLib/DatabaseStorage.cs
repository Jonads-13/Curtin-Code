using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;

namespace DatabaseLib
{
    public class DataStruct
    {
        public uint accNo { get; set; }
        public uint pin { get; set; }
        public int balance { get; set; }
        public string firstName { get; set; }
        public string lastName { get; set; }
        public byte[] profilePicture { get; set; }

        public DataStruct()
        {
            accNo = 0;
            pin = 0;
            balance = 0;
            firstName = ""; 
            lastName = "";
            profilePicture = null;
        }

        public DataStruct(uint a, uint p, int b, string f, string l, byte[] pic)
        {
            accNo = a;
            pin = p;
            balance = b;
            firstName = f; 
            lastName = l;
            profilePicture = pic;
        }
    }
}
