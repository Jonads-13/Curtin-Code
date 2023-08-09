using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;

namespace DatabaseLib
{
    internal class DataStruct
    {
        public uint accNo;
        public uint pin;
        public int balance;
        public string firstName;
        public string lastName;
        public byte[] profilePicture;

        public DataStruct()
        {
            accNo = 0;
            pin = 0;
            balance = 0;
            firstName = ""; 
            lastName = "";
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
