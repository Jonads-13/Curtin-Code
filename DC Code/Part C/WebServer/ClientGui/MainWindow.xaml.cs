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

namespace ClientGui
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private IServer serverInt;
        private ChannelFactory<IServer> serverIntFactory;
        private List<IServer> connectedServers;
        private NetTcpBinding tcp;
        private string URL;
        private string port;
        private Random rand;
        private List<Client> connectedClients;
        public MainWindow()
        {
            InitializeComponent();
            rand = new Random();
            connectedClients = new List<Client>();
            connectedServers = new List<IServer>();
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
                    if((!connectedClients.Contains(c)) && (c.Port != port))
                    {
                        ConnectToClient(c);
                    }
                }


                foreach (IServer s in connectedServers)
                {
                    Job tempJob = s.GetNextJob();
                    if (tempJob != null)
                    {
                        string result = serverInt.CompleteJob(tempJob.PythonCode);
                        s.UpdateJob(tempJob.Id);
                        s.AddResult(result);
                    }
                }
            }
        }

        private void ConnectToClient(Client c)
        {
            NetTcpBinding temptcp = new NetTcpBinding();
            string tempURL = "net.tcp://localhost:" + c.Port + "/JobService";
            ChannelFactory<IServer> tempFactory = new ChannelFactory<IServer>(temptcp, tempURL);
            connectedServers.Add(tempFactory.CreateChannel());
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

                Client c = new Client()
                {
                    Host = "localhost",
                    Port = port,
                    CompletedJobs = 0,
                };

                RestClient client = new RestClient("http://localhost:5280");
                RestRequest request = new RestRequest("/api/Client");
                request.AddJsonBody(c);

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
                await Task.Delay(3000);
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

    }
}
