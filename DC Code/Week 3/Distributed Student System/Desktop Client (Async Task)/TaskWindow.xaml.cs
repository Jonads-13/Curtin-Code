
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

using System.ServiceModel;
using System.IO;
using BusinessLayer;
using DBInterface;
using DatabaseLib;
using System.Runtime.Remoting.Messaging;
using System.Threading;

namespace TaskClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    /// 

    public partial class TaskWindow : Window
    {
        private BusinessServerInterface bsInt;
        private string query;
        public TaskWindow()
        {
            InitializeComponent();

            ChannelFactory<BusinessServerInterface> foobFactory;
            NetTcpBinding tcp = new NetTcpBinding();
            //Set the URL and create the connection!
            string URL = "net.tcp://localhost:8101/BusinessService";
            foobFactory = new ChannelFactory<BusinessServerInterface>(tcp, URL);
            bsInt = foobFactory.CreateChannel();
            //Also, tell me how many entries are in the DB.
            TotalNum.Text = bsInt.GetNumEntries().ToString();
        }

        private void SearchIndexButton_Click(object sender, RoutedEventArgs e)
        {
            BitmapImage pic;

            if (int.TryParse(ItemIndex.Text, out int index))
            {
                try
                {
                    DataStruct temp = bsInt.GetEntryFromIndex(index - 1);
                    FirstName.Text = temp.firstName;
                    LastName.Text = temp.lastName;
                    AccNo.Text = temp.accNo.ToString();
                    Pin.Text = temp.pin.ToString();
                    Balance.Text = temp.balance.ToString();
                    pic = DecodeImage(temp.profilePicture);
                    ProfilePicture.Source = pic;
                }
                catch (FaultException<IndexError> ie)
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
                Pin.Text = "Other";
                Balance.Text = "Search Box";
                pic = null;
                ProfilePicture.Source = pic;
            }
        }

        private async void SearchNameButton_Click(object sender, EventArgs e)
        {
            query = ItemName.Text;
            DataStruct customer;
            Task<DataStruct> task = new Task<DataStruct>(SearchDB);
            task.Start();

            ProgressBar.IsIndeterminate = true;
            if (await Task.WhenAny(task, Task.Delay(10000)) == task)
            {
                customer = task.Result;
            }
            else 
            {
                customer = new DataStruct(0, 0, 0, "Something went wrong", "Server timed out", null);
            }
            UpdateGui(customer);
            ProgressBar.IsIndeterminate = false;
        }

        private DataStruct SearchDB()
        {
            
            if (!int.TryParse(query, out int index))
            {
                if (NonSymbol(query))
                {
                    return bsInt.GetEntryFromString(query);
                }
                else { return new DataStruct(0,0,0,"No Symbols", "", null); }
            }
            else
            {
                return new DataStruct(0, 0, 0, "Invalid Search", "", null);
            }
        }

        private void UpdateGui(DataStruct customer)
        {
            FirstName.Text = customer.firstName;
            LastName.Text = customer.lastName;
            AccNo.Text = customer.accNo.ToString();
            Pin.Text = customer.pin.ToString();
            Balance.Text = customer.balance.ToString();
            ProfilePicture.Source = DecodeImage(customer.profilePicture);
            ProgressBar.IsIndeterminate = false;
            ItemIndex.IsReadOnly = false;
            ItemName.IsReadOnly = false;
            SearchIndexButton.IsEnabled = true;
            SearchNameButton.IsEnabled = true;
        }

        public BitmapImage DecodeImage(byte[] picture)
        {
            if (picture == null)
            {
                return null;
            }
            else
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

        // Checks ascii value and makes sure the character is from the alphabet
        private bool NonSymbol(string query)
        {
            for(int i = 0; i < query.Length; i++)
            {
                int asciiValue = (int)query[i];

                if((asciiValue < 65) || (asciiValue > 90))
                {
                    if((asciiValue < 97) ||  (asciiValue > 122))
                    {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
