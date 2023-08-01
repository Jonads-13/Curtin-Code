using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DatabaseLib
{
    internal class DatabaseGenerator
    {
        private Random rand = new Random();
        private string[] FirstNames = { "Jacob", "Nick", "Justin", "Tyler", "Christian", "Jarrid", "Caroline", "Stanley", "Tri", "Jordan", "Joleen", "Bec", "Clarissa", "Lauren", "Sam", "Oliver", "Wari" };
        private string[] LastNames = { "Jonas", "Wright", "Pan", "Milbourne", "Dao", "Twomney", "Lee", "Smith", "Tan", "Johnson", "Jones", "Lambie", "Cooper", "Herman", "Pang", "Gregorovich", "Dumbledore"};

        public DatabaseGenerator() { }
        public void GetNextAccount(out string firstName, out string lastName, out uint pin, out uint accno, out int balance)
        {
            firstName = GetFirstName();
            lastName = GetLastName();
            pin = GetPin();
            accno = GetAccNo(); 
            balance = GetBalance();
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
            return (uint)rand.Next(-1,10000);
        }

        private uint GetAccNo()
        {
            return (uint)rand.Next(-1,10000000);
        }

        private int GetBalance()
        {
            return rand.Next(-100000, 100000);
        }
    }
}
