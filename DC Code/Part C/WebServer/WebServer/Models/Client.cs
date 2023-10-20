using System.ServiceModel;

namespace WebServer.Models
{
    public class Client
    {
        public string Port { get; set; }
        public string Host { get; set; }
        public int CompletedJobs { get; set; }
    }
}
