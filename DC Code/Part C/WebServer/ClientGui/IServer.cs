﻿using System;
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
        void Post(Client c);

        [OperationContract]
        List<Client> Get();

        [OperationContract]
        bool Valid(string port);
    }
}
