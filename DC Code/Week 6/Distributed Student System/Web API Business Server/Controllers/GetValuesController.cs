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
            RestResponse response = client.Get(request);
            if (response.IsSuccessful)
            {
                DataIntermed customer = JsonConvert.DeserializeObject<DataIntermed>(response.Content);
                Console.WriteLine("customer: " + customer.ToString());
                return Ok(customer);
            }
            else 
            {
                RequestException re = JsonConvert.DeserializeObject<RequestException>(response.Content);
                Console.WriteLine(re.Message + ": " + re.StatusCode);
                return BadRequest();
            }
        }
    }
}
