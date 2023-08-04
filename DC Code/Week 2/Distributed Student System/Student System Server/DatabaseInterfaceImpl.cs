using StudentDLL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;
using DatabaseLib;


namespace Student_System_Server
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

        public void GetValuesForEntry(int index, out uint accNo, out uint pin, out int bal, out string fname, out string lname)
        {
            Console.WriteLine("Aquiring next item for display");

            try
            {
                accNo = db.GetAccNoByIndex(index);
                pin = db.GetPINByIndex(index);
                bal = db.GetBalanceByIndex(index);
                fname = db.GetFirstNameByIndex(index);
                lname = db.GetLastNameByIndex(index);
            }
            catch (ArgumentOutOfRangeException)
            {
                throw new FaultException<IndexError>(new IndexError("Index out of Bounds"));
            }
        }
    }
}

