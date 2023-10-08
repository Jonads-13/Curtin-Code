
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
using System.Runtime.Remoting.Messaging;
using System.Threading;
using RestSharp;
using Newtonsoft.Json;
using System.Diagnostics;
using APIClasses;
using System.Net.Http;

namespace TaskClient
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    /// 

    public partial class TaskWindow : Window
    {
        private SearchData query;
        private RestClient client;
        public TaskWindow()
        {
            InitializeComponent();
            client = new RestClient("http://localhost:5059");

            // Getmhow many entries are in the daabase
            RestRequest request = new RestRequest("/api/values");
            RestResponse response = client.Get(request);

            TotalNum.Text = response.Content.ToString();
        }

        private void SearchIndexButton_Click(object sender, RoutedEventArgs e)
        {
            if (int.TryParse(ItemIndex.Text, out int index))
            {
                try
                {
                    RestRequest request = new RestRequest("/api/getvalues/" + index.ToString());
                    RestResponse response = client.Get(request);

                    DataIntermed customer = JsonConvert.DeserializeObject<DataIntermed>(response.Content);

                    UpdateGui(customer);
                }
                catch (HttpRequestException)
                {
                    UpdateGui(new DataIntermed()
                    {
                        Acct = 0, Pin = 0, Bal = 0,
                        Fname = "Specified index", Lname = "Was Invalid",
                        ProfPic = null
                    });
                }
            }
            else
            {
                UpdateGui(new DataIntermed()
                {
                    Acct = 0, Pin = 0, Bal = 0,
                    Fname = "Invalid Search Type", Lname = "Use Other Box",
                    ProfPic = null
                });
            }
        }

        private async void SearchNameButton_Click(object sender, EventArgs e)
        {
            query = new SearchData
            {
                SearchStr = ItemName.Text
            };
            DataIntermed customer;
            Task<DataIntermed> task = new Task<DataIntermed>(SearchDB);

            ProgressBar.IsIndeterminate = true;
            task.Start();
            if (await Task.WhenAny(task, Task.Delay(10000)) == task)
            {
                customer = task.Result;
            }
            else 
            {
                customer = new DataIntermed() 
                { 
                    Acct = 0, Pin = 0, Bal = 0, 
                    Fname = "Something went wrong", Lname = "Server timed out",
                    ProfPic = null
                };
            }
            UpdateGui(customer);
            ProgressBar.IsIndeterminate = false;
        }

        private DataIntermed SearchDB()
        {
            if (!int.TryParse(query.SearchStr, out int index))
            {
                if (NonSymbol(query.SearchStr))
                {
                    RestRequest request = new RestRequest("api/search/");
                    request.AddJsonBody(query);
                    RestResponse response = client.Post(request);
                    if(response.IsSuccessful)
                    {
                        return JsonConvert.DeserializeObject<DataIntermed>(response.Content);
                    }
                    else
                    {
                        return new DataIntermed()
                        {
                            Acct = 0, Pin = 0, Bal = 0,
                            Fname = "No Match Found", Lname = "Try Another Name",
                            ProfPic = null
                        };
                    }
                }
                else 
                { 
                    return new DataIntermed() 
                    { 
                        Acct = 0, Pin = 0, Bal = 0, 
                        Fname = "No Symbols", Lname = "",
                        ProfPic = null
                    }; 
                }
            }
            else
            {
                return new DataIntermed() 
                { 
                    Acct = 0, Pin = 0, Bal = 0, 
                    Fname = "Invalid Search", Lname = "",
                    ProfPic = null
                };
            }
        }

        private void UpdateGui(DataIntermed customer)
        {
            FirstName.Text = customer.Fname;
            LastName.Text = customer.Lname;
            AccNo.Text = customer.Acct.ToString();
            Pin.Text = customer.Pin.ToString();
            Balance.Text = customer.Bal.ToString();
            ProfilePicture.Source = DecodeImage(customer.ProfPic);
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
                int asciiValue = query[i];

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
