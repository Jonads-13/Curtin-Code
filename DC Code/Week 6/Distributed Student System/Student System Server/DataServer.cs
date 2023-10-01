using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using DatabaseLib;
using DBInterface;

namespace Bank_Server
{
    [ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Multiple, UseSynchronizationContext = false)]
    public class DataServer : DatabaseInterface
    {
        private DatabaseClass db;
        public DataServer()
        {
            db = new DatabaseClass();
        }

        public int GetNumEntries()
        {
            return db.GetNumRecords();
        }

        public DataStruct GetEntryIndex(int index)
        {
            Console.WriteLine("Aquiring next item for display from index search");

            try
            {
                return db.GetCustomerByIndex(index);

            }
            catch (IndexOutOfRangeException)
            {
                Console.WriteLine("Specified index was out of bounds");
                throw new FaultException<IndexError>(new IndexError(), "Index out of bounds");
            }
        }

        public DataStruct GetEntryString(string query)
        {
            Console.WriteLine("Aquiring next item for display from name search");
            return db.Search(query);

        }
    }
}

