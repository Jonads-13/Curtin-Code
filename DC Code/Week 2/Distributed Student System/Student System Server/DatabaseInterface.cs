using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ServiceModel;
using System.Runtime.Serialization;

namespace Student_System_Server
{
    [ServiceContract]
    public interface DatabaseInterface
    {
        [OperationContract]
        int GetNumEntries();

        [OperationContract]
        [FaultContract(typeof(IndexError))]
        void GetValuesForEntry(int index, out uint accNo, out uint pin, out int bal, out string fname, out string lname);
    }

    [DataContract]
    public class IndexError
    {
        [DataMember]
        public string message { set; get; }

        public IndexError(string message)
        {
            this.message = message;
        }
    }

}
