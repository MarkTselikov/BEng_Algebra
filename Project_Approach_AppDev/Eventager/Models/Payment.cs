using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class Payment
    {
        public int IDPayment { get; set; }
        public string CreditCard { get; set; }
        public DateTime DatePayed { get; set; }
        public User User { get; set; }
        public double Sum { get; set; }
    }
}