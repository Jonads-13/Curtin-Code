using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Windows.Media.Imaging;

namespace DatabaseLib
{
    public class DatabaseClass
    {
        DataStruct[] data;
        DatabaseGenerator dbg;

        public DatabaseClass() 
        {
            data = new DataStruct[100000];
            dbg = new DatabaseGenerator();

            for (int i = 0; i < data.Length; i++) 
            {
                string tempFirst, tempLast;
                uint tempPin, tempAccNo;
                int tempBalance;
                byte[] tempPP;
                dbg.GetNextAccount(out tempFirst, out tempLast, out tempPin, out tempAccNo, out tempBalance, out tempPP);
                data[i] = new DataStruct(tempAccNo, tempPin, tempBalance, tempFirst, tempLast, tempPP);
            }

            Console.WriteLine("Created Database");
        }

        public bool Search(string query, out int index)
        {
            int i = 0;
            index = -1;
            while(i < data.Length)
            { 
                if (query.Equals(data[i].lastName))
                {
                    index = i;
                    return true;
                }
                i++;
            }

            return false;
        }

        public DataStruct GetCustomerByIndex(int i)
        {
            return data[i];
        }

        public uint GetAccNoByIndex(int i) 
        {
            return data[i].accNo;
        }

        public uint GetPINByIndex(int i)
        {
            return data[i].pin;
        }

        public int GetBalanceByIndex(int i) 
        {
            return data[i].balance;
        }

        public string GetFirstNameByIndex(int i)
        { 
            return data[i].firstName; 
        }

        public string GetLastNameByIndex(int i)
        {
            return data[i].lastName;
        }

        public byte[] GetPPByIndex(int i)
        {
            return data[i].profilePicture;
        }

        public int GetNumRecords()
        {
            return data.Length;
        }
    }
}
