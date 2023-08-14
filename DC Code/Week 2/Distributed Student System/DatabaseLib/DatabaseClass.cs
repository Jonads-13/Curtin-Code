using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Windows.Media.Imaging;

namespace DatabaseLib
{
    public class DatabaseClass
    {
        List<DataStruct> dataStruct;
        DatabaseGenerator dbg;

        public DatabaseClass() 
        {
            dataStruct = new List<DataStruct>();
            dbg = new DatabaseGenerator();

            for (int i = 0; i < 100; i++) 
            {
                string tempFirst, tempLast;
                uint tempPin, tempAccNo;
                int tempBalance;
                byte[] tempPP;
                dbg.GetNextAccount(out tempFirst, out tempLast, out tempPin, out tempAccNo, out tempBalance, out tempPP);
                dataStruct.Add(new DataStruct(tempAccNo, tempPin, tempBalance, tempFirst, tempLast, tempPP));

            }
            Console.WriteLine("Created Database");
        }

        public uint GetAccNoByIndex(int i) 
        {
            return dataStruct[i].accNo;
        }

        public uint GetPINByIndex(int i)
        {
            return dataStruct[i].pin;
        }

        public int GetBalanceByIndex(int i) 
        {
            return dataStruct[i].balance;
        }

        public string GetFirstNameByIndex(int i)
        { 
            return dataStruct[i].firstName; 
        }

        public string GetLastNameByIndex(int i)
        {
            return dataStruct[i].lastName;
        }

        public byte[] GetPPByIndex(int i)
        {
            return dataStruct[i].profilePicture;
        }

        public int GetNumRecords()
        {
            return dataStruct.Count;
        }
    }
}
