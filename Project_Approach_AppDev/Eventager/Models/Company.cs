using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class Company
    {
        public int IDCompany { get; set; }
        public string Name { get; set; }
        public string OIB { get; set; }
        public string Email { get; set; }
        public string Address { get; set; }
        public int UserID { get; set; }
    }
}