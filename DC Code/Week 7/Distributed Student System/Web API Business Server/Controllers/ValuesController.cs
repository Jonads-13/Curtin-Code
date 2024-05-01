using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using RestSharp;

namespace Web_API_Business_Server.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        public ActionResult Details()
        {
            RestClient client = new("http://localhost:5222");
            RestRequest request = new("api/database/details");
            RestResponse response = client.Get(request);
            string numEntries = response.Content.ToString();
            return Ok(numEntries);
        }
    }
}
