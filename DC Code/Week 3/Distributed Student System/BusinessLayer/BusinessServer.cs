using System;
using System.CodeDom;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

using DBInterface;
using DatabaseLib;
using System.Runtime.CompilerServices;

namespace BusinessLayer
{
    [ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Multiple, UseSynchronizationContext = false)]
    public class BusinessServer : BusinessServerInterface
    {
        private DatabaseInterface DBInt;
        private ChannelFactory<DatabaseInterface> DBIntFactory;
        private NetTcpBinding tcp;
        private string URL;
        private uint LogNumber;

        public BusinessServer() 
        {
            tcp = new NetTcpBinding();
            URL = "net.tcp://localhost:8100/DataService";
            DBIntFactory = new ChannelFactory<DatabaseInterface>(tcp, URL);
            DBInt = DBIntFactory.CreateChannel();
            LogNumber = 0;
        }

        public int GetNumEntries()
        {
            int total = DBInt.GetNumEntries();
            Log("Sending the number of entries in the database. Number returned is: " + total);
            return total;
        }

        public DataStruct GetEntryFromIndex(int index) 
        {
            Log("Attempting to find a customer from index: " + index);
            try
            {
                DataStruct temp = DBInt.GetEntryIndex(index);
                Log("Sending customer found at index: " + index + " to the client");
                return temp;
            }
            catch (FaultException<IndexError> ie)
            {
                Log("Failed to find a customer: " + ie.Message);
                throw new FaultException<IndexError>(new IndexError(), "Index Out of Bounds");
            }
        }

        public DataStruct GetEntryFromString(string query)
        {
            try
            {
                DataStruct temp = DBInt.GetEntryString(query);
                Log("Attempting to find customer with last name: " + query);
                if (temp.firstName.Equals("No Match Found"))
                {
                    Log("Failed to find customer with last name: " + query);
                }
                else
                {
                    Log("Returning customer with last name: " + query);
                }
                return temp;
            }
            catch(TimeoutException te)
            {
                Log("Server timeout trying to find customer: " + query);
                throw te;
            }
        }

        [MethodImpl(MethodImplOptions.Synchronized)]
        private void Log(string logString)
        {
            LogNumber++;
            Console.WriteLine(LogNumber + ": " + logString);
        }
    }
}
