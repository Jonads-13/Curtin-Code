namespace WebServer.Models
{
    public class Database
    {
        static List<Client> clients = new();

        public static List<Client> GetClients()
        {
            return clients;
        }

        public static void AddClient(Client client)
        {
            clients.Add(client);
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
