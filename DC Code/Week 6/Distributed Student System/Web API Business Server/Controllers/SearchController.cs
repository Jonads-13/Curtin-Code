using APIClasses;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using RestSharp;
using Web_API_Business_Server.models;

namespace Web_API_Business_Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SearchController : ControllerBase
    {
        [HttpPost]
        public IActionResult Detail([FromBody] SearchData search)
        {
            RestClient client = new("http://localhost:5222");
            RestRequest request = new("api/database/search");
            request.AddJsonBody(search);
            RestResponse response = client.Post(request);
            if (response.IsSuccessful)
            {
                DataIntermed customer = JsonConvert.DeserializeObject<DataIntermed>(response.Content);
                Console.WriteLine("customer: " + customer.ToString());
                return Ok(customer);
            }
            else
            {
                return NotFound();
            }
        }
    }
}
