using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebServer.Models;

namespace WebServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClientController : ControllerBase
    {
        [HttpGet]
        public IActionResult Get()
        {
            return Ok(Database.GetClients());
        }

        [HttpPost]
        public IActionResult Post([FromBody] Client client)
        {
            Database.AddClient(client);
            return Ok();
        }

        [HttpGet]
        public IActionResult Valid(string port) 
        {
            if(Database.Valid(port))
            {
                return Ok();
            }
            return NotFound();
        }
    }
}
