using System;
using System.Collections.Generic;
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
        private NetTcpBinding tcp;
        private string URL;
        public MainWindow()
        {
            InitializeComponent();
            tcp = new NetTcpBinding();
            URL = "net.tcp://localhost:8100/Service";
            serverIntFactory = new ChannelFactory<IServer>(tcp, URL);
            serverInt = serverIntFactory.CreateChannel();
        }

        public void PortButton_Click(object sender, RoutedEventArgs e)
        {
            string port = PortText.Text.ToString();
            if(serverInt.Valid(port))
            {
                Program.Run(port);
            }
        }
    }
}
