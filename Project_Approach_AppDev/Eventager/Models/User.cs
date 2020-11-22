using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class User
    {
        public int IDUser { get; set; }
        public string FName { get; set; }
        public string LName { get; set; }
        public string Email { get; set; }
        // OIB ?
        public string Username { get; set; }
        public string Password { get; set; }
        public int RoleID { get; set; }
        public int SubscriptionID { get; set; }
    }
}