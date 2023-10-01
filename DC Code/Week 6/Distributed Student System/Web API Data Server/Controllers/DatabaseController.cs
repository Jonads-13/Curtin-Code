using APIClasses;
using Microsoft.AspNetCore.Mvc;
using Web_API_Data_Server.models.Account;

namespace Web_API_Data_Server.Controllers
{
    public class DatabaseController : Controller
    {
        [HttpGet]
        public int Details()
        {
            Console.WriteLine("Returning number of customers");
            return DatabaseClass.GetNumCustomers();
        }

        [HttpGet]
        public ActionResult Detail(int id) 
        {
            Customer? customer = DatabaseClass.GetCustomerByIndex(id);
            if (customer == null)
            {
                Console.WriteLine("id was invalid");
                throw new HttpRequestException();
            }
            else
            {
                Console.WriteLine("id was valid");
                return new ObjectResult(customer)
                {
                    StatusCode = 200
                };
            }
        }

        [HttpPost]
        public ActionResult Search([FromBody] SearchData query)
        {
            Customer? customer = DatabaseClass.Search(query.SearchStr);
            if (customer == null)
            {
                Console.WriteLine("customer was not found with specified last name");
                return NotFound();
            }
            else
            {
                Console.WriteLine("customer was found");
                return Ok(customer);
            }
        }

        [HttpPost]
        public IActionResult Add([FromBody] Customer customer)
        {
            DatabaseClass.AddCustomer(customer);

            var response = new { Message = "Customer Created Successfully" };
            return new ObjectResult(response)
            {
                StatusCode = 200,
                ContentTypes = { "application/json" }
            };
        }
    }
}


