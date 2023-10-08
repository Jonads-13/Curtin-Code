using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Threading;

namespace Web_API_Data_Server.models.Account
{
    public class DatabaseClass
    {
        static List<Customer> data = new List<Customer>();
        static DatabaseGenerator dbg = new DatabaseGenerator();

        public static void Generate()
        {
            if (data.Count == 0)
            {
                for (int i = 0; i < 10000; i++)
                {
                    dbg.GetNextAccount(out string tempFirst, out string tempLast, out uint tempPin, out uint tempAccNo, out int tempBalance, out byte[] tempPic);
                    data.Add(new Customer(tempAccNo, tempPin, tempBalance, tempFirst, tempLast, tempPic));
                }
            }
        }

        public static Customer? Search(string query)
        {
            int i = 0;
            Thread.Sleep(5000);
            while(i < data.Count)
            { 
                if (query.Equals(data[i].Lname))
                {
                    return data[i];
                }
                i++;
            }

            return null;
        }

        public static Customer? GetCustomerByIndex(int i)
        {
            if((i > 0 ) && (i < data.Count))
            {
                return data[i];
            }
            else { return null; }

        }

        public static uint GetAccNoByIndex(int i) 
        {
            return data[i].Acct;
        }

        public static uint GetPINByIndex(int i)
        {
            return data[i].Pin;
        }

        public static int GetBalanceByIndex(int i) 
        {
            return data[i].Bal;
        }

        public static string GetFirstNameByIndex(int i)
        { 
            return data[i].Fname; 
        }

        public static string GetLastNameByIndex(int i)
        {
            return data[i].Lname;
        }

        public static byte[] GetPPByIndex(int i)
        {
            return data[i].ProfPic;
        }

        public static int GetNumRecords()
        {
            return data.Count;
        }

        public static List<Customer> GetCustomers()
        {
            return data;
        }

        public static int GetNumCustomers()
        {
            return data.Count;
        }

        public static void AddCustomer(Customer customer)
        {
            data.Add(customer);
        }
    }
}
