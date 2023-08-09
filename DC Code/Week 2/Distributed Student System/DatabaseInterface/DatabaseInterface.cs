using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using System.Runtime.Serialization;
using System.Windows.Media.Imaging;

namespace DBInterface
{
    [ServiceContract]
    public interface DatabaseInterface
    {
        [OperationContract]
        int GetNumEntries();

        [OperationContract]
        [FaultContract(typeof(IndexError))]
        void GetValuesForEntry(int index, out uint accNo, out uint pin, out int bal, out string fname, out string lname, out byte[] pp);
    }
}
