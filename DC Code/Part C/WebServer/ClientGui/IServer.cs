using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace ClientGui
{
    [ServiceContract]
    public interface IServer
    { 
        [OperationContract]
        Job GetNextJob();

        [OperationContract]
        void AddJob(Job j);

        [OperationContract]
        void AddResult(string r);

        [OperationContract]
        List<string> GetResults();

        [OperationContract]
        void UpdateJob(int id);

        [OperationContract]
        string CompleteJob(string pythonCode, byte[] hash);

        [OperationContract]
        string Decode(string data);

    }
}
