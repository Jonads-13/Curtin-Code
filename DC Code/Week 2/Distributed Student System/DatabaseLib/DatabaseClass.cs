using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;

namespace DatabaseLib
{
    public class DatabaseClass
    {
        List<DataStruct> dataStruct;

        public DatabaseClass() 
        {
            dataStruct = new List<DatabaseLib.DataStruct>();
            for (int i = 0; i < 1000000; i++) 
            {
                string tempFirst, tempLast;
                uint tempPin, tempAccNo;
                int tempBalance;
                DatabaseGenerator dbg = new DatabaseGenerator();
                dbg.GetNextAccount(out tempFirst, out tempLast, out tempPin, out tempAccNo, out tempBalance);
                
                dataStruct.Add(new DataStruct(tempAccNo, tempPin, tempBalance, tempFirst, tempLast));
            }
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
