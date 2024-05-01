using System.ServiceModel;

namespace WebServer.Models
{
    public class Client
    {
        public string Port { get; set; }
        public string Host { get; set; }
        public int CompletedJobs { get; set; }

        public override bool Equals(System.Object obj)
        {
            // If parameter is null return false.
            if (obj == null)
            {
                return false;
            }

            // If parameter cannot be cast to Point return false.
            Client p = obj as Client;
            if ((System.Object)p == null)
            {
                return false;
            }

            // Return true if the fields match:
            return (Port == p.Port) && (Host == p.Host);
        }

        public override int GetHashCode()
        {
            unchecked // Overflow is fine, just wrap
            {
                int hash = 17;
                // Suitable nullity checks etc, of course :)
                hash = hash * 23 + Port.GetHashCode();
                hash = hash * 23 + Host.GetHashCode();;
                return hash;
            }
        }
    }
}
