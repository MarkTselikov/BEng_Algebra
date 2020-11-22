using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Project.Models
{
    public class Product
    {
        public int IDProduct { get; set; }
        public string Name { get; set; }
        public string Number { get; set; }
        public string Color { get; set; }
        public short MinAmount { get; set; }
        public decimal Price { get; set; }
        public int SubcategoryID { get; set; }
    }
}