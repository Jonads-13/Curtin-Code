namespace WebServer.Models
{
    public class Database
    {
        static List<Client> clients = new();

        public static List<Client> GetClients()
        {
            return clients;
        }

        public static void Delete(Client client)
        {
            clients.Remove(client);
        }

        public static void AddClient(Client client)
        {
            clients.Add(client);
        }

        public static void IncrementNumJobs(string port)
        {
            foreach (Client client in clients)
            {
                if (client.Port == port)
                {
                    client.CompletedJobs++;
                }
            }
        }

        public static bool Valid(string port)
        {
            foreach (Client c in clients)
            {
                if(c.Port == port)
                {
                    return false;
                }
            }
            return true;
        }
    }
}
