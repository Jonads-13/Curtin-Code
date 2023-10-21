using Newtonsoft.Json;
using RestSharp;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Security.Cryptography;

namespace ClientGui
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private IServer serverInt;
        private ChannelFactory<IServer> serverIntFactory;
        private Dictionary<string, IServer> connectedServers;
        private NetTcpBinding tcp;
        private string URL;
        private string port;
        private Random rand;
        private List<Client> connectedClients;
        private Client current;
        public MainWindow()
        {
            InitializeComponent();
            rand = new Random();
            connectedClients = new List<Client>();
            connectedServers = new Dictionary<string, IServer>();
            PythonButton.IsEnabled = false;
        }


        private async void NetworkThread()
        {
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");

            while(true)
            {
                await Task.Delay(2000);
                RestResponse response = client.Get(request);

                List<Client> clients = JsonConvert.DeserializeObject<List<Client>>(response.Content);

                foreach (Client c in clients)
                {
                    if ((!connectedClients.Contains(c)) && (c.Port != port))
                    {
                        Debug.WriteLine("port not already connected to: " + c.Port);
                        ConnectToClient(c);
                    }
                }

                List<Client> toRemove = new List<Client>();
                foreach (Client conClient in connectedClients)
                {
                    if(!clients.Contains(conClient))
                    {
                        Debug.WriteLine("should never get here: " + current.Port);
                        toRemove.Add(conClient);
                    }
                }

                foreach (Client rm in toRemove)
                {
                    connectedClients.Remove(rm);
                }

                foreach (Client rm in toRemove)
                {
                    connectedServers.Remove(rm.Port);
                }


                foreach (IServer s in connectedServers.Values)
                {
                    Job tempJob = s.GetNextJob();

                    if (tempJob != null)
                    {
                        SHA256 sha256Hash = SHA256.Create();
                        byte[] hash = sha256Hash.ComputeHash(Encoding.UTF8.GetBytes(tempJob.PythonCode));
                        Dispatcher.Invoke(() => { ProgressBar.Visibility = Visibility.Visible; });
                        await Task.Delay(1500);
                        string result = serverInt.CompleteJob(tempJob.PythonCode, hash);
                        Dispatcher.Invoke(() => { ProgressBar.Visibility = Visibility.Hidden; });
                        if (result != null)
                        {
                            s.AddResult(result);
                            IncrementNumJobs();
                        }
                        else
                        {
                            s.AddJob(tempJob);
                        }
                    }
                }
            }
        }

        private void ConnectToClient(Client c)
        {
            Debug.WriteLine(current.Port + " connecting to: " + c.Port);
            NetTcpBinding temptcp = new NetTcpBinding();
            string tempURL = "net.tcp://localhost:" + c.Port + "/JobService";
            ChannelFactory<IServer> tempFactory = new ChannelFactory<IServer>(temptcp, tempURL);
            connectedServers.Add(c.Port, tempFactory.CreateChannel());
            connectedClients.Add(c);
        }







        public void PortButton_Click(object sender, RoutedEventArgs e)
        {
            port = PortText.Text.ToString();
            if (Valid(port))
            {
                Program.Run(port); // server thread
                tcp = new NetTcpBinding();
                URL = "net.tcp://localhost:" + port+ "/JobService"; // connect to own server to add jobs and get results
                serverIntFactory = new ChannelFactory<IServer>(tcp, URL);
                serverInt = serverIntFactory.CreateChannel();

                current = new Client()
                {
                    Host = "localhost",
                    Port = port,
                    CompletedJobs = 0,
                };

                RestClient client = new RestClient("http://localhost:5280");
                RestRequest request = new RestRequest("/api/Client");
                request.AddJsonBody(current);

                RestResponse response = client.Post(request);
                PortButton.Visibility = Visibility.Hidden;
                Portlabel.Visibility = Visibility.Hidden;
                PortText.Visibility = Visibility.Hidden;
                Error.Visibility = Visibility.Hidden;
                PythonButton.IsEnabled = true;
                NetworkThread();
                ResultsThread();
            }
            else
            {
                Error.Visibility = Visibility.Visible;
            }
        }


        public void PythonButton_Click(Object sender, RoutedEventArgs e)
        {
            string code = PythonInput.Text.ToString();
            string pythonCode = Encode(code);
            Job newJob = new Job()
            {
                Id = rand.Next(10000000),
                ClientPort = port,
                PythonCode = pythonCode,
            };

            serverInt.AddJob(newJob);

        }



        private bool Valid(string port)
        {
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");
            request.AddQueryParameter("port", port);

            RestResponse response = client.Put(request);

            return response.IsSuccessful;
        }




        private async void ResultsThread()
        {
            while(true)
            {
                List<string> results = serverInt.GetResults();
                await Task.Delay(2000);
                Dispatcher.Invoke(() => { FillResults(results); });
            }
        }


        private void FillResults(List<string> results)
        {
            StringBuilder sb = new StringBuilder();
            foreach (string messageEntry in results)
            {
                sb.AppendLine(messageEntry);
            }
            Results.Text = "";
            Results.Text = sb.ToString();
        }



        private string Encode(string data) // Base 64 Encoding
        {
            if (string.IsNullOrEmpty(data))
            {
                return data;
            }

            byte[] textBytes = Encoding.UTF8.GetBytes(data);

            return Convert.ToBase64String(textBytes);
        }


        protected override void OnClosed(EventArgs e)
        {
            base.OnClosed(e);
            DeleteClient();
        }


        private void DeleteClient()
        {
            Debug.WriteLine("This called?");
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");
            request.AddJsonBody(current);
            RestResponse response = client.Delete(request);
        }

        private void IncrementNumJobs()
        {
            Debug.WriteLine("It's this path isn't it");
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client/Jobs");
            request.AddQueryParameter("port", port);
            request.AddJsonBody(current);
            RestResponse response = client.Put(request);
        }

    }
}
