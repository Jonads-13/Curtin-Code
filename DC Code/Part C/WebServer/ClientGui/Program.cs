using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace ClientGui
{
    internal class Program
    {
        public static async void Run(string port)
        {

            Debug.WriteLine("Server Booting up");
            //This is the actual host service system
            ServiceHost host;
            //This represents a tcp/ip binding in the Windows network stack
            NetTcpBinding tcp = new NetTcpBinding();
            //Bind server to the implementation of DataServer
            host = new ServiceHost(typeof(Server));
            /*Present the publicly accessible interface to the client. 0.0.0.0 tells .net to
            accept on any interface. :8100 means this will use port 8100. DataService is a name for the
            actual service, this can be any string.*/

            host.AddServiceEndpoint(typeof(IServer), tcp, "net.tcp://localhost:" + port + "/JobService");
            //And open the host for business!
            host.Open();
            Debug.WriteLine("System Online");

            while (Server.run){ await Task.Delay(500); }

            //Don't forget to close the host after you're done!
            host.Close();
        }
    }
}
