using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace DBInterface
{
    [DataContract]
    public class IndexError
    {
        [DataMember]
        public string Message {set; get;}

        [DataMember]
        public string Reason {set; get;}

        public IndexError() { }
    }
}
