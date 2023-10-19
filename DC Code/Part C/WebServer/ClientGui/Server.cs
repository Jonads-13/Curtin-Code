using Newtonsoft.Json;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace ClientGui
{
    [ServiceBehavior(ConcurrencyMode = ConcurrencyMode.Multiple, UseSynchronizationContext = false)]
    public class Server : IServer
    {
        public Server() 
        {
            
        }

        public void Post(Client c)
        {
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");
            request.AddJsonBody(c);

            RestResponse response = client.Post(request);
        }

        public List<Client> Get()
        {
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");

            RestResponse response = client.Get(request);

            return JsonConvert.DeserializeObject<List<Client>>(response.Content);
        }

        public bool Valid(string port)
        {
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");
            request.AddQueryParameter("port", port);

            RestResponse response = client.Get(request);

            return response.IsSuccessful;
        }
    }
}
