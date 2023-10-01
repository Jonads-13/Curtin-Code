using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Web_API_Business_Server.models
{
    public class Customer
    {
        public uint AccNo { get; set; }
        public uint Pin { get; set; }
        public int Balance { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public byte[] ProfilePicture { get; set; }

        public Customer()
        {
            AccNo = 0;
            Pin = 0;
            Balance = 0;
            FirstName = ""; 
            LastName = "";
            ProfilePicture = null;
        }

        public Customer(uint a, uint p, int b, string f, string l, byte[] pic)
        {
            AccNo = a;
            Pin = p;
            Balance = b;
            FirstName = f; 
            LastName = l;
            ProfilePicture = pic;
        }

        public override string ToString()
        {
            return FirstName + ", " + LastName + ": " + AccNo + ", " + Pin + " $" + Balance;
        }
    }
}
