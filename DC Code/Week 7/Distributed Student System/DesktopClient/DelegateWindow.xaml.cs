
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
using System.Diagnostics;

namespace DelegateClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    /// 
    public delegate DataStruct Search(string query);

    public partial class DelegateWindow : Window
    {
        private BusinessServerInterface foob;
        private Search search;
        public DelegateWindow()
        {
            InitializeComponent();

            ChannelFactory<BusinessServerInterface> foobFactory;
            NetTcpBinding tcp = new NetTcpBinding();
            //Set the URL and create the connection!
            string URL = "net.tcp://localhost:8101/BusinessService";
            foobFactory = new ChannelFactory<BusinessServerInterface>(tcp, URL);
            foob = foobFactory.CreateChannel();
            //Also, tell me how many entries are in the DB.
            TotalNum.Text = foob.GetNumEntries().ToString();
        }

        private void SearchIndexButton_Click(object sender, RoutedEventArgs e)
        {
            BitmapImage pic;

            if(int.TryParse(ItemIndex.Text, out int index))
            {
                try
                {
                    DataStruct temp = foob.GetEntryFromIndex(index-1);
                    FirstName.Text = temp.firstName;
                    LastName.Text = temp.lastName;
                    AccNo.Text = temp.accNo.ToString();
                    Pin.Text = temp.pin.ToString();
                    Balance.Text = temp.balance.ToString();
                    pic = DecodeImage(temp.profilePicture);
                    ProfilePicture.Source = pic;
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
                Pin.Text = "Other";
                Balance.Text = "Search Box";
                pic = null;
                ProfilePicture.Source = pic;
            }
        }

        private void SearchNameButton_Click(object sender, EventArgs e)
        { 
            ProgressBar.IsIndeterminate = true;
            ItemIndex.IsReadOnly = true;
            ItemName.IsReadOnly = true;
            SearchIndexButton.IsEnabled = false;
            SearchNameButton.IsEnabled = false;
            search = SearchDB;
            AsyncCallback callback = this.OnSearchCompletion;
            IAsyncResult result = search.BeginInvoke(ItemName.Text, callback, null);

        }

        private DataStruct SearchDB(string query)
        {
            if (!int.TryParse(query, out int index))
            {
                if(NonSymbol(query))
                {
                    Task<DataStruct> task = Task.Run(() => {
                        return foob.GetEntryFromString(query);
                    });
                    if (Task.WhenAny(task, Task.Delay(10000)) == task) 
                    {
                        return task.Result; // returns on success
                    }
                    else
                    {
                        return new DataStruct(0, 0, 0, "Something went wrong", "Server timed out", null); // returns on server timeout
                    }
                }
                else // User entered a symbol
                { 
                    return new DataStruct(0, 0, 0, "No Symbols", "", null); 
                }
            }
            else // User entered a number
            { 
                return new DataStruct(0, 0, 0, "No Numbers", "", null); 
            }
        }

        private void OnSearchCompletion(IAsyncResult result)
        {
            DataStruct customer = null;
            Search search = null;
            AsyncResult asyncobj = (AsyncResult)result;
            if (!asyncobj.EndInvokeCalled)
            {
                search = (Search)asyncobj.AsyncDelegate;
                customer = search.EndInvoke(asyncobj);
                UpdateGui(customer);
            }

            asyncobj.AsyncWaitHandle.Close();
        }

        private void UpdateGui(DataStruct customer)
        {
            // Isn't this beautiful and not at all disgusting
            FirstName.Dispatcher.Invoke(new Action(() => FirstName.Text = customer.firstName));
            LastName.Dispatcher.Invoke(new Action(() => LastName.Text = customer.lastName));
            AccNo.Dispatcher.Invoke(new Action(() => AccNo.Text = customer.accNo.ToString()));
            Pin.Dispatcher.Invoke(new Action(() => Pin.Text = customer.pin.ToString()));
            Balance.Dispatcher.Invoke(new Action(() => Balance.Text = customer.balance.ToString()));
            ProfilePicture.Dispatcher.Invoke(new Action(() => ProfilePicture.Source = DecodeImage(customer.profilePicture)));
            ProgressBar.Dispatcher.Invoke(new Action(() => ProgressBar.IsIndeterminate = false));
            ItemIndex.Dispatcher.Invoke(new Action(() => ItemIndex.IsReadOnly = false));
            ItemName.Dispatcher.Invoke(new Action(() => ItemName.IsReadOnly = false));
            SearchIndexButton.Dispatcher.Invoke(new Action(() => SearchIndexButton.IsEnabled = true));
            SearchNameButton.Dispatcher.Invoke(new Action(() => SearchNameButton.IsEnabled = true));
        }

        public BitmapImage DecodeImage(byte[] picture)
        {
            if(picture == null)
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

        private bool NonSymbol(String query)
        {
            for (int i = 0; i < query.Length; i++)
            {
                int asciiValue = (int)query[i];

                if ((asciiValue < 65) || (asciiValue > 90))
                {
                    if ((asciiValue < 97) || (asciiValue > 122))
                    {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
