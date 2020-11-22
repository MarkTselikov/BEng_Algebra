using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Eventager.Models
{
    public class LecturePayment
    {
        public int UserID { get; set; }
        public string CreditCard { get; set; }
        public int LectureID { get; set; }
        public int NumOfTickets { get; set; }
    }
}