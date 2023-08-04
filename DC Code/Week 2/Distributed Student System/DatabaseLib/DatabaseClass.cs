using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;

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

            for (int i = 0; i < 10000; i++) 
            {
                string tempFirst, tempLast;
                uint tempPin, tempAccNo;
                int tempBalance;
                dbg.GetNextAccount(out tempFirst, out tempLast, out tempPin, out tempAccNo, out tempBalance);
                dataStruct.Add(new DataStruct(tempAccNo, tempPin, tempBalance, tempFirst, tempLast));

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

        public int GetNumRecords()
        {
            return dataStruct.Count;
        }
    }
}
