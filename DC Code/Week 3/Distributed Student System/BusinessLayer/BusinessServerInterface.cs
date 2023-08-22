using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using DBInterface;
using DatabaseLib;
using System.Net.NetworkInformation;
using System.Reflection;
using System.Xml.Linq;

namespace BusinessLayer
{
    [ServiceContract]
    public interface BusinessServerInterface
    {
        [OperationContract]
        int GetNumEntries();

        [OperationContract]
        [FaultContract(typeof(IndexError))]
        DataStruct GetEntryFromIndex(int index);
        [OperationContract]
        DataStruct GetEntryFromString(string query);
    }
}

