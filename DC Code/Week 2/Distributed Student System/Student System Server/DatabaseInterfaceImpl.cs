using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using DatabaseLib;
using DBInterface;

namespace Bank_Server
{
    [ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Multiple, UseSynchronizationContext = false)]
    public class DatabaseInterfaceImpl : DatabaseInterface
    {
        private DatabaseClass db;
        public DatabaseInterfaceImpl()
        {
            db = new DatabaseClass();
        }

        public int GetNumEntries()
        {
            return db.GetNumRecords();
        }

        public void GetValuesForEntry(int index, out uint accNo, out uint pin, out int bal, out string fname, out string lname, out byte[] pp)
        {
            Console.WriteLine("Aquiring next item for display");

            try
            {
                accNo = db.GetAccNoByIndex(index);
                pin = db.GetPINByIndex(index);
                bal = db.GetBalanceByIndex(index);
                fname = db.GetFirstNameByIndex(index);
                lname = db.GetLastNameByIndex(index);
                pp = db.GetPPByIndex(index);

            }
            catch (ArgumentOutOfRangeException)
            {
                Console.WriteLine("Specified index was out of bounds");
                throw new FaultException<IndexError>(new IndexError(), "Index out of bounds");
            }
        }
    }
}

