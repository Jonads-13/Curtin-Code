using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using RestSharp;
using Newtonsoft.Json;
using Website.Models;

namespace Website.Controllers
{
    [Route("api/[controller]")]
    public class GetController : Controller
    {
        [HttpGet]
        public IActionResult Get()
        {
            DashboardModel model = new DashboardModel();
            RestClient client = new RestClient("http://localhost:5280");
            RestRequest request = new RestRequest("/api/Client");
            RestResponse response = client.Get(request);

            model.Clients = JsonConvert.DeserializeObject<List<Client>>(response.Content);

            return PartialView("Dashboard", model);
        }
    }
}
