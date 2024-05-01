using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace APIClasses
{
    public class DataIntermed
    {
        public uint Acct { set; get; }
        public uint Pin { set; get; }
        public int Bal { set; get; } 
        public string Fname { set; get; }
        public string Lname { set; get; }
        public byte[] ProfPic { set; get; }
    }
}
