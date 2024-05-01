using APIClasses;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Web_API_Business_Server.models;
using Newtonsoft.Json;
using RestSharp;
using System.Diagnostics;

namespace Web_API_Business_Server.Controllers
{
    [Route("api/[controller]/{id}")]
    [ApiController]
    public class GetValuesController : ControllerBase
    {
        [HttpGet]
        public IActionResult Detail(int id)
        {
            RestClient client = new("http://localhost:5222");
            RestRequest request = new("api/database/detail/" + id.ToString());
            try
            {
                RestResponse response = client.Get(request);
                Customer customer = JsonConvert.DeserializeObject<Customer>(response.Content);
                Console.WriteLine("customer: " + customer.ToString());
                DataIntermed temp = new()
                {
                    Acct = customer.AccNo,
                    Bal = customer.Balance,
                    Pin = customer.Pin,
                    Fname = customer.FirstName,
                    Lname = customer.LastName,
                    ProfPic = customer.ProfilePicture
                };
                return Ok(temp);
            }
            catch(HttpRequestException hre)
            {
                Console.WriteLine(hre.Message + ": " + hre.StackTrace);
                throw hre;
            }
        }
    }
}
