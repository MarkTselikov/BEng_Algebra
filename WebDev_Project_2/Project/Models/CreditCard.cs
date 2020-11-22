using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Project.Models
{
    public class CreditCard
    {
        public int IDCreditCard { get; set; }
        public string Type { get; set; }
        public string Number { get; set; }
        public int ExpireMonth { get; set; }
        public int ExpireYear { get; set; }
    }
}