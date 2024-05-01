using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Web_API_Data_Server.models.Account
{
    public class Customer
    {
        public uint Acct { get; set; }
        public uint Pin { get; set; }
        public int Bal { get; set; }
        public string Fname { get; set; }
        public string Lname { get; set; }
        public byte[] ProfPic { get; set; }

        public Customer()
        {
            Acct = 0;
            Pin = 0;
            Bal = 0;
            Fname = ""; 
            Lname = "";
            ProfPic = new byte[1];
        }

        public Customer(uint a, uint p, int b, string f, string l, byte[] pic)
        {
            Acct = a;
            Pin = p;
            Bal = b;
            Fname = f; 
            Lname = l;
            ProfPic = pic;
        }
    }
}
