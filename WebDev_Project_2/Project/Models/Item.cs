using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Project.Models
{
    public class Item
    {
        public int IDItem { get; set; }
        public int BillID { get; set; }
        public short Amount { get; set; }
        public int ProductID { get; set; }
        public decimal Price { get; set; }
        public decimal Discount { get; set; }
        public decimal TotalPrice { get; set; }
    }
}