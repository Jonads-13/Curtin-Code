﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace BusinessLayer
{
    internal class BusinessProgram
    {
        static void Main(string[] args)
        {

            Console.WriteLine("Business Tier Booting up");
            //This is the actual host service system
            ServiceHost host;
            //This represents a tcp/ip binding in the Windows network stack
            NetTcpBinding tcp = new NetTcpBinding();
            //Bind server to the implementation of DataServer
            host = new ServiceHost(typeof(BusinessServer));
            /*Present the publicly accessible interface to the client. 0.0.0.0 tells .net to
            accept on any interface. :8100 means this will use port 8100. DataService is a name for the
            actual service, this can be any string.*/

            host.AddServiceEndpoint(typeof(BusinessServerInterface), tcp, "net.tcp://0.0.0.0:8101/BusinessService");
            //And open the host for business!
            host.Open();
            Console.WriteLine("System Online");
            Console.ReadLine();
            //Don't forget to close the host after you're done!
            host.Close();
        }
    }
}
