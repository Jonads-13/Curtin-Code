
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;

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
using System.Xml.Linq;

using DBInterface;
using System.ServiceModel;
using System.IO;

namespace DesktopClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private DatabaseInterface foob;
        public MainWindow()
        {
            InitializeComponent();

            ChannelFactory<DatabaseInterface> foobFactory;
            NetTcpBinding tcp = new NetTcpBinding();
            //Set the URL and create the connection!
            string URL = "net.tcp://localhost:8100/StudentService";
            foobFactory = new ChannelFactory<DatabaseInterface>(tcp, URL);
            foob = foobFactory.CreateChannel();
            //Also, tell me how many entries are in the DB.
            TotalNum.Text = foob.GetNumEntries().ToString();
        }

        private void SearchButton_Click(object sender, RoutedEventArgs e)
        {
            string fname = null, lname = null;
            int bal = 0, index;
            uint accNo = 0, pin = 0;
            byte[] picture;
            BitmapImage pp;

            if(int.TryParse(ItemIndex.Text, out index))
            {
                try
                {
                    foob.GetValuesForEntry(index-1, out accNo, out pin, out bal, out fname, out lname, out picture);
                    FirstName.Text = fname;
                    LastName.Text = lname;
                    AccNo.Text = accNo.ToString();
                    Pin.Text = pin.ToString();
                    Balance.Text = bal.ToString();
                    pp = DecodeImage(picture);
                    ProfilePicture.Source = pp;
                }
                catch(FaultException<IndexError> ie)
                {
                    FirstName.Text = ie.Message;
                    LastName.Text = "";
                    AccNo.Text = "";
                    Pin.Text = "";
                    Balance.Text = "";
                    ProfilePicture.Source = null;
                }
            }
            else
            {
                FirstName.Text = "Invalid";
                LastName.Text = "Search";
                AccNo.Text = "Use";
                Pin.Text = "Numbers";
                Balance.Text = "Please";
                ProfilePicture.Source = null;
            }
        }

        public BitmapImage DecodeImage(byte[] picture)
        {
            BitmapImage image = new BitmapImage();

            using (MemoryStream memoryStream = new MemoryStream(picture))
            {
                image.BeginInit();
                image.CacheOption = BitmapCacheOption.OnLoad;
                image.StreamSource = memoryStream;
                image.EndInit();
            }
            return image;
        }

    }
}
