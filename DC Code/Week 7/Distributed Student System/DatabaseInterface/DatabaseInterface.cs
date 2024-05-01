
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using System.Runtime.Serialization;
using System.Windows.Media.Imaging;

using DatabaseLib;

namespace DBInterface
{
    [ServiceContract]
    public interface DatabaseInterface
    {
        [OperationContract]
        int GetNumEntries();

        [OperationContract]
        [FaultContract(typeof(IndexError))]
        DataStruct GetEntryIndex(int index);

        [OperationContract]
        DataStruct GetEntryString(string query);
    }
}
